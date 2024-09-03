package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.enuns.TipoComidaEnum;

public record RestauranteRequestDTO(
        String nome,
        String cnpj,
        Double estrelas,
        TipoComidaEnum tipoComida
) {
}
