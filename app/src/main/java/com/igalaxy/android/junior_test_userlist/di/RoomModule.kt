package com.igalaxy.android.junior_test_userlist.di

import android.content.Context
import androidx.room.Room
import com.igalaxy.android.junior_test_userlist.data.database.UserDao
import com.igalaxy.android.junior_test_userlist.data.database.UserDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "user-database"

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            DATABASE_NAME
        )//.fallbackToDestructiveMigration()
            //.addMigrations()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }
}