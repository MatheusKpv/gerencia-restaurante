package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.dto.RestauranteRequestDTO;
import jv.gerencia_restaurante.dto.RestauranteResponseDTO;
import jv.gerencia_restaurante.entity.Restaurante;

import java.util.List;

public interface RestauranteService {
    List<RestauranteResponseDTO> getListaRestaurantes();

    RestauranteResponseDTO cadastraRestaurante(RestauranteRequestDTO restauranteRequestDTO);

    RestauranteResponseDTO alteraRestaurante(Long id, RestauranteRequestDTO restauranteRequestDTO);

    Restaurante findById(Long id);
}
