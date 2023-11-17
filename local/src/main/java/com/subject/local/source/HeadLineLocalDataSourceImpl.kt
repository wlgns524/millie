package com.subject.local.source

import com.subject.data.model.HeadLineData
import com.subject.data.source.local.HeadLineLocalDataSource
import com.subject.local.model.HeadLineEntity
import com.subject.local.model.toData
import com.subject.local.model.toEntity
import com.subject.local.room.HeadLineDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadLineLocalDataSourceImpl @Inject constructor(
    private val headLineDao: HeadLineDao,
) : HeadLineLocalDataSource {
    override suspend fun setHeadLineList(list: List<HeadLineData>) {
        headLineDao.setHeadLineList(list.map(HeadLineData::toEntity))
    }

    override suspend fun getHeadLineList(): Flow<List<HeadLineData>> =
        headLineDao.getHeadLineList().map { it.sortedBy { it.sort }.map(HeadLineEntity::toData) }

    override suspend fun updateImageResource(headLine: HeadLineData) {
        headLineDao.updateImageResource(headLine.id, headLine.imageResource)
    }
    override suspend fun updateIsViewed(headLine: HeadLineData) {
        headLineDao.updateIsViewed(headLine.id, headLine.isViewed)
    }

    override suspend fun clearHeadLine() {
        headLineDao.clearHeadLine()
    }
}