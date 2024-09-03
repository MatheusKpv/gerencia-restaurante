package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.dto.PedidoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", allocationSize = 1, sequenceName = "pedido_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Reserva reserva;

    @Column(name = "nome_prato")
    private String nomePrato;

    private BigDecimal valor;

    public Pedido(PedidoRequestDTO pedido, Reserva reserva) {
        this.reserva = reserva;
        this.nomePrato = pedido.nomePrato();
        this.valor = pedido.valor();
    }

    public void alteraDados(PedidoRequestDTO pedidoRequestDTO, Reserva reserva) {
        if (reserva != null) {
            this.reserva = reserva;
        }
        if (pedidoRequestDTO.nomePrato() != null) {
            this.nomePrato = pedidoRequestDTO.nomePrato();
        }
        if (pedidoRequestDTO.valor() != null) {
            this.valor = pedidoRequestDTO.valor();
        }
    }
}
