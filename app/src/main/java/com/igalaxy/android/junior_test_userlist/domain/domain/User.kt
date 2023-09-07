package com.igalaxy.android.junior_test_userlist.domain.domain

import androidx.room.Entity
import java.util.Date

@Entity
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val isActive: Boolean,
    val age: Int,
    val company: String,
    val phone: String,
    val address: String,
    val about: String,
    val eyeColor: EyeColor,
    val favoriteFruit: Fruit,
    val registerDate: Date,
    val latitude: Float,
    val longitude: Float,
    val friends: List<Int>
)