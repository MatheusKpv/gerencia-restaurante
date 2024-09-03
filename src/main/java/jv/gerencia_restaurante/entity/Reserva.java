package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.dto.ReservaRequestDTO;
import jv.gerencia_restaurante.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_seq")
    @SequenceGenerator(name = "reserva_seq", allocationSize = 1, sequenceName = "reserva_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Column(name = "quantidade_pessoas")
    private Integer quantidadePessoas;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Double avaliacao;

    private String observacao;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "reserva")
    private List<Pedido> pedidos;

    public Reserva(ReservaRequestDTO reserva, Cliente cliente, Mesa mesa) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.dataReserva = reserva.dataReserva();
        this.quantidadePessoas = reserva.quantidadePessoas();
        this.status = reserva.status();
        this.avaliacao = reserva.avaliacao();
        this.observacao = reserva.observacao();
    }

    public void alteraDados(ReservaRequestDTO reservaRequestDTO, Cliente cliente, Mesa mesa) {
        if (cliente != null) {
            this.cliente = cliente;
        }
        if (mesa != null) {
            this.mesa = mesa;
        }
        if (reservaRequestDTO.dataReserva() != null) {
            this.dataReserva = reservaRequestDTO.dataReserva();
        }
        if (reservaRequestDTO.quantidadePessoas() != null) {
            this.quantidadePessoas = reservaRequestDTO.quantidadePessoas();
        }
        if (reservaRequestDTO.status() != null) {
            this.status = reservaRequestDTO.status();
        }
        if (reservaRequestDTO.avaliacao() != null) {
            this.avaliacao = reservaRequestDTO.avaliacao();
        }
        if (reservaRequestDTO.observacao() != null) {
            this.observacao = reservaRequestDTO.observacao();
        }
    }
}
