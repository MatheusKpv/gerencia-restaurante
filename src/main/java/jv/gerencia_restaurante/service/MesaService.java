package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.MesaRequestDTO;
import jv.gerencia_restaurante.dto.MesaResponseDTO;
import jv.gerencia_restaurante.entity.Mesa;

import java.util.List;

public interface MesaService {
    List<MesaResponseDTO> getListaMesas();

    MesaResponseDTO cadastraMesa(MesaRequestDTO mesaRequestDTO);

    MesaResponseDTO alteraMesa(Long id, MesaRequestDTO mesaRequestDTO);

    Mesa findById(Long id);
}
