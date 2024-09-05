package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.MesaResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface MesaRepositoryCustom {
    List<MesaResponseDTO> findMesasDisponiveisPorDataEQtdPessoas(Long idRestaurante, LocalDate data, Integer qtdPessoas);
}
