package com.soutaka.fithub.domain.repository

import com.soutaka.fithub.domain.model.UserProfile


interface UserRepository {

    fun getUserProfile(): UserProfile

    fun addUserProfile(userProfile: UserProfile)

    fun updateUserProfile(userProfile: UserProfile)
}
