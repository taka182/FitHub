package com.soutaka.fithub.data.local.dao

import androidx.room.*
import com.soutaka.fithub.data.local.entity.BodyMetricsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BodyMetricsDao {
    @Insert
    suspend fun insertBodyMetrics(bodyMetricsEntity: BodyMetricsEntity)

    @Query("SELECT * FROM BodyMetricsEntity ORDER BY id")
    fun loadAllBodyMetrics(): Flow<List<BodyMetricsEntity>>

    @Update
    suspend fun updateBodyMetrics(bodyMetricsEntity: BodyMetricsEntity)

    @Delete
    suspend fun deleteBodyMetrics(bodyMetricsEntity: BodyMetricsEntity)
}
