package Specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.apifilmeseries.entity.Game;

import jakarta.persistence.criteria.Predicate;

@Component
public class GameSpecifications {

    public static Specification<Game> filterGames(Long idJogo, String titulo, String genero,
                                                   Integer anoLancamento, String plataforma,
                                                   String estudioProducao, String console) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            
            if (idJogo != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("idJogo"), idJogo));
            }
            if (titulo != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("titulo"), titulo));
            }
            if (genero != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("genero"), genero));
            }
            if (anoLancamento != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("anoLancamento"), anoLancamento));
            }
            if (plataforma != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("plataforma"), plataforma));
            }
            if (estudioProducao != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("estudioProducao"), estudioProducao));
            }
            if (console != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("console"), console));
            }
            
            return predicate;
        };
    }
}

