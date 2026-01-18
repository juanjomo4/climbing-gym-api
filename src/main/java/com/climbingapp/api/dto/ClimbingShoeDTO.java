package com.climbingapp.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for ClimbingShoe entity responses
 * Used to transfer data between backend and frontend
 * 
 * @author Climbing App
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClimbingShoeDTO {

    private Long id;
    private String marca;
    private String modelo;
    private BigDecimal precio;
    private String descripcion;
    private String imagenUrl;
    private BigDecimal tallaMinima;
    private BigDecimal tallaMaxima;
    private String tipoCierre;
    private String rigidez;
    private Boolean destacado;
    private LocalDateTime fechaCreacion;
}