package com.soutaka.fithub.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Woman
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soutaka.fithub.R
import com.soutaka.fithub.presentation.body_metrics.components.UserProfileNumberTextField
import com.soutaka.fithub.presentation.body_metrics.components.UserProfileTextField
import com.soutaka.fithub.presentation.profile.viewmodel.UserMetricsViewModel
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun ProfileScreen(
    viewModel: UserMetricsViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)},
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
                UserProfileTextField(
                    value = viewModel.user.name,
                    onValueChange = {
                        viewModel.user = viewModel.user.copy(name = it)
                        viewModel.validateUserName(viewModel.user.name)
                    },
                    isError = viewModel.nameError,
                    label = stringResource(R.string.user_name),
                    errorMessage = viewModel.userNameErrorMessage?.let { stringResource(it) }

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
                UserProfileTextField(
                    value = viewModel.user.birthDay.format(
                        DateTimeFormatter.ofLocalizedDate(
                            FormatStyle.SHORT
                        )
                    ),
                    onValueChange = {
                        viewModel.user = viewModel.user.copy(birthDay = it)
                        viewModel.validateUserBirth(viewModel.user.birthDay)
                    },
                    label = stringResource(R.string.user_birth_label),
                    isError = viewModel.birthError,
                    errorMessage = viewModel.userBirthErrorMessage?.let { stringResource(it) }
                )
                Spacer(Modifier.padding(8.dp))
                // 身長
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
                        viewModel.validateUserName(viewModel.user.name)
                        viewModel.validateHeight(viewModel.user.userHeight)
                        viewModel.validateGoalWeight(viewModel.user.goalWeight)
                        viewModel.validateUserBirth(viewModel.user.birthDay)
                        if (!viewModel.heightError && !viewModel.goalWeightError && !viewModel.nameError && !viewModel.birthError) {
                            if (viewModel.isUpdate) {
                                viewModel.updateUserProfile()
                                scope.launch {
                                    snackbarHostState.showSnackbar("ユーザー情報を更新しました!")
                                }
                            } else {
                                viewModel.addUserProfile()
                                scope.launch {
                                    snackbarHostState.showSnackbar("ユーザー情報を新規追加しました!")
                                }
                            }
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "追加")
                    Text(text = stringResource(R.string.ok_btn))
                }
            }
        }
    }
}
