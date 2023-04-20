package com.soutaka.fithub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val userId: Int = 1,
    var name: String,
    var age: Int,
    var height: Double,
    var goalWeight: Double,
)
