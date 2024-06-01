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

import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.entity.AvaliacaoGame;
import com.apifilmeseries.repository.AvaliacaoGameRepository;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoGameServiceTest {

    @Mock
    private AvaliacaoGameRepository avaliacaoGameRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AvaliacaoGameService avaliacaoGameService;

    private AvaliacaoGameDTO avaliacaoGameDTO;
    private AvaliacaoGame avaliacaoGame;

    @BeforeEach
    void setUp() {
        avaliacaoGameDTO = new AvaliacaoGameDTO();
        avaliacaoGameDTO.setId(1L);
        // Adicione outras propriedades conforme necessário

        avaliacaoGame = new AvaliacaoGame();
        avaliacaoGame.setId(1L);
        // Adicione outras propriedades conforme necessário
    }

    @Test
    void adicionarAvaliacao_DeveRetornarAvaliacaoDTO() {
        when(modelMapper.map(avaliacaoGameDTO, AvaliacaoGame.class)).thenReturn(avaliacaoGame);
        when(avaliacaoGameRepository.save(any(AvaliacaoGame.class))).thenReturn(avaliacaoGame);
        when(modelMapper.map(avaliacaoGame, AvaliacaoGameDTO.class)).thenReturn(avaliacaoGameDTO);

        AvaliacaoGameDTO result = avaliacaoGameService.adicionarAvaliacao(avaliacaoGameDTO);

        assertEquals(avaliacaoGameDTO, result);
    }

    @Test
    void recuperarAvaliacoesPorJogo_DeveRetornarListaAvaliacoesDTO() {
        Long idJogo = 1L;
        when(avaliacaoGameRepository.findByJogoId(idJogo)).thenReturn(Collections.singletonList(avaliacaoGame));
        when(modelMapper.map(avaliacaoGame, AvaliacaoGameDTO.class)).thenReturn(avaliacaoGameDTO);

        List<AvaliacaoGameDTO> result = avaliacaoGameService.recuperarAvaliacoesPorJogo(idJogo);

        assertEquals(Collections.singletonList(avaliacaoGameDTO), result);
    }

    @Test
    void excluirAvaliacao_DeveChamarDeleteById() {
        Long idAvaliacao = 1L;
        avaliacaoGameService.excluirAvaliacao(idAvaliacao);
        verify(avaliacaoGameRepository).deleteById(idAvaliacao);
    }
}
