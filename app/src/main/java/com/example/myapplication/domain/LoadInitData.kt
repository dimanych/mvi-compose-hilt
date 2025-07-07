package com.example.myapplication.domain

import com.example.myapplication.data.repository.MainRepository
import javax.inject.Inject

class LoadInitData @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke() = repository.getFirstData()
}