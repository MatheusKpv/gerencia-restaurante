package jv.gerencia_restaurante.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jv.gerencia_restaurante.dto.MesaResponseDTO;
import jv.gerencia_restaurante.entity.*;
import jv.gerencia_restaurante.enuns.StatusEnum;
import jv.gerencia_restaurante.repository.MesaRepositoryCustom;

import java.time.LocalDate;
import java.util.List;

public class MesaRepositoryImpl implements MesaRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    final QMesa mesa = QMesa.mesa;
    final QRestaurante restaurante = QRestaurante.restaurante;
    final QReserva reserva = QReserva.reserva;

    @Override
    public List<MesaResponseDTO> findMesasDisponiveisPorDataEQtdPessoas(Long idRestaurante, LocalDate data, Integer qtdPessoas) {
        var queryM = new JPAQuery<Mesa>(em);
        var queryR = new JPAQuery<Reserva>(em);

        return queryM.select(Projections.constructor(MesaResponseDTO.class, mesa)).from(mesa)
                .where(mesa.capacidadePessoas.goe(qtdPessoas)
                        .and(mesa.restaurante.id.eq(idRestaurante)
                                .and(mesa.id.notIn(
                                        queryR.select(reserva.mesa.id).from(reserva).where(reserva.dataReserva.eq(data)
                                                .and(reserva.status.eq(StatusEnum.AGENDADA)))
                                )))).fetch();
    }
}