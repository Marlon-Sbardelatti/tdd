package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmissaoCertidaoTest {
    // Teste do Cenário 1: Emissão de certidão para CPF regular
    @Test
    public void deveEmitirCertidaoQuandoCpfRegular() {
        Cpf cpf = new Cpf("081.136.189-61");
        PessoaFisica pessoa = new PessoaFisica(cpf, false);
        EmissorCertidao emissor = new EmissorCertidao(pessoa);

        Certidao certidao = emissor.emitirCertidao(pessoa);

        assertNotNull(certidao, "A certidão deveria ter sido emitida");
    }

    // Teste do Cenário 2: Validação de certidão emitida com número, assinatura e data corretos
    @Test
    public void deveInformarQueCertidaoEmitidaEhValida() {
        Cpf cpf = new Cpf("081.136.189-61");
        PessoaFisica pessoa = new PessoaFisica(cpf, false);
        EmissorCertidao emissor = new EmissorCertidao(pessoa);

        Certidao certidao = emissor.emitirCertidao(pessoa);

        boolean valida = emissor.validarCertidao(
                certidao.getNumeroCertidao(),
                certidao.getAssinaturaDigital(),
                certidao.getDataEmissao()
        );

        assertTrue(valida, "A certidão deveria ser reconhecida como válida");
    }

    // Teste do Cenário 3: Bloqueio de emissão quando há débito em aberto
    @Test
    public void naoDeveEmitirCertidaoQuandoCpfComDebito() {
        Cpf cpf = new Cpf("222.555.888-46");
        PessoaFisica pessoa = new PessoaFisica(cpf, true);
        EmissorCertidao emissor = new EmissorCertidao(pessoa);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> emissor.emitirCertidao(pessoa),
                "Deveria ter lançado exceção informando débito em aberto"
        );

        String mensagem = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();
        assertTrue(
                mensagem.contains("débito") || mensagem.contains("debito"),
                "A mensagem deveria informar sobre débitos em aberto. Mensagem recebida: " + ex.getMessage()
        );
    }

    // Teste do Cenário 4: Bloqueio da emissão quando CPF é inválido
    @Test
    public void naoDeveEmitirCertidaoParaCpfInvalido() {
        Cpf cpf = new Cpf("222.555.888-41");
        PessoaFisica pessoa = new PessoaFisica(cpf, true);
        EmissorCertidao emissor = new EmissorCertidao(pessoa);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> emissor.emitirCertidao(pessoa),
                "Deveria ter lançado exceção de CPF inválido"
        );

        String mensagem = ex.getMessage() == null ? "" : ex.getMessage();
        assertTrue(
                mensagem.equals("CPF inválido"),
                "A mensagem deveria informar sobre CPF inválido. Mensagem recebida: " + ex.getMessage()
        );
    }

    // Teste do Cenário 5: Tentativa de emitir certidão para CPF arbritário
    @Test
    public void naoDeveEmitirCertidaoParaCpfDiferenteDoPortador() {
        PessoaFisica usuarioAutenticado = new PessoaFisica(new Cpf("111.444.777-35"), false);
        PessoaFisica terceiro = new PessoaFisica(new Cpf("222.555.888-46"), false);
        EmissorCertidao emissor = new EmissorCertidao(usuarioAutenticado);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> emissor.emitirCertidao(terceiro),
                "Deveria ter lançado exceção de segurança"
        );
    }
}