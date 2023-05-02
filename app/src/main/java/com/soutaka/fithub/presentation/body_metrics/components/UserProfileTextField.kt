package com.soutaka.fithub.presentation.body_metrics.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun UserProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorMessage: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = errorMessage ?: "")
            }
        },
        trailingIcon = {
            if (isError)
                Icon(imageVector = Icons.Default.Error, contentDescription = "エラーアイコン")
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
    )
}
