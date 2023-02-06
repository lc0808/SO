package com.so.modelos;

public class Processo implements Comparable<Processo>{
    static int nProcessos = 0;

    private int id;
    private int status;
    private int prioridade;
    private String exception;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getException() {
        return this.exception;
    }

    public void setExceeption(String exception) {
        this.exception = exception;
    }

    public boolean hasException() {
        return this.exception != null;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public int compareTo(Processo processo) {
        if(this.prioridade < processo.prioridade){
            return 1;
        } else if(this.prioridade > processo.prioridade) {
            return -1;
        }
        return 0;
    }

    public Processo(int prioridade) {
        this.id = nProcessos;
        this.status = 0;
        this.prioridade = prioridade;
        nProcessos++;
    }

    @Override
    public String toString() {
        return "Processo{" +
                "id=" + id +
                ", status=" + status +
                ", prioridade=" + prioridade +
                ", exception='" + exception + '\'' +
                '}';
    }
}
