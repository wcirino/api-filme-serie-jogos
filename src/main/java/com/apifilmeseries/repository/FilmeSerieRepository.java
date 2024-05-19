package com.apifilmeseries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.apifilmeseries.entity.FilmeSerie;

@Repository
public interface FilmeSerieRepository extends JpaRepository<FilmeSerie, Long>, JpaSpecificationExecutor<FilmeSerie> {
}

