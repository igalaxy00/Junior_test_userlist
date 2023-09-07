package com.igalaxy.android.junior_test_userlist.presentation.user_deatails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.igalaxy.android.junior_test_userlist.R
import com.igalaxy.android.junior_test_userlist.databinding.FragmentUserDetailsBinding
import com.igalaxy.android.junior_test_userlist.domain.domain.User
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    private val userDetailsViewModel: UserDetailsViewModel by activityViewModels()
    private val binding get() = _binding!!
    private val args: UserDetailsFragmentArgs by navArgs()

    private val dateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH).apply {
        timeZone = UTC_TIMEZONE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.userFriendListRecyclerView.layoutManager = LinearLayoutManager(context)

        //поддержка кнопки назад
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listeners()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUserLiveDataPair = userDetailsViewModel.getUser(args.userId)
        val currentUserLiveData = currentUserLiveDataPair.first

        currentUserLiveData.observe(viewLifecycleOwner) { currentUser ->
            if (currentUser != null) {
                updateUI(currentUser)
            }
        }


        val currentUserFriendsLiveData = currentUserLiveDataPair.second
        currentUserFriendsLiveData.observe(viewLifecycleOwner) { friendsList ->
            binding.userFriendListRecyclerView.adapter = UserFriendsAdapter(friendsList)
        }
    }

    fun updateUI(currentUser: User) {
        with(binding) {
            userNameTextView.text = currentUser.name
            userAgeTextView.text = getString(R.string.user_age_string, currentUser.age)
            userPhoneButton.text = currentUser.phone
            userEmailButton.text = currentUser.email
            userRegisterDateTextView.text = getString(
                R.string.user_registered_string,
                dateFormat.format(currentUser.registerDate).toString()
            )
            userLocationButton.text = getString(
                R.string.user_coordinates_string,
                currentUser.latitude,
                currentUser.longitude
            )
            userFavoriteFruitImageView.setImageResource(currentUser.favoriteFruit.imgId)
            userEyeColorImageView.setImageResource(currentUser.eyeColor.imgId)
        }

    }

    fun listeners() {
        binding.userEmailButton.setOnClickListener {

        }
        binding.userPhoneButton.setOnClickListener {

        }
        binding.userLocationButton.setOnClickListener {

        }

    }


    companion object {
        private val UTC_TIMEZONE = TimeZone.getTimeZone("UTC")
    }
}