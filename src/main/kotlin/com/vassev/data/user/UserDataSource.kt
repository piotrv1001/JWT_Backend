package com.vassev.data.user

import org.bson.types.ObjectId

interface UserDataSource {

    suspend fun getUserById(id: ObjectId): User?

    suspend fun getUserByUsername(username: String): User?

    suspend fun insertUser(user: User): Boolean
}