package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.dto.MesaRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesa_seq")
    @SequenceGenerator(name = "mesa_seq", allocationSize = 1, sequenceName = "mesa_sequence")
    private Long id;

    private Integer numero;

    @Column(name = "capacidade_pessoas")
    private Integer capacidadePessoas;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "mesa")
    private List<Reserva> reservas;

    public Mesa(MesaRequestDTO mesa, Restaurante restaurante) {
        this.capacidadePessoas = mesa.capacidadePessoas();
        this.restaurante = restaurante;
    }

    public void alteraDados(MesaRequestDTO mesa, Restaurante restaurante) {
        if (mesa.capacidadePessoas() != null) {
            this.capacidadePessoas = mesa.capacidadePessoas();
        }
        if (restaurante != null) {
            this.restaurante = restaurante;
        }
    }
}
