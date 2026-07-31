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
        if (cpf == null) {
            return false;
        }

        String digitos = cpf.replaceAll("[^0-9]", "");
        if (digitos.length() != 11) {
            return false;
        }

        if (digitos.chars().allMatch(c -> c == digitos.charAt(0))) {
            return false;
        }

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = digitos.charAt(i) - '0';
        }

        return calcularDigitoVerificador(numeros, 9) == numeros[9]
                && calcularDigitoVerificador(numeros, 10) == numeros[10];
    }

    private static int calcularDigitoVerificador(int[] numeros, int tamanho) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            soma += numeros[i] * (tamanho + 1 - i);
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
