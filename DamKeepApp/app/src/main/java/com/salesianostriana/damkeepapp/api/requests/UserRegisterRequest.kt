package com.salesianostriana.damkeepapp.api.requests

data class UserRegisterRequest(
    val fullName: String,
    val password: String,
    val password2: String,
    val username: String
)