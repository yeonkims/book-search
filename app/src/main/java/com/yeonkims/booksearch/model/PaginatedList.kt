package com.yeonkims.booksearch.model

data class PaginatedList<T>(
    val total: Int,
    val items: List<T>
)