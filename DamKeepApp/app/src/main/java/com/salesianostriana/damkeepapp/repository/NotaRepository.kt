package com.salesianostriana.damkeepapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.salesianostriana.damkeepapp.api.NotaService
import com.salesianostriana.damkeepapp.api.requests.NotaEditRequest
import com.salesianostriana.damkeepapp.api.responses.Nota
import com.salesianostriana.damkeepapp.di.MyApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotaRepository @Inject constructor(var notaService: NotaService) {

    fun getNotes(): MutableLiveData<List<Nota>> {

        var notas : MutableLiveData<List<Nota>> = MutableLiveData()

        val call: Call<List<Nota>> = notaService.getNotas()
        call.enqueue(object : Callback<List<Nota>>{
            override fun onFailure(call: Call<List<Nota>>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de notas: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errornotas", t.message)
            }

            override fun onResponse(call: Call<List<Nota>>, response: Response<List<Nota>>) {
                if (response.isSuccessful) notas.value = response.body()
            }

        })

        return notas
    }

    fun findById(id : String) : MutableLiveData<Nota>{
        var nota = MutableLiveData<Nota>()

        val call : Call<Nota> = notaService.findNotaById(id)
        call.enqueue(object : Callback<Nota>{
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de nota by id: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errornotas", t.message)
            }

            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if (response.isSuccessful) nota.value = response.body()
            }

        })

        return nota
    }

    fun editNota(id : String, nota : NotaEditRequest) : MutableLiveData<Nota>{
        var result = MutableLiveData<Nota>()

        val call : Call<Nota> = notaService.editNota(id,nota)
        call.enqueue(object : Callback<Nota>{
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de editar nota: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errornotas", t.message)
            }

            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if (response.isSuccessful) result.value = response.body()
            }

        })

        return result
    }

    fun addNota(nota : NotaEditRequest) : MutableLiveData<Nota>{
        var result = MutableLiveData<Nota>()

        val call : Call<Nota> = notaService.addNota(nota)
        call.enqueue(object : Callback<Nota>{
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de añadir nota: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errornotas", t.message)
            }

            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if (response.isSuccessful) result.value = response.body()
            }

        })

        return result
    }

    fun deleteNota(id:String){
        val call : Call<Void> = notaService.deleteNota(id)
        call.enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de añadir nota: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errornotas", t.message)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    Toast.makeText(MyApp.instance, "Nota borada", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}