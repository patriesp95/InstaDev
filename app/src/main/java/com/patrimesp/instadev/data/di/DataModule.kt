package com.patrimesp.instadev.data.di

import com.patrimesp.instadev.data.repository.AuthRepositoryImpl
import com.patrimesp.instadev.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

}