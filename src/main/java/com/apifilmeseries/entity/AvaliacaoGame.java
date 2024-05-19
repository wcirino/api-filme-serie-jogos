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

@Entity
@Table(name = "avaliacoes_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "id_jogo")
    private Long jogoId;

    @Column(name = "avaliacao")
    private Integer avaliacao;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "data_avaliacao")
    private Date dataAvaliacao;

    // Getters e Setters
}
