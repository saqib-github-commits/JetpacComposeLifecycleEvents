package com.jetpackcompose.jetpackcomposelifecycleevents

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.jetpackcomposelifecycleevents.repository.NewsRepository
import com.jetpackcompose.jetpackcomposelifecycleevents.repository.NewsRepositoryImpl
import kotlinx.coroutines.launch

class NewsViewModelLifeCycleObserver(
    private val newsRepository: NewsRepository = NewsRepositoryImpl(),
): ViewModel(), DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        viewModelScope.launch {
            newsRepository.fetchNews()
        }
    }
}