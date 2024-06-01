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

import com.apifilmeseries.dto.FavoritoDTO;
import com.apifilmeseries.entity.Favorito;
import com.apifilmeseries.repository.FavoritoRepository;

@ExtendWith(MockitoExtension.class)
public class FavoritoServiceTest {

    @Mock
    private FavoritoRepository favoritoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FavoritoService favoritoService;

    private FavoritoDTO favoritoDTO;
    private Favorito favorito;

    @BeforeEach
    void setUp() {
        favoritoDTO = new FavoritoDTO();
        favoritoDTO.setId(1L);
        // Adicione outras propriedades conforme necessário

        favorito = new Favorito();
        favorito.setId(1L);
        // Adicione outras propriedades conforme necessário
    }

    @Test
    void adicionarFavorito_DeveRetornarFavoritoDTO() {
        when(modelMapper.map(favoritoDTO, Favorito.class)).thenReturn(favorito);
        when(favoritoRepository.save(any(Favorito.class))).thenReturn(favorito);
        when(modelMapper.map(favorito, FavoritoDTO.class)).thenReturn(favoritoDTO);

        FavoritoDTO result = favoritoService.adicionarFavorito(favoritoDTO);

        assertEquals(favoritoDTO, result);
    }

    @Test
    void recuperarFavoritosPorUsuario_DeveRetornarListaFavoritoDTO() {
        Long idUsuario = 1L;
        List<Favorito> favoritos = new ArrayList<>();
        favoritos.add(favorito);
        when(favoritoRepository.findByIdUsuario(idUsuario)).thenReturn(favoritos);
        when(modelMapper.map(favorito, FavoritoDTO.class)).thenReturn(favoritoDTO);

        List<FavoritoDTO> result = favoritoService.recuperarFavoritosPorUsuario(idUsuario);

        assertEquals(favoritos.stream().map(f -> favoritoDTO).collect(Collectors.toList()), result);
    }

    @Test
    void removerFavorito_DeveChamarDeleteById() {
        Long id = 1L;
        favoritoService.removerFavorito(id);
        verify(favoritoRepository).deleteById(id);
    }
}


