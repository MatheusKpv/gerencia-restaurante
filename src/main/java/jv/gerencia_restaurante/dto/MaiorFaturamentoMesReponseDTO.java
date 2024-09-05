package jv.gerencia_restaurante.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaiorFaturamentoMesReponseDTO(LocalDate data, String dia, BigDecimal faturamento) {
    public MaiorFaturamentoMesReponseDTO(MaiorFaturamentoMesRequestDTO dto) {
        this(dto.data(), dto.data().getDayOfWeek().toString(), dto.faturamento());
    }
}
