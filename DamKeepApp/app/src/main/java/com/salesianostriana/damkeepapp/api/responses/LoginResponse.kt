package com.salesianostriana.damkeepapp.api.responses

data class LoginResponse(
    val token: String,
    val user: User
)