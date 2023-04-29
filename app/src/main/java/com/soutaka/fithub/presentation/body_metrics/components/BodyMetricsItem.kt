package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.BodyMetrics
import com.soutaka.fithub.domain.model.bmi
import com.soutaka.fithub.presentation.body_metrics.viewmodel.BodyMetricsViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun BodyMetricsItem(
    bodyMetricsItem: BodyMetrics,
    viewModel: BodyMetricsViewModel = hiltViewModel(),
    onClickRow: (BodyMetrics) -> Unit,
    onClickDelete: (BodyMetrics) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

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
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.date) + bodyMetricsItem.createdAt.format(
                            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                        )
                    )
                    Spacer(modifier = Modifier.width(150.dp))
                    IconButton(onClick = {
                        showDialog = true
//                        onClickDelete(bodyMetricsItem)
                    }) {
                        Icon(imageVector = Icons.Default.WaterDrop, contentDescription = "削除")
                    }
                    if (showDialog) {
                        BodyMetricsDeleteDialog(
                            bodyMetricsItem = bodyMetricsItem,
                            onClickDelete = onClickDelete,
                            onDismissRequest = { showDialog = false }
                        )
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 7.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.height))
                    Text(text = bodyMetricsItem.height.toString())
                    Spacer(modifier = Modifier.width(80.dp))
                    Text(text = stringResource(R.string.weight))
                    Text(text = bodyMetricsItem.weight.toString())
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = stringResource(R.string.bmi))
                    Text(text = bodyMetricsItem.bmi.toString())
                }
            }
        }
    }
}
