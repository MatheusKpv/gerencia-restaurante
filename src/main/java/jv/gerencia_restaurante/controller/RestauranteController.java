package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.*;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin(origins = "http://localhost:4200")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<?> getListaRestaurantes() {
        try {
            List<RestauranteResponseDTO> restaurantes = restauranteService.getListaRestaurantes();
            return ResponseEntity.ok(restaurantes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/faturamento-dia/{id}")
    public ResponseEntity<?> getFaturamentoPorDia(@PathVariable Long id, @RequestParam LocalDate data) {
        try {
            BigDecimal faturamento = restauranteService.getFaturamentoPorDia(id, data);
            return ResponseEntity.ok(faturamento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/dia-maior-faturamento-mes/{id}")
    public ResponseEntity<?> getDiaMaiorFaturamentoMes(@PathVariable Long id, @RequestParam Integer mes) {
        try {
            MaiorFaturamentoMesReponseDTO maiorFaturamentoMes = restauranteService.getDiaMaiorFaturamentoMes(id, mes);
            return ResponseEntity.ok(maiorFaturamentoMes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraRestaurante(@RequestBody RestauranteRequestDTO restauranteRequestDTO) {
        try {
            RestauranteResponseDTO restaurante = restauranteService.cadastraRestaurante(restauranteRequestDTO);
            return ResponseEntity.ok(restaurante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraRestaurante(@PathVariable Long id, @RequestBody RestauranteRequestDTO restauranteRequestDTO) {
        try {
            RestauranteResponseDTO restaurante = restauranteService.alteraRestaurante(id, restauranteRequestDTO);
            return ResponseEntity.ok(restaurante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }
}
