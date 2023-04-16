package com.soutaka.fithub.presentation.body_metrics

import com.soutaka.fithub.domain.model.BodyMetrics

sealed class DialogState {
    data class Edit(val bodyMetrics: BodyMetrics) : DialogState()
    object Add : DialogState()
    object Close : DialogState()
}
