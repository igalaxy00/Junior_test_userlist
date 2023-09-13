package com.igalaxy.android.junior_test_userlist.domain.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = UserCacheEntity.TableName)
data class UserCacheEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val isActive: Boolean,
    val age: Int,
    val company: String,
    val phone: String,
    val address: String,
    val about: String,
    val eyeColor: String,
    val favoriteFruit: String,
    val registerDate: Date,
    val latitude: Float,
    val longitude: Float,
    val friends: List<Int>
) {
    companion object {
        const val TableName = "UserCaches"
    }
}