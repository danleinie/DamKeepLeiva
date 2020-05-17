package com.salesianostriana.dam.Damkeep.repositorios

import com.salesianostriana.dam.Damkeep.models.Nota
import com.salesianostriana.dam.Damkeep.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface NotaRepository : JpaRepository<Nota, UUID>{



    //@Query("select s from Nota s where s.usuario = :usuario")
    //@Query("select s from Nota s left join fetch s.usuario where s.username = :username")
    //@Query("select distinct n from Nota n right join fetch n.usuario where n.usuario.id = :id")
    fun findByUsuario(user: User) : List<Nota>

}

