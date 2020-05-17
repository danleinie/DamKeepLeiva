package com.salesianostriana.damkeepapp.ui.listadonotas

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.salesianostriana.damkeepapp.R
import com.salesianostriana.damkeepapp.api.responses.Nota
import com.salesianostriana.damkeepapp.di.MyApp
import com.salesianostriana.damkeepapp.ui.DetalleActivity
import com.salesianostriana.damkeepapp.viewmodels.NotaViewModel

import kotlinx.android.synthetic.main.fragment_nota.view.*

class MyNotaRecyclerViewAdapter(
    private val mValues: List<Nota>
) : RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Nota
            //Toast.makeText(MyApp.instance,item.titulo,Toast.LENGTH_SHORT).show()
            var i = Intent(MyApp.instance,DetalleActivity::class.java)
            i.putExtra("ID",item.id)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            MyApp.instance.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.titulo.text = item.titulo
        //holder.mContentView.text = item.content

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val titulo: TextView = mView.tituloNota

    }
}
