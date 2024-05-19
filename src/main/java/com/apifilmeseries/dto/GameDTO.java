package com.apifilmeseries.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idJogo;
    private String titulo;
    private String descricao;
    private String genero;
    private Integer anoLancamento;
    private String plataforma;
    private String estudioProducao;
    private String classificacao;
    private String imagemUrlJogo;
    private String imagemUrlPlataforma;
    private String console;

}

