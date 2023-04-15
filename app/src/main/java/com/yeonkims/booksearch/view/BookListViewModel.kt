package com.yeonkims.booksearch.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.network.BooksApi
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BookListViewModel : ViewModel() {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>>
        get() = _bookList

    val searchQuery : MutableLiveData<String> = MutableLiveData("")

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            Log.i(javaClass.simpleName, "${searchQuery.value}")
            try {
                val books = BooksApi.retrofitService.getBooks(query = searchQuery.value ?: "")
                _bookList.postValue(books)
                Log.i(javaClass.simpleName, "${books.size}")

            } catch (e: HttpException) {
                Log.i(javaClass.simpleName, e.stackTrace.contentToString())
                Log.i(javaClass.simpleName, "${e.cause}")
                Log.i(javaClass.simpleName, e.javaClass.simpleName)
                Log.i(javaClass.simpleName, "${e.message}")
            }
        }
    }
}