package com.example.myapplication.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.presentation.common.UiEffect
import com.example.myapplication.presentation.common.UiEvent
import com.example.myapplication.presentation.common.UiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<EVENT : UiEvent, STATE : UiState, EFFECT : UiEffect> : ViewModel() {
    protected abstract val initialState: STATE

    private val _viewState: MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val viewState: StateFlow<STATE> by lazy { _viewState.asStateFlow() }

    protected fun setState(reducer: STATE.() -> STATE) {
        _viewState.value = reducer(_viewState.value)
    }

    fun onEvent(event: EVENT) = viewModelScope.launch {
        handleEvent(event)
    }

    protected abstract suspend fun handleEvent(event: EVENT)

    private val _effectChannel =
        Channel<EFFECT>(capacity = Channel.BUFFERED, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val effectFlow = _effectChannel.receiveAsFlow()

    protected fun setEffect(builder: () -> EFFECT) {
        val effect = builder()
        viewModelScope.launch {
            _effectChannel.send(effect)
        }
    }
}