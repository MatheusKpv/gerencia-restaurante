package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.MesaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MesaRepositoryCustom {
    Page<MesaResponseDTO> findMesasDisponiveisPorDataEQtdPessoas(Pageable pageable, Long idRestaurante, LocalDate data, Integer qtdPessoas);
    //List<MesaResponseDTO> findMesasDisponiveisPorDataEQtdPessoas(Pageable pageable, Long idRestaurante, LocalDate data, Integer qtdPessoas);
}
