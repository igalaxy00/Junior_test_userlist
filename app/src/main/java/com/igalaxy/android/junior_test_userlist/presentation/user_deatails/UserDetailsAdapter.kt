package com.igalaxy.android.junior_test_userlist.presentation.user_deatails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.igalaxy.android.junior_test_userlist.R
import com.igalaxy.android.junior_test_userlist.domain.model.domain.User

class UserFriendsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
    private lateinit var user: User
    private val userNameTextView: TextView = view.findViewById(R.id.user_name_button)
    private val userEmailTextView: TextView = view.findViewById(R.id.user_email_button)
    private val isActive: ImageView = view.findViewById(R.id.user_is_active_image_view)


    init {
        itemView.setOnClickListener(this)
    }

    fun bind(user: User) {
        //расписываем какие элементы чему приравниваются в холдере
        this.user = user

        userNameTextView.text = user.name
        userEmailTextView.text = user.email

        if (user.isActive) {
            isActive.setImageResource(R.drawable.is_active_true)
        } else {
            isActive.setImageResource(R.drawable.is_active_false)
        }

    }

    override fun onClick(p0: View) {
        if (user.isActive) {
            val userId = user.id

            val action = UserDetailsFragmentDirections.actionUserDetailsFragmentSelf(userId)
            p0.findNavController().navigate(action)

        } else {
            val snackbar = Snackbar.make(p0, R.string.user_is_off, Snackbar.LENGTH_SHORT)
            //snackbar.view.setBackgroundColor(R.color.md_theme_dark_background)
            snackbar.show()
        }
    }

}


class UserFriendsAdapter(private val friends: List<User>) :
    RecyclerView.Adapter<UserFriendsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFriendsViewHolder {
        //когда нам нужен новый холдер мы создаем его
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        //создаем экземпляр привязки (binding)

        return UserFriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserFriendsViewHolder, position: Int) {
        val user = friends[position] // Получение человека из списка данных по позиции
        //получаем контекст
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return friends.size
    }
}

