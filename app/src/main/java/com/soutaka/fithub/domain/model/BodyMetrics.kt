package com.soutaka.fithub.domain.model

import java.time.LocalDate
import kotlin.math.pow
import kotlin.math.roundToInt

data class BodyMetrics(
    val id: Int,
    val height: Double,
    val weight: Double,
    val createdAt: LocalDate
)

val BodyMetrics.bmi: Double
    get() = ((weight / (height / 100).pow(2)) * 100.0).roundToInt() / 100.0
