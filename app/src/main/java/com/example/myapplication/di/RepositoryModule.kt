package com.example.myapplication.di

import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun provideMainRepository(repository: MainRepositoryImpl): MainRepository
}