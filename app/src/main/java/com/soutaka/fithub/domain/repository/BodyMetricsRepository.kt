package com.soutaka.fithub.domain.repository

import com.soutaka.fithub.domain.model.BodyMetrics
import kotlinx.coroutines.flow.Flow

interface BodyMetricsRepository {
    fun getBodyMetrics(): Flow<List<BodyMetrics>>

    fun updateBodyMetrics(bodyMetrics: BodyMetrics)

    fun addBodyMetrics(bodyMetrics: BodyMetrics)

    fun deleteBodyMetrics(bodyMetrics: BodyMetrics)
}
