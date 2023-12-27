package com.jetpackcompose.jetpackcomposelifecycleevents

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.jetpackcompose.jetpackcomposelifecycleevents.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NewsViewModelLifeCycleObserverTest {

    @Mock
    private lateinit var newsRepository: NewsRepository

    private val lifecycleRegistry = LifecycleRegistry.createUnsafe(mock<LifecycleOwner>())

    private val viewModel by lazy { NewsViewModelLifeCycleObserver(newsRepository) }

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @Before
    fun setup() {
        lifecycleRegistry.addObserver(viewModel)
    }

    @After
    fun tearDown() {
        lifecycleRegistry.removeObserver(viewModel)
    }

    @Test
    fun `track called on resume`() = runTest {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED

        verify(newsRepository).fetchNews()
    }
}