package jv.gerencia_restaurante.validation;

public class ValidationPessoa {
    public static void validaCPF(String cpf) {
        String cpfFormat = cpf.replaceAll("[^0-9]", "");
        if (cpfFormat.length() != 11) {
            throw new RuntimeException("CPF invalido");
        }
    }
}
