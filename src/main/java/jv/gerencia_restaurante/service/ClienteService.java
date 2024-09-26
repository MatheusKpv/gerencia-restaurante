package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> getListaClientes();

    ClienteResponseDTO cadastraCliente(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO alteraCliente(Long id, ClienteRequestDTO clienteRequestDTO);

    Cliente findById(Long id);

    void desbloqueioCliente(Long id);

    ClienteResponseDTO getClienteMaiorgasto();

    void bloqueioCliente(Long id);
}
