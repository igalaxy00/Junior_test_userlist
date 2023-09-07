package com.igalaxy.android.junior_test_userlist.presentation.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igalaxy.android.junior_test_userlist.domain.domain.User
import com.igalaxy.android.junior_test_userlist.domain.usecase.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel
@Inject constructor(private val getUserListUseCase: GetUserListUseCase) : ViewModel() {

    //создаем лайвдату
    private val _userListData: MutableLiveData<List<User>> = MutableLiveData()

    //Публичная лайвдата доступна только для чтения
    //Приватная для изменения
    val userListLiveData: LiveData<List<User>>
        get() = _userListData

    fun getUserList() {
        viewModelScope.launch {
            _userListData.value = getUserListUseCase.execute()
        }
    }

}


