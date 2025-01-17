package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.MesaRequestDTO;
import jv.gerencia_restaurante.dto.MesaResponseDTO;
import jv.gerencia_restaurante.entity.Mesa;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface MesaService {
    List<MesaResponseDTO> getListaMesas();

    MesaResponseDTO cadastraMesa(MesaRequestDTO mesaRequestDTO);

    MesaResponseDTO alteraMesa(Long id, MesaRequestDTO mesaRequestDTO);

    Mesa findById(Long id);

    Page<MesaResponseDTO> getMesasDisponiveis(Long idRestaurante, LocalDate data, Integer qtdPessoas, Integer pagina, Integer size);
}
