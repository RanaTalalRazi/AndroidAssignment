package com.synavos.androidassignment.di

import com.synavos.androidassignment.data.repository.AgeRepositoryImpl
import com.synavos.androidassignment.domain.repository.AgeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAgeRepository(ageRepositoryImpl: AgeRepositoryImpl): AgeRepository


}