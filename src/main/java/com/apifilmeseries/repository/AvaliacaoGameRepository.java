package com.apifilmeseries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apifilmeseries.entity.AvaliacaoGame;

@Repository
public interface AvaliacaoGameRepository extends JpaRepository<AvaliacaoGame, Long> {
	List<AvaliacaoGame> findByJogoId(Long jogoId);
}
