package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, FuncionarioRepositoryCustom {
}
