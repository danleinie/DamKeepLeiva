package com.salesianostriana.damkeepapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.damkeepapp.api.requests.NotaEditRequest
import com.salesianostriana.damkeepapp.api.responses.Nota
import com.salesianostriana.damkeepapp.repository.NotaRepository
import java.util.*
import javax.inject.Inject

class NotaViewModel @Inject constructor( var notaRepository: NotaRepository): ViewModel() {

    fun getNotes() : LiveData<List<Nota>> =  notaRepository.getNotes()
    fun findNotaById(id : String) : LiveData<Nota> = notaRepository.findById(id)
    fun editNota(id:String, nota:NotaEditRequest) = notaRepository.editNota(id,nota)
    fun addNota(nota:NotaEditRequest) = notaRepository.addNota(nota)
    fun deleteNota(id:String) = notaRepository.deleteNota(id)
}