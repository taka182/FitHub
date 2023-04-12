package com.soutaka.fithub.data.mapper

import com.soutaka.fithub.data.local.entity.BodyMetricsEntity
import com.soutaka.fithub.domain.model.BodyMetrics

fun BodyMetricsEntity.toBodyMetrics(): BodyMetrics {
    return BodyMetrics(
        id = id,
        height = height,
        weight = weight,
        createdAt = createdAt
    )
}

fun BodyMetrics.toBodyMetrics(): BodyMetricsEntity {
    return BodyMetricsEntity(
        id = id,
        height = height,
        weight = weight,
        createdAt = createdAt
    )
}
