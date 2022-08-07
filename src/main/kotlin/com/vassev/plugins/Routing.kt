package com.vassev.plugins

import com.vassev.authenticate
import com.vassev.data.user.UserDataSource
import com.vassev.security.hashing.HashingService
import com.vassev.security.token.TokenConfig
import com.vassev.security.token.TokenService
import com.vassev.signIn
import com.vassev.signUp
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {

    routing {
        signIn(hashingService, userDataSource, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
    }
}
