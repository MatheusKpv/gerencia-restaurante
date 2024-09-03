package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.entity.Mesa;
import jv.gerencia_restaurante.entity.Restaurante;

public record MesaResponseDTO(
        Long id,
        Integer numero,
        Integer capacidadePessoas
        //RestauranteResponseDTO restaurante
) {
    public MesaResponseDTO (Mesa mesa) {
        this(mesa.getId(), mesa.getNumero(), mesa.getCapacidadePessoas());
    }
}
