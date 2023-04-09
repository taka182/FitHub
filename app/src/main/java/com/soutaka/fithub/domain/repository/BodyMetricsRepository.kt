package com.soutaka.fithub.domain.repository

import com.soutaka.fithub.domain.model.BodyMetrics

interface BodyMetricsRepository {
    fun getBodyMetrics(id: Int): BodyMetrics?

    fun updateBodyMetrics(bodyMetrics: BodyMetrics)

    fun addBodyMatrics(bodyMetrics: BodyMetrics)

    fun deleteBodyMatrics(bodyMetrics: BodyMetrics)
}