# 🧗‍♂️ Climbing Gym API

RESTful API para gestión e información de escalada indoor (rocódromos). Backend desarrollado con Spring Boot como parte de un proyecto full stack demostrativo.

---

## 📋 Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [Endpoints API](#endpoints-api)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Base de Datos](#base-de-datos)
- [Autor](#autor)

---

## 🎯 Descripción

API REST para una aplicación web informativa sobre escalada indoor que proporciona:

- Información sobre **pies de gato** (climbing shoes)
- **Noticias** relacionadas con escalada
- Listado de **rocódromos** (climbing gyms)

Este proyecto tiene como objetivo demostrar:
- ✅ Arquitectura backend por capas (Controller → Service → Repository)
- ✅ Buenas prácticas en desarrollo Spring Boot
- ✅ Diseño de API RESTful
- ✅ Integración con base de datos MySQL
- ✅ Manejo de excepciones personalizado
- ✅ DTOs para transferencia de datos

---

## 🛠️ Tecnologías

### Backend
- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Data JPA** (persistencia)
- **Spring Web** (API REST)
- **Lombok** (reducción de boilerplate)
- **Maven** (gestión de dependencias)

### Base de Datos
- **MySQL 8.0**
- **Docker** (containerización de MySQL)
- **phpMyAdmin** (gestión visual de BD)

### Otros
- **HikariCP** (connection pooling)
- **Jakarta Validation** (validaciones)
- **SLF4J + Logback** (logging)

---

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java 17** o superior
- **Maven 3.8+**
- **Docker** y **Docker Compose**
- **IDE** recomendado: IntelliJ IDEA, Eclipse o VS Code
- **Postman** o similar (opcional, para probar endpoints)

---

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd climbing-gym-api
```

### 2. Levantar la base de datos con Docker

```bash
# Si tienes docker-compose.yml en la raíz del proyecto
docker-compose up -d

# Verificar que los contenedores están corriendo
docker ps
```

Deberías ver dos contenedores:
- `climbing-gym-mysql` (puerto 3306)
- `climbing-gym-phpmyadmin` (puerto 8081)

### 3. Crear la base de datos y tablas

1. Accede a phpMyAdmin: `http://localhost:8081`
   - Usuario: `root`
   - Password: `root`

2. Ejecuta el script SQL completo (ubicado en `/database/schema.sql` o copia el script inicial proporcionado)

3. Verifica que se crearon:
   - Base de datos: `escalada_indoor`
   - Tablas: `pie_de_gato`, `noticia`, `rocodromo`
   - Datos de ejemplo

### 4. Compilar el proyecto

```bash
mvn clean install
```

---

## ⚙️ Configuración

### application.properties

El archivo `src/main/resources/application.properties` ya está configurado para Docker. Si necesitas ajustarlo:

```properties
# Configuración de base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/escalada_indoor
spring.datasource.username=root
spring.datasource.password=root

# Puerto del servidor
server.port=8080

# Context path
server.servlet.context-path=/api
```

### Variables importantes

| Variable | Valor por defecto | Descripción |
|----------|-------------------|-------------|
| `server.port` | 8080 | Puerto del servidor backend |
| `spring.datasource.url` | localhost:3306 | URL de conexión MySQL |
| `server.servlet.context-path` | /api | Prefijo de todos los endpoints |

---

## ▶️ Ejecución

### Opción 1: Desde el IDE

1. Abre el proyecto en tu IDE
2. Ejecuta la clase principal: `ClimbingGymApiApplication.java`
3. Espera a ver el mensaje: `Started ClimbingGymApiApplication in X seconds`

### Opción 2: Desde la terminal

```bash
# Ejecutar con Maven
mvn spring-boot:run

# O ejecutar el JAR compilado
java -jar target/climbing-gym-api-0.0.1-SNAPSHOT.jar
```

### Verificar que funciona

Abre tu navegador y ve a:
```
http://localhost:8080/api/shoes
```

Deberías ver un JSON con los pies de gato.

---

## 🔌 Endpoints API

### Base URL
```
http://localhost:8080/api
```

### Climbing Shoes (Pies de Gato)

| Método | Endpoint | Descripción | Parámetros |
|--------|----------|-------------|------------|
| GET | `/shoes` | Obtener todos los pies de gato | `search`, `minPrice`, `maxPrice` |
| GET | `/shoes/{id}` | Obtener un pie de gato por ID | - |
| GET | `/shoes/latest` | Obtener últimos N pies de gato | `limit` (default: 3) |
| GET | `/shoes/highlighted` | Obtener pies de gato destacados | - |

**Ejemplos:**
```bash
# Todos los pies de gato
GET http://localhost:8080/api/shoes

# Buscar por marca/modelo
GET http://localhost:8080/api/shoes?search=scarpa

# Filtrar por precio
GET http://localhost:8080/api/shoes?minPrice=100&maxPrice=200

# Búsqueda combinada
GET http://localhost:8080/api/shoes?search=solution&minPrice=150

# Detalle de un pie de gato
GET http://localhost:8080/api/shoes/1

# Últimos 3 (para página Home)
GET http://localhost:8080/api/shoes/latest?limit=3

# Destacados
GET http://localhost:8080/api/shoes/highlighted
```

### News (Noticias)

| Método | Endpoint | Descripción | Parámetros |
|--------|----------|-------------|------------|
| GET | `/news` | Obtener todas las noticias | - |
| GET | `/news/{id}` | Obtener una noticia por ID | - |
| GET | `/news/latest` | Obtener últimas N noticias | `limit` (default: 2) |
| GET | `/news/highlighted` | Obtener noticias destacadas | - |

### Climbing Gyms (Rocódromos)

| Método | Endpoint | Descripción | Parámetros |
|--------|----------|-------------|------------|
| GET | `/gyms` | Obtener todos los rocódromos | `country`, `city`, `type` |
| GET | `/gyms/{id}` | Obtener un rocódromo por ID | - |
| GET | `/gyms/highlighted` | Obtener rocódromos destacados | - |

**Ejemplo de filtros:**
```bash
# Filtrar por país
GET http://localhost:8080/api/gyms?country=España

# Filtrar por ciudad
GET http://localhost:8080/api/gyms?city=Barcelona

# Filtrar por tipo
GET http://localhost:8080/api/gyms?type=boulder
```

---

## 📂 Estructura del Proyecto

```
climbing-gym-api/
│
├── src/main/java/com/climbingapp/api/
│   ├── config/                  # Configuraciones (CORS, etc)
│   │   └── CorsConfig.java
│   │
│   ├── controller/              # Controladores REST
│   │   ├── ClimbingShoeController.java
│   │   ├── NewsController.java
│   │   └── ClimbingGymController.java
│   │
│   ├── dto/response/            # Data Transfer Objects
│   │   ├── ClimbingShoeDTO.java
│   │   ├── NewsDTO.java
│   │   └── ClimbingGymDTO.java
│   │
│   ├── entity/                  # Entidades JPA
│   │   ├── ClimbingShoe.java
│   │   ├── News.java
│   │   └── ClimbingGym.java
│   │
│   ├── exception/               # Manejo de excepciones
│   │   ├── ResourceNotFoundException.java
│   │   └── GlobalExceptionHandler.java
│   │
│   ├── repository/              # Repositorios Spring Data
│   │   ├── ClimbingShoeRepository.java
│   │   ├── NewsRepository.java
│   │   └── ClimbingGymRepository.java
│   │
│   ├── service/                 # Interfaces de servicio
│   │   ├── ClimbingShoeService.java
│   │   ├── NewsService.java
│   │   └── ClimbingGymService.java
│   │
│   └── service/impl/            # Implementaciones de servicio
│       ├── ClimbingShoeServiceImpl.java
│       ├── NewsServiceImpl.java
│       └── ClimbingGymServiceImpl.java
│
├── src/main/resources/
│   └── application.properties   # Configuración de la aplicación
│
├── pom.xml                      # Dependencias Maven
├── docker-compose.yml           # Configuración Docker
└── README.md                    # Este archivo
```

---

## 🗄️ Base de Datos

### Diagrama de entidades

#### pie_de_gato
- `id` (PK)
- `marca`
- `modelo`
- `precio`
- `descripcion`
- `imagen_url`
- `talla_minima`
- `talla_maxima`
- `tipo_cierre`
- `rigidez`
- `destacado`
- `fecha_creacion`

#### noticia
- `id` (PK)
- `titulo`
- `contenido`
- `resumen`
- `imagen_url`
- `fecha_publicacion`
- `destacado`
- `fecha_creacion`

#### rocodromo
- `id` (PK)
- `nombre`
- `ciudad`
- `pais`
- `direccion`
- `tipo`
- `web_url`
- `descripcion`
- `destacado`
- `fecha_creacion`

### Acceso a phpMyAdmin

```
URL: http://localhost:8081
Usuario: root
Password: root
```

---

## 🧪 Testing

### Probar con cURL

```bash
# Obtener todos los pies de gato
curl -X GET http://localhost:8080/api/shoes

# Obtener un pie de gato específico
curl -X GET http://localhost:8080/api/shoes/1

# Buscar pies de gato
curl -X GET "http://localhost:8080/api/shoes?search=scarpa&minPrice=100"
```

### Probar con Postman

1. Importa la colección de endpoints (si está disponible)
2. Configura la variable de entorno `base_url` = `http://localhost:8080/api`
3. Ejecuta las peticiones

---

## 🔐 CORS

La API permite peticiones desde:
- `http://localhost:4200` (Angular desarrollo)
- `http://127.0.0.1:4200`

Si necesitas añadir más orígenes, edita `CorsConfig.java`.

---

## 📝 Notas Adicionales

### Características implementadas
- ✅ Arquitectura por capas limpia y profesional
- ✅ Separación de responsabilidades (SRP)
- ✅ DTOs para no exponer entidades directamente
- ✅ Manejo global de excepciones
- ✅ Validaciones en entidades
- ✅ Logging con SLF4J
- ✅ Connection pooling optimizado
- ✅ Queries personalizadas en repositories
- ✅ Filtros y búsquedas en backend

---

## 👤 Autor

**Tu Nombre**
- LinkedIn: [Juanjo](https://www.linkedin.com/in/juanjomo/)
- GitHub: [Juanjomo4](https://github.com/juanjomo4/)

## 🙏 Agradecimientos

Proyecto desarrollado como demostración de habilidades en desarrollo full stack con Spring Boot y Angular.

---

**¡Happy Climbing! 🧗‍♀️**
