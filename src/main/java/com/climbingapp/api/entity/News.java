package com.climbingapp.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad JPA que representa una noticia de escalada.
 * Mapea la tabla 'noticia' en la base de datos.
 */
@Entity
@Table(name = "noticia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    @NotBlank(message = "El contenido es obligatorio")
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String contenido;

    @Size(max = 500, message = "La URL de la imagen no puede exceder 500 caracteres")
    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @Size(max = 100, message = "El nombre del autor no puede exceder 100 caracteres")
    @Column(length = 100)
    private String autor;

    @NotNull(message = "La fecha de publicación es obligatoria")
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean destacado = false;

    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    @Column(length = 50)
    private String categoria;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    /**
     * Método auxiliar para verificar si la noticia está destacada.
     */
    public boolean isDestacado() {
        return destacado != null && destacado;
    }
}
