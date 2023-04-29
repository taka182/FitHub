package com.soutaka.fithub.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.R
import com.soutaka.fithub.presentation.profile.viewmodel.UserMetricsViewModel

@Composable
fun ProfileScreen(
    viewModel: UserMetricsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // 名前
            TextField(
                value = viewModel.user.name,
                onValueChange = { viewModel.user = viewModel.user.copy(name = it) },
                label = { Text("名前") },
                modifier = Modifier.fillMaxWidth()
            )

            // 生年月日
            TextField(
                value = viewModel.user.birthDay,
                onValueChange = { viewModel.user = viewModel.user.copy(birthDay = it) },
                label = { Text("生年月日") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            // 身長
            TextField(
                value = viewModel.user.userHeight,
                onValueChange = { viewModel.user = viewModel.user.copy(userHeight = it) },
                label = { Text("身長") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(Modifier.width(16.dp))
            // 目標体重
            TextField(
                value = viewModel.user.goalWeight,
                onValueChange = { viewModel.user = viewModel.user.copy(goalWeight = it) },
                label = { Text("目標体重") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            // 性別
            Text("性別")
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                RadioButton(
                    selected = !viewModel.user.isMan,
                    onClick = {
                        viewModel.user = viewModel.user.copy(isMan = false)
                    }
                )
                Text("男性")
                Spacer(Modifier.width(16.dp))
                RadioButton(
                    selected = viewModel.user.isMan,
                    onClick = {
                        viewModel.user = viewModel.user.copy(isMan = true)
                    }
                )
                Text("女性")
            }
            Button(
                modifier = Modifier.width(120.dp),
                onClick = {
                    if (viewModel.isUpdate) {
                        viewModel.updateUserProfile()
                    } else {
                        viewModel.addUserProfile()
                    }
                }
            ) {
                Text(text = stringResource(R.string.ok_btn))
            }
        }
    }
}
