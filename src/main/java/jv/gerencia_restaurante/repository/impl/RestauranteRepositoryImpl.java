package jv.gerencia_restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jv.gerencia_restaurante.dto.MaiorFaturamentoMesRequestDTO;
import jv.gerencia_restaurante.entity.*;
import jv.gerencia_restaurante.repository.RestauranteRepositoryCustom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    final QMesa mesa = QMesa.mesa;
    final QPedido pedido = QPedido.pedido;
    final QReserva reserva = QReserva.reserva;

    @Override
    public BigDecimal findFaturamentoPorDia(Long id, LocalDate data) {
        var queryP = new JPAQuery<BigDecimal>(em);
        var queryR = new JPAQuery<Reserva>(em);
        var queryM = new JPAQuery<Mesa>(em);
        queryP.select(pedido.valor.sum().coalesce(BigDecimal.ZERO))
                .from(pedido)
                .where(pedido.reserva.id.in(
                        queryR.select(reserva.id)
                                .from(reserva)
                                .where(reserva.mesa.id.in(
                                        queryM.select(mesa.id)
                                                .from(mesa)
                                                .where(mesa.restaurante.id.eq(id)
                                                        .and(reserva.dataReserva.eq(data)))
                                ))
                ));
        return queryP.fetchOne();
    }

    @Override
    public List<MaiorFaturamentoMesRequestDTO> findDatasMaiorFaturamentoMes(Long id, Integer mes) {
        var queryP = new JPAQuery<Pedido>(em);
        return queryP.select(Projections.constructor(
                MaiorFaturamentoMesRequestDTO.class, reserva.dataReserva, pedido.valor.sum().coalesce(BigDecimal.ZERO)))
                .from(pedido)
                .leftJoin(pedido.reserva, reserva)
                .leftJoin(reserva.mesa, mesa)
                .where(mesa.restaurante.id.eq(id)
                        .and(reserva.dataReserva.month().eq(mes)
                        ))
                .groupBy(reserva.dataReserva)
                .orderBy(pedido.valor.sum().coalesce(BigDecimal.ZERO).desc()).fetch();
    }
}
