package com.apifilmeseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoGameDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String genero;
    private int anoLancamento;
    private String plataforma;
    private String console;
    private String classificacao;
    private String estudioProducao;
    private String nomeUsuario;
    private Integer idUsuario;
    private Integer idJogo;

}
