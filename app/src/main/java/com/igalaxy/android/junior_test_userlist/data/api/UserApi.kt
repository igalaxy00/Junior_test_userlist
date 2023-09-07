package com.igalaxy.android.junior_test_userlist.data.api

import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkEntity
import retrofit2.http.GET

interface UserApi {
    @GET("v0/b/candidates--questionnaire.appspot.com/o/users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c")
    suspend fun fetchUsers(): List<UserNetworkEntity>?

}