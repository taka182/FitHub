package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.BodyMetrics
import com.soutaka.fithub.domain.model.bmi

@Composable
fun BodyMetricsItem(
    bodyMetricsItem: BodyMetrics,
    onClickRow: (BodyMetrics) -> Unit,
    onClickDelete: (BodyMetrics) -> Unit,
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),

        ) {
        Row(
            modifier = Modifier
                .clickable { onClickRow(bodyMetricsItem) }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Text(text = stringResource(R.string.date) + bodyMetricsItem.createdAt)
                Text(text = stringResource(R.string.height))
                Text(text = bodyMetricsItem.height.toString())
                Text(text = stringResource(R.string.weight))
                Text(text = bodyMetricsItem.weight.toString())
                Text(text = stringResource(R.string.bmi))
                Text(text = bodyMetricsItem.bmi.toString())
            }
            IconButton(onClick = {
                onClickDelete(bodyMetricsItem)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "削除")
            }
        }
    }
}
