package com.jetpackcompose.jetpackcomposelifecycleevents

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.jetpackcompose.jetpackcomposelifecycleevents.common.DisposableEffectWithLifecycle
import com.jetpackcompose.jetpackcomposelifecycleevents.common.rememberLifecycleEvent
import com.jetpackcompose.jetpackcomposelifecycleevents.extension.observeLifecycleEvents


@Composable
fun NewsScreenWithViewModelAsLifecycleObserver(
    viewModel: NewsViewModelLifeCycleObserver = NewsViewModelLifeCycleObserver()
) {
    viewModel.observeLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    // list of news
    LazyColumn {
        // list of news
    }
}


// Example 3
@Composable
fun NewsScreenWithDisposableEffectLifecycle(viewModel: NewsViewModel = NewsViewModel()) {
    DisposableEffectWithLifecycle(
        onResume = { viewModel.fetchNews() }
    )

    // list of news
    LazyColumn {
        // list of news
    }
}

// Example 2
@Composable
fun NewsScreenWithRememberLifecycle(viewModel: NewsViewModel = NewsViewModel()) {
    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchNews()
        }
    }

    // list of news
    LazyColumn {
        // list of news
    }
}

// Example 1
@Composable
fun NewsScreenBasicExample(
    viewModel: NewsViewModel = NewsViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {

    var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.fetchNews()
        }
    }

    // will use to display news
    LazyColumn {
        // list of news
    }
}

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = NewsViewModel()
) {
    // will use to display news
    LazyColumn {
        // list of news
    }

}