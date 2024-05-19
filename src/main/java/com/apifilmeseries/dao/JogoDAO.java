package com.apifilmeseries.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class JogoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<FavoritoGameDTO> consultarFavoritosGame(Long idUsuario, Long idFavorito, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append("   fg.id, ")
                .append("   g.titulo, ")
                .append("   g.descricao, ")
                .append("   g.genero, ")
                .append("   g.ano_lancamento, ")
                .append("   g.plataforma, ")
                .append("   g.console, ")
                .append("   g.classificacao, ")
                .append("   g.estudio_producao, ")
                .append("   u.nome, ")
                .append("   fg.data_favorito, ")
                .append("   u.id as idUsuario, ")
                .append("   g.id_jogo ")
                .append("FROM favoritos_game fg ")
                .append("INNER JOIN usuarios u ON fg.usuario_id = u.id ")
                .append("INNER JOIN games g ON fg.id_jogo = g.id_jogo ")
                .append("WHERE 1 = 1 ");

        if (idUsuario != null) {
            sqlBuilder.append("AND u.id = :idUsuario ");
        }

        if (idFavorito != null) {
            sqlBuilder.append("AND fg.id = :idFavorito ");
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString());

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        if (idFavorito != null) {
            query.setParameter("idFavorito", idFavorito);
        }

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<FavoritoGameDTO> dtos = new ArrayList<>();

        for (Object[] row : resultList) {
            FavoritoGameDTO dto = new FavoritoGameDTO();
            dto.setId((int) row[0]);
            dto.setTitulo((String) row[1]);
            dto.setDescricao((String) row[2]);
            dto.setGenero((String) row[3]);
            dto.setAnoLancamento((int) row[4]);
            dto.setPlataforma((String) row[5]);
            dto.setConsole((String) row[6]);
            dto.setClassificacao((String) row[7]);
            dto.setEstudioProducao((String) row[8]);
            dto.setNomeUsuario((String) row[9]);
            dto.setDataFavorito((Date) row[10]);
            dto.setIdUsuario((Long) row[11]);
            dto.setIdJogo((Long) row[12]);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }

    public Page<AvaliacaoGameDTO> consultarAvaliacoesGame(Long idUsuario, Long idAvaliacao, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append("   ag.id, ")
                .append("   g.titulo, ")
                .append("   g.descricao, ")
                .append("   g.genero, ")
                .append("   g.ano_lancamento, ")
                .append("   g.plataforma, ")
                .append("   g.console, ")
                .append("   g.classificacao, ")
                .append("   g.estudio_producao, ")
                .append("   u.nome, ")
                .append("   u.id as idUsuario, ")
                .append("   g.id_jogo ")
                .append("FROM avaliacoes_game ag ")
                .append("INNER JOIN usuarios u ON ag.usuario_id = u.id ")
                .append("INNER JOIN games g ON ag.id_jogo = g.id_jogo ")
                .append("WHERE 1 = 1 ");

        if (idUsuario != null) {
            sqlBuilder.append("AND u.id = :idUsuario ");
        }

        if (idAvaliacao != null) {
            sqlBuilder.append("AND ag.id = :idAvaliacao ");
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString());

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        if (idAvaliacao != null) {
            query.setParameter("idAvaliacao", idAvaliacao);
        }

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<AvaliacaoGameDTO> dtos = new ArrayList<>();

        for (Object[] row : resultList) {
            AvaliacaoGameDTO dto = new AvaliacaoGameDTO();
            dto.setId((int) row[0]);
            dto.setTitulo((String) row[1]);
            dto.setDescricao((String) row[2]);
            dto.setGenero((String) row[3]);
            dto.setAnoLancamento((int) row[4]);
            dto.setPlataforma((String) row[5]);
            dto.setConsole((String) row[6]);
            dto.setClassificacao((String) row[7]);
            dto.setEstudioProducao((String) row[8]);
            dto.setNomeUsuario((String) row[9]);
            dto.setIdUsuario((Long) row[10]);
            dto.setIdJogo((Long) row[11]);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }
}
