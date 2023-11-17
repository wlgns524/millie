package com.subject.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.subject.data.model.exception.ClientException
import com.subject.data.model.exception.DataException
import com.subject.data.model.exception.RedirectionException
import com.subject.data.model.exception.ServerException
import com.subject.remote.api.HeadLineApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        provideInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideInterceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            with(chain) {
                val response = proceed(request().newBuilder().build())
                when (response.code) {
                    in 300..399 -> throw RedirectionException(response.code, "리다이렉션 에러가 발생했습니다")
                    in 400..499 -> throw ClientException(response.code, "클라이언트 에러가 발생했습니다")
                    in 500..599 -> throw ServerException(response.code, "서버 에러가 발생했습니다")
                    !in 200..299 -> throw DataException(response.code, "알 수 없는 에러가 발생했습니다")
                }

                return@Interceptor response
            }
        }
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHeadLineApi(
        retrofit: Retrofit,
    ): HeadLineApi = retrofit.create(HeadLineApi::class.java)

    companion object {
        private const val BASE_URL = "https://newsapi.org/"
    }
}