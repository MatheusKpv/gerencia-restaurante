package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.enuns.TipoComidaEnum;

public record RestauranteResponseDTO(
        Long id,
        String nome,
        String cnpj,
        Double estrelas,
        TipoComidaEnum tipoComida
) {
    public RestauranteResponseDTO(Restaurante restaurante) {
        this(restaurante.getId(), restaurante.getNome(), restaurante.getCnpj(),
                restaurante.getEstrelas(), restaurante.getTipoComida());
    }
}
