package com.igalaxy.android.junior_test_userlist.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Date

class UserTypeConverters {

    @TypeConverter
    fun fromRegisterDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toRegisterDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }

    @TypeConverter
    fun fromFriends(friends: List<Int>): String {
        return Json.encodeToString(friends)
    }

    @TypeConverter
    fun toFriends(friendsString: String): List<Int> {
        return Json.decodeFromString(friendsString)
    }

}