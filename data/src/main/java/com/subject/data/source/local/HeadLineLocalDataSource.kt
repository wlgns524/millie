package com.subject.data.source.local

import com.subject.data.model.HeadLineData
import kotlinx.coroutines.flow.Flow

interface HeadLineLocalDataSource {
    suspend fun setHeadLineList(list: List<HeadLineData>)
    suspend fun getHeadLineList(): Flow<List<HeadLineData>>
    suspend fun updateImageResource(headLine: HeadLineData)
    suspend fun updateIsViewed(headLine: HeadLineData)
    suspend fun clearHeadLine()
}