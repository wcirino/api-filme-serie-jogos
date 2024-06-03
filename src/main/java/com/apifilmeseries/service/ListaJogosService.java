package com.apifilmeseries.service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.ListaJogosDTO;
import com.apifilmeseries.entity.ListaJogos;
import com.apifilmeseries.exception.ResourceNotFoundException;
import com.apifilmeseries.repository.ListaJogosRepository;

@Service
public class ListaJogosService {

	@Autowired
    private  ListaJogosRepository listaJogosRepository;
    
	@Autowired
	private  ModelMapper modelMapper;

    public ListaJogosDTO adicionarJogo(ListaJogosDTO listaJogosDTO) {
        ListaJogos listaJogos = modelMapper.map(listaJogosDTO, ListaJogos.class);
        listaJogos.setDataAcao(new Date());
        ListaJogos novoListaJogos = listaJogosRepository.save(listaJogos);
        return modelMapper.map(novoListaJogos, ListaJogosDTO.class);
    }

    public List<ListaJogosDTO> recuperarJogosPorUsuario(Long idUsuario, String tipoAcao) {
        List<ListaJogos> jogos;
        if (tipoAcao != null) {
            jogos = listaJogosRepository.findByIdUsuarioAndTipoAcao(idUsuario, tipoAcao);
        } else {
            jogos = listaJogosRepository.findByIdUsuario(idUsuario);
        }
        return jogos.stream()
                .map(jogo -> modelMapper.map(jogo, ListaJogosDTO.class))
                .collect(Collectors.toList());
    }

    public ListaJogosDTO atualizarJogo(Long id, ListaJogosDTO listaJogosDTO) {
        ListaJogos listaJogos = listaJogosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado com o ID: " + id));
        listaJogos.setTipoAcao(listaJogosDTO.getTipoAcao());
        listaJogos.setClassificacao(listaJogosDTO.getClassificacao());
        listaJogos.setComentario(listaJogosDTO.getComentario());
        ListaJogos atualizadoListaJogos = listaJogosRepository.save(listaJogos);
        return modelMapper.map(atualizadoListaJogos, ListaJogosDTO.class);
    }

    public void removerJogo(Long id) {
        listaJogosRepository.deleteById(id);
    }
}
