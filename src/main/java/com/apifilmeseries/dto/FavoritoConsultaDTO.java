package com.apifilmeseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoConsultaDTO {
    private int numMatricula;
    private Integer idFilmeSerie;
    private String nomeUsuario;
    private String titulo;
    private String sinopse;
    private String genero;
    private String elenco;
    private String classificacao;
    private int duracao;
    private String plataforma;
    private int anoLancamento;
    private String dataAdicao;
    private int idUsuario;
}