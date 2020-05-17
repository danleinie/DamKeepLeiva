
package com.salesianostriana.damkeepapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.salesianostriana.damkeepapp.R
import com.salesianostriana.damkeepapp.api.requests.NotaEditRequest
import com.salesianostriana.damkeepapp.di.MyApp
import com.salesianostriana.damkeepapp.viewmodels.NotaViewModel
import javax.inject.Inject

class AddNotaActivity : AppCompatActivity() {

    @Inject
    lateinit var notaViewModel: NotaViewModel
    lateinit var titulo : TextView
    lateinit var contenido : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nota)
        (applicationContext as MyApp).appComponent.inject(this)

        titulo = findViewById(R.id.tituloAdd)
        contenido = findViewById(R.id.contenidoAdd)

    }

    override fun onBackPressed() {
        if (!titulo.text.toString().isEmpty() || !contenido.text.isEmpty()){
            var resul = NotaEditRequest(titulo.text.toString(),contenido.text.toString())
            notaViewModel.addNota(resul).observeForever(Observer {
                Log.i("aquiii","" + it)
            })
        }
        super.onBackPressed()
    }
}
