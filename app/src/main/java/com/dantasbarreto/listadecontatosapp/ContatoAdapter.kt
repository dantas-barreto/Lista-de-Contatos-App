package com.dantasbarreto.listadecontatosapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContatoAdapter(
    private val context: Context,
    private val lista: List<ContatosVO>,
    private val onClick: ((Int) -> Unit)
) : RecyclerView.Adapter<ContatoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false)
        return ContatoViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato =lista[position]
        with(holder.itemView) {this
            tvNome.text = contato.nome
            tvTelefone.text = contato.telefone
            llItem.setOnClickListener { onClick(position) }
        }
    }
}

class ContatoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)