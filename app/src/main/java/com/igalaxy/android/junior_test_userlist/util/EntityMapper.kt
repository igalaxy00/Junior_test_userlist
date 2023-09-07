package com.igalaxy.android.junior_test_userlist.util

import androidx.room.Entity

interface EntityMapper<Entity, Model> {

    fun mapFromEntity(entity: Entity): Model

    fun mapToEntity(model: Model): Entity
}