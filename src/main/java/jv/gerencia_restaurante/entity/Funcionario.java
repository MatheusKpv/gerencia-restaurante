package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.dto.FuncionarioRequestDTO;
import jv.gerencia_restaurante.dto.FuncionarioRequestDTO;
import jv.gerencia_restaurante.enuns.CargoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity(name = "funcionario")
public class Funcionario extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
    @SequenceGenerator(name = "funcionario_seq", allocationSize = 1, sequenceName = "funcionario_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(name = "carga_horaria", nullable = false)
    private Double cargaHoraria;

    public Funcionario(FuncionarioRequestDTO funcionario, Restaurante restaurante) {
        super(funcionario.nome(), funcionario.sobrenome(), funcionario.cpf(), funcionario.dataNascimento(),
                funcionario.sexo(), funcionario.telefone(), restaurante);
        this.cargo = funcionario.cargo();
        this.dataAdmissao = funcionario.dataAdmissao();
        this.salario = funcionario.salario();
        this.cargaHoraria = funcionario.cargaHoraria();
    }

    public void alteraDados(FuncionarioRequestDTO funcionario, Restaurante restaurante) {
        super.alteraDados(funcionario.nome(), funcionario.sobrenome(), funcionario.cpf(), funcionario.dataNascimento(),
                funcionario.sexo(), funcionario.telefone(), restaurante);
        if (funcionario.cargo() != null) {
            this.cargo = funcionario.cargo();
        }
        if (funcionario.dataAdmissao() != null) {
            this.dataAdmissao = funcionario.dataAdmissao();
        }
        if (funcionario.salario() != null) {
            this.salario = funcionario.salario();
        }
        if (funcionario.cargaHoraria() != null) {
            this.cargaHoraria = funcionario.cargaHoraria();
        }
    }
}
