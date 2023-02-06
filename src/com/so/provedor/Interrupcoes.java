package com.so.provedor;

import java.util.ArrayList;
import java.util.List;

public class Interrupcoes {
    private List<String> listaInterrupcoes;

    public Interrupcoes(){
        listaInterrupcoes = new ArrayList<String>();
        criarListaInterrupcoes();
    }


    public String getInterrupcaoAleatoria() {
        return listaInterrupcoes.get(getNumeroAleatorio(listaInterrupcoes.size()-1));
    }
    
    private void criarListaInterrupcoes(){
        listaInterrupcoes.add("Aguardando disco rigido");
        listaInterrupcoes.add("IO do mouse");
        listaInterrupcoes.add("IO do teclado");
    }

    private int getNumeroAleatorio(int length){
        double randomDouble = (Math.random()*((length)));
        return (int) Math.round(randomDouble);
    }
}
