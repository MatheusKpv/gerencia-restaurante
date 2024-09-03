package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.RestauranteRequestDTO;
import jv.gerencia_restaurante.dto.RestauranteResponseDTO;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.repository.RestauranteRepository;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<RestauranteResponseDTO> getListaRestaurantes() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return restaurantes.stream().map(RestauranteResponseDTO::new).toList();
    }

    @Override
    public RestauranteResponseDTO cadastraRestaurante(RestauranteRequestDTO restauranteRequestDTO) {
        Restaurante restaurante = new Restaurante(restauranteRequestDTO);
        return new RestauranteResponseDTO(restaurante);
    }

    @Override
    public RestauranteResponseDTO alteraRestaurante(Long id, RestauranteRequestDTO restauranteRequestDTO) {
        Restaurante restaurante = findById(id);
        restaurante.alteraDados(restauranteRequestDTO);
        restauranteRepository.save(restaurante);
        return new RestauranteResponseDTO(restaurante);
    }

    @Override
    public Restaurante findById(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do restaurante não encontrado"));
    }
}
