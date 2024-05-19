package com.apifilmeseries.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.FavoritoDTO;
import com.apifilmeseries.entity.Favorito;
import com.apifilmeseries.repository.FavoritoRepository;

@Service
public class FavoritoService {

	@Autowired
    private final FavoritoRepository favoritoRepository;
	
	@Autowired
    private final ModelMapper modelMapper;

    public FavoritoService(FavoritoRepository favoritoRepository, ModelMapper modelMapper) {
        this.favoritoRepository = favoritoRepository;
        this.modelMapper = modelMapper;
    }

    public FavoritoDTO adicionarFavorito(FavoritoDTO favoritoDTO) {
        Favorito favorito = modelMapper.map(favoritoDTO, Favorito.class);
        favorito.setDataAdicao(new Date());
        Favorito novoFavorito = favoritoRepository.save(favorito);
        return modelMapper.map(novoFavorito, FavoritoDTO.class);
    }

    public List<FavoritoDTO> recuperarFavoritosPorUsuario(Long idUsuario) {
        List<Favorito> favoritos = favoritoRepository.findByIdUsuario(idUsuario);
        return favoritos.stream()
                .map(favorito -> modelMapper.map(favorito, FavoritoDTO.class))
                .collect(Collectors.toList());
    }

    public void removerFavorito(Long id) {
        favoritoRepository.deleteById(id);
    }
}
