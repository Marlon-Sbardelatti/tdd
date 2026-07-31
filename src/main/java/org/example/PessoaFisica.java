package org.example;

public class PessoaFisica {
    private Cpf cpf;
    private boolean temDebito;

    public PessoaFisica(Cpf cpf, boolean temDebito) {
        this.cpf = cpf;
        this.temDebito = temDebito;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public boolean isTemDebito() {
        return temDebito;
    }

    public void setTemDebito(boolean temDebito) {
        this.temDebito = temDebito;
    }
}
