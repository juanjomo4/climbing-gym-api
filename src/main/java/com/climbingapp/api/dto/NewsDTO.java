package com.climbingapp.api.dto;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para transferir datos de noticias al cliente.
 * No expone la entidad directamente y permite controlar qué información se envía.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    private Long id;
    private String titulo;
    private String resumen;
    private String contenido;
    private String imagenUrl;
    private String autor;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaPublicacion;
    
    private Boolean destacado;
    private String categoria;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

    /**
     * Método auxiliar para crear un DTO desde una entidad News.
     * Útil para conversiones rápidas.
     */
    public static NewsDTO fromEntity(com.climbingapp.api.entity.News news) {
        return NewsDTO.builder()
                .id(news.getId())
                .titulo(news.getTitulo())
                .resumen(news.getResumen())
                .contenido(news.getContenido())
                .imagenUrl(news.getImagenUrl())
                .autor(news.getAutor())
                .fechaPublicacion(news.getFechaPublicacion())
                .destacado(news.getDestacado())
                .categoria(news.getCategoria())
                .fechaCreacion(news.getFechaCreacion())
                .build();
    }
}
