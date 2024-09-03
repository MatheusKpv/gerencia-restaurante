package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.enuns.TipoComidaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restaurante")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurante_seq")
    @SequenceGenerator(name = "restaurante_seq", allocationSize = 1, sequenceName = "restaurante_sequence")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private Double estrelas;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comida", nullable = false)
    private TipoComidaEnum tipoComida;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "restaurante")
    private List<Cliente> clientes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "restaurante")
    private List<Funcionario> funcionarios;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "restaurante")
    private List<Mesa> mesas;
}
