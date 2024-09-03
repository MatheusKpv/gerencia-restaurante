package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.RestauranteRequestDTO;
import jv.gerencia_restaurante.dto.RestauranteResponseDTO;
import jv.gerencia_restaurante.dto.MessageErrorDTO;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
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
