package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.ClienteResponseDTO;

public interface ClienteRepositoryCustom {
    ClienteResponseDTO findClienteMaiorGasto();
}
