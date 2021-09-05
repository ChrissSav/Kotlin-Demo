package com.example.android_kotlin_demo.di

import com.example.android_kotlin_demo.data.UserDataSourceImpl
import com.example.android_kotlin_demo.data.UserRepository
import com.example.android_kotlin_demo.domain.UserDataSource
import com.example.android_kotlin_demo.framework.UserRepositoryImpl
import com.example.android_kotlin_demo.framework.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object StorageModule {

    @Singleton
    @Provides
    fun provideRepository(service: UserService): UserRepository = UserRepositoryImpl(service)


    @Singleton
    @Provides
    fun provideDataSource(repository: UserRepository): UserDataSource = UserDataSourceImpl(repository)
}