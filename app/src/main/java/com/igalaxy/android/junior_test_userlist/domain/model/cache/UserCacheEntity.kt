package com.igalaxy.android.junior_test_userlist.domain.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class UserCacheEntity(
    @PrimaryKey val id: Int = 0,
    val name: String = "Name",
    val age: Int = 20,
    val isActive: Boolean = true,
    val company: String = "",
    val email: String = "Email",
    val phone: String = "",
    val adress: String = "",
    val about: String = "",
    val eyeColor: String = "",
    val favoriteFruit: String = "",
    val registerDate: Date = Date(1532516399000),
    val latitude: Float = 0.0f,
    val longitude: Float = 0.0f,
    val friends: List<Int> = mutableListOf()

)