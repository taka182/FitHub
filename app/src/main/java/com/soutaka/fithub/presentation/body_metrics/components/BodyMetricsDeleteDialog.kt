package com.soutaka.fithub.presentation.body_metrics.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.BodyMetrics

@Composable
fun BodyMetricsDeleteDialog(
    bodyMetricsItem: BodyMetrics,
    onClickDelete: (BodyMetrics) -> Unit,
    onDismissRequest: () -> Unit,
) {


    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "test") },
        text = { Text(text = "test") },
        confirmButton = {
            TextButton(
                onClick = {
                    onClickDelete(bodyMetricsItem)
                }
            ) {
                Text(text = stringResource(R.string.ok_btn))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = stringResource(R.string.close_btn))
            }
        }
    )
}
