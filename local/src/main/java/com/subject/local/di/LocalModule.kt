package com.subject.local.di

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import com.subject.local.room.HeadLineDatabase
import com.subject.local.room.HeadLineDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class LocalModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideContentResolver(
        context: Context,
    ): ContentResolver {
        return context.contentResolver
    }

    @Singleton
    @Provides
    fun provideDatabase(
        context: Context,
    ): HeadLineDatabase = HeadLineDatabase.create(context)

    @Singleton
    @Provides
    fun provideHeadLineDao(
        database: HeadLineDatabase,
    ): HeadLineDao = database.headLineDao
}
