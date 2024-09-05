package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.ReservaRequestDTO;
import jv.gerencia_restaurante.dto.ReservaResponseDTO;
import jv.gerencia_restaurante.entity.Reserva;
import jv.gerencia_restaurante.enuns.StatusEnum;

import java.math.BigDecimal;
import java.util.List;

public interface ReservaService {
    List<ReservaResponseDTO> getListaReservas();

    ReservaResponseDTO cadastraReserva(ReservaRequestDTO reservaRequestDTO);

    ReservaResponseDTO alteraReserva(Long id, ReservaRequestDTO reservaRequestDTO);

    Reserva findById(Long id);

    ReservaResponseDTO alteraStatus(Long id, StatusEnum statusEnum);

    List<ReservaResponseDTO> getReservaPorObservacao(String obs);

    BigDecimal getValorTotalReserva(Long id);
}
