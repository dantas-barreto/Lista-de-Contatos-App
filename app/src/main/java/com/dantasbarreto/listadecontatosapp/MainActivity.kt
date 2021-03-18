package com.dantasbarreto.listadecontatosapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : BaseActivity() {

    private var adapter:ContatoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geralListaDeContatos()
        setupToolBar(toolBar, "Lista de Contatos",false)
        setupRecyclerView()
        setupOnClicks()
    }

    private fun setupOnClicks() {
        fab.setOnClickListenert { onClickAdd() }
        ivBuscar.setOnClickListener { onClickBuscar() }
    }

    private fun setupRecyclerView() {
        recyclerView.LayoutManager = LinearLayoutManager(this)
        adapter = ContatoAdapter(this, ContatoSingleton.lista) { onClickItemRecyclerView(it) }
        recyclerView.adapter = adapter
    }

    private fun geralListaDeContatos() {
        ContatoSingleton.lista.add(ContatosVO(1, "Fulano", "(00) 9900-0001"))
        ContatoSingleton.lista.add(ContatosVO(2, "Beltrano", "(00) 9900-0002"))
        ContatoSingleton.lista.add(ContatosVO(3, "Ciclano", "(00) 9900-0003"))
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }

    private fun onClickAdd() {
        val intent = Intent(this, ContatoActivity::class.java)
        startActivity(intent)
    }

    private fun onClickItemRecyclerView(index: Int) {
        val intent = Intent(this, ContatoActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }

    private fun onClickBuscar() {
        val busca: String = etBuscar.text.toString()
        var listaFiltrada: List<ContatosVO> = ContatoSingleton.lista
        if (!busca.isNullOrEmpty()) {
            listaFiltrada = ContatoSingleton.lista.filter { contato ->
                if (contato.nome.toLowerCase().contains(busca.toLowerCase())) {
                    return@filter true
                }
                return@filter false
            }
        }
        adapter = ContatoAdapter(this, listaFiltrada) { onClickItemRecyclerView(it) }
        recyclerView.adapter = adapter
        Toast.makeText(this, "Buscando por $busca", Toast.LENGTH_SHORT).show()
    }
}