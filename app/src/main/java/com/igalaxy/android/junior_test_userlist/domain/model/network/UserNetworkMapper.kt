package com.igalaxy.android.junior_test_userlist.domain.model.network

import com.igalaxy.android.junior_test_userlist.domain.model.domain.EyeColor
import com.igalaxy.android.junior_test_userlist.domain.model.domain.Fruit
import com.igalaxy.android.junior_test_userlist.domain.model.domain.User
import com.igalaxy.android.junior_test_userlist.util.EntityMapper
import javax.inject.Inject

class UserNetworkMapper @Inject constructor(): EntityMapper<UserNetworkEntity, User> {

    override fun mapFromEntity(entity: UserNetworkEntity): User {

        val friends: List<Int> = entity.friends.map {
            it.id
        }
        return User(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            isActive = entity.isActive,
            age = entity.age,
            company = entity.company,
            phone = entity.phone,
            address = entity.address,
            about = entity.about,
            eyeColor = EyeColor.convertFromString(entity.eyeColor),
            favoriteFruit = Fruit.convertFromString(entity.favoriteFruit),
            registerDate = entity.registered,
            latitude = entity.latitude,
            longitude = entity.longitude,
            friends = friends
        )
    }

     override fun mapToEntity(model: User): UserNetworkEntity {

         val eyeColor = EyeColor.convertToString(model.eyeColor)

         val favoriteFruit = Fruit.convertToString(model.favoriteFruit)

         val friends: List<Friend> = model.friends.map {
             Friend(it)
         }

         return UserNetworkEntity(
             id = model.id,
             name = model.name,
             email = model.email,
             isActive = model.isActive,
             age = model.age,
             company = model.company,
             phone = model.phone,
             address = model.address,
             about = model.about,
             eyeColor = eyeColor,
             favoriteFruit = favoriteFruit,
             registered = model.registerDate,
             latitude = model.latitude,
             longitude = model.longitude,
             friends = friends,
             balance = "",
             tags = listOf()
         )
     }

    //получаем список юзеров из инета
    fun mapFromEntityList(entities: List<UserNetworkEntity>): List<User> {
        //каждый элемент списка юзеров из инта мы конвертим в нашего юзера и возвращаем в тот же массив
        return entities.map { entity ->
            mapFromEntity(entity)
        }
    }
}