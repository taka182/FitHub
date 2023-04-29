package com.soutaka.fithub.presentation.body_metrics.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.BodyMetrics
import java.lang.reflect.Modifier

@Composable
fun BodyMetricsDeleteDialog(
    bodyMetricsItem: BodyMetrics,
    onClickDelete: (BodyMetrics) -> Unit,
    onDismissRequest: () -> Unit,
) {


    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Error, contentDescription = "エラーアイコン")
                Text(text = stringResource(R.string.delete_dialog_title))
            }
        },
        text = { Text(text = stringResource(R.string.delete_dialog_text)) },
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
