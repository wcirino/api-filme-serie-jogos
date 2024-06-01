package com.apifilmeseries.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.apifilmeseries.dto.GameDTO;
import com.apifilmeseries.entity.Game;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.GameRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GameService gameService;
    
    Page<Game> jogosPage;
    
    GameDTO dto;
    
    Game jogoExistente;
    
    @BeforeEach
    public void setUp() {
        Pageable pageable = Pageable.ofSize(10);
        jogosPage = criarPaginaDeJogos(5, pageable);
        jogoExistente = this.criarJogo(1);
        dto = criarGameDTO(1L);
    }


    @SuppressWarnings("unchecked")
	@Test
    public void testBuscarJogosComParametros() {
        String titulo = "Exemplo de título";
        String genero = "Exemplo de gênero";
        Integer anoLancamento = 2022;
        String plataforma = "Exemplo de plataforma";
        String console = "Exemplo de console";
        
        Pageable pageable = Pageable.ofSize(10);
        List<Game> jogos = new ArrayList<>();
        
        when(gameRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(jogosPage);

        Page<Game> resultado = gameService.buscarJogos(titulo, genero, anoLancamento, plataforma, console, pageable);

        verify(gameRepository).findAll(any(Specification.class), eq(pageable));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBuscarJogosSemParametros() {
        Pageable pageable = Pageable.ofSize(10);
        List<Game> jogos = new ArrayList<>();
        Page<Game> jogosPage = new PageImpl<>(jogos, pageable, jogos.size());
        
        when(gameRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(jogosPage);

        Page<Game> resultado = gameService.buscarJogos(null, null, null, null, null, pageable);

        verify(gameRepository).findAll(any(Specification.class), eq(pageable));
    }
        
    @Test
    void testBuscarJogoDTOPorIdExistente() {
    	 Long idJogoExistente = 1L;
         Game jogoExistente = criarJogo(idJogoExistente);
         GameDTO jogoExistenteDTO = criarGameDTO(idJogoExistente);
         
        when(gameRepository.findById(idJogoExistente)).thenReturn(Optional.of(jogoExistente));

        when(modelMapper.map(jogoExistente, GameDTO.class)).thenReturn(jogoExistenteDTO);

        GameDTO result = gameService.buscarJogoDTOPorId(idJogoExistente);

        assertEquals(jogoExistente.getIdJogo(), result.getIdJogo());
        
        verify(gameRepository).findById(idJogoExistente);
    }



    @Test
    public void testBuscarJogoDTOPorIdInexistente() {
        Long idJogoInexistente = 100L;
        
        when(gameRepository.findById(idJogoInexistente)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> gameService.buscarJogoDTOPorId(idJogoInexistente));
        
        verify(gameRepository).findById(idJogoInexistente);
    }

    @Test
    public void testFindAll() {
        List<Game> jogos = new ArrayList<>();
        
        when(gameRepository.findAll()).thenReturn(jogos);
        
        List<Game> jogosEncontrados = gameService.findAll();
        
        assertEquals(jogos, jogosEncontrados);
        
        verify(gameRepository).findAll();
    }
    
    private Page<Game> criarPaginaDeJogos(int quantidade, Pageable pageable) {
        List<Game> jogos = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Game jogo = criarJogo(i);
            jogos.add(jogo);
        }
        return new PageImpl<>(jogos, pageable, jogos.size());
    }

    private Game criarJogo(long id) {
        Game jogo = new Game();
        jogo.setIdJogo(id);
        jogo.setTitulo("Título do Jogo " + id);
        jogo.setDescricao("Descrição do Jogo " + id);
        jogo.setGenero("Gênero do Jogo " + id);
        jogo.setAnoLancamento(2000 + (int) id);
        jogo.setPlataforma("Plataforma do Jogo " + id);
        jogo.setEstudioProducao("Estúdio de Produção do Jogo " + id);
        jogo.setClassificacao("Classificação do Jogo " + id);
        jogo.setImagemUrlJogo("URL da Imagem do Jogo " + id);
        jogo.setImagemUrlPlataforma("URL da Imagem da Plataforma do Jogo " + id);
        jogo.setConsole("Console do Jogo " + id);
        return jogo;
    }
    
    public GameDTO criarGameDTO(Long id) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setIdJogo(id);
        gameDTO.setTitulo("Título do Jogo " + id);
        gameDTO.setDescricao("Descrição do Jogo " + id);
        gameDTO.setGenero("Gênero do Jogo " + id);
        gameDTO.setAnoLancamento(2000 + id.intValue());
        gameDTO.setPlataforma("Plataforma do Jogo " + id);
        gameDTO.setEstudioProducao("Estúdio de Produção do Jogo " + id);
        gameDTO.setClassificacao("Classificação do Jogo " + id);
        gameDTO.setImagemUrlJogo("URL da Imagem do Jogo " + id);
        gameDTO.setImagemUrlPlataforma("URL da Imagem da Plataforma do Jogo " + id);
        gameDTO.setConsole("Console do Jogo " + id);
        return gameDTO;
    }
}
