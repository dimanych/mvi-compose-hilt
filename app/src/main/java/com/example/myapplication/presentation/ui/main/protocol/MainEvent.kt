package com.example.myapplication.presentation.ui.main.protocol

import com.example.myapplication.presentation.common.UiEvent

sealed class MainEvent : UiEvent {
    data object GetFirstDataEvent : MainEvent()
    data class GetSecondDataEvent(val id: Int) : MainEvent()
}