package com.yeonkims.booksearch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Keyword(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "search_word") val searchWord: String,
    @ColumnInfo(name = "created_at") val createdAt: Date
) {
    companion object {
        fun new(searchWord: String): Keyword {
            return Keyword(
                searchWord = searchWord,
                createdAt = Date()
            )
        }
    }
}