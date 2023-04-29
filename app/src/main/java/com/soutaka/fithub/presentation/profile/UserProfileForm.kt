package com.soutaka.fithub.presentation.profile

data class UserProfileForm(
    var name: String = "",
    var birthDay: String = "",
    var userHeight: String = "",
    var goalWeight: String = "",
    var isMan: Boolean = true,
)
