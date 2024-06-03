package com.apifilmeseries.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.apifilmeseries.dao.FilmeiSeriesDAO;
import com.apifilmeseries.dao.JogoDAO;
import com.apifilmeseries.dao.ListaJogosDAO;
import com.apifilmeseries.dto.AvaliacaoConsultaDTO;
import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.dto.FavoritoConsultaDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.dto.ListaGamesDTO;

import java.util.Collections;

public class UtilServiceTest {

    @Mock
    private ListaJogosDAO listaJogosDAO;

    @Mock
    private JogoDAO jogoDAO;

    @Mock
    private FilmeiSeriesDAO filmeiSeriesDAO;

    @InjectMocks
    private UtilService utilService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarListaJogos() {
        Page<ListaGamesDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(listaJogosDAO.consultarListaJogos(anyLong(), anyLong(), any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);
        Page<ListaGamesDTO> result = utilService.consultarListaJogos(1L, 1L, pageable);

        assertEquals(mockPage, result);
    }

    @Test
    public void testConsultarFavoritosGame() {
        Page<FavoritoGameDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(jogoDAO.consultarFavoritosGame(anyLong(), anyLong(), any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);
        Page<FavoritoGameDTO> result = utilService.consultarFavoritosGame(1L, 1L, pageable);

        assertEquals(mockPage, result);
    }

    @Test
    public void testConsultarAvaliacoesGame() {
        Page<AvaliacaoGameDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(jogoDAO.consultarAvaliacoesGame(anyLong(), anyLong(), any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);
        Page<AvaliacaoGameDTO> result = utilService.consultarAvaliacoesGame(1L, 1L, pageable);

        assertEquals(mockPage, result);
    }

    @Test
    public void testConsultarFavoritos() {
        Page<FavoritoConsultaDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(filmeiSeriesDAO.consultarFavoritos(anyLong(), anyLong(), any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);
        Page<FavoritoConsultaDTO> result = utilService.consultarFavoritos(1L, 1L, pageable);

        assertEquals(mockPage, result);
    }

    @Test
    public void testConsultarAvaliacoes() {
        Page<AvaliacaoConsultaDTO> mockPage = new PageImpl<>(Collections.emptyList());
        when(filmeiSeriesDAO.consultarAvaliacoes(anyLong(), anyLong(), any(Pageable.class))).thenReturn(mockPage);

        Pageable pageable = PageRequest.of(0, 10);
        Page<AvaliacaoConsultaDTO> result = utilService.consultarAvaliacoes(1L, 1L, pageable);

        assertEquals(mockPage, result);
    }

    @Test
    public void testTipoListaGame() {
        assertEquals("desejado", utilService.tipoListaGame(1));
        assertEquals("jogado", utilService.tipoListaGame(2));
    }
}

