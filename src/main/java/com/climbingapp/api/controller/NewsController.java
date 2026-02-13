package com.climbingapp.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climbingapp.api.dto.NewsDTO;
import com.climbingapp.api.service.NewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para la gestión de noticias.
 * Expone endpoints para operaciones CRUD y búsquedas sobre noticias.
 */
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class NewsController {

    private final NewsService newsService;

    /**
     * GET /news
     * Obtiene todas las noticias.
     * Puede filtrar por categoría, autor o término de búsqueda.
     * 
     * @param categoria Filtro opcional por categoría
     * @param autor Filtro opcional por autor
     * @param search Término de búsqueda opcional
     * @return Lista de noticias
     */
    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNews(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String search) {
        
        log.info("GET /news - categoria: {}, autor: {}, search: {}", categoria, autor, search);
        
        List<NewsDTO> news;
        
        if (search != null && !search.trim().isEmpty()) {
            news = newsService.searchNews(search);
        } else if (categoria != null && !categoria.trim().isEmpty()) {
            news = newsService.getNewsByCategory(categoria);
        } else if (autor != null && !autor.trim().isEmpty()) {
            news = newsService.getNewsByAuthor(autor);
        } else {
            news = newsService.getAllNews();
        }
        
        return ResponseEntity.ok(news);
    }

    /**
     * GET /news/{id}
     * Obtiene una noticia específica por su ID.
     * 
     * @param id Identificador de la noticia
     * @return Noticia encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        log.info("GET /news/{}", id);
        NewsDTO news = newsService.getNewsById(id);
        return ResponseEntity.ok(news);
    }

    /**
     * GET /news/latest
     * Obtiene las últimas noticias publicadas.
     * 
     * @param limit Número de noticias a retornar (por defecto 5)
     * @return Lista de las últimas noticias
     */
    @GetMapping("/latest")
    public ResponseEntity<List<NewsDTO>> getLatestNews(
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        
        log.info("GET /news/latest - limit: {}", limit);
        List<NewsDTO> news = newsService.getLatestNews(limit);
        return ResponseEntity.ok(news);
    }

    /**
     * GET /news/highlighted
     * Obtiene las noticias marcadas como destacadas.
     * 
     * @return Lista de noticias destacadas
     */
    @GetMapping("/highlighted")
    public ResponseEntity<List<NewsDTO>> getHighlightedNews() {
        log.info("GET /news/highlighted");
        List<NewsDTO> news = newsService.getHighlightedNews();
        return ResponseEntity.ok(news);
    }

    /**
     * GET /news/categories
     * Obtiene todas las categorías disponibles.
     * 
     * @return Lista de categorías
     */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        log.info("GET /news/categories");
        List<String> categories = newsService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * GET /news/categories/{categoria}/count
     * Obtiene el número de noticias en una categoría específica.
     * 
     * @param categoria Categoría a contar
     * @return Número de noticias
     */
    @GetMapping("/categories/{categoria}/count")
    public ResponseEntity<Long> countByCategory(@PathVariable String categoria) {
        log.info("GET /news/categories/{}/count", categoria);
        Long count = newsService.countByCategory(categoria);
        return ResponseEntity.ok(count);
    }

    /**
     * Endpoint de health check para verificar que el servicio está funcionando.
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("News service is running");
    }
}
