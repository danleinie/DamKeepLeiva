package com.salesianostriana.dam.Damkeep.dtos

import com.salesianostriana.dam.Damkeep.models.User
import java.util.*

data class UserDTO(
        var username : String,
        var fullName: String,
        var roles: String,
        val id: UUID? = null
)

fun User.toUserDTO() = UserDTO(username, fullName, roles.joinToString(), id)

data class CreateUserDTO(
        var username: String,
        var fullName: String,
        val password: String,
        val password2: String
)