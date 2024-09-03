package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.enuns.SexoEnum;

import java.time.LocalDate;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        LocalDate dataNascimento,
        SexoEnum sexo,
        String telefone,
        //RestauranteResponseDTO restaurante,
        LocalDate dataCadastro,
        Integer quantidadeReservas
) {
    public ClienteResponseDTO (Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getCpf(), cliente.getDataNascimento(),
                cliente.getSexo(), cliente.getTelefone(), cliente.getDataCadastro(), cliente.getQuantidadeReservas());
    }
}
