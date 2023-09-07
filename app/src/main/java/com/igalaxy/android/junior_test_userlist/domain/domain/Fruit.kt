package com.igalaxy.android.junior_test_userlist.domain.domain

import com.igalaxy.android.junior_test_userlist.R

enum class Fruit(val imgId: Int) {
    APPLE(R.drawable.apple), BANANA(R.drawable.banana), STRAWBERRY(R.drawable.strawberry);

    companion object {
        fun convertFromString(str: String) = when (str.lowercase()) {
            "apple" -> APPLE
            "banana" -> BANANA
            "strawberry" -> STRAWBERRY
            else -> throw IllegalArgumentException("Wrong fruit")
        }

        fun convertToString(fruit: Fruit) = when (fruit) {
            APPLE -> "apple"
            BANANA -> "banana"
            STRAWBERRY -> "strawberry"
        }
    }


}