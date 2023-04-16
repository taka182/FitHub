package com.soutaka.fithub.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun dateToTimestamp(localDate: LocalDate?): Long? {
        return localDate?.toEpochDay()
    }
}
