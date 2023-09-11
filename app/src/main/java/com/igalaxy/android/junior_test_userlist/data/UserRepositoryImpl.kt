package com.igalaxy.android.junior_test_userlist.data

import android.util.Log
import com.igalaxy.android.junior_test_userlist.data.api.UserApi
import com.igalaxy.android.junior_test_userlist.domain.UserRepository
import com.igalaxy.android.junior_test_userlist.domain.model.domain.EyeColor
import com.igalaxy.android.junior_test_userlist.domain.model.domain.Fruit
import com.igalaxy.android.junior_test_userlist.domain.model.domain.User
import com.igalaxy.android.junior_test_userlist.domain.model.cache.UserCacheMapper
import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkEntity
import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkMapper
import java.util.Date
import javax.inject.Inject
import java.util.Random

private const val TAG = "REPO"
class UserRepositoryImpl @Inject constructor(
    private val userNetworkMapper: UserNetworkMapper,
    private val userApi: UserApi,
    //private val userDao: UserDao,
    private val userCacheMapper: UserCacheMapper,
) : UserRepository {
    private fun intToBoolean(value: Int): Boolean {
        return value != 0
    }

    fun mockUserList(): List<User> {

        val mutableList = mutableListOf<User>()
        for (i in 1..20) {
            mutableList.add(
                User(
                    i,
                    "John$i",
                    "www@gmail",
                    intToBoolean(i%2),
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
       /* Log.d("networkk","start")
        //получем из ДАО юзеров. Потом маппером приводим к обычному юзеру
        var userList = userCacheMapper.mapFromEntityList(userDao.getUserList())

        Log.d(TAG, "Got ${userList.size} users CACHED FROM DB")
        //если не получили список из ДАО
        if (userList.isEmpty()) {
            //плдучаем юзеров через fetch . Потом если там не пусто то с помощью маппера делаем из entity юзера и получаем лист
            userList =
                userApi.fetchUsers()?.let { userNetworkMapper.mapFromEntityList(it) } ?: listOf()
            //после запроса кэшируем всё
            cacheUserList(userList)
            Log.i(TAG, "DOWNLOADED AND CACHED USERS")
        }
        return userList
*/
        return mockUserList()

    }

    override suspend fun getUser(id: Int): User? {
        /*
        //достаем юзера из бд и преобразовываем в User
        val user = userDao.getUser(id)?.let { userCacheMapper.mapFromEntity(it) }
        Log.i(TAG, "Got user with id ${user?.id}")
        return user

         */

        return mockUser(id)
    }

    private suspend fun cacheUserList(userList: List<User>) {
        //добавляем в локальную бд
//        userDao.addUserList(userList.map {
//            userCacheMapper.mapToEntity(it)
//        })
    }

}