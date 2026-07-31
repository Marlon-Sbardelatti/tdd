package org.example;

import java.util.Date;

public class Certidao {
    private Long  numeroCertidao;
    private String assinaturaDigital;
    private Date  dataEmissao;
    private Cpf cpfPortador;

    public Certidao(Long numeroCertidao, String assinaturaDigital, Date dataEmissao, Cpf cpfPortador) {
        this.numeroCertidao = numeroCertidao;
        this.assinaturaDigital = assinaturaDigital;
        this.dataEmissao = dataEmissao;
        this.cpfPortador = cpfPortador;
    }

    public Long getNumeroCertidao() {
        return numeroCertidao;
    }

    public void setNumeroCertidao(Long numeroCertidao) {
        this.numeroCertidao = numeroCertidao;
    }

    public String getAssinaturaDigital() {
        return assinaturaDigital;
    }

    public void setAssinaturaDigital(String assinaturaDigital) {
        this.assinaturaDigital = assinaturaDigital;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Cpf getCpfPortador() {
        return cpfPortador;
    }

    public void setCpfPortador(Cpf cpfPortador) {
        this.cpfPortador = cpfPortador;
    }

    public boolean isValida() {
        return numeroCertidao != null
                && assinaturaDigital != null && !assinaturaDigital.isBlank()
                && dataEmissao != null
                && cpfPortador != null && Cpf.isValido(cpfPortador.getValor());
    }
}
