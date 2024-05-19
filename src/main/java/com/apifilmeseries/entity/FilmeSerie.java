package com.apifilmeseries.entity;

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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filme_serie")
public class FilmeSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "sinopse")
    private String sinopse;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "ano_lancamento")
    private int anoLancamento;
    
    @Column(name = "elenco")
    private String elenco;
    
    @Column(name = "classificacao")
    private String classificacao;
    
    @Column(name = "diretor")
    private String diretor;
    
    @Column(name = "duracao")
    private int duracao;
    
    @Column(name = "imagem_url")
    private String imagemUrl;
    
    @Column(name = "plataforma")
    private String plataforma;
    
    @Column(name = "tipo")
    private int tipo;
}
