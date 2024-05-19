package com.apifilmeseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    private Long id;
    private Long idUsuario;
    private Long idFilmeSerie;
    private int classificacao;
    private String comentario;
}