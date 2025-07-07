package com.example.myapplication.domain

import com.example.myapplication.data.repository.MainRepository
import javax.inject.Inject

class LoadSecondData @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke(param: String) = repository.getSecondData(param)

}