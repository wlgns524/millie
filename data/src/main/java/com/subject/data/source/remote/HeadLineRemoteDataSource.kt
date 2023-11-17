package com.subject.data.source.remote

import com.subject.data.model.HeadLineResponseData

interface HeadLineRemoteDataSource {
    suspend fun getHeadLine(page: Int, pageSize: Int): HeadLineResponseData
}