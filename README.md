# Streaming Service API

Este proyecto es una API REST para gestionar un sistema de streaming de música. Incluye la gestión de artistas, álbumes, canciones y géneros musicales.

## Características

-**Gestión de artistas**: Crear, listar, actualizar y eliminar artistas.

-**Gestión de álbumes**: Crear, listar, actualizar y eliminar albumes.

-**Gestión de canciones**: Crear, listar, actualizar y eliminar canciones.

-**Gestión de géneros**: Crear, listar, actualizar y eliminar géneros.

-**Autenticación y autorización**: Implementado con Spring Security para usuarios con diferentes roles (ADMIN, CREATOR, USER).

## Tecnologías Utilizadas

-**Java 17**

-**Spring Boot**

-**Spring Security**

-**JPA/ Hibernate**

-**MariaDB**

-**Swagger UI** para la documentación de la API

## Requisitos previos
-**Java 17** o superior.

-**Maven** para la gestión de dependencias.

-**MariaDB** instalado y configurado.

-**Git** para la gestión del repositorio.

## Instalación
1. Clona el repositorio:
   
   ```bash
   git@github.com:ralbertor/Music-Streaming.git
   
2. Configura la base de datos en `application.properties` o `application.yml`:

   ```properties
    spring.datasource.url=jdbc:mariadb://localhost:3306/tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
3. Ejecuta las migraciones de base de datos o inicializa la base de datos.
4. Construye y ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
## Documentación de la API
La documentación de la API está disponible a través de Swagger en la siguiente URL una vez que el servidor esté en funcionamiento:

  ```bash
    http://localhost:8080/swagger-ui/index.html
  ```

## Endpoint Principales
* Artistas:
  
  * `GET /api/artistas/todos`
  * `POST /api/artistas`

* Álbumes:
  
   * `GET /api/albumes/todos`
   * `POST /api/albumes`

* Canciones:
  
   * `GET /api/canciones/todos`
   * `POST /api/canciones`

* Géneros:
  
  * `GET /api/generos/todos`
  * `POST /api/generos`
## Roles y Permisos
|ROL          | Permisos |
|---------------------|-------------|
| ADMIN | Acceso total a todos los endpoints. |
| CREATOR  | Puede crear actualizar y eliminar contenido. |
| USER | Puede listar contenido. | 
## Contribuciones
Si deseas contribuir, por favor sigue los siguientes pasos:
1. Haz un fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad`).
4. Haz push a tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request

