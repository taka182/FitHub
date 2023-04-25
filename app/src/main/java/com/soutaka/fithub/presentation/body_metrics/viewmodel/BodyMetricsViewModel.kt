package com.soutaka.fithub.presentation.body_metrics.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.BodyMetrics
import com.soutaka.fithub.domain.repository.BodyMetricsRepository
import com.soutaka.fithub.presentation.body_metrics.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BodyMetricsViewModel @Inject constructor(
    private val repository: BodyMetricsRepository
) : ViewModel() {
    var bodyMetrics: List<BodyMetrics> by mutableStateOf(emptyList())
        private set

    var dialogState: DialogState by mutableStateOf(DialogState.Close)
        private set

    var heightError by mutableStateOf(false)
    var heightErrorMessage: Int? by mutableStateOf(null)

    var weightError by mutableStateOf(false)
    var weightErrorMessage: Int? by mutableStateOf(null)

    init {
        getBodyMetrics()
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
                height = height.toDoubleOrNull() ?: 0.0,
                weight = weight.toDoubleOrNull() ?: 0.0,
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
            val updatedBodyMetrics = bodyMetrics.copy(
                height = height.toDoubleOrNull() ?: 0.0,
                weight = weight.toDoubleOrNull() ?: 0.0
            )
            repository.updateBodyMetrics(updatedBodyMetrics)
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
    }
}
