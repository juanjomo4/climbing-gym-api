package com.climbingapp.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a climbing shoe (pie de gato)
 * 
 * @author Climbing App
 * @version 1.0
 */
@Entity
@Table(name = "pie_de_gato", indexes = {
    @Index(name = "idx_marca", columnList = "marca"),
    @Index(name = "idx_precio", columnList = "precio"),
    @Index(name = "idx_destacado", columnList = "destacado"),
    @Index(name = "idx_fecha_creacion", columnList = "fecha_creacion")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClimbingShoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 100, message = "La marca no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 100, message = "El modelo no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String modelo;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 dígitos enteros y 2 decimales")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Size(max = 500, message = "La URL de la imagen no puede exceder los 500 caracteres")
    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @DecimalMin(value = "0.0", message = "La talla mínima debe ser positiva")
    @Digits(integer = 2, fraction = 1, message = "La talla debe tener máximo 2 dígitos enteros y 1 decimal")
    @Column(name = "talla_minima", precision = 3, scale = 1)
    private BigDecimal tallaMinima;

    @DecimalMin(value = "0.0", message = "La talla máxima debe ser positiva")
    @Digits(integer = 2, fraction = 1, message = "La talla debe tener máximo 2 dígitos enteros y 1 decimal")
    @Column(name = "talla_maxima", precision = 3, scale = 1)
    private BigDecimal tallaMaxima;

    @Size(max = 50, message = "El tipo de cierre no puede exceder los 50 caracteres")
    @Column(name = "tipo_cierre", length = 50)
    private String tipoCierre; // velcro, cordones, slip-on

    @Size(max = 20, message = "La rigidez no puede exceder los 20 caracteres")
    @Column(length = 20)
    private String rigidez; // blanda, media, rígida

    @Column(nullable = false)
    private Boolean destacado = false;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Sets the creation timestamp before persisting
     */
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}