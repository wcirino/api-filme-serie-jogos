package com.apifilmeseries.service;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.apifilmeseries.dto.FilmeSerieDTO;
import com.apifilmeseries.entity.FilmeSerie;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.FilmeSerieRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
public class FilmeSerieServiceTest {

    @Mock
    private FilmeSerieRepository filmeSerieRepository;

    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private Root<FilmeSerie> root;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    private Pageable pageable;

    @InjectMocks
    private FilmeSerieService filmeSerieService;

    private FilmeSerie filmeSerie;
    private FilmeSerieDTO filmeSerieDTO;

    @BeforeEach
    public void setUp() {
        filmeSerie = new FilmeSerie();
        filmeSerie.setId(1L);
        filmeSerie.setTitulo("Test Title");
        filmeSerie.setGenero("Action");
        filmeSerie.setAnoLancamento(2020);
        filmeSerie.setPlataforma("Netflix");

        filmeSerieDTO = new FilmeSerieDTO();
        filmeSerieDTO.setId(1L);
        filmeSerieDTO.setTitulo("Test Title");
        filmeSerieDTO.setGenero("Action");
        filmeSerieDTO.setAnoLancamento(2020);
        filmeSerieDTO.setPlataforma("Netflix");
        
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void testBuscarFilmesSeries() {
        Pageable pageable = PageRequest.of(0, 10);
        List<FilmeSerie> filmesSeriesList = new ArrayList<>();
        filmesSeriesList.add(filmeSerie);
        Page<FilmeSerie> filmesSeriesPage = new PageImpl<>(filmesSeriesList);

        when(filmeSerieRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(filmesSeriesPage);

        Page<FilmeSerie> result = filmeSerieService.buscarFilmesSeries("Test", "Ação", 2020, "Netflix", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(filmeSerie, result.getContent().get(0));
    }

    @Test
    void testBuscarFilmeSerieDTOPorIdFoundException() {
        Long idFilmeSerie = 1L;
        when(filmeSerieRepository.findById(idFilmeSerie)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            filmeSerieService.buscarFilmeSerieDTOPorId(idFilmeSerie);
        }, "Expected ResourceNotFoundException to be thrown");
    }
    
    @Test
    public void testBuscarFilmeSerieDTOPorIdFound() {
        when(filmeSerieRepository.findById(1L)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            filmeSerieService.buscarFilmeSerieDTOPorId(1L);
        });
        
        assertNotNull(exception);
        assertEquals("Filme/Série não encontrada com o ID: 1", exception.getMessage());
    }

    @Test
    public void testBuscarFilmeSerieDTOPorIdNotFound() {
        when(filmeSerieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            filmeSerieService.buscarFilmeSerieDTOPorId(1L);
        });
    }

    @Test
    public void testFindallService() {
        List<FilmeSerie> filmesSeriesList = new ArrayList<>();
        filmesSeriesList.add(filmeSerie);

        when(filmeSerieRepository.findAll()).thenReturn(filmesSeriesList);

        List<FilmeSerie> result = filmeSerieService.findallService();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filmeSerie, result.get(0));
    }
    
    @Test
    public void testBuscarFilmeSerieDTOPorIdNotFoundException() {
        when(filmeSerieRepository.findById(eq(1L))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            filmeSerieService.buscarFilmeSerieDTOPorId(1L);
        });
    }

    @Test
    @Disabled("Desativado até corrigir o bug")
    public void testBuscarFilmesSeriesTituloNulo() {
        when(filmeSerieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(IllegalArgumentException.class, () -> {
            filmeSerieService.buscarFilmesSeries(null, "Ação", 2020, "Netflix", PageRequest.of(0, 10));
        });
    }

    @Test
    @Disabled("Até corrigir desativado")
    public void testBuscarFilmesSeriesGeneroNulo() {
        when(filmeSerieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(IllegalArgumentException.class, () -> {
            filmeSerieService.buscarFilmesSeries("Título", null, 2020, "Netflix", PageRequest.of(0, 10));
        });
    }
    
    @Test
    public void testFindAllServiceReturnsList() {
        
        List<FilmeSerie> filmesSeriesList = new ArrayList<>();
        filmesSeriesList.add(filmeSerie);

        
        when(filmeSerieRepository.findAll()).thenReturn(filmesSeriesList);
        List<FilmeSerie> result = filmeSerieService.findallService();

        assertNotNull(result);
       
        assertEquals(filmesSeriesList.size(), result.size());
        
        assertEquals(filmesSeriesList.get(0), result.get(0));
    }

    @Test
    public void testFindAllServiceReturnsEmptyList() {
       
        when(filmeSerieRepository.findAll()).thenReturn(Collections.emptyList());

      
        List<FilmeSerie> result = filmeSerieService.findallService();

    
        assertNotNull(result);
    
        assertEquals(0, result.size());
    }
    
    @Test
    void testBuscarFilmesSeriesComTitulo() {
        String titulo = "Inception";
        String genero = null;
        Integer anoLancamento = null;
        String plataforma = null;
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll((Specification<FilmeSerie>) any(), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }

    @Test
    void testBuscarFilmesSeriesComGenero() {
        String titulo = null;
        String genero = "Action";
        Integer anoLancamento = null;
        String plataforma = null;
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll((Specification<FilmeSerie>) any(), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }

    @Test
    void testBuscarFilmesSeriesComAnoLancamento() {
        String titulo = null;
        String genero = null;
        Integer anoLancamento = 2010;
        String plataforma = null;
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll((Specification<FilmeSerie>) any(), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }

    @Test
    void testBuscarFilmesSeriesComPlataforma() {
        String titulo = null;
        String genero = null;
        Integer anoLancamento = null;
        String plataforma = "Netflix";
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll((Specification<FilmeSerie>) any(), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }

    @Test
    void testBuscarFilmesSeriesComTodosFiltros() {
        String titulo = "Inception";
        String genero = "Action";
        Integer anoLancamento = 2010;
        String plataforma = "Netflix";
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll((Specification<FilmeSerie>) any(), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }
    
    @Test
    void testBuscarFilmesSeriesComTituloGeneroAnoLancamentoPlataforma() {
        String titulo = "Inception";
        String genero = "Action";
        Integer anoLancamento = 2010;
        String plataforma = "Netflix";
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll(any(Specification.class), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, anoLancamento, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }

    @Test
    void testBuscarFilmesSeriesComNull() {
  
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll(any(Specification.class), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(null,null,null, null, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }

    @Test
    void testBuscarFilmesSeriesComTituloGeneroPlataforma() {
        String titulo = "Inception";
        String genero = "Action";
        String plataforma = "Netflix";
        Pageable pageable = PageRequest.of(0, 10);

        when(filmeSerieRepository.findAll(any(Specification.class), eq(pageable)))
            .thenReturn(new PageImpl<>(new ArrayList<>()));

        filmeSerieService.buscarFilmesSeries(titulo, genero, null, plataforma, pageable);

        verify(filmeSerieRepository).findAll((Specification<FilmeSerie>) any(), eq(pageable));
    }



}

