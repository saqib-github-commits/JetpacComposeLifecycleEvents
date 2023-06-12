package com.jetpackcompose.jetpackcomposelifecycleevents.repository


class NewsRepositoryImpl : NewsRepository {

    override suspend fun fetchNews() {
        println("repository::fetchNews")
    }
}