package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.ReservaRequestDTO;
import jv.gerencia_restaurante.dto.ReservaResponseDTO;
import jv.gerencia_restaurante.dto.MessageErrorDTO;
import jv.gerencia_restaurante.enuns.StatusEnum;
import jv.gerencia_restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<?> getListaReservas() {
        try {
            List<ReservaResponseDTO> reservas = reservaService.getListaReservas();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/valor-total/{id}")
    public ResponseEntity<?> getValorTotalReserva(@PathVariable Long id) {
        try {
            BigDecimal valorTotal = reservaService.getValorTotalReserva(id);
            return ResponseEntity.ok(valorTotal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/observacao/{obs}")
    public ResponseEntity<?> getReservaPorObservacao(@PathVariable String obs) {
        try {
            List<ReservaResponseDTO> reservas = reservaService.getReservaPorObservacao(obs);
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraReserva(@RequestBody ReservaRequestDTO reservaRequestDTO) {
        try {
            ReservaResponseDTO reserva = reservaService.cadastraReserva(reservaRequestDTO);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraReserva(@PathVariable Long id, @RequestBody ReservaRequestDTO reservaRequestDTO) {
        try {
            ReservaResponseDTO reserva = reservaService.alteraReserva(id, reservaRequestDTO);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/altera-status/{id}")
    public ResponseEntity<?> alteraStatus(@PathVariable Long id, @RequestParam StatusEnum statusEnum) {
        try {
            ReservaResponseDTO reserva = reservaService.alteraStatus(id, statusEnum);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }
}
