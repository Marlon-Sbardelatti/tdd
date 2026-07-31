package org.example;

public class Cpf {
    private String valor;

    public Cpf(String valor) {
       this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public static boolean isValido(String cpf) {
        return false;
    }
}
