package com.igalaxy.android.junior_test_userlist.domain.usecase

import com.igalaxy.android.junior_test_userlist.domain.UserRepository
import com.igalaxy.android.junior_test_userlist.domain.model.domain.User
import javax.inject.Inject

class GetUserUseCase
@Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(id: Int): User? {
        return userRepository.getUser(id)
    }

}