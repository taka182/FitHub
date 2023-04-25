package com.soutaka.fithub.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
//        Text(text = viewModel.user?.id.toString())
//        Text(text = viewModel.user?.age.toString())
//        Text(text = viewModel.user?.height.toString())
//        Text(text = viewModel.user?.goalWeight.toString())
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // 名前
            TextField(
                value = "",
                onValueChange = { },
                label = { Text("名前") },
                modifier = Modifier.fillMaxWidth()
            )

            // 生年月日
            TextField(
                value = "",
                onValueChange = { },
                label = { Text("生年月日") },
                modifier = Modifier.fillMaxWidth()
            )

            // 身長
            TextField(
                value = "",
                onValueChange = { },
                label = { Text("身長") },
                modifier = Modifier.fillMaxWidth()
            )

            // 目標体重
            TextField(
                value = "",
                onValueChange = { "" },
                label = { Text("目標体重") },
                modifier = Modifier.fillMaxWidth()
            )

            // 性別
            Text("性別")
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = true,
                    onClick = { }
                )
                Text("男性")
                Spacer(Modifier.width(16.dp))
                RadioButton(
                    selected = false,
                    onClick = { }
                )
                Text("女性")
            }
        }
    }
}


