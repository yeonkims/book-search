package com.yeonkims.booksearch.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yeonkims.booksearch.adapter.BooksJsonAdapter
import com.yeonkims.booksearch.model.Book
import com.yeonkims.booksearch.model.PaginatedList
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://openapi.naver.com/v1/"
private const val CLIENT_ID = "ILL0azNCUUahF8PnhpTc"
private const val CLIENT_SECRET = "_si9CcOPZd"

private val moshi = Moshi.Builder()
    .add(BooksJsonAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {
    @Headers("X-Naver-Client-Id: $CLIENT_ID", "X-Naver-Client-Secret: $CLIENT_SECRET")
    @GET("search/book.json")
    suspend fun getBooks(
        @Query("query") query: String,
        @Query("display") pageSize: Int = 20,
        @Query("start") pageStart: Int = 1
    ): PaginatedList<Book>
}

object BooksApi {
    val retrofitService: BooksApiService by lazy { retrofit.create(BooksApiService::class.java) }
}