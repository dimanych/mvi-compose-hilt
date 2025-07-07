package com.example.myapplication.data.repository

import android.content.Context
import com.example.myapplication.data.network.ApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository {

    override suspend fun getFirstData(): Result<String> = withContext(Dispatchers.IO) {
        delay(2000)
        Result.success("Success")
    }

    override suspend fun getSecondData(param: String): Flow<Int> = withContext(Dispatchers.IO) {
        var inc = 0
        flow {
            if (param.isNotBlank()) {
                while (true) {
                    delay(1000)
                    emit(inc++)
                }
            }
        }
    }
}