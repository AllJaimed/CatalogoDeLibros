# 📚 Catálogo de Libros - API Gutendex
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)

Este proyecto implementa un catálogo de libros que utiliza la API de **Gutendex** para permitir a los usuarios buscar títulos y almacenarlos en una base de datos.  
Una vez guardados, los usuarios pueden realizar diferentes tipos de consultas sobre la información registrada, como filtrado, búsqueda por autor, idioma y más.

![Badge en Desarollo](https://img.shields.io/badge/STATUS-TERMINADO-green)

## 🚀 Características
- Búsqueda de libros en tiempo real usando la API de Gutendex.
- Almacenamiento de resultados en una base de datos.
- Consultas personalizadas sobre los libros registrados.
- Arquitectura basada en **Spring Boot** y **Java 21**.

## 🛠️ Tecnologías utilizadas
- **Java** 21
- **Spring Boot** 3.5.4
- **Maven** (gestión de dependencias)
- **JPA/Hibernate** (persistencia)
- **PostgreSQL** (base de datos)

## 📦 Requisitos previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- [Java 21](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/) (o la base de datos que uses en `application.properties`)
- Conexión a internet para acceder a la API Gutendex

## ⚙️ Configuración
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/catalogo-libros.git
   cd catalogo-libros
2. Configurar la conexión a la base de datos en src/main/resources/application.properties:
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_libros
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
   spring.jpa.hibernate.ddl-auto=update
3. Compilar y ejecutar el proyecto:
   ```Bash
   mvn spring-boot:run

