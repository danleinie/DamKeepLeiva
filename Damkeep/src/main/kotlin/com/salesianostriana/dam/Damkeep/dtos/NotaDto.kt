package com.salesianostriana.dam.Damkeep.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import com.salesianostriana.dam.Damkeep.models.Nota
import com.salesianostriana.dam.Damkeep.models.User
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class NotaDto(
        val id : UUID?,
        val titulo : String,
        val contenido : String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        val fechaCreacion : LocalDateTime?,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        val fechaEdicion : LocalDateTime?,
        val username : String?
        )

fun Nota.toNotaDto() = NotaDto(id,titulo,contenido,fechaCreacion,fechaEdicion, usuario?.username)

data class NuevaNotaDto(
        val titulo: String,
        val contenido: String
){
        fun toNota() = Nota(titulo,contenido)
}
