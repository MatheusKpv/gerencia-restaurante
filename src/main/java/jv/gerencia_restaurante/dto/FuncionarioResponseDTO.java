package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.Funcionario;
import jv.gerencia_restaurante.enuns.CargoEnum;
import jv.gerencia_restaurante.enuns.SexoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        LocalDate dataNascimento,
        SexoEnum sexo,
        String telefone,
        //RestauranteResponseDTO restaurante,
        CargoEnum cargo,
        LocalDate dataAdmissao,
        BigDecimal salario,
        Double cargaHoraria
) {
    public FuncionarioResponseDTO (Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getCpf(),
                funcionario.getDataNascimento(), funcionario.getSexo(), funcionario.getTelefone(),
                funcionario.getCargo(), funcionario.getDataAdmissao(), funcionario.getSalario(),
                funcionario.getCargaHoraria());
    }
}
