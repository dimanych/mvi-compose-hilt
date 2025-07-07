package com.example.myapplication.presentation.ui.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.common.UiStatus
import com.example.myapplication.presentation.common.collectAsEffect
import com.example.myapplication.presentation.ui.main.protocol.MainEffect
import com.example.myapplication.presentation.ui.main.protocol.MainEvent
import com.example.myapplication.presentation.ui.main.protocol.MainState
import com.example.myapplication.presentation.ui.theme.AppTheme
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_CONTAINER
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_FIRST_REQUEST
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_LOADER
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_SECOND_REQUEST
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_TEXT_1
import com.example.myapplication.util.TestId.MainScreenTestId.MAIN_SCREEN_TEXT_2

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val state = viewModel.viewState.collectAsState().value

    val context = LocalContext.current

    viewModel.effectFlow.collectAsEffect { effect ->
        when (effect) {
            is MainEffect.SuccessEffect -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    MainScreen(
        state = state,
        buttonClickFirst = {
            viewModel.onEvent(MainEvent.GetFirstDataEvent)
        },
        buttonClickSecond = {
            viewModel.onEvent(MainEvent.GetSecondDataEvent(0))
        },
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun MainScreen(
    state: MainState,
    modifier: Modifier = Modifier,
    buttonClickFirst: () -> Unit = {},
    buttonClickSecond: () -> Unit = {},
) {
    AppTheme {
        Surface(
            modifier = modifier,
            color = Color.White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(MAIN_SCREEN_CONTAINER)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 20.dp)
                ) {
                    val text1 = state.text

                    val text2 = state.count.toString()

                    Text(
                        modifier = Modifier
                            .testTag(MAIN_SCREEN_TEXT_1),
                        text = text1
                    )
                    Text(
                        modifier = Modifier
                            .testTag(MAIN_SCREEN_TEXT_2),
                        text = text2
                    )

                    TextButton(
                        modifier = Modifier
                            .testTag(MAIN_SCREEN_FIRST_REQUEST),
                        onClick = buttonClickFirst
                    ) {
                        Text(
                            text = "First Request"
                        )
                    }

                    TextButton(
                        modifier = Modifier
                            .testTag(MAIN_SCREEN_SECOND_REQUEST),
                        onClick = buttonClickSecond
                    ) {
                        Text(
                            text = "Second Request"
                        )
                    }
                }
                ProgressBar(
                    modifier = Modifier.align(Alignment.Center),
                    state = state
                )
            }
        }
    }
}

@Composable
fun ProgressBar(
    modifier: Modifier,
    state: MainState
) {
    if (state.status is UiStatus.Loading) {
        ShowProgressBar(modifier)
    }
}

@Composable
fun ShowProgressBar(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .width(64.dp)
            .testTag(MAIN_SCREEN_LOADER),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}