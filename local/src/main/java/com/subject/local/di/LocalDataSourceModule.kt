package com.subject.local.di

import com.subject.data.source.local.HeadLineLocalDataSource
import com.subject.local.source.HeadLineLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalDataSourceModule {

    @Singleton
    @Binds
    fun bindHeadLineLocalDataSource(
        impl: HeadLineLocalDataSourceImpl,
    ): HeadLineLocalDataSource
}
