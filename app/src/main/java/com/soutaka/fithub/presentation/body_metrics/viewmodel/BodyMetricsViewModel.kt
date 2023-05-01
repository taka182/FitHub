package com.soutaka.fithub.presentation.body_metrics.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.BodyMetrics
import com.soutaka.fithub.domain.repository.BodyMetricsRepository
import com.soutaka.fithub.domain.repository.UserRepository
import com.soutaka.fithub.presentation.body_metrics.DialogState
import com.soutaka.fithub.presentation.profile.UserProfileForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BodyMetricsViewModel @Inject constructor(
    private val repository: BodyMetricsRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    var bodyMetrics: List<BodyMetrics> by mutableStateOf(emptyList())
        private set

    var dialogState: DialogState by mutableStateOf(DialogState.Close)
        private set

    var heightError by mutableStateOf(false)
    var heightErrorMessage: Int? by mutableStateOf(null)

    var weightError by mutableStateOf(false)
    var weightErrorMessage: Int? by mutableStateOf(null)

    private var _user: MutableStateFlow<UserProfileForm> = MutableStateFlow(UserProfileForm())
    val user: StateFlow<UserProfileForm> = _user

    init {
        getBodyMetrics()
        getUserProfile()
    }

    private fun getBodyMetrics() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBodyMetrics().collect {
                bodyMetrics = it
            }
        }
    }

    fun addBodyMetrics(height: String, weight: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newBody = BodyMetrics(
                id = 0,
                height = height.toDouble(),
                weight = weight.toDouble(),
                createdAt = LocalDate.now(),
            )
            repository.addBodyMetrics(newBody)
        }
    }

    fun deleteBodyMetrics(bodyMetrics: BodyMetrics) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBodyMetrics(bodyMetrics)
        }
    }

    fun updateBodyMetrics(bodyMetrics: BodyMetrics, height: String, weight: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val updateBodyMetrics = bodyMetrics.copy(
                height = height.toDouble(),
                weight = weight.toDouble()
            )
            repository.updateBodyMetrics(updateBodyMetrics)
        }
    }

    fun closeDialog() {
        dialogState = DialogState.Close
    }

    fun showEditDialog(bodyMetrics: BodyMetrics) {
        dialogState = DialogState.Edit(bodyMetrics)
    }

    fun showAddDialog() {
        dialogState = DialogState.Add
    }


    fun validateHeight(height: String) {
        val heightNum = height.toDoubleOrNull()

//        数値かどうか判定
        if (heightNum == null) {
            heightError = true
            heightErrorMessage = R.string.body_metrics_text_symbol
            return
        }

//        0より小さいか判定
        if (heightNum <= 0) {
            heightError = true
            heightErrorMessage = R.string.body_metrics_text_min
            return
        }

//        500より大きいか判定
        if (heightNum >= 500) {
            heightError = true
            heightErrorMessage = R.string.body_metrics_text_over
            return
        }

        heightError = false
        heightErrorMessage = null
    }


    fun validateWeight(weight: String) {
        val weightNum = weight.toDoubleOrNull()

        // 数値かどうか判定
        if (weightNum == null) {
            weightError = true
            weightErrorMessage = R.string.body_metrics_text_symbol
            return
        }

        // 0より小さいか判定
        if (weightNum <= 0) {
            weightError = true
            weightErrorMessage = R.string.body_metrics_text_min
            return
        }

        // 500より大きいか判定
        if (weightNum >= 500) {
            weightError = true
            weightErrorMessage = R.string.body_metrics_text_over
            return
        }

        weightError = false
        weightErrorMessage = null
    }

    fun getUserProfile() {
        viewModelScope.launch {
            val userProfile = userRepository.getUserProfile()
            if (userProfile != null) {
                _user.value = UserProfileForm(
                    name = userProfile.name,
                    birthDay = userProfile.birthDay.toString(),
                    userHeight = userProfile.userHeight.toString(),
                    goalWeight = userProfile.goalWeight.toString(),
                    isMan = userProfile.isMan
                )
            }
        }
    }
}
