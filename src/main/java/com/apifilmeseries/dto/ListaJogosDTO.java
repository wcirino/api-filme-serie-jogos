package com.apifilmeseries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaJogosDTO {
    private Long id;
    private Long idUsuario;
    private Long idJogo;
    private Date dataAcao;
    private String tipoAcao;
    private Integer classificacao;
    private String comentario;
}
