package com.apifilmeseries.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apifilmeseries.entity.Game;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.GameRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Page<Game> buscarJogos(String titulo, String genero, Integer anoLancamento, String plataforma, String console, Pageable pageable) {
        return gameRepository.findAll((root, query, criteriaBuilder) -> {
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

            if (console != null && !console.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("console"), console));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
    
    public Game buscarJogoPorId(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID: " + id));
    }
}
