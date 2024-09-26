package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.repository.ClienteRepository;
import jv.gerencia_restaurante.service.ClienteService;
import jv.gerencia_restaurante.service.RestauranteService;
import jv.gerencia_restaurante.validation.ValidationPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
        validaDataNascimento(clienteRequestDTO.dataNascimento());
        ValidationPessoa.validaCPF(clienteRequestDTO.cpf());
        Restaurante restaurante = restauranteService.findById(clienteRequestDTO.idRestaurante());
        Cliente cliente = new Cliente(clienteRequestDTO, restaurante);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    private void validaDataNascimento(LocalDate dataNascimento) {
        Integer idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 12 || idade > 100) {
            throw new RuntimeException("idade deve ser entre 12 a 100 anos");
        }
    }

    @Override
    public ClienteResponseDTO alteraCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = findById(id);
        Restaurante restaurante = null;
        if (clienteRequestDTO.idRestaurante() != null) {
            restaurante = restauranteService.findById(clienteRequestDTO.idRestaurante());
        }
        if (clienteRequestDTO.cpf() != null) {
            ValidationPessoa.validaCPF(clienteRequestDTO.cpf());
        }
        cliente.alteraDados(clienteRequestDTO, restaurante);
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do cliente não encontrado"));
    }

    @Override
    public void desbloqueioCliente(Long id) {
        Cliente cliente = findById(id);
        if (!cliente.getFlgBloqueado()) {
            throw new RuntimeException("cliente não está bloqueado");
        }
        cliente.desbloqueia();
        clienteRepository.save(cliente);
    }

    @Override
    public ClienteResponseDTO getClienteMaiorgasto() {
        ClienteResponseDTO cliente = clienteRepository.findClienteMaiorGasto();
        return cliente;
    }

    @Override
    public void bloqueioCliente(Long id) {
        Cliente cliente = findById(id);
        if (cliente.getFlgBloqueado()) {
            throw new RuntimeException("cliente já está bloqueado");
        }
        cliente.bloqueia();
        clienteRepository.save(cliente);
    }
}
