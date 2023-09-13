package com.igalaxy.android.junior_test_userlist.data

import android.util.Log
import com.igalaxy.android.junior_test_userlist.data.api.UserApi
import com.igalaxy.android.junior_test_userlist.data.database.UserDao
import com.igalaxy.android.junior_test_userlist.domain.UserRepository
import com.igalaxy.android.junior_test_userlist.domain.model.cache.UserCacheMapper
import com.igalaxy.android.junior_test_userlist.domain.model.domain.User
import com.igalaxy.android.junior_test_userlist.domain.model.network.UserNetworkMapper
import javax.inject.Inject

private const val TAG = "REPO1"

class UserRepositoryImpl @Inject constructor(
    private val userNetworkMapper: UserNetworkMapper,
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val userCacheMapper: UserCacheMapper,
) : UserRepository {
    override suspend fun getUserList(): List<User> {
        Log.d(TAG, "start")
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

    }

    override suspend fun getUser(id: Int): User? {

        //достаем юзера из бд и преобразовываем в User
        val user = userDao.getUser(id)?.let { userCacheMapper.mapFromEntity(it) }
        Log.i(TAG, "Got user with id ${user?.id}")
        return user
    }

    private suspend fun cacheUserList(userList: List<User>) {
        //добавляем в локальную бд
        userDao.addUserList(userList.map {
            userCacheMapper.mapToEntity(it)
        })
        Log.i(TAG, "cashed")
    }

}