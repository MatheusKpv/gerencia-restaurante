package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.Mesa;
import jv.gerencia_restaurante.entity.Reserva;
import jv.gerencia_restaurante.enuns.SexoEnum;
import jv.gerencia_restaurante.enuns.StatusEnum;

import java.time.LocalDate;

public record ReservaResponseDTO(
        Long id,
        ClienteResponseDTO cliente,
        MesaResponseDTO mesa,
        LocalDate dataReserva,
        Integer quantidadePessoas,
        StatusEnum status,
        Double avaliacao,
        String observacao
) {
    public ReservaResponseDTO(Reserva reserva) {
        this(reserva.getId(), new ClienteResponseDTO(reserva.getCliente()), new MesaResponseDTO(reserva.getMesa()),
                reserva.getDataReserva(), reserva.getQuantidadePessoas(), reserva.getStatus(),
                reserva.getAvaliacao(), reserva.getObservacao());
    }
}
