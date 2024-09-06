package jv.gerencia_restaurante.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jv.gerencia_restaurante.dto.PedidoFiltroDTO;
import jv.gerencia_restaurante.dto.PedidoResponseDTO;
import jv.gerencia_restaurante.entity.QPedido;
import jv.gerencia_restaurante.entity.QReserva;
import jv.gerencia_restaurante.repository.PedidoRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class PedidoRepositoryImpl implements PedidoRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    final QPedido pedido = QPedido.pedido;
    final QReserva reserva = QReserva.reserva;

    @Override
    public Page<PedidoResponseDTO> getListaComFiltro(PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable) {
        BooleanBuilder condicoes = new BooleanBuilder();

        if (Objects.nonNull(pedidoFiltroDTO.idCliente())) {
            condicoes.and(pedido.id.eq(pedidoFiltroDTO.idCliente()));
        }
        if (Objects.nonNull(pedidoFiltroDTO.valor())) {
            condicoes.and(pedido.valor.eq(pedidoFiltroDTO.valor()));
        }
        if (Objects.nonNull(pedidoFiltroDTO.data())) {
            condicoes.and(reserva.dataReserva.eq(pedidoFiltroDTO.data()));
        }
        if (Objects.nonNull(pedidoFiltroDTO.status())) {
            condicoes.and(reserva.status.eq(pedidoFiltroDTO.status()));
        }

        var query = new JPAQuery<PedidoResponseDTO>(em);

        query.select(Projections.constructor(PedidoResponseDTO.class, pedido)).from(pedido)
                .join(pedido.reserva, reserva)
                .where(condicoes)
                .orderBy(pedido.valor.desc(), pedido.id.asc());

        query.limit(pageable.getPageSize());
        query.offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
