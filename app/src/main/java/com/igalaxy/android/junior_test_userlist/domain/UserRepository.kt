package com.igalaxy.android.junior_test_userlist.domain

import com.igalaxy.android.junior_test_userlist.domain.domain.User

interface UserRepository {
    suspend fun getUserList(): List<User>

    suspend fun getUser(id: Int): User?

}