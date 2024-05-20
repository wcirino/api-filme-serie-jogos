package com.apifilmeseries.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.apifilmeseries.dto.AvaliacaoConsultaDTO;
import com.apifilmeseries.dto.FavoritoConsultaDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FilmeiSeriesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<FavoritoConsultaDTO> consultarFavoritos(Long idUsuario, Long idFilmeSerie, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append("   U.num_matricula, ")
                .append("   F.id_filme_serie, ")
                .append("   U.nome, ")
                .append("   FS.titulo, ")
                .append("   FS.sinopse, ")
                .append("   FS.genero, ")
                .append("   FS.elenco, ")
                .append("   FS.classificacao, ")
                .append("   FS.duracao, ")
                .append("   FS.plataforma, ")
                .append("   FS.ano_lancamento, ")
                .append("   F.data_adicao, ")
                .append("   FS.duracao, ")
                .append("   F.id_usuario ")
                .append("FROM favoritos F ")
                .append("INNER JOIN usuarios U ON F.id_usuario = U.id ")
                .append("INNER JOIN filme_serie FS ON F.id_filme_serie = FS.id ")
                .append("WHERE 1 = 1 ");

        if (idUsuario != null) {
            sqlBuilder.append("AND U.id = :idUsuario ");
        }

        if (idFilmeSerie != null) {
            sqlBuilder.append("AND F.id_filme_serie = :idFilmeSerie ");
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString());

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        if (idFilmeSerie != null) {
            query.setParameter("idFilmeSerie", idFilmeSerie);
        }

        // Adicionando paginação
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<FavoritoConsultaDTO> dtos = new ArrayList<>();

        for (Object[] row : resultList) {
            FavoritoConsultaDTO dto = new FavoritoConsultaDTO();
            dto.setNumMatricula((Integer) row[0]);
            dto.setIdFilmeSerie((Integer) row[1]);
            dto.setNomeUsuario((String) row[2]);
            dto.setTitulo((String) row[3]);
            dto.setSinopse((String) row[4]);
            dto.setGenero((String) row[5]);
            dto.setElenco((String) row[6]);
            dto.setClassificacao((String) row[7]);
            dto.setDuracao((int) row[8]);
            dto.setPlataforma((String) row[9]);
            dto.setAnoLancamento((int) row[10]);
            dto.setDataAdicao((String) row[11].toString());
            dto.setDuracao((int) row[12]);
            dto.setIdUsuario((Integer) row[13]);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }

    public Page<AvaliacaoConsultaDTO> consultarAvaliacoes(Long idUsuario, Long idAvaliacao, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ")
                .append("   U.nome, ")
                .append("   FS.titulo, ")
                .append("   FS.genero, ")
                .append("   A.classificacao, ")
                .append("   FS.duracao, ")
                .append("   FS.plataforma, ")
                .append("   FS.ano_lancamento, ")
                .append("   A.comentario, ")
                .append("   U.num_matricula, ")
                .append("   A.id_filme_serie, ")
                .append("   A.id, ")
                .append("   U.id ")
                .append("FROM avaliacoes AS A ")
                .append("JOIN usuarios AS U ON A.id_usuario = U.id ")
                .append("JOIN filme_serie AS FS ON A.id_filme_serie = FS.id ")
                .append("WHERE 1 = 1 ");

        if (idUsuario != null) {
            sqlBuilder.append("AND U.id = :idUsuario ");
        }

        if (idAvaliacao != null) {
            sqlBuilder.append("AND A.id = :idAvaliacao ");
        }

        Query query = entityManager.createNativeQuery(sqlBuilder.toString());

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        if (idAvaliacao != null) {
            query.setParameter("idAvaliacao", idAvaliacao);
        }

        // Adicionando paginação
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> resultList = query.getResultList();
        List<AvaliacaoConsultaDTO> dtos = new ArrayList<>();

        for (Object[] row : resultList) {
            AvaliacaoConsultaDTO dto = new AvaliacaoConsultaDTO();
            dto.setNomeUsuario((String) row[0]);
            dto.setTituloFilmeSerie((String) row[1]);
            dto.setGenero((String) row[2]);
            dto.setClassificacao((Integer) row[3]);
            dto.setDuracao((int) row[4]);
            dto.setPlataforma((String) row[5]);
            dto.setAnoLancamento((int) row[6]);
            dto.setComentario((String) row[7]);
            dto.setNumMatricula((int) row[8]);
            dto.setIdFilmeSerie((int) row[9]);
            dto.setIdAvaliacao((Integer) row[10]);
            dtos.add(dto);
        }

        return new PageImpl<>(dtos, pageable, dtos.size());
    }
}
