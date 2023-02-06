package com.so.controladores;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.so.modelos.Processo;

import static com.so.utilidades.Utilidades.*;

public class ProcessoGeralController {
    private List<Processo> processos = new ArrayList<>();
    private List<Processo> processosInterrompidos = new ArrayList<>();
    private List<Processo> processosProntosParaExecucao = new ArrayList<>();
    private List<Processo> processosEmExecucao = new ArrayList<>();
    private List<Processo> processosFinalizados = new ArrayList<>();
    
    private Random nAleatorio = new Random();

    private Scanner input = new Scanner(System.in);
    
    public ProcessoGeralController(){}

    public void exec() {
        inicializarProcessos();
        int escolha;

        do {

            if (!processosProntosParaExecucao.isEmpty()) {
                Processo processoIndex0 = processosProntosParaExecucao.get(0);
                if (processosInterrompidos.isEmpty()) {
                    execProcesso(processoIndex0);
                } else {
                    Processo interrompidoPorAltaPrioridade = interromperPorPrioridade();
                    if (processoIndex0.compareTo(interrompidoPorAltaPrioridade) > 0) {
                        execInterrompida(interrompidoPorAltaPrioridade);
                    } else {
                        execProcesso(processoIndex0);
                    }
                }

            }
            else if (processosInterrompidos.isEmpty()) {
                System.out.println(COR_ROXA + "→ TODOS OS PROCESSOS FORAM FINZALIADOS COM SUCESSO!" + RESETA_COR);
                break;
            }
            else {
                execInterrompida(interromperPorPrioridade());
            }

            System.out.println(COR_ROXA + "\n\n - - - - - - - - RELATÓRIO - - - - - - - - " + RESETA_COR);

            System.out.print(COR_AMARELO + "Processos na fila de execução: " + RESETA_COR);
            for (Processo processo : processosProntosParaExecucao) {
                System.out.print(processo.getId() + "  ");

            }

            System.out.println("\nTOTAL: " + processosProntosParaExecucao.size());

            System.out.print(COR_VERDE + "\nProcessos finalizados: " + RESETA_COR);

            for (Processo processo : processosFinalizados) {
                System.out.print(processo.getId() + "  ");
            }

            System.out.println("\nTotal: " + processosFinalizados.size());

            System.out.print(COR_VERMELHA + "\nInterrupções: \n" + RESETA_COR);

            for (Processo processo : processosInterrompidos) {
                System.out.println("Id do processo: " + processo.getId() + "   Motivo: " + processo.getException() );
            }

            System.out.println("Total: " + processosInterrompidos.size());

            System.out.println(COR_ROXA + " - - - - - - - - - - - - - - - - - - - - - " + RESETA_COR);

            System.out.println(COR_ROXA + "→ Prosseguir para próxima etapa?" + RESETA_COR + "\n0- Encerrar  1- Prosseguir" + RESETA_COR );
            escolha = input.nextInt();
            System.out.println("\n\n\n");
        } while(escolha != 0);
    }

    private void inicializarProcessos() {
        for(int i=0; i<10; i++){
            Processo processo = new Processo(nAleatorio.nextInt(20));
            processos.add(processo);
            processosProntosParaExecucao.add(processo);
            Collections.sort(processos);
            Collections.sort(processosProntosParaExecucao);
        }
    }

    private Processo interromperPorPrioridade() {
        Collections.sort(processosInterrompidos);
        return processosInterrompidos.get(0);
    }

    private void execProcesso(Processo processo) {
        processosProntosParaExecucao.remove(processo);
        processosEmExecucao.add(processo);
        ProcessoSimplesController processoUnicoController = new ProcessoSimplesController(processo);
        processoUnicoController.exec();
        try {
            processoUnicoController.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (processoUnicoController.hasException()) {
            Processo processoWithException = processoUnicoController.getProcess();
            processosEmExecucao.remove(processo);
            processosInterrompidos.add(processoWithException);
        }else{
            processosEmExecucao.remove(processo);
            processosFinalizados.add(processo);
        }
    }

    private void execInterrompida(Processo processo) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000 ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        processosInterrompidos.remove(processo);
        processosProntosParaExecucao.add(processo);
    }
}
