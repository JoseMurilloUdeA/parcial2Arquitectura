# Inventario Web API & Frontend

Este proyecto permite la gestión de inventarios por sede de almacén, a través de una **API RESTful desarrollada con Java Spring Boot** y un **frontend interactivo en HTML + CSS + JavaScript (vanilla)**.

Incluye funcionalidades para:
- Consultar el inventario de productos por almacén (GET)
- Registrar productos con su cantidad inicial (POST)
- Respuestas en formato JSON
- HATEOAS

## 🧱 Estructura del Proyecto
frontend/             # HTML, CSS y JavaScript Vanilla
│   ├── index.html
│   ├── css/
│   │   └── style.css
│   └── js/
│       └── main.js

backend/              # Java Spring Boot API
│   ├── src/...
│   └── Dockerfile

README.md

## Tecnologías Utilizadas

### Backend
- Java 21
- Spring Boot
- Supabase (PostgreSQL) como base de datos
- Docker + Docker Compose

### Frontend
- HTML5 + CSS3
- JavaScript (vanilla)
- Fetch API para consumo de servicios


## Endpoints REST principales
### 1. Obtener productos por almacén (GET)

````
GET /api/v1/storage/{storageId}/products
Headers:
X-API-Version: v1

````

**Respuesta:**

````json
[
  {
    "id": 1,
    "store_id": "abc123",
    "name": "Laptop",
    "description": "Portátil",
    "price": 2500.00,
    "initial_amout": 10
  },
]
````

### 2. Agregar producto a un almacén (POST)

````
POST /api/v1/storage/{storageId}/products
Headers:
  Content-Type: application/json
  X-API-Version: v1
Body:
{
  "name": "Laptop HP",
  "description": "Portátil para oficina",
  "price": 2500.00,
  "initial_amout": 20
}
````

## Interfaz (Frontend)

La interfaz permite:
* Agregar productos a una sede
* Consultar productos de una sede

### Configuración inicial

1. Edita el archivo `js/main.js` y cambia la variable `API_BASE_URL`:

````js
const API_BASE_URL = "http://localhost:8080"; // <-- Usa tu URL real
````

2. Abre `frontend/index.html` en tu navegador.

## Docker

### Backend

````bash
cd backend
docker build -t inventario-api .
docker run -p 8080:8080 inventario-api
````

## 👨‍💻 Contribuyentes

* Mateo Garviria Ruiz
* Jose Luis Murillo Fernandez
* Santiago Zapata Barahona
