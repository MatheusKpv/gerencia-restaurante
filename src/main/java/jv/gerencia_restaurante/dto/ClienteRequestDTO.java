package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.enuns.SexoEnum;

import java.time.LocalDate;

public record ClienteRequestDTO(
        String nome,
        String sobrenome,
        String cpf,
        LocalDate dataNascimento,
        SexoEnum sexo,
        String telefone,
        Long idRestaurante,
        LocalDate dataCadastro,
        Integer quantidadeReservas) {
}
