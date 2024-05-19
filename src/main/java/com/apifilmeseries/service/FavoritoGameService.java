package com.apifilmeseries.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.entity.FavoritoGame;
import com.apifilmeseries.repository.FavoritoGameRepository;

@Service
public class FavoritoGameService {

    private final FavoritoGameRepository favoritoGameRepository;
    private final ModelMapper modelMapper;

    public FavoritoGameService(FavoritoGameRepository favoritoGameRepository, ModelMapper modelMapper) {
        this.favoritoGameRepository = favoritoGameRepository;
        this.modelMapper = modelMapper;
    }

    public FavoritoGameDTO adicionarFavorito(FavoritoGameDTO favoritoGameDTO) {
        FavoritoGame favoritoGame = modelMapper.map(favoritoGameDTO, FavoritoGame.class);
        favoritoGame.setDataFavorito(new Date());
        FavoritoGame novoFavoritoGame = favoritoGameRepository.save(favoritoGame);
        return modelMapper.map(novoFavoritoGame, FavoritoGameDTO.class);
    }

    public List<FavoritoGameDTO> recuperarFavoritosPorUsuario(Long usuarioId) {
        List<FavoritoGame> favoritosGame = favoritoGameRepository.findByUsuarioId(usuarioId);
        return favoritosGame.stream()
                .map(favoritoGame -> modelMapper.map(favoritoGame, FavoritoGameDTO.class))
                .collect(Collectors.toList());
    }

    public void removerFavorito(Long id) {
        favoritoGameRepository.deleteById(id);
    }
}
