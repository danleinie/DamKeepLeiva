package com.salesianostriana.damkeepapp.api.responses

data class Nota(
    val contenido: String,
    val fechaCreacion: String,
    val fechaEdicion: String,
    val id: String,
    val titulo: String,
    val username: String
)