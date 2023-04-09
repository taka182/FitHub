package com.soutaka.fithub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class BodyMetricsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var height: Double,
    var weight: Double,
    var createdAt: LocalDate
)
