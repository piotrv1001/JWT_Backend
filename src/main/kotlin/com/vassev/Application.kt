package com.vassev

import com.vassev.data.user.MongoUserDataSource
import com.vassev.data.user.User
import io.ktor.server.application.*
import com.vassev.plugins.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val mongoPassword = System.getenv("MONGO_PASSWORD")
    val dbName = "ktor-auth"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://piotrv1001:$mongoPassword@cluster0.6bwasqg.mongodb.net/$dbName?retryWrites=true&w=majority"
    ).coroutine.getDatabase(dbName)
    val userDataSource = MongoUserDataSource(db)
    GlobalScope.launch {
        val user = User(
            username = "username",
            password = "password",
            salt = "salt"
        )
        userDataSource.insertUser(user)
    }
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
