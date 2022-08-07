package com.vassev.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String,
    val userId: String
)