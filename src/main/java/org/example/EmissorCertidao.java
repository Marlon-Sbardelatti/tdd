package org.example;

import java.util.Date;

public class EmissorCertidao {
    private final PessoaFisica usuarioAutenticado;

    public EmissorCertidao(PessoaFisica usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public static Certidao emitirCertidao(PessoaFisica usuario) {
       throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static boolean validarCertidao(Long numeroCertidao, String assinatura, Date data) {
        return false;
    }
}
