package com.subject.data.di

import com.subject.data.HeadLineRepositoryImpl
import com.subject.domain.repository.HeadLineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Singleton
    @Binds
    fun bindHeadLineRepository(repository: HeadLineRepositoryImpl): HeadLineRepository
}