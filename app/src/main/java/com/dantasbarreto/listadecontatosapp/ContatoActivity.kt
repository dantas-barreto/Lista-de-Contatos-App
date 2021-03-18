package com.dantasbarreto.listadecontatosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dantasbarreto.listadecontatosapp.R
import com.dantasbarreto.listadecontatosapp.bases.BaseActivity
import com.dantasbarreto.listadecontatosapp.feature.listacontatos.model.ContatosVO
import com.dantasbarreto.listadecontatosapp.singleton.ContatoSingleton
import kotlinx.android.synthetic.main.activity_contato.*
import.kotlinx.android.synthetic.main.activity_contato.toolBar

class ContatoActivity : BaseActivity() {

    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)
        setupToolBar(toolBar, "Contato", true)
        setupContato()
        btnSalvarConato.setOnClickListener { onClickSalvarContato() }
    }

    private fun setupContato() {
        index = intent.getIntExtra("index", -1)
        if (index == -1) {
            btnExcluirContato.visibility = View.GONE
            return
        }
        etNome.setText(ContatoSingleton.lista[index].nome)
        etTelefone.setText(ContatoSingleton.lista[index].telefone)
    }

    private fun onClickSalvarContato() {
        val nome: String = etNome.text.toString()
        val telefone: String = etTelefone.text.toString()
        val contato = COntatosVO(
            0,
            nome,
            telefone
        )
        if (index == -1) {
            ContatoSingleton.lista.add(contato)
        } else {
            ContatoSingleton.lista.set(index, contato)
        }
        finish()
    }

    fun onClickExcluirContato(view: View) {
        if (index > -1) {
            ContatoSingleton.lista.removeAt(index)
            finish()
        }
    }
}