package com.soutaka.fithub.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Woman
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.R
import com.soutaka.fithub.presentation.body_metrics.components.UserProfileNumberTextField
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
            modifier = Modifier.padding(16.dp),

            ) {
            Text(text = "プロフィール設定")
            Spacer(Modifier.padding(8.dp))
            // 名前
            OutlinedTextField(
                value = viewModel.user.name,
                onValueChange = { viewModel.user = viewModel.user.copy(name = it) },
                label = { Text("名前") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.padding(8.dp))
// 性別
            Text("性別")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = !viewModel.user.isMan,
                    onClick = {
                        viewModel.user = viewModel.user.copy(isMan = false)
                    }
                )
                Icon(imageVector = Icons.Default.Man, contentDescription = "男性")
                Text("男性")
                Spacer(Modifier.width(16.dp))
                RadioButton(
                    selected = viewModel.user.isMan,
                    onClick = {
                        viewModel.user = viewModel.user.copy(isMan = true)
                    }
                )
                Icon(imageVector = Icons.Default.Woman, contentDescription = "女性")
                Text("女性")
            }
            Spacer(Modifier.padding(8.dp))
            // 生年月日
            OutlinedTextField(
                value = viewModel.user.birthDay,
                onValueChange = { viewModel.user = viewModel.user.copy(birthDay = it) },
                label = { Text("生年月日") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(Modifier.padding(8.dp))
            // 身長
//            OutlinedTextField(
//                value = viewModel.user.userHeight,
//                onValueChange = { viewModel.user = viewModel.user.copy(userHeight = it) },
//                label = { Text(text = stringResource(R.string.height)) },
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            )
            UserProfileNumberTextField(
                value = viewModel.user.userHeight,
                onValueChange = {
                    viewModel.user = viewModel.user.copy(userHeight = it)
                    viewModel.validateHeight(viewModel.user.userHeight)
                },
                isError = viewModel.heightError,
                label = stringResource(R.string.height),
                errorMessage = viewModel.userHeightErrorMessage?.let { stringResource(it) }
            )
            Spacer(Modifier.padding(8.dp))
            // 目標体重
//            OutlinedTextField(
//                value = viewModel.user.goalWeight,
//                onValueChange = { viewModel.user = viewModel.user.copy(goalWeight = it) },
//                label = { Text("目標体重(kg)") },
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            )
            UserProfileNumberTextField(
                value = viewModel.user.goalWeight,
                onValueChange = {
                    viewModel.user = viewModel.user.copy(goalWeight = it)
                    viewModel.validateGoalWeight(viewModel.user.goalWeight)
                },
                isError = viewModel.goalWeightError,
                label = stringResource(R.string.goalWeight),
                errorMessage = viewModel.goalWeightErrorMessage?.let { stringResource(it) }
            )
            Spacer(Modifier.padding(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (viewModel.isUpdate) {
                        viewModel.updateUserProfile()
                    } else {
                        viewModel.addUserProfile()
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "追加")
                Text(text = stringResource(R.string.ok_btn))
            }
        }
    }
}
