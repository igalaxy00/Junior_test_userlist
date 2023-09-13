package com.igalaxy.android.junior_test_userlist.domain.model.domain

import java.util.Date

//@Entity
data class User(
    val id: Int,
    val name: String = "",
    val email: String = "",
    val isActive: Boolean = true,
    val age: Int = 1,
    val company: String = "",
    val phone: String = "",
    val address: String = "",
    val about: String = "",
    val eyeColor: EyeColor = EyeColor.BLUE,
    val favoriteFruit: Fruit = Fruit.APPLE,
    val registerDate: Date = Date(11134),
    val latitude: Float = 1f,
    val longitude: Float = 1f,
    val friends: List<Int> = listOf()
)