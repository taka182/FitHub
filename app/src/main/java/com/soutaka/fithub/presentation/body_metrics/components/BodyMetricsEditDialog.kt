package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.R
import com.soutaka.fithub.presentation.body_metrics.DialogState
import com.soutaka.fithub.presentation.body_metrics.viewmodel.BodyMetricsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun BodyMetricsEditDialog(
    viewModel: BodyMetricsViewModel = hiltViewModel(),
    dialogState: DialogState = DialogState.Close,
) {
    var weight by remember {
        mutableStateOf(
            if (dialogState is DialogState.Edit) dialogState.bodyMetrics.weight.toString()
            else ""
        )
    }
    val currentDate = LocalDate.now()

    Dialog(
        onDismissRequest = {
            viewModel.closeDialog()
            viewModel.heightError = false
            viewModel.weightError = false
        }) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(50.dp))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (dialogState is DialogState.Edit) stringResource(R.string.record_update) else stringResource(
                            R.string.record_add
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "カレンダー")
                    Text(
                        text = currentDate
                            .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.width(10.dp))
                PinkLabelTextField(
                    value = weight,
                    onValueChange = {
                        weight = it
                        viewModel.validateWeight(weight)
                    },
                    isError = viewModel.weightError,
                    label = stringResource(R.string.weight),
                    placeholder = stringResource(R.string.weight_label),
                    errorMessage = viewModel.weightErrorMessage?.let { stringResource(it) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Button(
                        onClick = {
                            viewModel.closeDialog()
                            viewModel.heightError = false
                            viewModel.weightError = false
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "閉じる")
                        Text(text = stringResource(R.string.close_btn))
                    }
                    Button(
                        onClick = {
                            if (dialogState is DialogState.Edit) {

                                viewModel.validateWeight(weight)
                                if (!viewModel.heightError && !viewModel.weightError) {
                                    viewModel.closeDialog()
                                    viewModel.updateBodyMetrics(
                                        dialogState.bodyMetrics,
                                        weight
                                    )
                                }
                            } else {

                                viewModel.validateWeight(weight)
                                if (!viewModel.heightError && !viewModel.weightError) {
                                    viewModel.closeDialog()
                                    viewModel.addBodyMetrics(weight)
                                }
                            }
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "追加")
                        Text(text = stringResource(R.string.ok_btn))
                    }
                }
            }
        }
    }
}
