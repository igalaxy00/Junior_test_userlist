package com.igalaxy.android.junior_test_userlist.domain.domain

import com.igalaxy.android.junior_test_userlist.R

enum class EyeColor(val imgId: Int) {
    BROWN(R.drawable.eye_color_brown), GREEN(R.drawable.eye_color_green), BLUE(R.drawable.eye_color_blue);

    companion object {
        fun convertFromString(str: String) = when (str.lowercase()) {
            "blue" -> BLUE
            "green" -> GREEN
            "brown" -> BROWN
            else -> throw IllegalArgumentException("Wrong eye color")
        }

        fun convertToString(eyeColor: EyeColor) = when (eyeColor) {
            GREEN -> "green"
            BLUE -> "blue"
            BROWN -> "brown"
        }
    }


}