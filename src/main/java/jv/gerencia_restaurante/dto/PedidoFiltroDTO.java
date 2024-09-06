package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.enuns.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoFiltroDTO(LocalDate data, BigDecimal valor, StatusEnum status, Long idCliente) {
}
