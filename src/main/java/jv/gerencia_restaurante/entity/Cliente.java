package jv.gerencia_restaurante.entity;

import jakarta.persistence.*;
import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "cliente")
public class Cliente extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", allocationSize = 1, sequenceName = "cliente_sequence")
    private Long id;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "quantidade_reservas")
    private Integer quantidadeReservas;

    private BigDecimal quantidadeValorGasto;

    private Boolean flgBloqueado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "cliente")
    private List<Reserva> reservas;

    public Cliente(ClienteRequestDTO cliente, Restaurante restaurante) {
        super(cliente.nome(), cliente.sobrenome(), cliente.cpf(), cliente.dataNascimento(),
                cliente.sexo(), cliente.telefone(), restaurante);
        this.dataCadastro = cliente.dataCadastro();
        this.quantidadeReservas = 0;
        this.quantidadeValorGasto = BigDecimal.ZERO;
        this.flgBloqueado = false;
    }

    public void alteraDados(ClienteRequestDTO cliente, Restaurante restaurante) {
        super.alteraDados(cliente.nome(), cliente.sobrenome(), cliente.cpf(), cliente.dataNascimento(),
                cliente.sexo(), cliente.telefone(), restaurante);
        if (cliente.dataCadastro() != null) {
            this.dataCadastro = cliente.dataCadastro();
        }
        if (cliente.quantidadeReservas() != null) {
            this.quantidadeReservas = cliente.quantidadeReservas();
        }
        if (cliente.quantidadeValorGasto() != null) {
            this.quantidadeValorGasto = cliente.quantidadeValorGasto();
        }
    }

    public void desbloqueia() {
        this.flgBloqueado = false;
    }

    public void bloqueia() {
        this.flgBloqueado = true;
    }
}
