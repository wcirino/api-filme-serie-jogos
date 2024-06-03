package com.apifilmeseries.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.apifilmeseries.dto.ListaJogosDTO;
import com.apifilmeseries.entity.ListaJogos;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.ListaJogosRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class ListaJogosServiceTest {

    @Mock
    private ListaJogosRepository listaJogosRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ListaJogosService listaJogosService;

    @Test
    void testRecuperarJogosPorUsuarioQuandoTipoAcaoNulo() {
        Long idUsuario = 1L;
        when(listaJogosRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList(new ListaJogos(), new ListaJogos()));

        List<ListaJogosDTO> result = listaJogosService.recuperarJogosPorUsuario(idUsuario, null);

        assertEquals(2, result.size());
    }

    @Test
    void testRecuperarJogosPorUsuarioQuandoTipoAcaoNaoNulo() {
        Long idUsuario = 1L;
        String tipoAcao = "adicionar";
        when(listaJogosRepository.findByIdUsuarioAndTipoAcao(idUsuario, tipoAcao)).thenReturn(Arrays.asList(new ListaJogos(), new ListaJogos()));

        List<ListaJogosDTO> result = listaJogosService.recuperarJogosPorUsuario(idUsuario, tipoAcao);

        assertEquals(2, result.size());
    }
    
    @Test
    void testAdicionarJogoQuandoListaJogosVazia() {
        ListaJogosDTO listaJogosDTO = new ListaJogosDTO();
        ListaJogos listaJogos = new ListaJogos();
        when(modelMapper.map(listaJogosDTO, ListaJogos.class)).thenReturn(listaJogos);
        when(listaJogosRepository.save(listaJogos)).thenReturn(listaJogos);
        when(modelMapper.map(listaJogos, ListaJogosDTO.class)).thenReturn(listaJogosDTO);

        ListaJogosDTO result = listaJogosService.adicionarJogo(listaJogosDTO);

        assertEquals(listaJogosDTO, result);
    }

    @Test
    void testAtualizarJogoQuandoJogoEncontrado() {
        Long id = 1L;
        ListaJogosDTO listaJogosDTO = new ListaJogosDTO();
        listaJogosDTO.setId(id);
        ListaJogos listaJogos = new ListaJogos();
        listaJogos.setId(id);
        when(listaJogosRepository.findById(id)).thenReturn(Optional.of(listaJogos));
        when(listaJogosRepository.save(listaJogos)).thenReturn(listaJogos);
        when(modelMapper.map(listaJogos, ListaJogosDTO.class)).thenReturn(listaJogosDTO);

        ListaJogosDTO result = listaJogosService.atualizarJogo(id, listaJogosDTO);

        assertEquals(listaJogosDTO, result);
    }

    @Test
    void testRemoverJogoQuandoJogoEncontrado() {
        Long id = 1L;

        listaJogosService.removerJogo(id);

        verify(listaJogosRepository).deleteById(id);
    }
    
    @Test
    void testAtualizarJogoQuandoJogoNaoEncontrado() {
        Long id = 1L;
        ListaJogosDTO listaJogosDTO = new ListaJogosDTO();
        listaJogosDTO.setId(id);
        when(listaJogosRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> listaJogosService.atualizarJogo(id, listaJogosDTO));
    }

}

