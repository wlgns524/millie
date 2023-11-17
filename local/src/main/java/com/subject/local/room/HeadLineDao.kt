package com.subject.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.subject.local.model.HeadLineEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Dao
@Singleton
interface HeadLineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setHeadLineList(headLineEntities: List<HeadLineEntity>)

    @Query("SELECT * FROM headLine")
    fun getHeadLineList(): Flow<List<HeadLineEntity>>

    @Query("UPDATE headLine SET imageResource =:imageResource  WHERE id =:id ")
    fun updateImageResource(id: Int, imageResource: ByteArray?)

    @Query("UPDATE headLine SET isViewed =:isViewed  WHERE id =:id ")
    fun updateIsViewed(id: Int, isViewed: Boolean)

    @Query("DELETE FROM headLine")
    fun clearHeadLine()
}