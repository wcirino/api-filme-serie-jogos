package com.apifilmeseries.entity;

import java.io.Serializable;

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
@Table(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jogo")
    private Long idJogo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "genero")
    private String genero;

    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

    @Column(name = "plataforma")
    private String plataforma;

    @Column(name = "estudio_producao")
    private String estudioProducao;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "imagem_url_jogo")
    private String imagemUrlJogo;

    @Column(name = "imagem_url_plataforma")
    private String imagemUrlPlataforma;

    @Column(name = "console")
    private String console;

    // Getters e Setters
}
