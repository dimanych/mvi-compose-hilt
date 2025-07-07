package com.example.myapplication.presentation.ui.main

import com.example.myapplication.presentation.base.BaseViewModel
import com.example.myapplication.presentation.common.UiStatus
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.domain.LoadInitData
import com.example.myapplication.domain.LoadSecondData
import com.example.myapplication.presentation.ui.main.protocol.MainEffect
import com.example.myapplication.presentation.ui.main.protocol.MainEvent
import com.example.myapplication.presentation.ui.main.protocol.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadInitData: LoadInitData,
    private val loadSecondData: LoadSecondData,
) : BaseViewModel<MainEvent, MainState, MainEffect>() {

    override val initialState: MainState
        get() = MainState(status = UiStatus.Idle, text = "", count = 0)

    override suspend fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetFirstDataEvent -> {
                setState {
                    viewState.value.copy(status = UiStatus.Loading, text = "Loading...")
                }
                val result = loadInitData()
                if (result.isSuccess) {
                    setState {
                        viewState.value.copy(status = UiStatus.Success, text = "Success!")
                    }
                    setEffect {
                        MainEffect.SuccessEffect("Success")
                    }
                } else {
                    setState {
                        viewState.value.copy(status = UiStatus.Failed("Error"), text = "Fail!")
                    }
                }
            }

            is MainEvent.GetSecondDataEvent -> {
                loadSecondData(event.id.toString())
                    .collect {
                        setState {
                            viewState.value.copy(count = it)
                        }
                    }
            }
        }
    }
}