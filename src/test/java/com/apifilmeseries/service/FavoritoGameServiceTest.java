package com.apifilmeseries.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.entity.FavoritoGame;
import com.apifilmeseries.repository.FavoritoGameRepository;

@ExtendWith(MockitoExtension.class)
public class FavoritoGameServiceTest {

    @Mock
    private FavoritoGameRepository favoritoGameRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FavoritoGameService favoritoGameService;

    private FavoritoGameDTO favoritoGameDTO;
    private FavoritoGame favoritoGame;

    @BeforeEach
    void setUp() {
        favoritoGameDTO = new FavoritoGameDTO();
        favoritoGameDTO.setId(1L);
        // Adicione outras propriedades conforme necessário

        favoritoGame = new FavoritoGame();
        favoritoGame.setId(1L);
        // Adicione outras propriedades conforme necessário
    }

    @Test
    void adicionarFavorito_DeveRetornarFavoritoGameDTO() {
        when(modelMapper.map(favoritoGameDTO, FavoritoGame.class)).thenReturn(favoritoGame);
        when(favoritoGameRepository.save(any(FavoritoGame.class))).thenReturn(favoritoGame);
        when(modelMapper.map(favoritoGame, FavoritoGameDTO.class)).thenReturn(favoritoGameDTO);

        FavoritoGameDTO result = favoritoGameService.adicionarFavorito(favoritoGameDTO);

        assertEquals(favoritoGameDTO, result);
    }

    @Test
    void recuperarFavoritosPorUsuario_DeveRetornarListaFavoritoGameDTO() {
        Long usuarioId = 1L;
        List<FavoritoGame> favoritosGame = new ArrayList<>();
        favoritosGame.add(favoritoGame);
        when(favoritoGameRepository.findByUsuarioId(usuarioId)).thenReturn(favoritosGame);
        when(modelMapper.map(favoritoGame, FavoritoGameDTO.class)).thenReturn(favoritoGameDTO);

        List<FavoritoGameDTO> result = favoritoGameService.recuperarFavoritosPorUsuario(usuarioId);

        assertEquals(favoritosGame.stream().map(f -> favoritoGameDTO).collect(Collectors.toList()), result);
    }

    @Test
    void removerFavorito_DeveChamarDeleteById() {
        Long id = 1L;
        favoritoGameService.removerFavorito(id);
        verify(favoritoGameRepository).deleteById(id);
    }
}

