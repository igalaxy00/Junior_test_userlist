package com.igalaxy.android.junior_test_userlist.data

import android.util.Log
import com.igalaxy.android.junior_test_userlist.data.api.UserApi
import com.igalaxy.android.junior_test_userlist.domain.UserRepository
import com.igalaxy.android.junior_test_userlist.domain.domain.EyeColor
import com.igalaxy.android.junior_test_userlist.domain.domain.Fruit
import com.igalaxy.android.junior_test_userlist.domain.domain.User
import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkEntity
import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkMapper
import java.util.Date
import javax.inject.Inject
import java.util.Random


class UserRepositoryImpl @Inject constructor(
    private val userNetworkMapper: UserNetworkMapper,
    private val userApi: UserApi
) : UserRepository {

    fun mockUserList(): List<User> {

        val mutableList = mutableListOf<User>()
        for (i in 1..20) {
            mutableList.add(
                User(
                    i,
                    "John$i",
                    "www@gmail",
                    true,
                    22,
                    "EA",
                    "89335068193",
                    "Leningrad",
                    "smart",
                    EyeColor.convertFromString("blue"),
                    Fruit.APPLE,
                    Date(121444),
                    12.1F,
                    3.2F,
                    listOf(Random().nextInt(15) + 1, Random().nextInt(15) + 2)
                )
            )
        }
        return mutableList.toList()

    }

    fun mockUser(id: Int): User {
        val a = User(
            id,
            "John$id",
            "www@gmail",
            true,
            22,
            "EA",
            "89335068193",
            "Leningrad",
            "smart",
            EyeColor.convertFromString("blue"),
            Fruit.APPLE,
            Date(121444),
            12.1F,
            3.2F,
            listOf(Random().nextInt(15) + 1, Random().nextInt(15) + 2)
        )
        return a
    }


    override suspend fun getUserList(): List<User> {
        Log.d("networkk","start")


        //плдучаем юзеров через fetch . Потом если там не пусто то с помощью маппера делаем из entity юзера и получаем лист
        val userList =userApi.fetchUsers()?.let { userNetworkMapper.mapFromEntityList(it) } ?: listOf()
        Log.d("networkk",userList[0].toString())

        return mockUserList()

    }

    override suspend fun getUser(id: Int): User? {
        return mockUser(id)
    }

}