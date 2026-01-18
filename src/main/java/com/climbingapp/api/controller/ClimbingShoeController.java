package com.climbingapp.api.controller;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbingapp.api.dto.ClimbingShoeDTO;
import com.climbingapp.api.service.ClimbingShoeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for climbing shoes endpoints
 * Base path: /api/shoes
 * 
 * @author Climbing App
 * @version 1.0
 */
@RestController
@RequestMapping("/shoes")
@RequiredArgsConstructor
@Slf4j
// @CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class ClimbingShoeController {

    private final ClimbingShoeService climbingShoeService;

    /**
     * GET /api/shoes
     * Get all climbing shoes with optional filters
     * 
     * @param search optional search term (brand or model)
     * @param minPrice optional minimum price
     * @param maxPrice optional maximum price
     * @return List of shoes
     */
    @GetMapping
    public ResponseEntity<List<ClimbingShoeDTO>> getAllShoes(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        
        log.info("GET /shoes - search: {}, minPrice: {}, maxPrice: {}", search, minPrice, maxPrice);
        
        List<ClimbingShoeDTO> shoes;
        
        // If any filter is present, use search and filter
        if (search != null || minPrice != null || maxPrice != null) {
            shoes = climbingShoeService.searchAndFilterShoes(search, minPrice, maxPrice);
        } else {
            shoes = climbingShoeService.getAllShoes();
        }
        
        return ResponseEntity.ok(shoes);
    }

    /**
     * GET /api/shoes/{id}
     * Get a specific shoe by ID
     * 
     * @param id shoe ID
     * @return ClimbingShoeDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClimbingShoeDTO> getShoeById(@PathVariable Long id) {
        log.info("GET /shoes/{}", id);
        ClimbingShoeDTO shoe = climbingShoeService.getShoeById(id);
        return ResponseEntity.ok(shoe);
    }

    /**
     * GET /api/shoes/latest
     * Get latest shoes (for home page)
     * 
     * @param limit number of shoes to return (default: 3)
     * @return List of latest shoes
     */
    @GetMapping("/latest")
    public ResponseEntity<List<ClimbingShoeDTO>> getLatestShoes(
            @RequestParam(defaultValue = "3") int limit) {
        
        log.info("GET /shoes/latest?limit={}", limit);
        List<ClimbingShoeDTO> shoes = climbingShoeService.getLatestShoes(limit);
        return ResponseEntity.ok(shoes);
    }

    /**
     * GET /api/shoes/highlighted
     * Get highlighted/featured shoes (for home page)
     * 
     * @return List of highlighted shoes
     */
    @GetMapping("/highlighted")
    public ResponseEntity<List<ClimbingShoeDTO>> getHighlightedShoes() {
        log.info("GET /shoes/highlighted");
        List<ClimbingShoeDTO> shoes = climbingShoeService.getHighlightedShoes();
        return ResponseEntity.ok(shoes);
    }
}

/**
 * API ENDPOINTS SUMMARY:
 * 
 * GET /api/shoes                           → All shoes (with optional filters)
 * GET /api/shoes?search=La Sportiva        → Search by brand/model
 * GET /api/shoes?minPrice=100&maxPrice=200 → Filter by price range
 * GET /api/shoes?search=solution&minPrice=100 → Combined filters
 * GET /api/shoes/{id}                      → Specific shoe by ID
 * GET /api/shoes/latest?limit=3            → Latest N shoes (for home)
 * GET /api/shoes/highlighted               → Highlighted shoes (for home)
 */