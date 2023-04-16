package com.soutaka.fithub.presentation.body_metrics.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soutaka.fithub.domain.model.BodyMetrics
import com.soutaka.fithub.domain.repository.BodyMetricsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyMetricsViewModel @Inject constructor(
    private val repository: BodyMetricsRepository
) : ViewModel() {
    var bodyMetrics: List<BodyMetrics> by mutableStateOf(emptyList())
        private set

    var dialogState: DialogState by mutableStateOf(DialogState.Close)
        private set

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

//    fun addBodyMetrics() {
//        viewModelScope.launch {
//            val newBody = BodyMetrics(
//                height = height.toDouble(),
//                weight = weight.toDouble(),
//                createdAt = LocalDate.now(),
//            )
//            repository.addBodyMetrics(newBody)
//        }
//    }
}
