package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.enuns.CargoEnum;
import jv.gerencia_restaurante.enuns.SexoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioRequestDTO(
        String nome,
        String sobrenome,
        String cpf,
        LocalDate dataNascimento,
        SexoEnum sexo,
        String telefone,
        Long idRestaurante,
        CargoEnum cargo,
        LocalDate dataAdmissao,
        BigDecimal salario,
        Double cargaHoraria
) {
}
