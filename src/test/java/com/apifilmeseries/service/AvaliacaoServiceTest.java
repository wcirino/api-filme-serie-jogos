package com.apifilmeseries.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.apifilmeseries.dto.AvaliacaoDTO;
import com.apifilmeseries.entity.Avaliacao;
import com.apifilmeseries.repository.AvaliacaoRepository;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    private AvaliacaoDTO avaliacaoDTO;
    private Avaliacao avaliacao;

    @BeforeEach
    void setUp() {
        avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(1L);
        // Adicione outras propriedades conforme necessário

        avaliacao = new Avaliacao();
        avaliacao.setId(1L);
        // Adicione outras propriedades conforme necessário
    }

    @Test
    void adicionarAvaliacao_DeveRetornarAvaliacaoDTO() {
        when(modelMapper.map(avaliacaoDTO, Avaliacao.class)).thenReturn(avaliacao);
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacao);
        when(modelMapper.map(avaliacao, AvaliacaoDTO.class)).thenReturn(avaliacaoDTO);

        AvaliacaoDTO result = avaliacaoService.adicionarAvaliacao(avaliacaoDTO);

        assertEquals(avaliacaoDTO, result);
    }

    @Test
    void recuperarAvaliacoesPorFilmeSerie_DeveRetornarListaAvaliacoesDTO() {
        Long idFilmeSerie = 1L;
        when(avaliacaoRepository.findByIdFilmeSerie(idFilmeSerie)).thenReturn(Collections.singletonList(avaliacao));
        when(modelMapper.map(avaliacao, AvaliacaoDTO.class)).thenReturn(avaliacaoDTO);

        List<AvaliacaoDTO> result = avaliacaoService.recuperarAvaliacoesPorFilmeSerie(idFilmeSerie);

        assertEquals(Collections.singletonList(avaliacaoDTO), result);
    }

    @Test
    void excluirAvaliacao_DeveChamarDeleteById() {
        Long idAvaliacao = 1L;
        avaliacaoService.excluirAvaliacao(idAvaliacao);
        verify(avaliacaoRepository).deleteById(idAvaliacao);
    }
}
