package com.soutaka.fithub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.soutaka.fithub.data.local.converter.DateTimeConverter
import com.soutaka.fithub.data.local.dao.BodyMetricsDao
import com.soutaka.fithub.data.local.dao.UserDao
import com.soutaka.fithub.data.local.entity.BodyMetricsEntity
import com.soutaka.fithub.data.local.entity.UserEntity

@Database(entities = [BodyMetricsEntity::class, UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class FithubDatabase : RoomDatabase() {
    abstract fun bodyMetricsDao(): BodyMetricsDao

    abstract fun userDao(): UserDao
}
