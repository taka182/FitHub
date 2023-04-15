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
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var isShowDialog by mutableStateOf(false)

    init {
        getBodyMetrics()
    }

    fun getBodyMetrics() {
        viewModelScope.launch {
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
