package com.igalaxy.android.junior_test_userlist.presentation.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.igalaxy.android.junior_test_userlist.R
import com.igalaxy.android.junior_test_userlist.databinding.UserListItemBinding
import com.igalaxy.android.junior_test_userlist.domain.domain.User
import hilt_aggregated_deps._com_igalaxy_android_junior_test_userlist_UserApplication_GeneratedInjector

//реализует viewholder с ссылкой на корневой элемент  макета
class UserViewHolder(private val binding: UserListItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private lateinit var user: User


    init {
        itemView.setOnClickListener(this)
    }

    fun bind(user: User) {
        //расписываем какие элементы чему приравниваются в холдере
        this.user = user
        with(binding) {
            userNameButton.text = user.name
            userEmailButton.text = user.email

            if (user.isActive) {
                userIsActiveImageView.setImageResource(R.drawable.is_active_true)
            } else {
                userIsActiveImageView.setImageResource(R.drawable.is_active_false)
            }
        }
    }

    override fun onClick(p0: View) {
        if(user.isActive){
            val userId = user.id
            val action =UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(userId)
            p0.findNavController().navigate(action)

        }else {
            val snackbar = Snackbar.make(p0, R.string.user_is_off, Snackbar.LENGTH_SHORT)
            //snackbar.view.setBackgroundColor(R.color.md_theme_dark_background)
            snackbar.show()
        }
    }

}


class UserListAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        //когда нам нужен новый холдер мы создаем его
        val inflater = LayoutInflater.from(parent.context)
        //создаем экземпляр привязки (binding)
        val binding = UserListItemBinding.inflate(inflater, parent, false)


        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position] // Получение человека из списка данных по позиции
        //получаем контекст
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}