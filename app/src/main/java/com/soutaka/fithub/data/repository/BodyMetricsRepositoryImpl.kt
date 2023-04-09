package com.soutaka.fithub.data.repository

import com.soutaka.fithub.data.local.BodyMetricsDao
import com.soutaka.fithub.data.mapper.toBodyMetrics
import com.soutaka.fithub.domain.model.BodyMetrics
import com.soutaka.fithub.domain.repository.BodyMetricsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BodyMetricsRepositoryImpl(
  val bodyMetricsDao: BodyMetricsDao
) : BodyMetricsRepository{
    override fun getBodyMetrics(): Flow<List<BodyMetrics>>{
        return bodyMetricsDao.loadAllBodyMetrics().map { bodyMetricsEntities ->
            bodyMetricsEntities.map { it.toBodyMetrics() }
        }
    }

    override fun updateBodyMetrics(bodyMetrics: BodyMetrics) {
        TODO("Not yet implemented")
    }

    override fun addBodyMetrics(bodyMetrics: BodyMetrics) {
        TODO("Not yet implemented")
    }

    override fun deleteBodyMetrics(bodyMetrics: BodyMetrics) {
        TODO("Not yet implemented")
    }
}
