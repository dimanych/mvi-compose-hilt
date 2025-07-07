package com.example.myapplication.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Any> Flow<T>.collectAsEffect(
    lifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(this@collectAsEffect) {
        lifecycleOwner.repeatOnLifecycle(lifeCycleState) {
            this@collectAsEffect.collect(collector)
        }
    }
}