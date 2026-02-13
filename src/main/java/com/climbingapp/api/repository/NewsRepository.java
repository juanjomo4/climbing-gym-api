package com.climbingapp.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.climbingapp.api.entity.News;

/**
 * Repositorio para operaciones de base de datos sobre la entidad News. Extiende
 * JpaRepository para heredar métodos CRUD básicos.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    /**
     * Encuentra todas las noticias destacadas ordenadas por fecha de
     * publicación descendente.
     */
    List<News> findByDestacadoTrueOrderByFechaPublicacionDesc();

    /**
     * Encuentra noticias por categoría ordenadas por fecha de publicación
     * descendente.
     */
    List<News> findByCategoriaOrderByFechaPublicacionDesc(String categoria);

    /**
     * Encuentra las últimas N noticias publicadas.
     */
    @Query("SELECT n FROM News n ORDER BY n.fechaPublicacion DESC")
    List<News> findLatestNews();

    /**
     * Busca noticias por título o contenido (búsqueda parcial).
     */
    @Query("SELECT n FROM News n WHERE "
            + "LOWER(n.titulo) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
            + "LOWER(n.contenido) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
            + "LOWER(n.resumen) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<News> searchNews(@Param("searchTerm") String searchTerm);

    /**
     * Encuentra noticias publicadas después de una fecha específica.
     */
    List<News> findByFechaPublicacionAfterOrderByFechaPublicacionDesc(LocalDateTime fecha);

    /**
     * Encuentra noticias por autor.
     */
    List<News> findByAutorOrderByFechaPublicacionDesc(String autor);

    /**
     * Cuenta noticias por categoría.
     */
    @Query("SELECT COUNT(n) FROM News n WHERE n.categoria = :categoria")
    Long countByCategoria(@Param("categoria") String categoria);

    /**
     * Encuentra todas las categorías únicas.
     */
    @Query("SELECT DISTINCT n.categoria FROM News n WHERE n.categoria IS NOT NULL ORDER BY n.categoria")
    List<String> findAllCategories();

    /**
     * Encuentra noticias destacadas limitadas a un número específico.
     */
    @Query("SELECT n FROM News n WHERE n.destacado = true ORDER BY n.fechaPublicacion DESC")
    List<News> findTopHighlightedNewsJPQL(PageRequest pageable);
}
