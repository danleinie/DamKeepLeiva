package com.salesianostriana.damkeepapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.salesianostriana.damkeepapp.R
import com.salesianostriana.damkeepapp.api.requests.NotaEditRequest
import com.salesianostriana.damkeepapp.di.MyApp
import com.salesianostriana.damkeepapp.viewmodels.NotaViewModel
import javax.inject.Inject


class DetalleActivity : AppCompatActivity() {

    @Inject lateinit var notaViewModel: NotaViewModel
    lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        (applicationContext as MyApp).appComponent.inject(this)

        val titulo : TextView = findViewById(R.id.tituloDetalle)
        val fecha : TextView = findViewById(R.id.fechaDetalle)
        val contenido : EditText = findViewById(R.id.contenidoNota)

        id = intent.extras?.get("ID").toString()

        notaViewModel.findNotaById(id).observeForever(Observer {
            titulo.text = it.titulo
            fecha.text = it.fechaEdicion
            contenido.setText(it.contenido)
        })

        contenido.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var notaEditada = NotaEditRequest(titulo.text.toString(),s.toString())
                notaViewModel.editNota(id,notaEditada).observeForever(Observer {
                    Log.i("holaa", it.contenido)
                })
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.borrarNota -> {
                notaViewModel.deleteNota(id)
                finish()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}
