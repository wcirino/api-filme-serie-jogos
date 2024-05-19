package com.apifilmeseries.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apifilmeseries.entity.FilmeSerie;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.FilmeSerieRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class FilmeSerieService {

    private final FilmeSerieRepository filmeSerieRepository;

    public FilmeSerieService(FilmeSerieRepository filmeSerieRepository) {
        this.filmeSerieRepository = filmeSerieRepository;
    }

    public Page<FilmeSerie> buscarFilmesSeries(String titulo, String genero, Integer anoLancamento, String plataforma, Pageable pageable) {
        return filmeSerieRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (titulo != null && !titulo.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%"));
            }

            if (genero != null && !genero.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("genero"), genero));
            }

            if (anoLancamento != null) {
                predicates.add(criteriaBuilder.equal(root.get("anoLancamento"), anoLancamento));
            }

            if (plataforma != null && !plataforma.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("plataforma"), plataforma));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
    
    public FilmeSerie buscarFilmeSeriePorId(Long id) {
        return filmeSerieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme/Série não encontrada com o ID: " + id));
    }
}
