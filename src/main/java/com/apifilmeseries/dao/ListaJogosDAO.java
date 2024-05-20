package com.apifilmeseries.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.apifilmeseries.dto.ListaGamesDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ListaJogosDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<ListaGamesDTO> consultarListaJogos(Long idListaJogos, Long idUsuario, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append("   l.id, ")
                .append("   l.classificacao, ")
                .append("   l.comentario, ")
                .append("   l.data_acao, ")
                .append("   l.id_jogo, ")
                .append("   l.id_usuario, ")
                .append("   l.tipo_acao, ")
                .append("   g.titulo, ")
                .append("   g.plataforma, ")
                .append("   g.console, ")
                .append("   g.estudio_producao, ")
                .append("   u.nome ")
                .append("FROM lista_jogos l ")
                .append("INNER JOIN usuarios u ON l.id_usuario = u.id ")
                .append("INNER JOIN games g ON l.id_jogo = g.id_jogo ");

        boolean hasWhereClause = false;

        if (idListaJogos != null) {
            sqlBuilder.append("WHERE l.id = :idListaJogos ");
            hasWhereClause = true;
        }

        if (idUsuario != null) {
            if (hasWhereClause) {
                sqlBuilder.append("AND ");
            } else {
                sqlBuilder.append("WHERE ");
                hasWhereClause = true;
            }
            sqlBuilder.append("u.id = :idUsuario ");
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString());

        if (idListaJogos != null) {
            query.setParameter("idListaJogos", idListaJogos);
        }

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<ListaGamesDTO> dtos = new ArrayList<>();

        for (Object[] row : resultList) {
            ListaGamesDTO dto = new ListaGamesDTO();
            dto.setId((Long) row[0]);
            dto.setClassificacao((Integer) row[1]);
            dto.setComentario((String) row[2]);
            dto.setDataAcao((Date) row[3]);
            dto.setIdJogo((Long) row[4]);
            dto.setIdUsuario((Long) row[5]);
            dto.setTipoAcao((String) row[6]);
            dto.setTituloJogo((String) row[7]);
            dto.setPlataforma((String) row[8]);
            dto.setConsole((String) row[9]);
            dto.setEstudioProducao((String) row[10]);
            dto.setNomeUsuario((String) row[11]);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }
}

