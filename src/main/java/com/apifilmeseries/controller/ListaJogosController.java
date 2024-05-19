package com.apifilmeseries.controller;

import com.apifilmeseries.dto.ListaJogosDTO;
import com.apifilmeseries.service.ListaJogosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista-jogos")
public class ListaJogosController {

	@Autowired
    private final ListaJogosService listaJogosService;

    public ListaJogosController(ListaJogosService listaJogosService) {
        this.listaJogosService = listaJogosService;
    }

    @PostMapping("/inserir")
    public ResponseEntity<ListaJogosDTO> adicionarJogo(@RequestBody ListaJogosDTO listaJogosDTO) {
        ListaJogosDTO novoJogo = listaJogosService.adicionarJogo(listaJogosDTO);
        return ResponseEntity.ok(novoJogo);
    }

    @GetMapping("find/{idUsuario}")
    public ResponseEntity<List<ListaJogosDTO>> recuperarJogosPorUsuario(@PathVariable Long idUsuario, 
                                                                        @RequestParam(required = false) String tipoAcao) {
        List<ListaJogosDTO> jogos = listaJogosService.recuperarJogosPorUsuario(idUsuario, tipoAcao);
        return ResponseEntity.ok(jogos);
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<ListaJogosDTO> atualizarJogo(@PathVariable Long id, @RequestBody ListaJogosDTO listaJogosDTO) {
        ListaJogosDTO atualizadoJogo = listaJogosService.atualizarJogo(id, listaJogosDTO);
        return ResponseEntity.ok(atualizadoJogo);
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<Void> removerJogo(@PathVariable Long id) {
        listaJogosService.removerJogo(id);
        return ResponseEntity.noContent().build();
    }
}
