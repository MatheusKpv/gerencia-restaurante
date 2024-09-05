package jv.gerencia_restaurante.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaiorFaturamentoMesRequestDTO(LocalDate data, BigDecimal faturamento) {
}
