package com.soutaka.fithub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soutaka.fithub.data.local.entity.BodyMetricsEntity

@Database(entities = [BodyMetricsEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class FithubDatabase : RoomDatabase() {
    abstract fun bodyMetricsDao(): BodyMetricsDao
}
