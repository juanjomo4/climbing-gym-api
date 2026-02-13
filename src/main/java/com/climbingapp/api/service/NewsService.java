package com.climbingapp.api.service;

import java.util.List;

import com.climbingapp.api.dto.NewsDTO;


/**
 * Interfaz que define los servicios disponibles para la gestión de noticias.
 * Define el contrato que debe implementar NewsServiceImpl.
 */
public interface NewsService {

    /**
     * Obtiene todas las noticias disponibles.
     * @return Lista de todas las noticias
     */
    List<NewsDTO> getAllNews();

    /**
     * Obtiene una noticia específica por su ID.
     * @param id Identificador de la noticia
     * @return DTO de la noticia encontrada
     * @throws com.climbingapp.api.exception.ResourceNotFoundException si no se encuentra
     */
    NewsDTO getNewsById(Long id);

    /**
     * Obtiene las últimas N noticias publicadas.
     * @param limit Número de noticias a retornar (por defecto 5)
     * @return Lista de las últimas noticias
     */
    List<NewsDTO> getLatestNews(Integer limit);

    /**
     * Obtiene las noticias destacadas.
     * @return Lista de noticias destacadas
     */
    List<NewsDTO> getHighlightedNews();

    /**
     * Obtiene noticias filtradas por categoría.
     * @param categoria Categoría a filtrar
     * @return Lista de noticias de la categoría especificada
     */
    List<NewsDTO> getNewsByCategory(String categoria);

    /**
     * Busca noticias por término de búsqueda.
     * Busca en título, contenido y resumen.
     * @param searchTerm Término a buscar
     * @return Lista de noticias que coinciden con la búsqueda
     */
    List<NewsDTO> searchNews(String searchTerm);

    /**
     * Obtiene noticias filtradas por autor.
     * @param autor Nombre del autor
     * @return Lista de noticias del autor especificado
     */
    List<NewsDTO> getNewsByAuthor(String autor);

    /**
     * Obtiene todas las categorías disponibles.
     * @return Lista de categorías únicas
     */
    List<String> getAllCategories();

    /**
     * Obtiene el número de noticias por categoría.
     * @param categoria Categoría a contar
     * @return Número de noticias en la categoría
     */
    Long countByCategory(String categoria);

    List<NewsDTO> getTopHighlightedNews(int limit);

}
