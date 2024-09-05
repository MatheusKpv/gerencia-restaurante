package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.MaiorFaturamentoMesReponseDTO;
import jv.gerencia_restaurante.dto.RestauranteRequestDTO;
import jv.gerencia_restaurante.dto.RestauranteResponseDTO;
import jv.gerencia_restaurante.entity.Restaurante;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RestauranteService {
    List<RestauranteResponseDTO> getListaRestaurantes();

    RestauranteResponseDTO cadastraRestaurante(RestauranteRequestDTO restauranteRequestDTO);

    RestauranteResponseDTO alteraRestaurante(Long id, RestauranteRequestDTO restauranteRequestDTO);

    Restaurante findById(Long id);

    BigDecimal getFaturamentoPorDia(Long id, LocalDate data);

    MaiorFaturamentoMesReponseDTO getDiaMaiorFaturamentoMes(Long id, Integer mes);
}
