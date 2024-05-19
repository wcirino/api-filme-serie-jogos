package com.apifilmeseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeSerieDTO {
    private Long id;
    private String titulo;
    private String sinopse;
    private String genero;
    private int anoLancamento;
    private String elenco;
    private String classificacao;
    private String diretor;
    private int duracao;
    private String imagemUrl;
    private String plataforma;
    private int tipo;
}
