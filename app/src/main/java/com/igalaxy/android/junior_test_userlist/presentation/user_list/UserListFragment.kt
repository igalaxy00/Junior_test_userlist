package com.igalaxy.android.junior_test_userlist.presentation.user_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.igalaxy.android.junior_test_userlist.R
import com.igalaxy.android.junior_test_userlist.databinding.FragmentUserListBinding
import com.igalaxy.android.junior_test_userlist.domain.domain.User

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private val userListViewModel: UserListViewModel by activityViewModels()
    private var _binding: FragmentUserListBinding? = null

    private lateinit var userListRecyclerViewAdapter: UserListAdapter
    private lateinit var userListSwipeRefreshLayout: SwipeRefreshLayout

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TESTT", "Test")
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.userListRecyclerView.layoutManager = LinearLayoutManager(context)
        userListRecyclerViewAdapter = UserListAdapter(listOf())
        binding.userListRecyclerView.adapter = userListRecyclerViewAdapter

        binding.userListSwipeRefreshLayout.setOnRefreshListener {

            binding.userListSwipeRefreshLayout.isRefreshing = false

        }


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.getUserList()
        //объект viewLifecycleOwner в качестве параметра, который указывает, что наблюдатель будет автоматически удален при уничтожении фрагмента.
        userListViewModel.userListLiveData.observe(viewLifecycleOwner) { userList ->
            userList?.let { currentUserList ->
                Log.i("UI", "Got ${currentUserList.size} users")
                //передаем список котоорый получили в лайвдате в апдейтUI
                updateUI(currentUserList)
            }
        }
    }

    private fun updateUI(userList: List<User>) {
        //получаем измененный список и вызываем адаптер который создает холдер
        userListRecyclerViewAdapter = UserListAdapter(userList)
        //связываем адаптер с тем что написали мы
        binding.userListRecyclerView.adapter = userListRecyclerViewAdapter
        Log.i("UI", "UI was updated")
    }

    fun RefreshUi(){

    }
}