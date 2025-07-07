package com.example.myapplication.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xff4c8c4a),
    secondary = Color(0xff212121),
    onPrimary = Color(0xffffffff)
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xff4c8c4a),
    secondary = Color(0xff212121),
    onPrimary = Color(0xffffffff)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}