package com.soutaka.fithub.presentation.body_metrics

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.presentation.body_metrics.components.BodyMetricsEditDialog
import com.soutaka.fithub.presentation.body_metrics.viewmodel.BodyMetricsViewModel

@Composable
fun BodyMetricsScreen(
    viewModel: BodyMetricsViewModel = hiltViewModel(),
) {
    if (viewModel.isShowDialog){
        BodyMetricsEditDialog()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.isShowDialog = true }) {
                Icon(imageVector = Icons.Default.NoteAdd, contentDescription = "体重記録")
            }
        }){
}

    Column() {
        Text(text = viewModel.bodyMetrics.firstOrNull()?.id.toString())
        Text(text = viewModel.bodyMetrics.firstOrNull()?.height.toString())
        Text(text = viewModel.bodyMetrics.firstOrNull()?.weight.toString())
        Text(text = viewModel.bodyMetrics.firstOrNull()?.createdAt.toString())
    }
}
