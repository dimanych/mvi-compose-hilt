package com.example.myapplication.screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_CONTAINER
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_FIRST_REQUEST
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_LOADER
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_SECOND_REQUEST
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_TEXT_1
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_TEXT_2
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class MainScreenProvider(
    semanticProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<MainScreenProvider>(
    semanticsProvider = semanticProvider,
    viewBuilderAction = { hasTestTag(MAIN_SCREEN_CONTAINER) }
) {
    val text1: KNode = child {
        hasTestTag(MAIN_SCREEN_TEXT_1)
    }

    val text2: KNode = child {
        hasTestTag(MAIN_SCREEN_TEXT_2)
    }

    val firstButton: KNode = child {
        hasTestTag(MAIN_SCREEN_FIRST_REQUEST)
    }

    val secondButton: KNode = child {
        hasTestTag(MAIN_SCREEN_SECOND_REQUEST)
    }

    val loader: KNode = child {
        hasTestTag(MAIN_SCREEN_LOADER)
    }
}