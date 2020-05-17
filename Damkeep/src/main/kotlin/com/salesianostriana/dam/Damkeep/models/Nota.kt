package com.salesianostriana.dam.Damkeep.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Nota(
        var titulo : String,
        var contenido : String,
        val fechaCreacion: LocalDateTime? = LocalDateTime.now(),
        var fechaEdicion : LocalDateTime? = LocalDateTime.now(),
        @JsonBackReference @ManyToOne var usuario : User?= null,
        @Id @GeneratedValue val id: UUID? = null
)