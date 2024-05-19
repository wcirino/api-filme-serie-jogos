package com.apifilmeseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoConsultaDTO {
    private String nomeUsuario;
    private String tituloFilmeSerie;
    private String genero;
    private String classificacao;
    private int duracao;
    private String plataforma;
    private int anoLancamento;
    private String comentario;
    private int numMatricula;
    private int idFilmeSerie;
    private int idAvaliacao;
}