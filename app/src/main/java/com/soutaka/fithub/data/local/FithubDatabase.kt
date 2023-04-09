package com.soutaka.fithub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BodyMetricsDao::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class FithubDatabase : RoomDatabase() {
    abstract fun bodyMetricsDao(): BodyMetricsDao
}
