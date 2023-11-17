package com.subject.remote.api

import com.subject.remote.model.HeadLineResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HeadLineApi {
    @GET("v2/top-headlines")
    suspend fun getTopHeadLine(
        @Query("country") country: String = "kr",
        @Query("apiKey") apiKey: String = "001dee4704a74149a1d1f0b1e6f8a4c0",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): HeadLineResponseDto
}