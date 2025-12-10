# 📘 Guía Simple - Sintaxis Tipo Pseudocódigo

## 🎯 Estructura Básica

Cada archivo sigue esta estructura simple:

```
Inicio pagina
    ... tu contenido aquí ...
fin pagina
```

---

## 📝 Comandos Básicos (Como Pseudocódigo)

### 1. TÍTULO
```
titulo "Mi Título Aquí"
```
**Qué hace:** Pone un título grande en la página

---

### 2. LÍNEA SEPARADORA
```
linea simple       ← línea delgada
linea doble        ← línea gruesa
```
**Qué hace:** Dibuja una línea horizontal

---

### 3. TEXTO
```
texto "Tu texto aquí"
```
**Qué hace:** Muestra un párrafo de texto

---

### 4. MENÚ
```
menu opciones
```
**Qué hace:** Crea un menú de navegación automático con opciones:
- Inicio
- Productos  
- Servicios
- Contacto

---

### 5. IMÁGENES
```
seccion imagenes
    foto1.jpg
    foto2.jpg
    foto3.jpg
```
**Qué hace:** Muestra las imágenes en una galería

---

### 6. SECCIÓN "ACERCA DE"
```
seccion acercade
    domicilio : "Tu dirección"
    telefono : "555-1234"
    email : "tu@email.com"
```
**Qué hace:** Muestra información de contacto

---

### 7. CRUD (Base de Datos)
```
crud inicio
    bdd nombre_base_datos
    tabla NombreTabla
    campo campo1
    campo campo2
    campo campo3
    fintabla
crud fin
```
**Qué hace:** Define una estructura de base de datos

---

## 🎨 Funcionalidades Avanzadas Simples

### A. ENLACES EXTERNOS

```
texto "<p><a href='https://www.google.com' target='_blank'>Ir a Google</a></p>"
```

**Explicación:**
- `<p>` = párrafo
- `<a href='URL'>` = enlace
- `target='_blank'` = abrir en nueva pestaña

---

### B. COLORES DE FONDO

```
texto "<div style='background:#2ecc71;color:white;padding:20px'>Texto Verde</div>"
```

**Explicación:**
- `background:#2ecc71` = color de fondo (verde)
- `color:white` = color del texto (blanco)
- `padding:20px` = espacio interno

**Colores comunes:**
- `#2ecc71` = Verde
- `#e74c3c` = Rojo
- `#3498db` = Azul
- `#f39c12` = Naranja

---

### C. TABLA INTERACTIVA SIMPLE

**Paso 1: Crear los estilos**
```
texto "<style>
.mitabla { border:2px solid blue }
.mitabla button { background:blue; color:white }
</style>"
```

**Paso 2: Crear el formulario y tabla**
```
texto "<div class='mitabla'>
<input id='nom' placeholder='Nombre'>
<button onclick='agregar()'>Agregar</button>
<table><tbody id='datos'></tbody></table>
</div>"
```

**Paso 3: Agregar el código JavaScript**
```
texto "<script>
let personas = [];

function agregar() {
    let nombre = document.getElementById('nom').value;
    personas.push({nombre: nombre});
    mostrar();
}

function mostrar() {
    let tabla = document.getElementById('datos');
    tabla.innerHTML = '';
    personas.forEach(function(p) {
        tabla.innerHTML += '<tr><td>' + p.nombre + '</td></tr>';
    });
}
</script>"
```

---

## 📋 Ejemplo Completo Comentado

```
// INICIAR LA PÁGINA
Inicio pagina

// PONER UN TÍTULO
titulo "Mi Página Web"

// DIBUJAR UNA LÍNEA
linea simple

// MOSTRAR TEXTO
texto "Bienvenido a mi sitio"

// CREAR MENÚ DE NAVEGACIÓN
menu opciones

// OTRA LÍNEA MÁS GRUESA
linea doble

// ENLACE A GOOGLE
texto "<p><a href='https://www.google.com'>Google</a></p>"

// SECCIÓN CON COLOR VERDE
texto "<div style='background:#2ecc71;color:white;padding:20px'>
         <h3>Mensaje en Verde</h3>
       </div>"

// MOSTRAR IMÁGENES
seccion imagenes
    imagen1.jpg
    imagen2.jpg

// DEFINIR UNA TABLA DE BASE DE DATOS
crud inicio
    bdd mi_base
    tabla Clientes
    campo nombre
    campo telefono
    fintabla
crud fin

// INFORMACIÓN DE CONTACTO
seccion acercade
    domicilio : "Calle Principal 123"
    telefono : "555-1234"
    email : "info@ejemplo.com"

// TERMINAR LA PÁGINA
fin pagina
```

---

## 🎓 Plantilla Vacía para Copiar

```
Inicio pagina

titulo "Tu Título"

linea simple

texto "Tu texto aquí"

menu opciones

linea doble

seccion imagenes
    imagen1.jpg
    imagen2.jpg

linea simple

crud inicio
    bdd tu_base
    tabla TuTabla
    campo campo1
    campo campo2
    fintabla
crud fin

linea simple

seccion acercade
    domicilio : "Tu dirección"
    telefono : "Tu teléfono"
    email : "tu@email.com"

fin pagina
```

---

## ⚡ Tips Rápidos

### ✅ HACER:
- Comenzar siempre con `Inicio pagina`
- Terminar siempre con `fin pagina`
- Usar comillas dobles `"` para textos
- No olvidar `fintabla` después de los campos

### ❌ NO HACER:
- No usar tildes en palabras clave (usa `pagina` no `página`)
- No usar la palabra `email` como nombre de campo (es palabra reservada)
- No mezclar comillas simples y dobles en el mismo nivel

---

## 🚀 Ejecutar tu Archivo

```powershell
# Si tu archivo se llama: mi_pagina.txt
java -cp ".;C:\ANTLR\antlr-3.5.2-complete.jar" test mi_pagina.txt salida

# Abrir el resultado
start salida.html
```

---

## 📊 Tabla de Referencia Rápida

| Comando | Sintaxis | Qué Hace |
|---------|----------|----------|
| Título | `titulo "texto"` | Encabezado grande |
| Texto | `texto "texto"` | Párrafo normal |
| Línea simple | `linea simple` | Separador fino |
| Línea doble | `linea doble` | Separador grueso |
| Menú | `menu opciones` | Barra de navegación |
| Imágenes | `seccion imagenes` + archivos | Galería de fotos |
| CRUD | `crud inicio` ... `crud fin` | Estructura BD |
| Contacto | `seccion acercade` | Info de contacto |

---

## 💡 Ejemplos Prácticos

### Página Simple
```
Inicio pagina
titulo "Mi Blog"
texto "Hola mundo"
fin pagina
```

### Con Imágenes
```
Inicio pagina
titulo "Mi Galería"
seccion imagenes
    foto1.jpg
    foto2.jpg
fin pagina
```

### Con Base de Datos
```
Inicio pagina
titulo "Sistema de Usuarios"
crud inicio
    bdd usuarios_db
    tabla Usuarios
    campo nombre
    campo edad
    fintabla
crud fin
fin pagina
```

---

**¡Así de simple!** 🎉

Cada comando hace una cosa específica y clara.
No necesitas saber HTML, CSS o JavaScript para lo básico.
