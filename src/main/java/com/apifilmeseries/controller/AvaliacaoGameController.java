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

import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.service.AvaliacaoGameService;

@RestController
@RequestMapping("/avaliacoes/games")
public class AvaliacaoGameController {

	@Autowired
    private final AvaliacaoGameService avaliacaoGameService;

    public AvaliacaoGameController(AvaliacaoGameService avaliacaoGameService) {
        this.avaliacaoGameService = avaliacaoGameService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoGameDTO> adicionarAvaliacao(@RequestBody AvaliacaoGameDTO avaliacaoGameDTO) {
        AvaliacaoGameDTO novaAvaliacaoGameDTO = avaliacaoGameService.adicionarAvaliacao(avaliacaoGameDTO);
        return ResponseEntity.ok(novaAvaliacaoGameDTO);
    }

    @GetMapping("/{idJogo}")
    public ResponseEntity<List<AvaliacaoGameDTO>> recuperarAvaliacoesPorJogo(@PathVariable Long idJogo) {
        List<AvaliacaoGameDTO> avaliacoesDTO = avaliacaoGameService.recuperarAvaliacoesPorJogo(idJogo);
        return ResponseEntity.ok(avaliacoesDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAvaliacao(@PathVariable Long id) {
        avaliacaoGameService.excluirAvaliacao(id);
        return ResponseEntity.noContent().build();
    }
}

