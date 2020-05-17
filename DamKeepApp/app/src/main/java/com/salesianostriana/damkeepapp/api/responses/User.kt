package com.salesianostriana.damkeepapp.api.responses

data class User(
    val fullName: String,
    val id: String,
    val roles: String,
    val username: String
)