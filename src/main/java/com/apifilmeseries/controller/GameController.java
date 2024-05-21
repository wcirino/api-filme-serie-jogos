package com.apifilmeseries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apifilmeseries.dto.GameDTO;
import com.apifilmeseries.entity.Game;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.service.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/search")
    public ResponseEntity<Page<Game>> buscarJogos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer anoLancamento,
            @RequestParam(required = false) String plataforma,
            @RequestParam(required = false) String console,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Game> jogos = gameService.buscarJogos(titulo, genero, anoLancamento, plataforma,console ,pageable);
        return ResponseEntity.ok(jogos);
    }
    
    @GetMapping("/jogos/{id}")
    public ResponseEntity<?> buscarJogoPorId(@PathVariable Long id) {
        try {
            GameDTO jogoDTO = gameService.buscarJogoDTOPorId(id);
            return ResponseEntity.ok(jogoDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/find-all")
    public ResponseEntity<?> findall() {
        try {
        	  return ResponseEntity.ok(gameService.findAll());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
