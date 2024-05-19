package com.apifilmeseries.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoDTO {
    private Long id;
    private Long idUsuario;
    private Long idFilmeSerie;
    private Date dataAdicao;
}
