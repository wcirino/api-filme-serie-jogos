package com.apifilmeseries.controller;

import java.util.List;

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

import com.apifilmeseries.dto.FilmeSerieDTO;
import com.apifilmeseries.entity.FilmeSerie;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.service.FilmeSerieService;

@RestController
@RequestMapping("/api/filmes-series")
public class FilmeSerieController {

    @Autowired
    private FilmeSerieService filmeSerieService;

    @GetMapping("/search")
    public ResponseEntity<Page<FilmeSerie>> buscarFilmesSeries(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer anoLancamento,
            @RequestParam(required = false) String plataforma,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FilmeSerie> filmesSeries = filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);
        return ResponseEntity.ok(filmesSeries);
    }
    
    @GetMapping("/filmes-series/{id}")
    public ResponseEntity<FilmeSerieDTO> buscarFilmeSeriePorId(@PathVariable Long id) {
        try {
            FilmeSerieDTO filmeSerieDTO = filmeSerieService.buscarFilmeSerieDTOPorId(id);
            return ResponseEntity.ok(filmeSerieDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/find-all")
    public ResponseEntity<List<FilmeSerie>> findAll() {
        try {
            List<FilmeSerie> dto = filmeSerieService.findallService();
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

