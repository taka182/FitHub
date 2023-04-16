package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soutaka.fithub.domain.model.BodyMetrics

@Composable
fun BodyMetricsList(
    bodyMetricsList: List<BodyMetrics>,
    onClickRow: (BodyMetrics) -> Unit,
    onClickDelete: (BodyMetrics) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ) {
        items(bodyMetricsList) { bodyMetricsItem ->
            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                BodyMetricsItem(
                    bodyMetricsItem = bodyMetricsItem,
                    onClickRow = onClickRow,
                    onClickDelete = onClickDelete
                )
            }

        }
    }
}


