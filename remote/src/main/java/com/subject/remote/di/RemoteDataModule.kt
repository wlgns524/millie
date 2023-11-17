package com.subject.remote.di

import com.subject.data.source.remote.HeadLineRemoteDataSource
import com.subject.remote.source.HeadLineRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataModule {
    @Singleton
    @Binds
    fun bindRemoteDataSource(
        remoteDataSource: HeadLineRemoteDataSourceImpl,
    ): HeadLineRemoteDataSource
}