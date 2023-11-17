package com.subject.data

import com.subject.data.model.HeadLineData
import com.subject.data.model.toData
import com.subject.data.model.toDomain
import com.subject.data.source.local.HeadLineLocalDataSource
import com.subject.data.source.remote.HeadLineRemoteDataSource
import com.subject.domain.model.HeadLine
import com.subject.domain.model.HeadLineResponse
import com.subject.domain.repository.HeadLineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class HeadLineRepositoryImpl @Inject constructor(
    val local: HeadLineLocalDataSource,
    val remote: HeadLineRemoteDataSource,
) : HeadLineRepository {

    override suspend fun getHeadLineList(): Flow<List<HeadLine>> =
        local.getHeadLineList().map { it.map(HeadLineData::toDomain) }

    override suspend fun loadRemoteHeadLine(page: Int, pageSize: Int): HeadLineResponse {
        val headLineResponse = remote.getHeadLine(page, pageSize).toDomain()
//        if (page == 1) local.clearHeadLine()
        local.setHeadLineList(headLineResponse.articles.map(HeadLine::toData))
        return headLineResponse
    }

    override suspend fun updateImageResource(headLine: HeadLine) {
        local.updateImageResource(headLine.toData())
    }

    override suspend fun updateIsViewed(headLine: HeadLine) {
        local.updateIsViewed(headLine.toData())
    }
}