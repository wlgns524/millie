package com.subject.domain.repository

import com.subject.domain.model.HeadLine
import com.subject.domain.model.HeadLineResponse
import kotlinx.coroutines.flow.Flow

interface HeadLineRepository {
    suspend fun getHeadLineList(): Flow<List<HeadLine>>
    suspend fun loadRemoteHeadLine(page: Int, pageSize: Int): HeadLineResponse
    suspend fun updateImageResource(headLine: HeadLine)
    suspend fun updateIsViewed(headLine: HeadLine)
}
