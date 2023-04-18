package com.yeonkims.booksearch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeonkims.booksearch.database.KeywordDao
import com.yeonkims.booksearch.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeywordListViewModel(private val keywordDao: KeywordDao) : ViewModel() {
    private val _keywords = MutableLiveData<List<String>>()
    val keywords: LiveData<List<String>>
        get() = _keywords

    init {
        loadKeywords()
    }

    private fun loadKeywords() {
        viewModelScope.launch(Dispatchers.IO) {
            _keywords.postValue(keywordDao.getKeywords())
        }
    }
}