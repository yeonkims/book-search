package com.yeonkims.booksearch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.model.fakeBookData

class BookListViewModel {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>>
        get() = _bookList


    init {
        loadData()
    }

    fun loadData() {
        fakeBookData.forEach { book ->
            val loadedBookList = _bookList.value?.toMutableList() ?: mutableListOf()
            loadedBookList.add(book)
            _bookList.postValue(loadedBookList.toList())
        }
    }
}