package com.example.myapplication.presentation.ui.main.protocol

import com.example.myapplication.presentation.common.UiEffect

sealed class MainEffect : UiEffect {
    data class SuccessEffect(val message: String) : MainEffect()
}