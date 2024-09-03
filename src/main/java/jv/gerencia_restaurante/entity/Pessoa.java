package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.enuns.SexoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
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

    public Pessoa(String nome, String sobrenome, String cpf, LocalDate dataNascimento, SexoEnum sexo,
                  String telefone, Restaurante restaurante) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.restaurante = restaurante;
    }

    protected void alteraDados(String nome, String sobrenome, String cpf, LocalDate dataNascimento, SexoEnum sexo,
                               String telefone, Restaurante restaurante) {
        if (nome != null) {
            this.nome = nome;
        }
        if (sobrenome != null) {
            this.sobrenome = sobrenome;
        }
        if (cpf != null) {
            this.cpf = cpf;
        }
        if (dataNascimento != null) {
            this.dataNascimento = dataNascimento;
        }
        if (sexo != null) {
            this.sexo = sexo;
        }
        if (telefone != null) {
            this.telefone = telefone;
        }
        if (restaurante != null) {
            this.restaurante = restaurante;
        }
    }
}
