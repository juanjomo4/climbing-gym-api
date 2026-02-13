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
    titulo VARCHAR(255) NOT NULL,
    resumen TEXT,
    contenido LONGTEXT NOT NULL,
    imagen_url VARCHAR(500),
    autor VARCHAR(100),
    fecha_publicacion DATETIME NOT NULL,
    destacado BOOLEAN DEFAULT FALSE,
    categoria VARCHAR(50),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_fecha_publicacion (fecha_publicacion),
    INDEX idx_destacado (destacado),
    INDEX idx_categoria (categoria)
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

-- INSERTAR noticias

INSERT INTO noticia (titulo, resumen, contenido, imagen_url, autor, fecha_publicacion, destacado, categoria) VALUES
(
    'Adam Ondra completa la primera repetición de Silence 9c',
    'El escalador checo logra la segunda ascensión de la vía más difícil del mundo, confirmando su nivel excepcional.',
    'Adam Ondra ha logrado realizar la primera repetición de Silence (9c), la vía considerada más difícil del mundo, ubicada en Flatanger, Noruega. Esta hazaña confirma el nivel excepcional del escalador checo y valida la graduación de la ruta establecida por él mismo en 2017.\n\nLa ascensión tomó varias semanas de trabajo intenso, con múltiples sesiones donde Ondra perfeccionó cada movimiento de esta desafiante línea de 45 metros que atraviesa un techo de 15 metros.\n\n"Cada intento me acercaba más a entender los movimientos perfectos. Es increíble volver a esta cueva después de años y sentir que todavía me desafía", comentó Ondra tras el éxito.\n\nSilence requiere una combinación única de fuerza de dedos, resistencia y técnica refinada. Los movimientos clave incluyen bloqueos extremos en regletas milimétricas y secuencias dinámicas que requieren una precisión absoluta.',
    'https://images.unsplash.com/photo-1522163182402-834f871fd851?w=800',
    'Carlos Martínez',
    '2025-02-10 10:30:00',
    TRUE,
    'Competición'
),
(
    'Nuevas rutas equipadas en el Rocódromo de Barcelona',
    'El rocódromo Sharma Climbing BCN inaugura 20 nuevas vías de diferentes grados para todos los niveles.',
    'El reconocido rocódromo Sharma Climbing BCN ha inaugurado 20 nuevas rutas equipadas que abarcan desde el grado 5 hasta el 8a, ofreciendo opciones para escaladores de todos los niveles.\n\nEl equipo de apertura, liderado por Dani Andrada, ha trabajado durante dos semanas para crear líneas que combinan movimientos técnicos, de fuerza y resistencia. Las nuevas rutas están distribuidas en las diferentes secciones del gimnasio.\n\n"Hemos intentado crear rutas que no solo desafíen físicamente, sino que también sean entretenidas y educativas", explica Andrada. "Cada vía tiene su propia personalidad y enseña diferentes aspectos de la escalada."\n\nEntre las rutas destacadas se encuentra "El Despertar del Bloque", un 7b+ que requiere una lectura precisa y movimientos dinámicos en desplomes, y "Paciencia Vertical", un 6c de resistencia perfecto para trabajar la continuidad.\n\nLas rutas estarán disponibles a partir del próximo lunes y se mantendrán durante al menos dos meses antes de la próxima rotación.',
    'https://images.unsplash.com/photo-1564769625905-50e93615e769?w=800',
    'Laura Sánchez',
    '2025-02-08 14:20:00',
    TRUE,
    'Instalaciones'
),
(
    'Campeonato Nacional de Escalada Boulder 2025',
    'Se anuncian las fechas y sedes del campeonato nacional que reunirá a los mejores escaladores del país.',
    'La Federación Española de Deportes de Montaña y Escalada (FEDME) ha anunciado oficialmente las fechas y sedes del Campeonato Nacional de Escalada Boulder 2025, que se celebrará en tres jornadas eliminatorias y una gran final.\n\nLas fechas confirmadas son:\n\n- Primera eliminatoria: 15-16 de marzo en Madrid (Rocódromo Sputnik)\n- Segunda eliminatoria: 12-13 de abril en Valencia (Sharma Climbing Valencia)\n- Tercera eliminatoria: 10-11 de mayo en Bilbao (Muro Bilbao)\n- Gran Final: 7-8 de junio en Barcelona (Sharma Climbing BCN)\n\nEl formato del campeonato incluirá categorías masculina y femenina, con series clasificatorias, semifinales y finales. Los mejores escaladores de cada eliminatoria obtendrán puntos para el ranking nacional.\n\n"Este año hemos diseñado un formato más competitivo y espectacular", comenta el director técnico de la FEDME. "Queremos que sea una celebración de la escalada española y una oportunidad para que los jóvenes talentos se midan con los veteranos."\n\nLas inscripciones estarán abiertas hasta el 1 de marzo y se espera una participación récord de más de 200 escaladores.',
    'https://images.unsplash.com/photo-1601570470875-69a87c60c975?w=800',
    'Miguel Ángel Torres',
    '2025-02-07 09:15:00',
    TRUE,
    'Competición'
),
(
    'Beneficios del entrenamiento de fuerza para escaladores',
    'Expertos explican por qué complementar la escalada con ejercicios de fuerza mejora el rendimiento y previene lesiones.',
    'El entrenamiento de fuerza se ha convertido en un componente fundamental en la preparación de escaladores de todos los niveles. Diversos estudios y la experiencia de profesionales demuestran que incorporar ejercicios específicos de fuerza puede marcar la diferencia en el rendimiento.\n\nSegún Eva López, doctora en Ciencias de la Actividad Física y entrenadora de escalada de élite, "el entrenamiento de fuerza no solo mejora la potencia y la resistencia muscular, sino que también es crucial para la prevención de lesiones, especialmente en dedos, codos y hombros."\n\nLos ejercicios recomendados incluyen:\n\n1. Dominadas con lastre: Desarrollan la fuerza de tracción vertical\n2. Suspensiones en tabla: Fortalecen dedos y antebrazos\n3. Flexiones en anillas: Trabajan estabilidad y fuerza del core\n4. Ejercicios de movilidad escapular: Previenen lesiones de hombro\n5. Trabajo de core: Fundamental para movimientos de bloqueo\n\n"Lo importante es personalizar el entrenamiento según el nivel y objetivos de cada escalador", añade López. "No se trata de convertirse en un levantador de pesas, sino de desarrollar la fuerza específica que necesitamos en la pared."\n\nUn programa equilibrado que combine escalada con 2-3 sesiones semanales de fuerza puede incrementar el rendimiento hasta un 25% en un periodo de 3 meses.',
    'https://images.unsplash.com/photo-1571902943202-507ec2618e8f?w=800',
    'Ana Beltrán',
    '2025-02-05 11:45:00',
    FALSE,
    'Entrenamiento'
),
(
    'Destinos imperdibles para escalar en España',
    'Recorrido por los mejores lugares de escalada al aire libre en la península ibérica.',
    'España cuenta con algunos de los destinos de escalada más espectaculares de Europa, con paisajes que van desde acantilados costeros hasta paredes de caliza en zonas de montaña.\n\n**Alquézar (Huesca)**\nUno de los destinos más emblemáticos de España, con más de 600 vías equipadas en roca caliza. Ofrece rutas desde el 5º hasta el 9a, en un entorno natural impresionante junto al río Vero.\n\n**El Chorro (Málaga)**\nFamoso por el Caminito del Rey y su espectacular desfiladero, alberga más de 2000 rutas. La zona de Santa María ofrece vías de todos los niveles con placas técnicas y desplomes atléticos.\n\n**Montserrat (Barcelona)**\nLa montaña mágica catalana es un paraíso para la escalada tradicional y deportiva. Sus agujas de conglomerado ofrecen experiencias únicas, desde clásicas de varios largos hasta desplomes modernos.\n\n**Rodellar (Huesca)**\nLa meca de la escalada en techo de España. Con techos de hasta 25 metros, es el lugar perfecto para trabajar la resistencia y la fuerza. Rutas desde 6c hasta proyectos de élite.\n\n**Siurana (Tarragona)**\nPueblo medieval con más de 1000 rutas en sus alrededores. La calidad de la roca y las vistas sobre el embalse lo convierten en un destino de visita obligada.\n\nCada uno de estos lugares ofrece no solo escalada de calidad, sino también una experiencia cultural y gastronómica única.',
    'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800',
    'Roberto Díaz',
    '2025-02-03 16:00:00',
    FALSE,
    'Viajes'
),
(
    'Cómo elegir tus primeros pies de gato',
    'Guía completa para principiantes sobre qué buscar al comprar tu primer par de zapatillas de escalada.',
    'La elección del primer par de pies de gato es crucial para cualquier escalador que comienza. Una buena decisión puede marcar la diferencia entre disfrutar de la escalada o abandonar por molestias innecesarias.\n\n**Factores a considerar:**\n\n1. **Ajuste**: Los primeros pies de gato deben quedar ajustados pero no dolorosos. Un error común es comprar la talla demasiado pequeña pensando que "deben doler".\n\n2. **Tipo de cierre**: \n   - Velcro: Más cómodo y fácil de quitar, ideal para principiantes\n   - Cordones: Mejor ajuste personalizado, requiere más tiempo\n   - Slip-on: Para escaladores más avanzados\n\n3. **Forma**:\n   - Simétrica: Cómoda, polivalente, perfecta para empezar\n   - Asimétrica: Más técnica, para escaladores con experiencia\n\n4. **Rigidez**:\n   - Rígidos: Más cómodos en regletas y cantos pequeños\n   - Blandos: Mejor sensibilidad en adherencia\n\n**Modelos recomendados para principiantes:**\n- La Sportiva Tarantula\n- Scarpa Helix\n- Black Diamond Momentum\n- Five Ten Rogue VCS\n\n"Lo más importante es que te sientas cómodo", aconseja Marta Ruiz, vendedora especializada. "Vas a pasar horas con ellos puestos, así que prioriza la comodidad sobre el rendimiento en tus primeros meses."\n\nRecuerda que tus pies de gato evolucionarán con tu nivel. Es normal cambiar a modelos más específicos a medida que progresas.',
    'https://images.unsplash.com/photo-1522163723043-478af0939200?w=800',
    'Patricia Moreno',
    '2025-02-01 10:00:00',
    FALSE,
    'Equipo'
),
(
    'La escaladora española María Martín gana medalla de oro en el Europeo',
    'España celebra un nuevo éxito en competición internacional de escalada deportiva.',
    'La escaladora española María Martín ha conquistado la medalla de oro en la modalidad de dificultad del Campeonato Europeo de Escalada celebrado en Munich, Alemania.\n\nMartín, de 24 años y natural de Granada, demostró una actuación impecable en la final, siendo la única competidora en completar la vía hasta el top. Su ascenso fue descrito por los comentaristas como "una clase magistral de lectura y ejecución".\n\n"Estoy abrumada por la emoción", declaró Martín tras recibir la medalla. "He trabajado muy duro para llegar hasta aquí, y ver que el esfuerzo da sus frutos es increíble. Este oro es para todo mi equipo y para todos los que me han apoyado."\n\nLa ruta final, diseñada por el aperturista francés Jacky Godoffe, presentaba una sección de techo técnico seguida de una placa vertical con movimientos de equilibrio extremadamente delicados. Martín navegó por estas secciones con aparente facilidad.\n\nEste triunfo consolida a España como una potencia emergente en la escalada deportiva europea y genera expectativas de cara a futuras competiciones internacionales.\n\nEl próximo objetivo de Martín será el Campeonato Mundial que se celebrará en Tokio en septiembre.',
    'https://images.unsplash.com/photo-1517649763962-0c623066013b?w=800',
    'Javier Fernández',
    '2025-01-28 18:30:00',
    FALSE,
    'Competición'
),
(
    'Nutrición para escaladores: qué comer antes y después de entrenar',
    'Expertos en nutrición deportiva comparten consejos específicos para optimizar el rendimiento en escalada.',
    'La nutrición juega un papel fundamental en el rendimiento deportivo, y la escalada no es una excepción. Una dieta adecuada puede mejorar la fuerza, la resistencia y la recuperación.\n\n**Antes de escalar (1-2 horas antes):**\n\n- Carbohidratos de absorción media: avena, plátano, tostadas integrales\n- Proteínas ligeras: yogur griego, claras de huevo\n- Hidratación: agua o bebidas isotónicas\n\n"Los carbohidratos son el combustible principal para actividades de alta intensidad", explica la nutricionista deportiva Laura Campos. "Necesitamos energía disponible pero sin sentirnos pesados."\n\n**Durante la sesión:**\n\n- Hidratación constante\n- Para sesiones largas (+2 horas): frutos secos, barritas energéticas\n- Evitar comidas pesadas o muy azucaradas\n\n**Después de escalar (ventana de 30-60 minutos):**\n\n- Proteínas de calidad: pollo, pescado, legumbres\n- Carbohidratos para reponer glucógeno: arroz, pasta, patata\n- Grasas saludables: aguacate, frutos secos, aceite de oliva\n\n"La recuperación empieza justo después del entrenamiento", añade Campos. "Un batido con proteína y carbohidratos es una opción rápida y efectiva."\n\n**Suplementos útiles:**\n- Creatina: mejora la fuerza explosiva\n- Beta-alanina: aumenta la resistencia\n- Omega-3: reduce la inflamación\n- Magnesio: importante para la función muscular\n\nRecuerda que cada cuerpo es diferente. Experimenta y encuentra lo que mejor funciona para ti.',
    'https://images.unsplash.com/photo-1490645935967-10de6ba17061?w=800',
    'Sandra López',
    '2025-01-25 12:00:00',
    FALSE,
    'Salud'
),
(
    'Prevención de lesiones en dedos para escaladores',
    'Guía práctica con ejercicios y consejos para mantener tus dedos sanos y fuertes.',
    'Las lesiones en dedos son una de las preocupaciones más comunes entre escaladores. Desde poleas hasta tendones, nuestras manos están sometidas a una tensión constante que requiere cuidados específicos.\n\n**Lesiones más comunes:**\n\n1. **Rotura de polea A2**: La más temida, ocurre por sobrecarga en arqueo\n2. **Tendinitis de flexores**: Inflamación por uso excesivo\n3. **Capsulitis**: Inflamación de las cápsulas articulares\n4. **Tenosinovitis**: Inflamación de la vaina del tendón\n\n**Estrategias de prevención:**\n\n**Calentamiento específico (15 minutos):**\n- Movilidad articular de muñecas y dedos\n- Masaje suave de antebrazos\n- Escalada progresiva en presas grandes\n- Series cortas en tabla con presas positivas\n\n**Fortalecimiento:**\n- Extensores de dedos con gomas elásticas\n- Rice bucket (cubo con arroz para movilidad)\n- Suspensiones progresivas en tabla\n- Trabajo excéntrico controlado\n\n**Señales de alarma:**\n- Dolor agudo durante el movimiento\n- Hinchazón visible\n- Pérdida de rango de movimiento\n- Chasquidos o crujidos anormales\n\n"La prevención es mucho más efectiva que el tratamiento", advierte el fisioterapeuta especializado en escalada Carlos Ruiz. "Escucha a tu cuerpo y no ignores las molestias leves, porque pueden convertirse en problemas mayores."\n\n**Protocolo si aparece dolor:**\n1. Descanso inmediato\n2. Hielo 15 minutos cada 2-3 horas\n3. Evaluación por especialista\n4. No retomar hasta recuperación completa\n\nRecuerda: la paciencia en la recuperación te ahorrará meses de inactividad forzada.',
    'https://images.unsplash.com/photo-1516733725897-1aa73b87c8e8?w=800',
    'Dr. Alberto Ruiz',
    '2025-01-22 09:30:00',
    FALSE,
    'Salud'
),
(
    'Entrevista exclusiva con Chris Sharma sobre el futuro de la escalada',
    'El legendario escalador comparte su visión sobre la evolución del deporte y sus próximos proyectos.',
    'Tuvimos la oportunidad de sentarnos con Chris Sharma, uno de los escaladores más influyentes de la historia, para hablar sobre el presente y futuro de la escalada.\n\n**¿Cómo ves la evolución de la escalada desde que empezaste?**\n\n"Es increíble ver cómo ha crecido el deporte. Cuando yo empecé, éramos un grupo pequeño de locos escalando en Yosemite. Ahora es un deporte olímpico con millones de practicantes en todo el mundo. La escalada en rocódromo ha democratizado el acceso, y eso es fantástico."\n\n**¿Crees que la escalada competitiva ha cambiado la esencia del deporte?**\n\n"La competición ha añadido una dimensión interesante, pero para mí la escalada siempre ha sido sobre la conexión personal con la roca y superar tus propios límites. Hay espacio para ambas: la competición y la escalada en roca natural. Son experiencias diferentes pero igualmente válidas."\n\n**¿Qué proyectos tienes en mente?**\n\n"Estoy trabajando en algunas líneas nuevas en Mallorca que son absolutamente increíbles. También estoy invirtiendo tiempo en formar a la próxima generación de escaladores a través de mi red de gimnasios. Quiero que más gente experimente la magia de este deporte."\n\n**Consejo para escaladores que están empezando:**\n\n"Disfruta el proceso. No te obsesiones con los grados o con compararte con otros. La escalada es un viaje personal. Encuentra lo que te apasiona, ya sea boulder, vías largas, competición o simplemente pasar tiempo con amigos en la pared. Y sobre todo, cuida tu cuerpo."\n\nSharma continúa siendo una inspiración para escaladores de todas las edades y niveles, demostrando que la pasión por la escalada no tiene fecha de caducidad.',
    'https://images.unsplash.com/photo-1571902943202-507ec2618e8f?w=800',
    'Redacción ClimbingGym',
    '2025-01-20 15:00:00',
    FALSE,
    'Entrevistas'
);
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

SELECT 
    id,
    titulo,
    categoria,
    fecha_publicacion,
    destacado
FROM noticia
ORDER BY fecha_publicacion DESC;

-- Estadísticas
SELECT 
    categoria,
    COUNT(*) as total,
    SUM(CASE WHEN destacado = TRUE THEN 1 ELSE 0 END) as destacadas
FROM noticia
GROUP BY categoria;