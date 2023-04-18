package com.yeonkims.booksearch.adapter

import com.squareup.moshi.FromJson
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.model.PaginatedList
import com.yeonkims.booksearch.network.BooksJson

class BooksJsonAdapter {
    @FromJson
    fun booksFromJson(json: BooksJson) : PaginatedList<Book> {
        val jsonList = json.items

        val books = jsonList.map { bookJson ->
            Book(
                title = bookJson.title,
                author = bookJson.author ?: "",
                publisher = bookJson.publisher,
                price = bookJson.discount,
                coverUrl = bookJson.image,
                link = bookJson.link
            )
        }

        return PaginatedList(
            total = json.total,
            items = books,
        )
    }
}