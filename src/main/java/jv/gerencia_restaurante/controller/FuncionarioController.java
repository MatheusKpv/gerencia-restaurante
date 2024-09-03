package jv.gerencia_restaurante.controller;

import jv.gerencia_restaurante.dto.FuncionarioRequestDTO;
import jv.gerencia_restaurante.dto.FuncionarioResponseDTO;
import jv.gerencia_restaurante.dto.MessageErrorDTO;
import jv.gerencia_restaurante.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<?> getListaFuncionarios() {
        try {
            List<FuncionarioResponseDTO> funcionarios = funcionarioService.getListaFuncionarios();
            return ResponseEntity.ok(funcionarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastraFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        try {
            FuncionarioResponseDTO funcionario = funcionarioService.cadastraFuncionario(funcionarioRequestDTO);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        try {
            FuncionarioResponseDTO funcionario = funcionarioService.alteraFuncionario(id, funcionarioRequestDTO);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageErrorDTO(e.getMessage()));
        }
    }
}
