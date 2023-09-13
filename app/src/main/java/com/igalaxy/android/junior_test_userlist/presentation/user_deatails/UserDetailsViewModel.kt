package com.igalaxy.android.junior_test_userlist.presentation.user_deatails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igalaxy.android.junior_test_userlist.domain.model.domain.User
import com.igalaxy.android.junior_test_userlist.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserDetailsViewModel
@Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    fun getUser(id: Int): Pair<LiveData<User?>, LiveData<List<User>>> {
        val userLiveData: MutableLiveData<User?> = MutableLiveData()
        val userFriendsListLiveData: MutableLiveData<List<User>> = MutableLiveData()
        val userFriendsList: MutableList<User> = mutableListOf()
        viewModelScope.launch {
            val currentUser = getUserUseCase.execute(id)
            userLiveData.value = currentUser
            //получаем список id друзей
            Log.d("REPO1", currentUser?.friends.toString())
            currentUser?.friends?.forEach { friendId ->
                //по id друзей добавляем самих user в список
                getUserUseCase.execute(friendId)?.let { userFriendsList.add(it) }
            }

            userFriendsListLiveData.value = userFriendsList
        }
        return Pair(userLiveData, userFriendsListLiveData)

    }

}