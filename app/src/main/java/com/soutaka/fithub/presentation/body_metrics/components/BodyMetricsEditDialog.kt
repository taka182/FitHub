package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.presentation.body_metrics.viewmodel.BodyMetricsViewModel
import java.time.LocalDate

@Composable
fun BodyMetricsEditDialog(
    viewModel: BodyMetricsViewModel = hiltViewModel()
) {
    val heightState = remember { mutableStateOf("") }
    val weightState = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { viewModel.isShowDialog = false }) {
        Box(
            modifier = Modifier
                .size(350.dp)
                .background(Color.White, RoundedCornerShape(50.dp))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.White)
            ) {
                Text(text = "記録登録")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = LocalDate.now().toString())
                PinkLabelTextField(
                    value = heightState.value,
                    onValueChange = { newtext -> heightState.value = newtext },
                    label = "身長(cm)",
                    placeholder = "170"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.width(10.dp))
                PinkLabelTextField(
                    value = weightState.value,
                    onValueChange = { newtext -> weightState.value = newtext },
                    label = "体重(kg)",
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

                        },
                    ) {
                        Text(text = "Close")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier.width(120.dp),
                        onClick = {
//                                  viewModel.addBodyMetrics()
                        },
                    ) {
                        Text(text = "OK")
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
            placeholder =
            {
                Text(text = placeholder)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
        )
    }
}




