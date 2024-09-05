package jv.gerencia_restaurante.repository.impl;


import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jv.gerencia_restaurante.entity.QPedido;
import jv.gerencia_restaurante.entity.QReserva;
import jv.gerencia_restaurante.entity.Reserva;
import jv.gerencia_restaurante.repository.ReservaRepositoryCustom;

import java.math.BigDecimal;

public class ReservaRepositoryImpl implements ReservaRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    final QReserva reserva = QReserva.reserva;
    final QPedido pedido = QPedido.pedido;

    @Override
    public BigDecimal findTotalPorReserva(Long id) {
        var query = new JPAQuery<Reserva>(em);
        return query.select(pedido.valor.sum()).from(reserva).leftJoin(reserva.pedidos, pedido).where(reserva.id.eq(id)).fetchOne();
    }
}
