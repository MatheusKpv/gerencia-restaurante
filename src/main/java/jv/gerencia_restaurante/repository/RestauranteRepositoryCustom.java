package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.MaiorFaturamentoMesRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RestauranteRepositoryCustom {
    BigDecimal findFaturamentoPorDia(Long id, LocalDate data);
    List<MaiorFaturamentoMesRequestDTO> findDatasMaiorFaturamentoMes(Long id, Integer mes);
}
