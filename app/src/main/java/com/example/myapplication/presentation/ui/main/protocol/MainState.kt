package com.example.myapplication.presentation.ui.main.protocol

import com.example.myapplication.presentation.common.UiState
import com.example.myapplication.presentation.common.UiStatus

data class MainState(
    val status: UiStatus,
    val text: String,
    val count: Int
) : UiState
