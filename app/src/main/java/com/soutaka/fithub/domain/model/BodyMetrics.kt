package com.soutaka.fithub.domain.model

import java.time.LocalDate
import kotlin.math.pow

data class BodyMetrics(
    val id: Int,
    var height: Double,
    var weight: Double,
    val createdAt: LocalDate
)

val BodyMetrics.bmi: Double
    get() = weight / (height / 100).pow(2)
