package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.MaiorFaturamentoMesReponseDTO;
import jv.gerencia_restaurante.dto.MaiorFaturamentoMesRequestDTO;
import jv.gerencia_restaurante.dto.RestauranteRequestDTO;
import jv.gerencia_restaurante.dto.RestauranteResponseDTO;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.repository.RestauranteRepository;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        ValidaCNPJ(restauranteRequestDTO.cnpj());
        ValidaEstrelas(restauranteRequestDTO.estrelas());
        Restaurante restaurante = new Restaurante(restauranteRequestDTO);
        restauranteRepository.save(restaurante);
        return new RestauranteResponseDTO(restaurante);
    }

    private void ValidaEstrelas(Double estrelas) {
        if (estrelas < 1 || estrelas > 3) {
            throw new RuntimeException("estrelas deve ser de 1 a 3");
        }
    }

    private void ValidaCNPJ(String cnpj) {
        String cnpjFormat = cnpj.replaceAll("[^a-zA-Z0-9]", "");
        if (cnpjFormat.length() != 14) {
            throw new RuntimeException("cnpj inválido");
        }
    }

    @Override
    public RestauranteResponseDTO alteraRestaurante(Long id, RestauranteRequestDTO restauranteRequestDTO) {
        if (restauranteRequestDTO.cnpj() != null) {
            ValidaCNPJ(restauranteRequestDTO.cnpj());
        }
        if (restauranteRequestDTO.estrelas() != null) {
            ValidaEstrelas(restauranteRequestDTO.estrelas());
        }
        Restaurante restaurante = findById(id);
        restaurante.alteraDados(restauranteRequestDTO);
        restauranteRepository.save(restaurante);
        return new RestauranteResponseDTO(restaurante);
    }

    @Override
    public Restaurante findById(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do restaurante não encontrado"));
    }

    @Override
    public BigDecimal getFaturamentoPorDia(Long id, LocalDate data) {
        return restauranteRepository.findFaturamentoPorDia(id, data);
    }

    @Override
    public MaiorFaturamentoMesReponseDTO getDiaMaiorFaturamentoMes(Long id, Integer mes) {
        List<MaiorFaturamentoMesRequestDTO> maiorFaturamentoMesRequestDTOList = restauranteRepository.findDatasMaiorFaturamentoMes(id, mes);
        return new MaiorFaturamentoMesReponseDTO(maiorFaturamentoMesRequestDTOList.stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Nenhum pedido registrado nessa data")));
    }
}
