package com.igalaxy.android.junior_test_userlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.igalaxy.android.junior_test_userlist.domain.model.cache.UserCacheEntity

@Database(entities = [UserCacheEntity::class], version = 2)

@TypeConverters(UserTypeConverters::class)

abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}