package com.apifilmeseries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apifilmeseries.dto.AvaliacaoConsultaDTO;
import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.dto.FavoritoConsultaDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.dto.ListaGamesDTO;
import com.apifilmeseries.service.UtilService;

@RestController
public class UtilController {

    @Autowired
    private UtilService utilService;

    @GetMapping("/lista-jogos")
    public ResponseEntity<Page<ListaGamesDTO>> consultarListaJogos(
            @RequestParam(required = false) Long idListaJogos,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ListaGamesDTO> listaJogos = utilService.consultarListaJogos(idListaJogos, idUsuario, pageable);
        return ResponseEntity.ok(listaJogos);
    }

    @GetMapping("/favoritos-game")
    public ResponseEntity<Page<FavoritoGameDTO>> consultarFavoritosGame(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Long idFavorito,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FavoritoGameDTO> favoritosGame = utilService.consultarFavoritosGame(idUsuario, idFavorito, pageable);
        return ResponseEntity.ok(favoritosGame);
    }

    @GetMapping("/avaliacoes-game")
    public ResponseEntity<Page<AvaliacaoGameDTO>> consultarAvaliacoesGame(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Long idAvaliacao,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AvaliacaoGameDTO> avaliacoesGame = utilService.consultarAvaliacoesGame(idUsuario, idAvaliacao, pageable);
        return ResponseEntity.ok(avaliacoesGame);
    }

    @GetMapping("/favoritos")
    public ResponseEntity<Page<FavoritoConsultaDTO>> consultarFavoritos(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Long idFilmeSerie,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FavoritoConsultaDTO> favoritos = utilService.consultarFavoritos(idUsuario, idFilmeSerie, pageable);
        return ResponseEntity.ok(favoritos);
    }

    @GetMapping("/avaliacoes")
    public ResponseEntity<Page<AvaliacaoConsultaDTO>> consultarAvaliacoes(
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Long idAvaliacao,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AvaliacaoConsultaDTO> avaliacoes = utilService.consultarAvaliacoes(idUsuario, idAvaliacao, pageable);
        return ResponseEntity.ok(avaliacoes);
    }
}
