package com.soutaka.fithub.domain.repository

import com.soutaka.fithub.domain.model.BodyMetrics
import kotlinx.coroutines.flow.Flow

interface BodyMetricsRepository {
    fun getBodyMetrics(): Flow<List<BodyMetrics>>

    suspend fun updateBodyMetrics(bodyMetrics: BodyMetrics)

    suspend fun addBodyMetrics(bodyMetrics: BodyMetrics)

    suspend fun deleteBodyMetrics(bodyMetrics: BodyMetrics)
}
