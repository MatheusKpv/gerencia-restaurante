package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.MesaRequestDTO;
import jv.gerencia_restaurante.dto.MesaResponseDTO;
import jv.gerencia_restaurante.dto.MessageErrorDTO;
import jv.gerencia_restaurante.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<?> getListaMesas() {
        try {
            List<MesaResponseDTO> mesas = mesaService.getListaMesas();
            return ResponseEntity.ok(mesas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("disponiveis")
    public ResponseEntity<?> getMesasDisponiveis(@RequestParam Long idRestaurante, @RequestParam LocalDate data, @RequestParam Integer qtdPessoas) {
        try {
            List<MesaResponseDTO> mesas = mesaService.getMesasDisponiveis(idRestaurante, data, qtdPessoas);
            return ResponseEntity.ok(mesas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraMesa(@RequestBody MesaRequestDTO mesaRequestDTO) {
        try {
            MesaResponseDTO mesa = mesaService.cadastraMesa(mesaRequestDTO);
            return ResponseEntity.ok(mesa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraMesa(@PathVariable Long id, @RequestBody MesaRequestDTO mesaRequestDTO) {
        try {
            MesaResponseDTO mesa = mesaService.alteraMesa(id, mesaRequestDTO);
            return ResponseEntity.ok(mesa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }
}
