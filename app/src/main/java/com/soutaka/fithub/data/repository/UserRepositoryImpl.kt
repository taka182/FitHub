package com.soutaka.fithub.data.repository

import com.soutaka.fithub.data.local.dao.UserDao
import com.soutaka.fithub.data.mapper.toUserEntity
import com.soutaka.fithub.data.mapper.toUserProfile
import com.soutaka.fithub.domain.model.UserProfile
import com.soutaka.fithub.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUserProfile(): UserProfile? {
        return userDao.loadUser()?.toUserProfile()
    }

    override suspend fun addUserProfile(userProfile: UserProfile) {
        return userDao.insertUser(userProfile.toUserEntity())
    }

    override suspend fun updateUserProfile(userProfile: UserProfile) {
        return userDao.updateUser(userProfile.toUserEntity())
    }
}
