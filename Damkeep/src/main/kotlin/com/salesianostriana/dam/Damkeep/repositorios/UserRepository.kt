package com.salesianostriana.dam.Damkeep.repositorios

import com.salesianostriana.dam.Damkeep.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(username : String) : Optional<User>


}