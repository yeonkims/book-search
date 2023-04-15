package com.yeonkims.booksearch.adapter

import com.squareup.moshi.FromJson
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.network.BooksJson

class BooksJsonAdapter {
    @FromJson
    fun booksFromJson(json: BooksJson) : List<Book> {
        val jsonList = json.items

        return jsonList.map { bookJson ->
            Book(
                title = bookJson.title,
                author = bookJson.author,
                publisher = bookJson.publisher,
                price = bookJson.discount,
                coverUrl = bookJson.image
            )
        }
    }
}