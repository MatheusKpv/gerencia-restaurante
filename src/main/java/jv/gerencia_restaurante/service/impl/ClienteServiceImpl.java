package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.repository.ClienteRepository;
import jv.gerencia_restaurante.service.ClienteService;
import jv.gerencia_restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RestauranteService restauranteService;

    @Override
    public List<ClienteResponseDTO> getListaClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteResponseDTO::new).toList();
    }

    @Override
    public ClienteResponseDTO cadastraCliente(ClienteRequestDTO clienteRequestDTO) {
        Restaurante restaurante = restauranteService.findById(clienteRequestDTO.idRestaurante());
        Cliente cliente = new Cliente(clienteRequestDTO, restaurante);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO alteraCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = findById(id);
        Restaurante restaurante = null;
        if (clienteRequestDTO.idRestaurante() != null) {
            restaurante = restauranteService.findById(clienteRequestDTO.idRestaurante());
        }
        cliente.alteraDados(clienteRequestDTO, restaurante);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do cliente não encontrado"));
    }
}
