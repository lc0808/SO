package com.so.controladores;

import com.so.modelos.Processo;
import com.so.provedor.Interrupcoes;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.so.utilidades.Utilidades.COR_VERMELHA;
import static com.so.utilidades.Utilidades.RESETA_COR;

public class ProcessoSimplesController extends Thread {
    private Interrupcoes provedorInterrupcao;
    
    private Random nAleatorio = new Random();
    private Processo processo;
    private boolean hasException = false;

    public ProcessoSimplesController(Processo processo){
        provedorInterrupcao = new Interrupcoes();
        this.processo = processo;
        processo.setStatus(1);
    }

    public void exec() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000 + 1));
            int numAleatorio = nAleatorio.nextInt(3);
            if (numAleatorio == 1) {
                String error = provedorInterrupcao.getInterrupcaoAleatoria();
                processo.setExceeption(error);
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(COR_VERMELHA + "- - - - - PROCESSO INTERROMPIDO - - - - -" + RESETA_COR);
            System.out.println(COR_VERMELHA + "ID DO PROCESSO: " + RESETA_COR + processo.getId());
            System.out.println(COR_VERMELHA + "Motivo: " + RESETA_COR + processo.getException());
            System.out.println(COR_VERMELHA + "- - - - - - - - - - - - - - - - - - - - -" + RESETA_COR);
            hasException = true;
        }
    }

    public Processo getProcess() {
        return processo;
    }

    public boolean hasException() {
        return hasException;
    }
}
