package com.example.android_kotlin_demo.di

import com.example.android_kotlin_demo.domain.UserDataSource
import com.example.android_kotlin_demo.usecase.RegisterUserUseCase
import com.example.android_kotlin_demo.usecase.UserDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideRegisterUserUseCase(dataSource: UserDataSource) = RegisterUserUseCase(dataSource)


    @Singleton
    @Provides
    fun provideUserDetailsUseCase(dataSource: UserDataSource)= UserDetailsUseCase(dataSource)
}