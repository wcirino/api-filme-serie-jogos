package Specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.apifilmeseries.entity.FilmeSerie;

import jakarta.persistence.criteria.Predicate;

@Component
public class FilmeSerieSpecifications {

    public static Specification<FilmeSerie> filterFilmeSeries(String titulo, String genero,
                                                               int anoLancamento, String plataforma,
                                                               int tipo) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            
            if (titulo != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("titulo"), titulo));
            }
            if (genero != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("genero"), genero));
            }
            if (anoLancamento != 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("anoLancamento"), anoLancamento));
            }
            if (plataforma != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("plataforma"), plataforma));
            }
            if (tipo != 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("tipo"), tipo));
            }
            
            return predicate;
        };
    }
}
