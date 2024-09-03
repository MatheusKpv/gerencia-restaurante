package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.FuncionarioRequestDTO;
import jv.gerencia_restaurante.dto.FuncionarioResponseDTO;
import jv.gerencia_restaurante.entity.Funcionario;

import java.util.List;

public interface FuncionarioService {
    List<FuncionarioResponseDTO> getListaFuncionarios();

    FuncionarioResponseDTO cadastraFuncionario(FuncionarioRequestDTO funcionarioRequestDTO);

    FuncionarioResponseDTO alteraFuncionario(Long id, FuncionarioRequestDTO funcionarioRequestDTO);

    Funcionario findById(Long id);
}
