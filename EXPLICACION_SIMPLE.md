# 📘 SINTAXIS SIMPLE - Como Pseudocódigo

## ⚡ Ejemplo Super Simple

**Archivo:** `ejemplo_simple.txt` → Genera: `mi_pagina.html`

---

## 📝 El Código Explicado Línea por Línea

```
Inicio pagina                    ← EMPEZAR la página

titulo "Mi Sistema Web Completo" ← PONER título grande

linea simple                     ← DIBUJAR línea delgada

texto "Bienvenido..."            ← ESCRIBIR texto normal

menu opciones                    ← CREAR menú de navegación

linea doble                      ← DIBUJAR línea gruesa

texto "Enlaces a sitios..."      ← ESCRIBIR más texto

texto "<p>...enlace...</p>"      ← AGREGAR enlace a Google

texto "<p>...enlace...</p>"      ← AGREGAR enlace a YouTube

linea simple                     ← DIBUJAR otra línea

texto "Secciones con colores:"   ← ESCRIBIR texto

texto "<div style='background:#2ecc71...'>..." ← HACER cuadro VERDE

texto "<div style='background:#e74c3c...'>..." ← HACER cuadro ROJO  

texto "<div style='background:#3498db...'>..." ← HACER cuadro AZUL

linea simple                     ← DIBUJAR línea

texto "Mis imagenes:"            ← ESCRIBIR texto

seccion imagenes                 ← MOSTRAR imágenes:
    foto1.jpg                    ←   imagen 1
    foto2.jpg                    ←   imagen 2
    foto3.jpg                    ←   imagen 3

linea simple                     ← DIBUJAR línea

texto "Tabla para registrar..."  ← ESCRIBIR texto

texto "<style>...</style>"       ← DEFINIR estilos de la tabla

texto "<div>...tabla...</div>"   ← CREAR formulario y tabla

texto "<script>...</script>"     ← AGREGAR código para guardar datos
texto "<script>...</script>"     ← AGREGAR código para agregar
texto "<script>...</script>"     ← AGREGAR código para mostrar
texto "<script>...</script>"     ← AGREGAR código para eliminar

linea simple                     ← DIBUJAR línea

crud inicio                      ← DEFINIR base de datos:
    bdd mi_base_datos            ←   nombre de la base
    tabla Usuarios               ←   nombre de tabla
    campo nombre                 ←   campo 1
    campo apellido               ←   campo 2
    campo correo                 ←   campo 3
    fintabla                     ←   terminar campos
crud fin                         ← FIN de base de datos

linea simple                     ← DIBUJAR línea

seccion acercade                 ← MOSTRAR contacto:
    domicilio : "..."            ←   dirección
    telefono : "..."             ←   teléfono
    email : "..."                ←   email

linea simple                     ← DIBUJAR línea

texto "Gracias por visitar..."   ← ESCRIBIR despedida

fin pagina                       ← TERMINAR la página
```

---

## 🎯 Resumen de Comandos Usados

| # | Comando | Cuántas veces | Qué hace |
|---|---------|---------------|----------|
| 1 | `Inicio pagina` | 1 | Empezar |
| 2 | `titulo` | 1 | Título grande |
| 3 | `linea simple` | 6 | Líneas finas |
| 4 | `linea doble` | 1 | Línea gruesa |
| 5 | `texto` | 15 | Escribir texto/HTML |
| 6 | `menu opciones` | 1 | Menú navegación |
| 7 | `seccion imagenes` | 1 | Galería 3 fotos |
| 8 | `crud inicio/fin` | 1 | Base de datos |
| 9 | `seccion acercade` | 1 | Datos contacto |
| 10 | `fin pagina` | 1 | Terminar |

---

## 🎨 Las 3 Funcionalidades Principales

### 1️⃣ **Enlaces Externos** (líneas 18-20)
```
texto "<p><a href='URL'>Texto</a></p>"
```
→ Crea un link que abre en nueva ventana

### 2️⃣ **Colores** (líneas 26-28)
```
texto "<div style='background:#COLOR;color:white'>Texto</div>"
```
→ Crea cuadro con color de fondo

### 3️⃣ **Tabla Interactiva** (líneas 36-48)
```
texto "<style>...</style>"         ← Estilos
texto "<div>...formulario...</div>" ← HTML
texto "<script>...</script>"        ← JavaScript
```
→ Tabla para agregar/eliminar datos

---

## 📊 Estructura Visual

```
┌─────────────────────────────┐
│  INICIO PAGINA              │
├─────────────────────────────┤
│  Título                     │
│  ────────                   │  ← linea simple
│  Menú                       │  ← menu opciones
│  ════════                   │  ← linea doble
│                             │
│  📎 Enlaces                 │  ← texto con <a>
│                             │
│  🎨 Cuadros de Colores      │  ← texto con <div>
│                             │
│  🖼️  Imágenes               │  ← seccion imagenes
│                             │
│  📊 Tabla Interactiva       │  ← texto con tabla+JS
│                             │
│  💾 Definición BD           │  ← crud inicio/fin
│                             │
│  📞 Contacto                │  ← seccion acercade
│                             │
│  Despedida                  │
└─────────────────────────────┘
   FIN PAGINA
```

---

## 🚀 Cómo Usar

1. **Copiar** `ejemplo_simple.txt`
2. **Modificar** el texto que quieras
3. **Ejecutar:**
   ```powershell
   java -cp ".;C:\ANTLR\antlr-3.5.2-complete.jar" test ejemplo_simple.txt mi_pagina
   ```
4. **Abrir:** `mi_pagina.html`

---

## ✏️ Personalizar Fácilmente

### Cambiar Título
```
titulo "Tu Nuevo Título Aquí"
```

### Cambiar Color
```
background:#2ecc71  ← verde
background:#e74c3c  ← rojo
background:#3498db  ← azul
background:#f39c12  ← naranja
```

### Agregar Más Enlaces
```
texto "<p><a href='https://www.ejemplo.com'>Mi Sitio</a></p>"
```

### Cambiar Imágenes
```
seccion imagenes
    tu_foto1.jpg
    tu_foto2.jpg
```

### Cambiar Campos de BD
```
crud inicio
    bdd tu_base
    tabla TuTabla
    campo tu_campo1
    campo tu_campo2
    fintabla
crud fin
```

---

## 💡 Tips para Principiantes

✅ **SÍ puedes:**
- Cambiar textos entre comillas
- Cambiar nombres de archivos de imagen
- Cambiar colores (códigos #...)
- Agregar más líneas `texto`

❌ **NO cambies:**
- Las palabras clave (`Inicio`, `fin`, `texto`, etc.)
- La estructura básica
- Los comandos especiales (`menu opciones`, etc.)

---

## 🎓 Ejercicio Propuesto

Modifica el archivo para crear tu propia página con:

1. Tu nombre en el título
2. Enlaces a tus sitios favoritos
3. Tus colores preferidos
4. Tus propias imágenes
5. Tus propios campos en la tabla

---

**¡Así de fácil es crear páginas web con este lenguaje!** 🎉
