package com.yeonkims.booksearch.view

import android.util.Log
import androidx.lifecycle.*
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.network.BooksApi
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>>
        get() = _bookList

    val searchQuery : MutableLiveData<String> = MutableLiveData("")
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoadingFirstPage : LiveData<Boolean> = Transformations.map(isLoading) {
        it && bookList.value.isNullOrEmpty()
    }

    var pageStart = 1
    private val pageSize = 20
    var searchedWord: String = ""
    var total: Int? = null
    var hasNextPage = false

    fun loadData() {
        val searchWord = searchQuery.value
        Log.i("javaClass.simpleName", "ATTEMPTING SEARCH WORD $searchWord")

        if(searchWord.isNullOrEmpty() || isLoading.value == true) return
        _bookList.value = emptyList()
        isLoading.value = true

        viewModelScope.launch {
            try {
                pageStart = 1
                val books = BooksApi.retrofitService.getBooks(
                    query = searchWord,
                    pageSize = pageSize,
                    pageStart = pageStart
                )
                pageStart += pageSize
                searchedWord = searchWord
                total = books.total
                hasNextPage = books.items.size == pageSize || total != books.items.size
                _bookList.postValue(books.items)
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
                    pageSize = pageSize,
                    pageStart = pageStart
                )

                pageStart += pageSize

                val combinedList = _bookList.value!!.toMutableList()
                combinedList.addAll(books.items)

                hasNextPage = books.items.size == pageSize || total != combinedList.size
                _bookList.postValue(combinedList)
            } catch (_: Throwable) {
            }
            isLoading.postValue(false)
        }
    }
}