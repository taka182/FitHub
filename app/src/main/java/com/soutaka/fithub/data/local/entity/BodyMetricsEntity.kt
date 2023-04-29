package com.soutaka.fithub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class BodyMetricsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val height: Double,
    val weight: Double,
    val createdAt: LocalDate
)
