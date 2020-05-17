package com.salesianostriana.damkeepapp.ui.listadonotas

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.salesianostriana.damkeepapp.R
import com.salesianostriana.damkeepapp.api.responses.Nota
import com.salesianostriana.damkeepapp.di.MyApp
import com.salesianostriana.damkeepapp.viewmodels.NotaViewModel
import javax.inject.Inject

class NotaFragment : Fragment() {

    @Inject lateinit var notaViewModel: NotaViewModel
    private var columnCount = 2
    private var listaNotas = ArrayList<Nota>()
    private lateinit var myAdapter: MyNotaRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nota_list, container, false)
        (activity?.applicationContext as MyApp).appComponent.inject(this)

        myAdapter = MyNotaRecyclerViewAdapter(listaNotas)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = myAdapter
            }
        }

        notaViewModel.getNotes().observe(viewLifecycleOwner, Observer {
            listaNotas.addAll(it)
            myAdapter.notifyDataSetChanged()
        })
        return view
    }

    override fun onResume() {
        listaNotas.clear()
        myAdapter.notifyDataSetChanged()
        notaViewModel.getNotes().observe(viewLifecycleOwner, Observer {
            listaNotas.addAll(it)
            myAdapter.notifyDataSetChanged()
        })
        super.onResume()
    }
}
