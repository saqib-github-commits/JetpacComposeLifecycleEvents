package com.jetpackcompose.jetpackcomposelifecycleevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.jetpackcomposelifecycleevents.repository.NewsRepository
import com.jetpackcompose.jetpackcomposelifecycleevents.repository.NewsRepositoryImpl
import kotlinx.coroutines.launch

class NewsViewModel (
    private val newsRepository: NewsRepository = NewsRepositoryImpl()
) : ViewModel() {

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            newsRepository.fetchNews()
        }
    }

}