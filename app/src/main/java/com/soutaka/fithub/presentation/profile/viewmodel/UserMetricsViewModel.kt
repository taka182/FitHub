package com.soutaka.fithub.presentation.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soutaka.fithub.domain.model.UserProfile
import com.soutaka.fithub.domain.repository.UserRepository
import com.soutaka.fithub.presentation.profile.UserProfileForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class UserMetricsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var user: UserProfileForm by mutableStateOf(UserProfileForm())
    var isUpdate = false

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            val userProfile = userRepository.getUserProfile()
            if (userProfile != null) {
                isUpdate = true
                user = UserProfileForm(
                    name = userProfile.name,
                    birthDay = userProfile.birthDay.toString(),
                    userHeight = userProfile.userHeight.toString(),
                    goalWeight = userProfile.goalWeight.toString(),
                    isMan = userProfile.isMan
                )
            }
        }
    }

    fun addUserProfile() {
        viewModelScope.launch {
            val newProfile = UserProfile(
                userId = 1,
                name = user.name,
                birthDay = LocalDate.parse(user.birthDay),
                userHeight = user.userHeight.toDouble(),
                isMan = user.isMan,
                goalWeight = user.goalWeight.toDouble(),
            )
            userRepository.addUserProfile(newProfile)
            isUpdate = true
        }
    }

    fun updateUserProfile() {
        viewModelScope.launch {
            userRepository.updateUserProfile(
                UserProfile(
                    userId = 1,
                    name = user.name,
                    birthDay = LocalDate.parse(user.birthDay),
                    userHeight = user.userHeight.toDouble(),
                    goalWeight = user.goalWeight.toDouble(),
                    isMan = user.isMan,
                )
            )
        }
    }
}
