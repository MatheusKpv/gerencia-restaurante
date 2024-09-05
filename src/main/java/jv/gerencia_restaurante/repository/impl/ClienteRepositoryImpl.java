package jv.gerencia_restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.entity.Cliente;
import jv.gerencia_restaurante.entity.QCliente;
import jv.gerencia_restaurante.repository.ClienteRepositoryCustom;

public class ClienteRepositoryImpl implements ClienteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final QCliente cliente = QCliente.cliente;

    @Override
    public ClienteResponseDTO findClienteMaiorGasto() {
        var query = new JPAQuery<Cliente>(em);

        return query.select(Projections.constructor(ClienteResponseDTO.class, cliente))
                .from(cliente).orderBy(cliente.quantidadeValorGasto.desc()).fetchFirst();
    }
}