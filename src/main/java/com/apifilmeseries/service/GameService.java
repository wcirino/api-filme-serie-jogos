package com.apifilmeseries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.GameDTO;
import com.apifilmeseries.entity.Game;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.GameRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class GameService {

	@Autowired
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    
    @Autowired
    private ModelMapper modelMapper;

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
    
    public GameDTO buscarJogoDTOPorId(Long id) {
        Optional<Game> jogoOptional = gameRepository.findById(id);
        if (jogoOptional.isPresent()) {
            Game jogo = jogoOptional.get();
            return modelMapper.map(jogo, GameDTO.class);
        } else {
            throw new ResourceNotFoundException("Jogo não encontrado com o ID: " + id);
        }
    }
    
    public  List<Game> findAll() {
        List<Game> dto = gameRepository.findAll();
        return dto;
    }
    
    
}
