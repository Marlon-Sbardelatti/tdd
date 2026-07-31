package org.example;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EmissorCertidao {
    private final PessoaFisica usuarioAutenticado;
    private final Set<Certidao> certidoesEmitidas = new HashSet<>();
    private long proximoNumero = 1;

    public EmissorCertidao(PessoaFisica usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public Certidao emitirCertidao(PessoaFisica solicitada) {
        String valorCpf = solicitada.getCpf().getValor();

        if (!Cpf.isValido(valorCpf)) {
            throw new IllegalStateException("CPF inválido");
        }

        if (!valorCpf.equals(usuarioAutenticado.getCpf().getValor())) {
            throw new IllegalStateException("Operação negada: o CPF solicitado não pertence ao usuário autenticado");
        }

        if (solicitada.isTemDebito()) {
            throw new IllegalStateException("Pessoa possui débitos em aberto, certidão não emitida");
        }

        Certidao certidao = new Certidao(
                proximoNumero++,
                UUID.randomUUID().toString(),
                new Date(),
                solicitada.getCpf()
        );
        certidoesEmitidas.add(certidao);
        return certidao;
    }

    public boolean validarCertidao(Long numeroCertidao, String assinatura, Date data) {
        for (Certidao certidao : certidoesEmitidas) {
            if (certidao.getNumeroCertidao().equals(numeroCertidao)
                    && certidao.getAssinaturaDigital().equals(assinatura)
                    && certidao.getDataEmissao().equals(data)) {
                return true;
            }
        }
        return false;
    }
}
