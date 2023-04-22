package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var height by remember {
        mutableStateOf(
            if (dialogState is DialogState.Edit) dialogState.bodyMetrics.height.toString()
            else ""
        )
    }
    var weight by remember {
        mutableStateOf(
            if (dialogState is DialogState.Edit) dialogState.bodyMetrics.weight.toString()
            else ""
        )
    }
    val currentDate = LocalDate.now()
    val context = LocalContext.current

    Dialog(
        onDismissRequest = { viewModel.closeDialog() }) {
        Box(
            modifier = Modifier
                .size(350.dp)
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
                Text(
                    text = currentDate
                        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                )
                PinkLabelTextField(
                    value = height,
                    onValueChange = {
                        height = it
                        viewModel.validateHeight(height)
                    },
                    isError = viewModel.heightError,
                    label = stringResource(R.string.height),
                    placeholder = "170"
                )
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
                    placeholder = "65"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = {
                            viewModel.closeDialog()
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "閉じる")
                        Text(text = stringResource(R.string.close_btn))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = {
                            if (dialogState is DialogState.Edit) {
                                viewModel.closeDialog()
                                viewModel.updateBodyMetrics(dialogState.bodyMetrics, height, weight)
                            } else {
                                viewModel.validateHeight(height)
                                viewModel.validateWeight(weight)
                                if (!viewModel.heightError && !viewModel.weightError) {
                                    viewModel.closeDialog()
                                    viewModel.addBodyMetrics(height, weight)
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

@Composable
fun PinkLabelTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isError: Boolean,
) {
    Column {
        Text(
            text = label,
            color = Color(0xFFF85F6A),
            fontWeight = FontWeight.Bold,

            )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(text = stringResource(R.string.body_metrics_text))
                }
            },
            placeholder =
            {
                Text(text = placeholder)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
        )
    }
}






