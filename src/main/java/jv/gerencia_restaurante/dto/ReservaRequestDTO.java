package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.enuns.StatusEnum;

import java.time.LocalDate;

public record ReservaRequestDTO(
        Long idCliente,
        Long idMesa,
        LocalDate dataReserva,
        Integer quantidadePessoas,
        StatusEnum status,
        Double avaliacao,
        String observacao
) {
}
