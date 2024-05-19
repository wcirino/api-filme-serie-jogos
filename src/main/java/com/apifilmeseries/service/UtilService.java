package com.apifilmeseries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dao.FilmeiSeriesDAO;
import com.apifilmeseries.dao.JogoDAO;
import com.apifilmeseries.dao.ListaJogosDAO;
import com.apifilmeseries.dto.AvaliacaoConsultaDTO;
import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.dto.FavoritoConsultaDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.dto.ListaGamesDTO;

@Service
public class UtilService {

    @Autowired
    private ListaJogosDAO listaJogosDAO;

    @Autowired
    private JogoDAO jogoDAO;

    @Autowired
    private FilmeiSeriesDAO filmeiSeriesDAO;

    public Page<ListaGamesDTO> consultarListaJogos(Long idListaJogos, Long idUsuario, Pageable pageable) {
        return listaJogosDAO.consultarListaJogos(idListaJogos, idUsuario, pageable);
    }

    public Page<FavoritoGameDTO> consultarFavoritosGame(Long idUsuario, Long idFavorito, Pageable pageable) {
        return jogoDAO.consultarFavoritosGame(idUsuario, idFavorito, pageable);
    }

    public Page<AvaliacaoGameDTO> consultarAvaliacoesGame(Long idUsuario, Long idAvaliacao, Pageable pageable) {
        return jogoDAO.consultarAvaliacoesGame(idUsuario, idAvaliacao, pageable);
    }

    public Page<FavoritoConsultaDTO> consultarFavoritos(Long idUsuario, Long idFilmeSerie, Pageable pageable) {
        return filmeiSeriesDAO.consultarFavoritos(idUsuario, idFilmeSerie, pageable);
    }

    public Page<AvaliacaoConsultaDTO> consultarAvaliacoes(Long idUsuario, Long idAvaliacao, Pageable pageable) {
        return filmeiSeriesDAO.consultarAvaliacoes(idUsuario, idAvaliacao, pageable);
    }
}
