package com.climbingapp.api.repository;

import com.climbingapp.api.entity.ClimbingShoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository for ClimbingShoe entity
 * Provides CRUD operations and custom queries
 * 
 * @author Climbing App
 * @version 1.0
 */
@Repository
public interface ClimbingShoeRepository extends JpaRepository<ClimbingShoe, Long> {

    /**
     * Find all highlighted climbing shoes
     * @return List of highlighted shoes
     */
    List<ClimbingShoe> findByDestacadoTrue();

    /**
     * Find shoes ordered by creation date (newest first)
     * @return List of shoes ordered by date
     */
    List<ClimbingShoe> findAllByOrderByFechaCreacionDesc();

    /**
     * Find top N shoes by creation date
     * @param limit number of shoes to return
     * @return List of latest shoes
     */
    @Query("SELECT s FROM ClimbingShoe s ORDER BY s.fechaCreacion DESC")
    List<ClimbingShoe> findLatestShoes(@Param("limit") int limit);

    /**
     * Search shoes by brand or model (case insensitive)
     * @param searchTerm text to search
     * @return List of matching shoes
     */
    @Query("SELECT s FROM ClimbingShoe s WHERE " +
           "LOWER(s.marca) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.modelo) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<ClimbingShoe> searchByBrandOrModel(@Param("searchTerm") String searchTerm);

    /**
     * Find shoes by price range
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return List of shoes in price range
     */
    List<ClimbingShoe> findByPrecioBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Search and filter shoes by text and price range
     * @param searchTerm text to search (optional)
     * @param minPrice minimum price (optional)
     * @param maxPrice maximum price (optional)
     * @return List of filtered shoes
     */
    @Query("SELECT s FROM ClimbingShoe s WHERE " +
           "(:searchTerm IS NULL OR LOWER(s.marca) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.modelo) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
           "(:minPrice IS NULL OR s.precio >= :minPrice) AND " +
           "(:maxPrice IS NULL OR s.precio <= :maxPrice) " +
           "ORDER BY s.fechaCreacion DESC")
    List<ClimbingShoe> searchAndFilter(
        @Param("searchTerm") String searchTerm,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice
    );
}