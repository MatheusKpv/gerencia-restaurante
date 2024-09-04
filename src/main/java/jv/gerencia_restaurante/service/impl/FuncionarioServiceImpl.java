package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.FuncionarioRequestDTO;
import jv.gerencia_restaurante.dto.FuncionarioResponseDTO;
import jv.gerencia_restaurante.entity.Funcionario;
import jv.gerencia_restaurante.entity.Restaurante;
import jv.gerencia_restaurante.enuns.CargoEnum;
import jv.gerencia_restaurante.repository.FuncionarioRepository;
import jv.gerencia_restaurante.service.FuncionarioService;
import jv.gerencia_restaurante.service.RestauranteService;
import jv.gerencia_restaurante.validation.ValidationPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private RestauranteService restauranteService;

    @Override
    public List<FuncionarioResponseDTO> getListaFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(FuncionarioResponseDTO::new).toList();
    }

    @Override
    public FuncionarioResponseDTO cadastraFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        ValidationPessoa.validaCPF(funcionarioRequestDTO.cpf());
        validaCargaHoraria(funcionarioRequestDTO.cargaHoraria());
        validaSalarioCLT(funcionarioRequestDTO.salario(), funcionarioRequestDTO.cargo());
        Restaurante restaurante = restauranteService.findById(funcionarioRequestDTO.idRestaurante());
        Funcionario funcionario = new Funcionario(funcionarioRequestDTO, restaurante);
        funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    private void validaSalarioCLT(BigDecimal salario, CargoEnum cargo) {
        BigDecimal salarioMin = BigDecimal.valueOf(1412);
        if (!CargoEnum.FREELANCER.equals(cargo)) {
            if (salario.compareTo(salarioMin) < 0) {
                throw new RuntimeException("funcionário diferente de Freelancer deve ter " +
                        "um salário cadastrado superior a um salário mínimo");
            }
        }
    }

    private void validaCargaHoraria(Double cargaHoraria) {
        if (cargaHoraria > 220) {
            throw new RuntimeException("carga horária do funcionário não deve ultrapassar 220 horas");
        }
    }

    @Override
    public FuncionarioResponseDTO alteraFuncionario(Long id, FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = findById(id);
        Restaurante restaurante = null;
        if (funcionarioRequestDTO.idRestaurante() != null) {
            restaurante = restauranteService.findById(funcionarioRequestDTO.idRestaurante());
        }
        if (funcionarioRequestDTO.cpf() != null) {
            ValidationPessoa.validaCPF(funcionarioRequestDTO.cpf());
        }
        if (funcionarioRequestDTO.cargaHoraria() != null) {
            validaCargaHoraria(funcionarioRequestDTO.cargaHoraria());
        }
        funcionario.alteraDados(funcionarioRequestDTO, restaurante);
        funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do funcionario não encontrado"));
    }
}
