package com.climbingapp.api.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.climbingapp.api.dto.ClimbingShoeDTO;
import com.climbingapp.api.entity.ClimbingShoe;
import com.climbingapp.api.exception.ResourceNotFoundException;
import com.climbingapp.api.repository.ClimbingShoeRepository;
import com.climbingapp.api.service.ClimbingShoeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ClimbingShoeService
 * Contains business logic for climbing shoes
 * 
 * @author Climbing App
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ClimbingShoeServiceImpl implements ClimbingShoeService {

    private final ClimbingShoeRepository climbingShoeRepository;

    @Override
    public List<ClimbingShoeDTO> getAllShoes() {
        log.info("Fetching all climbing shoes");
        List<ClimbingShoe> shoes = climbingShoeRepository.findAllByOrderByFechaCreacionDesc();
        return shoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClimbingShoeDTO getShoeById(Long id) {
        log.info("Fetching climbing shoe with ID: {}", id);
        ClimbingShoe shoe = climbingShoeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pie de gato no encontrado con ID: " + id));
        return convertToDTO(shoe);
    }

    @Override
    public List<ClimbingShoeDTO> getLatestShoes(int limit) {
        log.info("Fetching latest {} climbing shoes", limit);
        List<ClimbingShoe> shoes = climbingShoeRepository.findAllByOrderByFechaCreacionDesc();
        return shoes.stream()
                .limit(limit)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClimbingShoeDTO> getHighlightedShoes() {
        log.info("Fetching highlighted climbing shoes");
        List<ClimbingShoe> shoes = climbingShoeRepository.findByDestacadoTrue();
        return shoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClimbingShoeDTO> searchAndFilterShoes(String searchTerm, BigDecimal minPrice, BigDecimal maxPrice) {
        log.info("Searching shoes - Term: {}, MinPrice: {}, MaxPrice: {}", searchTerm, minPrice, maxPrice);
        
        List<ClimbingShoe> shoes = climbingShoeRepository.searchAndFilter(searchTerm, minPrice, maxPrice);
        
        return shoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convert ClimbingShoe entity to DTO
     * @param shoe entity
     * @return DTO
     */
    private ClimbingShoeDTO convertToDTO(ClimbingShoe shoe) {
        return ClimbingShoeDTO.builder()
                .id(shoe.getId())
                .marca(shoe.getMarca())
                .modelo(shoe.getModelo())
                .precio(shoe.getPrecio())
                .descripcion(shoe.getDescripcion())
                .imagenUrl(shoe.getImagenUrl())
                .tallaMinima(shoe.getTallaMinima())
                .tallaMaxima(shoe.getTallaMaxima())
                .tipoCierre(shoe.getTipoCierre())
                .rigidez(shoe.getRigidez())
                .destacado(shoe.getDestacado())
                .fechaCreacion(shoe.getFechaCreacion())
                .build();
    }
}