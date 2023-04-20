package com.soutaka.fithub.di

import android.content.Context
import androidx.room.Room
import com.soutaka.fithub.data.local.FithubDatabase
import com.soutaka.fithub.data.repository.BodyMetricsRepositoryImpl
import com.soutaka.fithub.data.repository.UserRepositoryImpl
import com.soutaka.fithub.domain.repository.BodyMetricsRepository
import com.soutaka.fithub.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FithubDatabase {
        return Room.databaseBuilder(
            context,
            FithubDatabase::class.java,
            "fithub_database"
        ).build()
    }

    @Provides
    @Singleton
    fun bodyMetricsRepository(db: FithubDatabase): BodyMetricsRepository {
        return BodyMetricsRepositoryImpl(db.bodyMetricsDao())
    }

    @Provides
    @Singleton
    fun userRepository(db: FithubDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao())
    }
}
