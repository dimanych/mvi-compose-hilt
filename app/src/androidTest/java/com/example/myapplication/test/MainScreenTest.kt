package com.example.myapplication.test

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.MainActivity
import com.example.myapplication.presentation.common.UiStatus
import com.example.myapplication.presentation.ui.main.MainScreen
import com.example.myapplication.presentation.ui.main.protocol.MainState
import com.example.myapplication.screen.MainScreenProvider
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder
        .withComposeSupport()
        .apply {
            testLogger = UiTestLoggerImpl("MainScreenTestLogger")
        }
) {

    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLoader() {
        run {
            val state = MainState(
                status = UiStatus.Loading,
                text = "Loading...",
                count = 0
            )
            setState(state)
            step("Check loading state") {
                onComposeScreen<MainScreenProvider>(testRule) {
                    text1 {
                        assertIsDisplayed()
                        assertTextContains("Loading...")
                    }
                }
            }
            step("Check initial count") {
                onComposeScreen<MainScreenProvider>(testRule) {
                    text2 {
                        assertIsDisplayed()
                        assertTextContains("0")
                    }
                }
            }
            step("Check loader visibility") {
                onComposeScreen<MainScreenProvider>(testRule) {
                    loader {
                        assertIsDisplayed()
                    }
                }
            }
        }
    }

    @Test
    fun testData() {
        run {
            val state = MainState(
                status = UiStatus.Success,
                text = "Data Loaded",
                count = 42
            )
            setState(state)
            step("Check success state") {
                onComposeScreen<MainScreenProvider>(testRule) {
                    text1 {
                        assertIsDisplayed()
                        assertTextContains("Data Loaded")
                    }
                    text2 {
                        assertIsDisplayed()
                        assertTextContains("42")
                    }
                }
            }
        }
    }

    @Test
    fun testButtons() {
        run {
            val state = MainState(
                status = UiStatus.Idle,
                text = "Click a button",
                count = 0
            )
            setState(state)
            step("Check initial state") {
                onComposeScreen<MainScreenProvider>(testRule) {
                    text1 {
                        assertIsDisplayed()
                        assertTextContains("Click a button")
                    }
                    text2 {
                        assertIsDisplayed()
                        assertTextContains("0")
                    }
                }
            }
            step("Check button click") {
                onComposeScreen<MainScreenProvider>(testRule) {
                    firstButton {
                        assertIsDisplayed()
                        assertTextContains("First Request")
                        performClick()
                    }
                }
                onComposeScreen<MainScreenProvider>(testRule) {
                    secondButton {
                        assertIsDisplayed()
                        assertTextContains("Second Request")
                        performClick()
                    }
                }
            }
        }
    }

    private fun setState(state: MainState) {
        testRule.activity.setContent {
            MainScreen(state = state)
        }
    }
}