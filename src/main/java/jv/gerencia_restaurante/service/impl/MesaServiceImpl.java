package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.MesaRequestDTO;
import jv.gerencia_restaurante.dto.MesaResponseDTO;
import jv.gerencia_restaurante.entity.Mesa;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.repository.MesaRepository;
import jv.gerencia_restaurante.service.MesaService;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements MesaService {
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private RestauranteService restauranteService;

    @Override
    public List<MesaResponseDTO> getListaMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        return mesas.stream().map(MesaResponseDTO::new).toList();
    }

    @Override
    public MesaResponseDTO cadastraMesa(MesaRequestDTO mesaRequestDTO) {
        Restaurante restaurante = restauranteService.findById(mesaRequestDTO.idRestaurante());
        Mesa mesa = new Mesa(mesaRequestDTO, restaurante);
        mesaRepository.save(mesa);
        return new MesaResponseDTO(mesa);
    }

    @Override
    public MesaResponseDTO alteraMesa(Long id, MesaRequestDTO mesaRequestDTO) {
        Mesa mesa = findById(id);
        Restaurante restaurante = null;
        if (mesaRequestDTO.idRestaurante() != null) {
            restaurante = restauranteService.findById(mesaRequestDTO.idRestaurante());
        }
        mesa.alteraDados(mesaRequestDTO, restaurante);
        mesaRepository.save(mesa);
        return new MesaResponseDTO(mesa);
    }

    @Override
    public Mesa findById(Long id) {
        return mesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do mesa não encontrado"));
    }
}
