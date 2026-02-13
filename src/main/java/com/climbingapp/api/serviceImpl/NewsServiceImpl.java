package com.climbingapp.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.climbingapp.api.dto.NewsDTO;
import com.climbingapp.api.entity.News;
import com.climbingapp.api.exception.ResourceNotFoundException;
import com.climbingapp.api.repository.NewsRepository;
import com.climbingapp.api.service.NewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación del servicio NewsService. Contiene la lógica de negocio para
 * la gestión de noticias.
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> getAllNews() {
        log.info("Obteniendo todas las noticias");
        return newsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDTO getNewsById(Long id) {
        log.info("Buscando noticia con ID: {}", id);
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Noticia no encontrada con ID: " + id));
        return convertToDTO(news);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> getLatestNews(Integer limit) {
        int newsLimit = (limit != null && limit > 0) ? limit : 5;
        log.info("Obteniendo las últimas {} noticias", newsLimit);

        return newsRepository.findLatestNews().stream()
                .limit(newsLimit)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> getHighlightedNews() {
        log.info("Obteniendo noticias destacadas");
        return newsRepository.findByDestacadoTrueOrderByFechaPublicacionDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> getNewsByCategory(String categoria) {
        log.info("Obteniendo noticias de la categoría: {}", categoria);
        return newsRepository.findByCategoriaOrderByFechaPublicacionDesc(categoria).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> searchNews(String searchTerm) {
        log.info("Buscando noticias con término: {}", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllNews();
        }
        return newsRepository.searchNews(searchTerm).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> getNewsByAuthor(String autor) {
        log.info("Obteniendo noticias del autor: {}", autor);
        return newsRepository.findByAutorOrderByFechaPublicacionDesc(autor).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        log.info("Obteniendo todas las categorías");
        return newsRepository.findAllCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByCategory(String categoria) {
        log.info("Contando noticias de la categoría: {}", categoria);
        return newsRepository.countByCategoria(categoria);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<NewsDTO> getTopHighlightedNews(int limit) {
        return newsRepository.findTopHighlightedNewsJPQL(PageRequest.of(0, limit)).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una entidad News a su DTO correspondiente.
     *
     * @param news Entidad a convertir
     * @return DTO creado
     */
    private NewsDTO convertToDTO(News news) {
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
