package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.North
import androidx.compose.material.icons.filled.South
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
    val user = viewModel.user.collectAsState()

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
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val currentItemIndex = viewModel.bodyMetrics.indexOf(bodyMetricsItem)
                    if (currentItemIndex != viewModel.bodyMetrics.lastIndex) {
                        val prevItem = viewModel.bodyMetrics[currentItemIndex + 1]
                        if (viewModel.bodyMetrics[currentItemIndex].weight > prevItem.weight) {
                            Icon(imageVector = Icons.Default.North, contentDescription = "増量")
                        } else if (viewModel.bodyMetrics[currentItemIndex].weight < prevItem.weight) {
                            Icon(imageVector = Icons.Default.South, contentDescription = "減量")
                        } else {
                            Icon(imageVector = Icons.Default.East, contentDescription = "変化なし")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = stringResource(R.string.date) + bodyMetricsItem.createdAt.format(
                                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                            )
                        )
                        Spacer(modifier = Modifier.width(150.dp))
                        IconButton(onClick = {
                            showDialog = true
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
                }
                Divider(modifier = Modifier.padding(vertical = 7.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = stringResource(R.string.height))
                    Text(text = bodyMetricsItem.height.toString())
                    Text(text = stringResource(R.string.weight))
                    Text(text = bodyMetricsItem.weight.toString())
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = stringResource(R.string.bmi))
                    if (bodyMetricsItem.height != 0.0) {
                        Text(text = bodyMetricsItem.bmi.toString())
                    } else {
                        Text(text = "-")
                    }
                    Text(text = stringResource(R.string.target_weight_difference))
                    if (user.value.goalWeight != "") {
                        val weightDifference =
                            bodyMetricsItem.weight - user.value.goalWeight.toDouble()
                        if (weightDifference > 0) {
                            Text(text = "+$weightDifference")
                        } else if (weightDifference < 0) {
                            Text(text = "$weightDifference")
                        } else {
                            Text(text = "$weightDifference")
                        }
                    } else {
                        Text(text = " -")
                    }
                }
            }
        }
    }
}
