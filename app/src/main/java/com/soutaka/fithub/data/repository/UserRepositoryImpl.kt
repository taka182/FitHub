package com.soutaka.fithub.data.repository

import com.soutaka.fithub.data.local.dao.UserDao
import com.soutaka.fithub.domain.model.UserProfile
import com.soutaka.fithub.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override fun getUserProfile(): UserProfile {
        TODO("Not yet implemented")
    }

    override fun addUserProfile(userProfile: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun updateUserProfile(userProfile: UserProfile) {
        TODO("Not yet implemented")
    }
}
