package com.yeonkims.booksearch.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KeywordDao {
    @Query("SELECT search_word FROM keyword ORDER BY created_at DESC")
    fun getKeywords(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(keyword: Keyword)

    @Query("DELETE FROM keyword WHERE id NOT IN (SELECT id FROM keyword ORDER BY created_at DESC LIMIT 10)")
    fun deleteOldKeywords()
}
