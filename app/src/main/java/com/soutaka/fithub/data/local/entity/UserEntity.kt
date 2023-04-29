package com.soutaka.fithub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class UserEntity(
    @PrimaryKey val userId: Int,
    val name: String,
    val birthDay: LocalDate,
    val userHeight: Double,
    val isMan: Boolean,
    val goalWeight: Double,
)
