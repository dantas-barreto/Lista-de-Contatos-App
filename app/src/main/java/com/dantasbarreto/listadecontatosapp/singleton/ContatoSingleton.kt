package com.dantasbarreto.listadecontatosapp.singleton

import com.dantasbarreto.listadecontatosapp.features.listacontatos.model.ContatosVO

object ContatoSingleton {
    var lista: MutableList<ContatosVO> = mutableListOf()
}