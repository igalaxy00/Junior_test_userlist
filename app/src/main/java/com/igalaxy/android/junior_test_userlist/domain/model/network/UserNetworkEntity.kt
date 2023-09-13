package com.igalaxy.android.junior_test_userlist.domain.model.network

import java.util.Date

// убрать переменные
data class UserNetworkEntity(
    val id: Int = 0,
    val guid: String = "",
    val isActive: Boolean = true,
    val balance: String,
    val age: Int = 20,
    val eyeColor: String = "",
    val name: String = "Name",
    val gender: String = "",
    val company: String = "",
    val email: String = "Email",
    val phone: String = "",
    val address: String,
    val about: String = "",
    val registered: Date = Date(1532516399000),
    val latitude: Float = 0.0f,
    val longitude: Float = 0.0f,
    val tags: List<String>,
    val friends: List<Friend>,
    val favoriteFruit: String = "",
)