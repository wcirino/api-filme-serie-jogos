package com.apifilmeseries.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.AvaliacaoDTO;
import com.apifilmeseries.entity.Avaliacao;
import com.apifilmeseries.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
    private final AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
    private final ModelMapper modelMapper;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, ModelMapper modelMapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.modelMapper = modelMapper;
    }

    public AvaliacaoDTO adicionarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = modelMapper.map(avaliacaoDTO, Avaliacao.class);
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        return modelMapper.map(novaAvaliacao, AvaliacaoDTO.class);
    }

    public List<AvaliacaoDTO> recuperarAvaliacoesPorFilmeSerie(Long idFilmeSerie) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByIdFilmeSerie(idFilmeSerie);
        return avaliacoes.stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList());
    }

    public void excluirAvaliacao(Long idAvaliacao) {
        avaliacaoRepository.deleteById(idAvaliacao);
    }
}

