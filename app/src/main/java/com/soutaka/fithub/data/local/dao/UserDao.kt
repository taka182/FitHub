package com.soutaka.fithub.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.soutaka.fithub.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE userId = 1")
    suspend fun loadUser(): UserEntity?

    @Update
    suspend fun updateUser(userEntity: UserEntity)
}
