package com.soutaka.fithub.presentation.body_metrics

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.presentation.body_metrics.components.BodyMetricsEditDialog
import com.soutaka.fithub.presentation.body_metrics.viewmodel.BodyMetricsViewModel

@Composable
fun BodyMetricsScreen(
    viewModel: BodyMetricsViewModel = hiltViewModel(),
) {
    BodyMetricsEditDialog()

    Column() {
        Text(text = viewModel.bodyMetrics.firstOrNull()?.id.toString())
        Text(text = viewModel.bodyMetrics.firstOrNull()?.height.toString())
        Text(text = viewModel.bodyMetrics.firstOrNull()?.weight.toString())
        Text(text = viewModel.bodyMetrics.firstOrNull()?.createdAt.toString())
    }
}
