package com.apifilmeseries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifilmeseries.entity.FavoritoGame;

@Repository
public interface FavoritoGameRepository extends JpaRepository<FavoritoGame, Long> {
	 List<FavoritoGame> findByUsuarioId(Long usuarioId);
}
