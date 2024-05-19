package com.apifilmeseries.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaGamesDTO {

    private Long id;
    private String classificacao;
    private String comentario;
    private Date dataAcao;
    private Long idJogo;
    private Long idUsuario;
    private String tipoAcao;
    private String tituloJogo;
    private String plataforma;
    private String console;
    private String estudioProducao;
    private String nomeUsuario;
	
}
