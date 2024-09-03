package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.enuns.SexoEnum;
import lombok.Getter;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {
    @Column(nullable = false)
    private String nome;

    private String sobrenome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nasciemnto")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    private String telefone;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}
