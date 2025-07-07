package com.example.myapplication.data.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getFirstData() : Result<String>
    suspend fun getSecondData(param: String) : Flow<Int>
}