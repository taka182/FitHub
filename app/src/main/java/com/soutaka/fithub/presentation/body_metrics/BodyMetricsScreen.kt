package com.soutaka.fithub.presentation.body_metrics

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.R
import com.soutaka.fithub.presentation.body_metrics.components.BodyMetricsEditDialog
import com.soutaka.fithub.presentation.body_metrics.components.BodyMetricsList
import com.soutaka.fithub.presentation.body_metrics.viewmodel.BodyMetricsViewModel

@Composable
fun BodyMetricsScreen(
    viewModel: BodyMetricsViewModel = hiltViewModel(),
) {
    when (val dialogState = viewModel.dialogState) {
        is DialogState.Edit, DialogState.Add -> BodyMetricsEditDialog(dialogState = dialogState)
        is DialogState.Close -> {}
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.showAddDialog() }) {
                Icon(
                    imageVector = Icons.Default.NoteAdd,
                    contentDescription = stringResource(R.string.weight_record)
                )
            }
        }) {
        if (viewModel.bodyMetrics.isEmpty()) {
            NoDataAvailableUI()
        } else {
            BodyMetricsList(
                bodyMetricsList = viewModel.bodyMetrics,
                onClickRow = {
                    viewModel.showEditDialog(it)
                }
            ) {
                viewModel.deleteBodyMetrics(it)
            }
        }
    }
}

@Composable
fun NoDataAvailableUI() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(R.string.no_data))
        }
    }
}
