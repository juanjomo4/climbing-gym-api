-- ============================================
-- BASE DE DATOS: ESCALADA INDOOR
-- ============================================

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS escalada_indoor
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE escalada_indoor;

-- ============================================
-- TABLA: pie_de_gato
-- ============================================
CREATE TABLE pie_de_gato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion TEXT,
    imagen_url VARCHAR(500),
    talla_minima DECIMAL(3,1),
    talla_maxima DECIMAL(3,1),
    tipo_cierre VARCHAR(50) COMMENT 'velcro, cordones, slip-on',
    rigidez VARCHAR(20) COMMENT 'blanda, media, rígida',
    destacado BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_marca (marca),
    INDEX idx_precio (precio),
    INDEX idx_destacado (destacado),
    INDEX idx_fecha_creacion (fecha_creacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: noticia
-- ============================================
CREATE TABLE noticia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    contenido TEXT NOT NULL,
    resumen VARCHAR(500),
    imagen_url VARCHAR(500),
    fecha_publicacion DATE NOT NULL,
    destacado BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_fecha_publicacion (fecha_publicacion),
    INDEX idx_destacado (destacado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- TABLA: rocodromo
-- ============================================
CREATE TABLE rocodromo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    direccion VARCHAR(300),
    tipo VARCHAR(20) NOT NULL COMMENT 'boulder, vias, mixto',
    web_url VARCHAR(500),
    descripcion TEXT,
    destacado BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_ciudad (ciudad),
    INDEX idx_pais (pais),
    INDEX idx_tipo (tipo),
    INDEX idx_destacado (destacado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- DATOS DE EJEMPLO (SEED DATA)
-- ============================================

-- Insertar pies de gato de ejemplo
INSERT INTO pie_de_gato (marca, modelo, precio, descripcion, imagen_url, talla_minima, talla_maxima, tipo_cierre, rigidez, destacado) VALUES
('La Sportiva', 'Solution', 159.95, 'Pie de gato de alto rendimiento con cierre de velcro. Ideal para boulder y vías exigentes.', 'https://example.com/solution.jpg', 36.0, 46.0, 'velcro', 'rígida', TRUE),
('Scarpa', 'Instinct VSR', 149.00, 'Excelente sensibilidad y adherencia. Perfecto para boulder técnico.', 'https://example.com/instinct.jpg', 35.5, 47.0, 'velcro', 'media', TRUE),
('Five Ten', 'Hiangle', 139.95, 'Diseño agresivo con puntera curvada. Óptimo para extraplomos.', 'https://example.com/hiangle.jpg', 36.0, 46.5, 'velcro', 'rígida', FALSE),
('Evolv', 'Shaman', 169.00, 'Gran versatilidad y comodidad. Perfecto para sesiones largas.', 'https://example.com/shaman.jpg', 35.0, 47.0, 'cordones', 'media', TRUE),
('Ocun', 'Ozone Plus', 119.90, 'Excelente relación calidad-precio para iniciación.', 'https://example.com/ozone.jpg', 36.0, 46.0, 'velcro', 'blanda', FALSE),
('Tenaya', 'Oasi', 155.00, 'Diseño innovador sin costuras. Máxima comodidad y rendimiento.', 'https://example.com/oasi.jpg', 35.5, 46.5, 'slip-on', 'media', FALSE);

-- Insertar noticias de ejemplo
INSERT INTO noticia (titulo, contenido, resumen, imagen_url, fecha_publicacion, destacado) VALUES
('Adam Ondra establece nuevo récord en competición', 
'El escalador checo Adam Ondra ha establecido un nuevo récord en la Copa del Mundo de escalada deportiva, demostrando una vez más por qué es considerado uno de los mejores escaladores del mundo. En la competición celebrada en Innsbruck, Ondra completó una ruta que ningún otro competidor pudo finalizar, mostrando una técnica impecable y una resistencia sobresaliente.',
'Adam Ondra bate récord en la Copa del Mundo de escalada en Innsbruck.',
'https://example.com/ondra.jpg',
'2025-01-15',
TRUE),

('Inauguración del nuevo rocódromo en Madrid',
'Madrid cuenta con un nuevo espacio para la escalada indoor. El rocódromo "Vertical Dreams" abre sus puertas con más de 1200m² de superficie de escalada, incluyendo zona de boulder, vías de hasta 15 metros y área infantil. El centro promete convertirse en un referente para la comunidad escaladora madrileña.',
'Nuevo rocódromo en Madrid con 1200m² de superficie y múltiples zonas.',
'https://example.com/rocodromo-madrid.jpg',
'2025-01-10',
TRUE),

('Consejos para mejorar tu técnica de adherencia',
'La adherencia es uno de los aspectos fundamentales en la escalada. En este artículo exploramos las mejores técnicas para mejorar tu colocación de pies, el uso del peso corporal y cómo elegir el calzado adecuado según el tipo de presa.',
'Guía completa para mejorar la técnica de adherencia en escalada.',
'https://example.com/tecnica.jpg',
'2025-01-05',
FALSE),

('Janja Garnbret se prepara para los próximos Juegos Olímpicos',
'La eslovena Janja Garnbret, campeona olímpica en Tokio 2020, ha comenzado su preparación intensiva para los próximos Juegos Olímpicos. En declaraciones recientes, Garnbret expresó su entusiasmo por defender su título y seguir inspirando a nuevas generaciones de escaladores.',
'Janja Garnbret inicia preparación para defender su título olímpico.',
'https://example.com/garnbret.jpg',
'2024-12-28',
FALSE);

-- Insertar rocódromos de ejemplo
INSERT INTO rocodromo (nombre, ciudad, pais, direccion, tipo, web_url, descripcion, destacado) VALUES
('Sharma Climbing BCN', 'Barcelona', 'España', 'Carrer de Pujades, 38, 08005 Barcelona', 'mixto', 'https://sharmclimbingbcn.com', 'Rocódromo fundado por Chris Sharma. Cuenta con zona de boulder y vías de hasta 15m de altura. Espacio amplio y bien equipado.', TRUE),

('Climbat Montigalà', 'Badalona', 'España', 'Av. Alfons XIII, 459, 08918 Badalona', 'mixto', 'https://climbat.com', 'Uno de los rocódromos más grandes de España con más de 2000m² de superficie de escalada. Incluye boulder, top rope y lead.', TRUE),

('Bloc Shop Madrid', 'Madrid', 'España', 'Calle de Albasanz, 14, 28037 Madrid', 'boulder', 'https://blocshop.es', 'Rocódromo especializado en boulder con múltiples zonas de diferentes niveles. Ambiente acogedor y comunidad activa.', FALSE),

('The Castle Climbing Centre', 'Londres', 'Reino Unido', 'Green Lanes, London N4 2HA', 'mixto', 'https://castle-climbing.co.uk', 'Icónico centro de escalada situado en un castillo victoriano. Amplia variedad de vías y boulder.', TRUE),

('Arkose Nation', 'París', 'Francia', 'Multiple locations in Paris', 'mixto', 'https://arkose.com', 'Cadena de rocódromos con varias ubicaciones en París. Diseño moderno y excelentes instalaciones.', FALSE),

('Magicline Boulder', 'Múnich', 'Alemania', 'Boschetsrieder Str. 59, 81379 München', 'boulder', 'https://magiclineboulder.de', 'Centro especializado en boulder con diseño innovador y rutas constantemente renovadas.', FALSE);

-- ============================================
-- VERIFICACIÓN
-- ============================================
-- Consultas para verificar los datos insertados

SELECT COUNT(*) as total_pies_gato FROM pie_de_gato;
SELECT COUNT(*) as total_noticias FROM noticia;
SELECT COUNT(*) as total_rocodromos FROM rocodromo;

-- Ver datos destacados
SELECT marca, modelo, precio FROM pie_de_gato WHERE destacado = TRUE;
SELECT titulo, fecha_publicacion FROM noticia WHERE destacado = TRUE;
SELECT nombre, ciudad, tipo FROM rocodromo WHERE destacado = TRUE;