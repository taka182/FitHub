package com.soutaka.fithub.data.mapper

import com.soutaka.fithub.data.local.entity.UserEntity
import com.soutaka.fithub.domain.model.UserProfile

fun UserEntity.toUserProfile(): UserProfile {
    return UserProfile(
        userId = userId,
        name = name,
        birthDay = birthDay,
        userHeight = userHeight,
        isMan = isMan,
        goalWeight = goalWeight
    )
    }
fun UserProfile.toUserEntity(): UserEntity {
    return UserEntity(
        userId = userId,
        name = name,
        birthDay = birthDay,
        userHeight = userHeight,
        isMan = isMan,
        goalWeight = goalWeight
    )
}
