package com.soutaka.fithub.data.repository

import com.soutaka.fithub.data.local.dao.BodyMetricsDao
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

    override suspend fun updateBodyMetrics(bodyMetrics: BodyMetrics) {
        return bodyMetricsDao.updateBodyMetrics(bodyMetrics.toBodyMetrics())
    }

    override suspend fun addBodyMetrics(bodyMetrics: BodyMetrics) {
        return bodyMetricsDao.insertBodyMetrics(bodyMetrics.toBodyMetrics())
    }

    override suspend fun deleteBodyMetrics(bodyMetrics: BodyMetrics) {
        return bodyMetricsDao.deleteBodyMetrics(bodyMetrics.toBodyMetrics())
    }
}
