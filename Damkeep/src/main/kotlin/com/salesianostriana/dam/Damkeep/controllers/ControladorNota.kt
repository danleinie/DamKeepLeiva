package com.salesianostriana.dam.Damkeep.controllers

import com.salesianostriana.dam.Damkeep.dtos.NotaDto
import com.salesianostriana.dam.Damkeep.dtos.NuevaNotaDto
import com.salesianostriana.dam.Damkeep.dtos.toNotaDto
import com.salesianostriana.dam.Damkeep.models.Nota
import com.salesianostriana.dam.Damkeep.models.User
import com.salesianostriana.dam.Damkeep.repositorios.NotaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/notas")
class ControladorNota(val notaRepository: NotaRepository){

    @GetMapping("/")
    fun all(@AuthenticationPrincipal user : User): List<NotaDto> {
        var result : List<Nota>
        //result = notaRepository.findAll()

        result = notaRepository.findByUsuario(user)

        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"No hay ninguna nota")
        return result.map { nota -> nota.toNotaDto() }
    }

    @GetMapping("/{id}")
    fun findNotaById(@PathVariable id :UUID) : NotaDto{
        return notaRepository.findById(id).map { nota -> nota.toNotaDto() }
                .orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
                }
    }

    @PostMapping("/")
    fun createNota(@RequestBody nuevaNota : NuevaNotaDto,@AuthenticationPrincipal user : User) : ResponseEntity<NotaDto> {

        var resul = nuevaNota.toNota()
        resul.usuario = user
        return ResponseEntity.status(HttpStatus.CREATED).body(notaRepository.save(resul).toNotaDto())
    }

    @PutMapping("/{id}")
    fun editNota(@RequestBody notaEditada : NuevaNotaDto, @PathVariable id: UUID) : NotaDto {
        return notaRepository.findById(id)
                .map { nota ->
                    val notaActualizada : Nota =
                            nota.copy(
                                    titulo = notaEditada.titulo,
                                    contenido = notaEditada.contenido,
                                    fechaEdicion = LocalDateTime.now())
                    notaRepository.save(notaActualizada).toNotaDto()
                }.orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
                }

        // Forma fea de hacerlo
        /*
         var notaEncontrada : Nota = notaRepository.findById(id).orElseThrow{
            ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id")
        }

        with(notaEditada){
            notaEncontrada.titulo = titulo
            notaEncontrada.contenido = contenido
            notaEncontrada.fechaEdicion = LocalDateTime.now()
        }
        return notaRepository.save(notaEncontrada).toNotaDto()
         */

    }

    @DeleteMapping("/{id}")
    fun deleteNota(@PathVariable id :UUID) : ResponseEntity<Void>{
        notaRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}