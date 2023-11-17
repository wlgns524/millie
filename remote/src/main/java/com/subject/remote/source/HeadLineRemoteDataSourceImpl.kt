package com.subject.remote.source

import com.subject.data.model.HeadLineResponseData
import com.subject.data.source.remote.HeadLineRemoteDataSource
import com.subject.remote.api.HeadLineApi
import com.subject.remote.model.toData
import javax.inject.Inject

internal class HeadLineRemoteDataSourceImpl @Inject constructor(
    private val api: HeadLineApi,
) : HeadLineRemoteDataSource {
    override suspend fun getHeadLine(page: Int, pageSize: Int): HeadLineResponseData {
        return api.getTopHeadLine(page = page, pageSize = pageSize).toData()
    }
}