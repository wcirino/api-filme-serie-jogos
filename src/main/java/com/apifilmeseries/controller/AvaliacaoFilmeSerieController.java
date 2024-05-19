package com.apifilmeseries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifilmeseries.dto.AvaliacaoDTO;
import com.apifilmeseries.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes/filmes-series")
public class AvaliacaoFilmeSerieController {

	@Autowired
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoFilmeSerieController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> adicionarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO novaAvaliacaoDTO = avaliacaoService.adicionarAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok(novaAvaliacaoDTO);
    }

    @GetMapping("/{idFilmeSerie}")
    public ResponseEntity<List<AvaliacaoDTO>> recuperarAvaliacoesPorFilmeSerie(@PathVariable Long idFilmeSerie) {
        List<AvaliacaoDTO> avaliacoesDTO = avaliacaoService.recuperarAvaliacoesPorFilmeSerie(idFilmeSerie);
        return ResponseEntity.ok(avaliacoesDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAvaliacao(@PathVariable Long id) {
        avaliacaoService.excluirAvaliacao(id);
        return ResponseEntity.noContent().build();
    }
}


