package com.soutaka.fithub.presentation.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soutaka.fithub.R
import com.soutaka.fithub.domain.model.UserProfile
import com.soutaka.fithub.domain.repository.UserRepository
import com.soutaka.fithub.presentation.profile.UserProfileForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class UserMetricsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var user: UserProfileForm by mutableStateOf(UserProfileForm())
    var isUpdate = false

    var nameError by mutableStateOf(false)
    var userNameErrorMessage: Int? by mutableStateOf(null)

    var birthError by mutableStateOf(false)
    var userBirthErrorMessage: Int? by mutableStateOf(null)

    var heightError by mutableStateOf(false)
    var userHeightErrorMessage: Int? by mutableStateOf(null)

    var goalWeightError by mutableStateOf(false)
    var goalWeightErrorMessage: Int? by mutableStateOf(null)

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val userProfile = userRepository.getUserProfile()
            withContext(Dispatchers.Main) {
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
    }

    fun addUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
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

    fun validateHeight(height: String) {
        val heightNum = height.toDoubleOrNull()

//        数値かどうか判定
        if (heightNum == null) {
            heightError = true
            userHeightErrorMessage = R.string.body_metrics_text_symbol
            return
        }

//        0より小さいか判定
        if (heightNum <= 0) {
            heightError = true
            userHeightErrorMessage = R.string.body_metrics_text_min
            return
        }

//        500より大きいか判定
        if (heightNum >= 500) {
            heightError = true
            userHeightErrorMessage = R.string.body_metrics_text_over
            return
        }

        heightError = false
        userHeightErrorMessage = null
    }

    fun validateGoalWeight(goalWeight: String) {
        val goalWeightNum = goalWeight.toDoubleOrNull()

//        数値かどうか判定
        if (goalWeightNum == null) {
            goalWeightError = true
            goalWeightErrorMessage = R.string.body_metrics_text_symbol
            return
        }
//        0より小さいか判定
        if (goalWeightNum < 0) {
            goalWeightError = true
            goalWeightErrorMessage = R.string.body_metrics_text_min
            return
        }
//        500より大きいか判定
        if (goalWeightNum >= 500) {
            goalWeightError = true
            goalWeightErrorMessage = R.string.body_metrics_text_over
            return
        }

        goalWeightError = false
        goalWeightErrorMessage = null
    }

    fun validateUserName(name: String) {
        val notNumber = """\d+""".toRegex()

        // 空白か判定
        if (name == "") {
            nameError = true
            userNameErrorMessage = R.string.user_text_symbol
            return
        }
        // スペースが含まれているか判定
        if (name.contains(" ") || name.contains("　")) {
            nameError = true
            userNameErrorMessage = R.string.user_text_space
            return
        }
        // 数値が含まれているか判定
        if (notNumber.containsMatchIn(name)) {
            nameError = true
            userNameErrorMessage = R.string.user_text_number
            return
        }

        nameError = false
        userNameErrorMessage = null
    }

    fun validateUserBirth(birthDay: String) {
        val birthFormat = """\d{4}-\d{2}-\d{2}""".toRegex()

        // 空白か判定
        if (birthDay == "") {
            birthError = true
            userBirthErrorMessage = R.string.user_birth_text_symbol
            return
        }
        // スペースが含まれているか判定
        if (birthDay.contains(" ") || birthDay.contains("　")) {
            birthError = true
            userBirthErrorMessage = R.string.user_text_space
            return
        }
//        yyyy-MM-dd形式か判定
        if (!birthFormat.matches(birthDay)) {
            birthError = true
            userBirthErrorMessage = R.string.user_birth_format
            return
        }
//        存在している日付かチェックする
        try {
            LocalDate.parse(birthDay)
        } catch (e: DateTimeParseException) {
            birthError = true
            userBirthErrorMessage = R.string.user_birth_date_error
            return
        }

        birthError = false
        userBirthErrorMessage = null
    }
}
