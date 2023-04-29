package com.soutaka.fithub.domain.model

import java.time.LocalDate

data class UserProfile(
    val userId: Int,
    val name: String,
    val birthDay: LocalDate,
    val userHeight: Double,
    val isMan: Boolean,
    val goalWeight: Double,
)
