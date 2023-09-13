package com.igalaxy.android.junior_test_userlist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.igalaxy.android.junior_test_userlist.domain.model.cache.UserCacheEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM ${UserCacheEntity.TableName}")
    suspend fun getUserList(): List<UserCacheEntity>

    @Query("SELECT * FROM ${UserCacheEntity.TableName} WHERE id == (:id)")
    suspend fun getUser(id: Int): UserCacheEntity?

    @Insert(onConflict = REPLACE)
    suspend fun addUserList(userList: List<UserCacheEntity>)
}