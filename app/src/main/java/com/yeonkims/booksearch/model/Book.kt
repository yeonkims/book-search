package com.yeonkims.booksearch.model

data class Book (
    val title: String,
    val author: String,
    val publisher: String,
    val price: String,
    val coverUrl: String
    )

val fakeBook1 = Book(
    title = "아주 작은 습관의 힘아주 작은 습관의 힘",
    author = "제임스 클리어",
    publisher = "비즈니스북스",
    price = "14400",
    coverUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAzMjJfOTkg%2FMDAxNjc5NDkwMzEzNDk4.yW11DGo6TSSQTMKCm22VN0pWPcvswoDfuqbJCcy2h90g.IjuXvPs8vPYyZhH8xWjSCl1n6EQSmn4QKctNIjfzLbYg.PNG.koryeo113%2Fimage.png&type=sc960_832"
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