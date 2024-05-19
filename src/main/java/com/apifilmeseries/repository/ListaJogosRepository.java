package com.apifilmeseries.repository;

import com.apifilmeseries.entity.ListaJogos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaJogosRepository extends JpaRepository<ListaJogos, Long> {
    List<ListaJogos> findByIdUsuario(Long idUsuario);
    List<ListaJogos> findByIdUsuarioAndTipoAcao(Long idUsuario, String tipoAcao);
}
