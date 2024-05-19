package com.apifilmeseries.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifilmeseries.dto.FavoritoDTO;
import com.apifilmeseries.service.FavoritoService;

@RestController
@RequestMapping("/favoritos/filmes-series")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<FavoritoDTO> adicionarFavorito(@RequestBody FavoritoDTO favoritoDTO) {
        FavoritoDTO novoFavoritoDTO = favoritoService.adicionarFavorito(favoritoDTO);
        return ResponseEntity.ok(novoFavoritoDTO);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<FavoritoDTO>> recuperarFavoritosPorUsuario(@PathVariable Long idUsuario) {
        List<FavoritoDTO> favoritosDTO = favoritoService.recuperarFavoritosPorUsuario(idUsuario);
        return ResponseEntity.ok(favoritosDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFavorito(@PathVariable Long id) {
        favoritoService.removerFavorito(id);
        return ResponseEntity.noContent().build();
    }
}
