package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.ReservaRequestDTO;
import jv.gerencia_restaurante.dto.ReservaResponseDTO;
import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.Mesa;
import jv.gerencia_restaurante.entity.Reserva;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.enuns.StatusEnum;
import jv.gerencia_restaurante.repository.ReservaRepository;
import jv.gerencia_restaurante.service.ClienteService;
import jv.gerencia_restaurante.service.MesaService;
import jv.gerencia_restaurante.service.ReservaService;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private MesaService mesaService;

    @Override
    public List<ReservaResponseDTO> getListaReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream().map(ReservaResponseDTO::new).toList();
    }

    @Override
    public ReservaResponseDTO cadastraReserva(ReservaRequestDTO reservaRequestDTO) {
        validaReserva(reservaRequestDTO.dataReserva());
        Cliente cliente = clienteService.findById(reservaRequestDTO.idCliente());
        Mesa mesa = mesaService.findById(reservaRequestDTO.idMesa());
        Reserva reserva = new Reserva(reservaRequestDTO, cliente, mesa);
        reservaRepository.save(reserva);
        return new ReservaResponseDTO(reserva);
    }

    private void validaReserva(LocalDate dataRserva) {
        if (dataRserva.isBefore(LocalDate.now())) {
            throw new RuntimeException("reserva não pode ser feita para uma data no passado");
        }
    }

    @Override
    public ReservaResponseDTO alteraReserva(Long id, ReservaRequestDTO reservaRequestDTO) {
        Reserva reserva = findById(id);
        Cliente cliente = null;
        if (reservaRequestDTO.idCliente() != null) {
            cliente = clienteService.findById(reservaRequestDTO.idCliente());
        }
        Mesa mesa = null;
        if (reservaRequestDTO.idMesa() != null) {
            mesa = mesaService.findById(reservaRequestDTO.idMesa());
        }
        reserva.alteraDados(reservaRequestDTO, cliente, mesa);
        reservaRepository.save(reserva);
        return new ReservaResponseDTO(reserva);
    }

    @Override
    public Reserva findById(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do reserva não encontrado"));
    }

    @Override
    public ReservaResponseDTO alteraStatus(Long id, StatusEnum statusEnum) {
        Reserva reserva = findById(id);
        ValidaAlteracaoStatus(statusEnum, reserva.getDataReserva());
        reserva.setStatus(statusEnum);
        reservaRepository.save(reserva);
        return new ReservaResponseDTO(reserva);
    }

    @Override
    public List<ReservaResponseDTO> getReservaPorObservacao(String obs) {
        List<Reserva> reservas = reservaRepository.findAllByObservacaoContainingIgnoreCase(obs);
        return reservas.stream().map(ReservaResponseDTO::new).toList();
    }

    private void ValidaAlteracaoStatus(StatusEnum statusEnum, LocalDate dataReserva) {
        if (StatusEnum.CANCELADA.equals(statusEnum)) {
            if (!dataReserva.isBefore(LocalDate.now())) {
                throw new RuntimeException("reserva só pode ser cancelada com 1 dia de antecedência");
            }
        }
        if (StatusEnum.CONCLUIDA.equals(statusEnum)) {
            if (dataReserva.isBefore(LocalDate.now())) {
                throw new RuntimeException("reserva só pode ser concluída no dia mesmo dia ou posterior");
            }
        }
    }
}
