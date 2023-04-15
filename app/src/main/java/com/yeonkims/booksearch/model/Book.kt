package com.yeonkims.booksearch.model

data class Book (
    val title: String,
    val author: String,
    val publisher: String,
    val price: String,
    val coverUrl: String
    )

val fakeBook1 = Book(
    title = "아주 작은 습관의 힘",
    author = "제임스 클리어",
    publisher = "비즈니스북스",
    price = "14400",
    coverUrl = ""
)

val fakeBook2 = Book(
    title = "투자에 대한 생각",
    author = "하워드 막스",
    publisher = "비즈니스맵",
    price = "13500",
    coverUrl = ""
)

val fakeBookData = listOf(
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
    fakeBook1,
    fakeBook2,
)