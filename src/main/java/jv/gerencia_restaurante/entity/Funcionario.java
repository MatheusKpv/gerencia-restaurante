package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.enuns.CargoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "funcionario")
public class Funcionario extends Pessoa{
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
}
