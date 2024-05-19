package com.apifilmeseries.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lista_jogos")
public class ListaJogos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "id_jogo")
    private Long idJogo;
    
    @Column(name = "data_acao")
    private Date dataAcao;
    
    @Column(name = "tipo_acao")
    private String tipoAcao;
    
    @Column(name = "classificacao")
    private Integer classificacao;
    
    @Column(name = "comentario")
    private String comentario;

}

