package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.PedidoFiltroDTO;
import jv.gerencia_restaurante.dto.PedidoRequestDTO;
import jv.gerencia_restaurante.dto.PedidoResponseDTO;
import jv.gerencia_restaurante.dto.MessageErrorDTO;
import jv.gerencia_restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<?> getListaPedidos() {
        try {
            List<PedidoResponseDTO> pedidos = pedidoService.getListaPedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity<?> getListaComFiltro(@RequestBody PedidoFiltroDTO pedidoFiltroDTO,
                                               @RequestParam(defaultValue = "0", required = false) Integer page,
                                               @RequestParam(defaultValue = "10", required = false) Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<PedidoResponseDTO> pedidos = pedidoService.getListaComFiltro(pedidoFiltroDTO, pageable);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        try {
            PedidoResponseDTO pedido = pedidoService.cadastraPedido(pedidoRequestDTO);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraPedido(@PathVariable Long id, @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        try {
            PedidoResponseDTO pedido = pedidoService.alteraPedido(id, pedidoRequestDTO);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }
}
