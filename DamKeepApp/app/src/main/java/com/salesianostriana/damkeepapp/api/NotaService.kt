package com.salesianostriana.damkeepapp.api

import com.salesianostriana.damkeepapp.api.requests.NotaEditRequest
import com.salesianostriana.damkeepapp.api.responses.Nota
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface NotaService {

    @GET("notas/")
    fun getNotas() : Call<List<Nota>>

    @GET("notas/{id}")
    fun findNotaById(@Path("id") id: String) : Call<Nota>

    @PUT("notas/{id}")
    fun editNota(@Path("id") id: String, @Body nota: NotaEditRequest) : Call<Nota>

    @POST("notas/")
    fun addNota(@Body nota : NotaEditRequest) : Call<Nota>

    @DELETE("notas/{id}")
    fun deleteNota(@Path("id") id : String) : Call<Void>
}