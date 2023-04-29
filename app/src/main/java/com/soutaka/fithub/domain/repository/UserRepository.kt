package com.soutaka.fithub.domain.repository

import com.soutaka.fithub.domain.model.UserProfile


interface UserRepository {
    suspend fun getUserProfile(): UserProfile?

    suspend fun addUserProfile(userProfile: UserProfile)

    suspend fun updateUserProfile(userProfile: UserProfile)
}
