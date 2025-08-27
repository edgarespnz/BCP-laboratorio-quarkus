# Microservicio de Consulta de Tipo de Cambio

Este microservicio permite a los clientes consultar el tipo de cambio del día, con un límite de 10 consultas diarias por cliente identificado por su DNI. El servicio utiliza una base de datos en memoria H2 para almacenar los registros de consultas y se implementa con Quarkus.

***

## Requisitos para levantar el proyecto

Primero necesitarás tener instalado lo siguiente:

* **Java (JDK 21)**
* **Apache Maven**
* **Docker Desktop**

***

## Levantar el proyecto con Docker

Como premisa se necesita correr docker en la computadora. Se puede usar docker desktop.

### 1. Construir la Imagen de Docker

Abre una terminal en la raíz del proyecto (donde está el `Dockerfile`) y ejecuta:

```bash
docker build -t change-type-service .
```

### 2. Ejecutar contenedor

Si ya se construyó la imagen (validar en cliente de docker si se está usando) puede levantar el contenedor

```bash
docker run -d -p 8080:8080 --name change-type-app change-type-service
```
El servicio corre en segundo plano.
La base de datos se reinicia cada vez que se inicia el servicio porque H2 es una base en memoria.

### 3. Verificar que está funcionando
Para probar se puede llamar a la api:

```bash
curl "http://localhost:8080/change-type?dni=12345678"
```
Deberías recibir una respuesta JSON con el tipo de cambio del día.

### Utilitarios de docker

* **Ver logs:** `docker logs -f change-type-app`
* **Detener el contenedor:** `docker stop change-type-app`
* **Eliminar el contenedor:** `docker rm change-type-app`

***

## Docs de la api

### Endpoint

* **Método:** `GET`
* **URL:** `/tipo-cambio`
* **Parámetros:**
    * `dni` (String, **requerido**): DNI del cliente que hace la consulta.

#### Ejemplo

```bash
curl "http://localhost:8080/change-type?dni=87654321"
```

#### Respuestas esperadas

* **`200 OK`**: Exito. El body en la respuesta tiene el tipo de cambio.
    ```json
    {
      "fecha": "2025-08-22",
      "sunat": 3.750,
      "compra": 3.745,
      "venta": 3.755
    }
    ```
* **`429 Too Many Requests`**: El cliente excedió el número de respuestas diaria (máximo 10).
    ```json
    {
      "error": "Ha superado el límite de 10 consultas diarias."
    }
    ```
* **`400 Bad Request`**: No se proporcionó el parámetro `dni`.

***

## Tecnologías usadas:

* **Lenguaje:** Java 21
* **Framework:** Quarkus
* **Acceso a Datos:** Hibernate ORM con Panache
* **API:** RESTEasy Reactive con Jackson
* **Cliente HTTP:** Quarkus REST Client Reactive
* **Contenerización:** Docker
* **Base de Datos:** H2 (en memoria)