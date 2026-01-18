package com.climbingapp.api.service;

import java.math.BigDecimal;
import java.util.List;

import com.climbingapp.api.dto.ClimbingShoeDTO;

/**
 * Service interface for ClimbingShoe business logic
 * 
 * @author Climbing App
 * @version 1.0
 */
public interface ClimbingShoeService {

    /**
     * Get all climbing shoes
     * @return List of all shoes
     */
    List<ClimbingShoeDTO> getAllShoes();

    /**
     * Get a specific shoe by ID
     * @param id shoe ID
     * @return ClimbingShoeDTO
     * @throws com.climbingapp.api.exception.ResourceNotFoundException if not found
     */
    ClimbingShoeDTO getShoeById(Long id);

    /**
     * Get latest N shoes (for home page)
     * @param limit number of shoes
     * @return List of latest shoes
     */
    List<ClimbingShoeDTO> getLatestShoes(int limit);

    /**
     * Get all highlighted shoes
     * @return List of highlighted shoes
     */
    List<ClimbingShoeDTO> getHighlightedShoes();

    /**
     * Search and filter shoes
     * @param searchTerm text to search in brand/model (optional)
     * @param minPrice minimum price (optional)
     * @param maxPrice maximum price (optional)
     * @return List of filtered shoes
     */
    List<ClimbingShoeDTO> searchAndFilterShoes(String searchTerm, BigDecimal minPrice, BigDecimal maxPrice);
}