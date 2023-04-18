package com.yeonkims.booksearch.network

class BooksJson(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<BookJson>
)

class BookJson(
    val title: String,
    val link: String,
    val image: String,
    val author: String?,
    val discount: String,
    val publisher: String,
    val pubdate: String,
    val isbn: String,
    val description: String
)