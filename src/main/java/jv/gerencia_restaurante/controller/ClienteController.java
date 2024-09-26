package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.dto.MessageErrorDTO;
import jv.gerencia_restaurante.dto.RestauranteResponseDTO;
import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> getListaClientes() {
        try {
            List<ClienteResponseDTO> clientes = clienteService.getListaClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(new ClienteResponseDTO(cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/maior-valor-gasto")
    public ResponseEntity<?> getClienteMaiorgasto() {
        try {
            ClienteResponseDTO cliente = clienteService.getClienteMaiorgasto();
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.cadastraCliente(clienteRequestDTO);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        try {
            ClienteResponseDTO cliente = clienteService.alteraCliente(id, clienteRequestDTO);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/desbloqueio/{id}")
    public ResponseEntity<?> desbloqueioCliente(@PathVariable Long id) {
        try {
            clienteService.desbloqueioCliente(id);
            return ResponseEntity.ok(new MessageErrorDTO("Cliente desbloqueado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/bloqueio/{id}")
    public ResponseEntity<?> bloqueioCliente(@PathVariable Long id) {
        try {
            clienteService.bloqueioCliente(id);
            return ResponseEntity.ok(new MessageErrorDTO("Cliente bloqueado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }
}
