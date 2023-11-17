package com.subject.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.subject.local.model.HeadLineEntity

@Database(
    entities = [HeadLineEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class HeadLineDatabase : RoomDatabase() {

    abstract val headLineDao: HeadLineDao
    companion object {
        private const val DATABASE_NAME = "com.subject.local.db"
        fun create(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HeadLineDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
    }
}
