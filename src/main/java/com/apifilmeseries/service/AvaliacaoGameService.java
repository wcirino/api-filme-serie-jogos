package com.apifilmeseries.service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.AvaliacaoGameDTO;
import com.apifilmeseries.entity.AvaliacaoGame;
import com.apifilmeseries.repository.AvaliacaoGameRepository;

@Service
public class AvaliacaoGameService {

	@Autowired
    private final AvaliacaoGameRepository avaliacaoGameRepository;
	
	@Autowired
    private final ModelMapper modelMapper;

    public AvaliacaoGameService(AvaliacaoGameRepository avaliacaoGameRepository, ModelMapper modelMapper) {
        this.avaliacaoGameRepository = avaliacaoGameRepository;
        this.modelMapper = modelMapper;
    }

    public AvaliacaoGameDTO adicionarAvaliacao(AvaliacaoGameDTO avaliacaoGameDTO) {
        AvaliacaoGame avaliacao = modelMapper.map(avaliacaoGameDTO, AvaliacaoGame.class);
        AvaliacaoGame novaAvaliacao = avaliacaoGameRepository.save(avaliacao);
        return modelMapper.map(novaAvaliacao, AvaliacaoGameDTO.class);
    }

    public List<AvaliacaoGameDTO> recuperarAvaliacoesPorJogo(Long idJogo) {
        List<AvaliacaoGame> avaliacoes = avaliacaoGameRepository.findByJogoId(idJogo);
        return avaliacoes.stream()
                .map(avaliacao -> modelMapper.map(avaliacao, AvaliacaoGameDTO.class))
                .collect(Collectors.toList());
    }

    public void excluirAvaliacao(Long idAvaliacao) {
        avaliacaoGameRepository.deleteById(idAvaliacao);
    }
}

