package com.yeonkims.booksearch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeonkims.booksearch.database.Keyword
import com.yeonkims.booksearch.database.KeywordDao
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.network.BooksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookListViewModel(private val keywordDao: KeywordDao) : ViewModel() {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>>
        get() = _bookList

    val searchQuery : MutableLiveData<String> = MutableLiveData("")
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoadingFirstPage : LiveData<Boolean> = Transformations.map(isLoading) {
        it && bookList.value.isNullOrEmpty()
    }

    private var pageStart = 1
    private var searchedWord: String = ""
    private var total: Int? = null
    private var hasNextPage = false

    fun searchCurrentText() {
        val searchWord = searchQuery.value

        if(searchWord.isNullOrBlank()) return

        searchNewWord(searchWord)
    }

    fun searchNewWord(searchWord: String) {
        if(searchWord.isBlank() || isLoading.value == true) return
        _bookList.value = emptyList()
        searchQuery.value = searchWord
        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                pageStart = 1
                val books = BooksApi.retrofitService.getBooks(
                    query = searchWord,
                    pageSize = PAGE_SIZE,
                    pageStart = pageStart
                )
                pageStart += PAGE_SIZE
                searchedWord = searchWord
                total = books.total
                hasNextPage = (books.items.size == PAGE_SIZE || total != books.items.size) && pageStart <= PAGE_START_LIMIT
                _bookList.postValue(books.items)
                saveSearchWord(searchWord)
            } catch (_: Throwable) {
            }
            isLoading.postValue(false)
        }
    }


    fun loadNextPage() {
        if(!hasNextPage || isLoading.value == true) return
        isLoading.value = true

        viewModelScope.launch {
            try {
                val books = BooksApi.retrofitService.getBooks(
                    query = searchedWord,
                    pageSize = PAGE_SIZE,
                    pageStart = pageStart
                )

                pageStart += PAGE_SIZE

                val combinedList = _bookList.value!!.toMutableList()
                combinedList.addAll(books.items)

                hasNextPage = (books.items.size == PAGE_SIZE || total != books.items.size) && pageStart <= PAGE_START_LIMIT
                _bookList.postValue(combinedList)
            } catch (_: Throwable) {
            }
            isLoading.postValue(false)
        }
    }

    private fun saveSearchWord(searchWord: String) {
        keywordDao.insert(Keyword.new(searchWord =  searchWord))
        keywordDao.deleteOldKeywords()
    }

    companion object {
        private const val PAGE_START_LIMIT = 1000
        private const val PAGE_SIZE = 20
    }
}