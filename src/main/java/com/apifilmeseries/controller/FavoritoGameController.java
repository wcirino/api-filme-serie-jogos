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

import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.service.FavoritoGameService;

@RestController
@RequestMapping("/favoritos/games")
public class FavoritoGameController {

    private final FavoritoGameService favoritoGameService;

    public FavoritoGameController(FavoritoGameService favoritoGameService) {
        this.favoritoGameService = favoritoGameService;
    }

    @PostMapping
    public ResponseEntity<FavoritoGameDTO> adicionarFavorito(@RequestBody FavoritoGameDTO favoritoGameDTO) {
        FavoritoGameDTO novoFavoritoGameDTO = favoritoGameService.adicionarFavorito(favoritoGameDTO);
        return ResponseEntity.ok(novoFavoritoGameDTO);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<FavoritoGameDTO>> recuperarFavoritosPorUsuario(@PathVariable Long usuarioId) {
        List<FavoritoGameDTO> favoritosGameDTO = favoritoGameService.recuperarFavoritosPorUsuario(usuarioId);
        return ResponseEntity.ok(favoritosGameDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerFavorito(@PathVariable Long id) {
        favoritoGameService.removerFavorito(id);
        return ResponseEntity.noContent().build();
    }
}
