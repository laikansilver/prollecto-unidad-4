# 📁 Sistema de Carpetas Organizadas

## ✨ Nueva Funcionalidad Implementada

Ahora cuando compiles un archivo, **todos los archivos generados se guardan en una carpeta con el nombre del proyecto**, manteniendo todo organizado y fácil de encontrar.

---

## 🎯 ¿Cómo Funciona?

### **Antes (Sistema Antiguo)**
```
ejercico10 copy/
├── ejemplo_crud_estudiantes.txt
├── salida.html          ❌ Archivos sueltos
├── salida.css           ❌ Difícil de organizar
├── salida.php           ❌ Todo mezclado
├── salida_database.sql
└── ...
```

### **Ahora (Sistema Nuevo)** ✅
```
ejercico10 copy/
├── ejemplo_crud_estudiantes.txt
└── ejemplo_crud_estudiantes/    📁 Carpeta del proyecto
    ├── ejemplo_crud_estudiantes.html
    ├── ejemplo_crud_estudiantes.css
    ├── ejemplo_crud_estudiantes.php
    ├── ejemplo_crud_estudiantes_config.php
    ├── ejemplo_crud_estudiantes_database.sql
    └── ejemplo_crud_estudiantes_crud.txt
```

---

## 🚀 Ventajas del Nuevo Sistema

### 1. **📦 Organización Total**
- Cada proyecto tiene su propia carpeta
- No más archivos mezclados
- Fácil de encontrar y gestionar

### 2. **🎨 Nombres Consistentes**
- Todos los archivos usan el nombre del proyecto
- Fácil identificar qué archivo pertenece a qué proyecto
- Ejemplo: `productos.html`, `productos.css`, `productos.php`

### 3. **🔄 Fácil de Compartir**
- Copia toda la carpeta del proyecto
- Incluye HTML, CSS, PHP, SQL - todo junto
- Listo para copiar a htdocs de XAMPP

### 4. **💼 Profesional**
- Estructura similar a proyectos reales
- Cada proyecto es autocontenido
- Facilita el trabajo en múltiples proyectos

---

## 📝 Cómo Usar

### **Opción 1: Desde la GUI (Recomendado)**

#### **Paso 1: Guardar el Archivo**
1. Escribe tu código en el área de texto
2. Click en **"Guardar Archivo"**
3. Elige nombre para tu proyecto: `mi_proyecto.txt`
4. Se creará automáticamente la carpeta `mi_proyecto/`

#### **Paso 2: Compilar**
1. Click en **"► COMPILAR"**
2. Los archivos se generan dentro de `mi_proyecto/`:
   - ✓ `mi_proyecto.html`
   - ✓ `mi_proyecto.css`
   - ✓ `mi_proyecto.php` (si hay CRUD)
   - ✓ `mi_proyecto_database.sql` (si hay CRUD)
   - ✓ `mi_proyecto_config.php` (si hay CRUD)
   - ✓ `mi_proyecto_crud.txt` (si hay CRUD)

#### **Paso 3: Probar**
- Click en **"🗄️ Probar BD"** para ver en el navegador
- O navega a la carpeta y abre el HTML directamente

---

### **Opción 2: Desde la Línea de Comandos**

```powershell
# Ejemplo: Compilar ejemplo_crud_productos.txt
java -cp ".;C:\ANTLR\antlr-3.5.2-complete.jar" test ejemplo_crud_productos.txt

# Resultado:
# Se crea la carpeta: ejemplo_crud_productos/
# Contiene: ejemplo_crud_productos.html, .css, .php, etc.
```

**Puedes especificar un nombre diferente:**
```powershell
java -cp ".;C:\ANTLR\antlr-3.5.2-complete.jar" test ejemplo_crud_productos.txt tienda
# Crea la carpeta: tienda/
# Archivos: tienda.html, tienda.css, tienda.php, etc.
```

---

## 🌐 Desplegar en XAMPP

### **Ahora es MUY Fácil:**

1. **Compila tu proyecto** (crea la carpeta automáticamente)
   
2. **Copia toda la carpeta:**
   ```
   ejemplo_crud_productos/  →  C:\xampp\htdocs\ejemplo_crud_productos\
   ```

3. **Importa la base de datos:**
   - Abre phpMyAdmin: `http://localhost/phpmyadmin`
   - Importa: `ejemplo_crud_productos_database.sql`

4. **Abre en el navegador:**
   ```
   http://localhost/ejemplo_crud_productos/ejemplo_crud_productos.php
   ```

---

## 📊 Ejemplos de Proyectos

### **Proyecto: Sistema de Estudiantes**
```
ejemplo_crud_estudiantes/
├── ejemplo_crud_estudiantes.html      ← Interfaz HTML
├── ejemplo_crud_estudiantes.css       ← Estilos modernos
├── ejemplo_crud_estudiantes.php       ← CRUD con PHP/MySQL
├── ejemplo_crud_estudiantes_config.php    ← Conexión BD
├── ejemplo_crud_estudiantes_database.sql  ← Script SQL
└── ejemplo_crud_estudiantes_crud.txt      ← Especificaciones
```

### **Proyecto: Inventario de Productos**
```
ejemplo_crud_productos/
├── ejemplo_crud_productos.html
├── ejemplo_crud_productos.css
├── ejemplo_crud_productos.php
├── ejemplo_crud_productos_config.php
├── ejemplo_crud_productos_database.sql
└── ejemplo_crud_productos_crud.txt
```

### **Proyecto: Gestión de Empleados**
```
ejemplo_crud_empleados/
├── ejemplo_crud_empleados.html
├── ejemplo_crud_empleados.css
├── ejemplo_crud_empleados.php
├── ejemplo_crud_empleados_config.php
├── ejemplo_crud_empleados_database.sql
└── ejemplo_crud_empleados_crud.txt
```

---

## 🎓 Para tu Reporte Escolar

### **Capturas Importantes:**

1. **Estructura de Carpetas:**
   - Muestra el explorador con la carpeta del proyecto
   - Resalta los archivos generados

2. **Código Fuente:**
   - Captura el código en la GUI
   - Muestra el nombre del archivo

3. **Compilación Exitosa:**
   - Consola mostrando: "✓ Carpeta creada: nombre_proyecto/"
   - Lista de archivos generados

4. **Carpeta del Proyecto:**
   - Explorador de archivos mostrando todos los archivos
   - Con el mismo nombre base

5. **Resultado en el Navegador:**
   - La página web funcionando
   - Operaciones CRUD en acción

---

## 💡 Tips Profesionales

### **Nomenclatura Recomendada:**
- ✅ `sistema_estudiantes.txt` → carpeta: `sistema_estudiantes/`
- ✅ `inventario_productos.txt` → carpeta: `inventario_productos/`
- ✅ `gestion_empleados.txt` → carpeta: `gestion_empleados/`

### **Evitar:**
- ❌ Nombres con espacios: `mi proyecto.txt`
- ❌ Caracteres especiales: `proyecto#1.txt`
- ❌ Nombres muy largos

### **Mejores Prácticas:**
- 📌 Un proyecto = una carpeta
- 📌 Nombres descriptivos y cortos
- 📌 Usar guiones bajos `_` en lugar de espacios
- 📌 Guardar el archivo fuente (.txt) fuera de la carpeta generada

---

## 🔧 Solución de Problemas

### **Problema: "Compilando sin guardar"**
**Solución:** Guarda el archivo antes de compilar para que se cree la carpeta correcta.

### **Problema: "Archivos no se encuentran"**
**Solución:** Verifica que exista la carpeta con el nombre del proyecto.

### **Problema: "PHP no funciona en XAMPP"**
**Solución:** Asegúrate de:
1. Copiar TODA la carpeta a htdocs
2. Importar el archivo SQL en phpMyAdmin
3. Usar la ruta correcta: `localhost/nombre_carpeta/nombre_proyecto.php`

---

## ✅ Cambios Implementados

### **En AnalizadorGUI.java:**
- ✅ Variable `nombreProyecto` para almacenar el nombre
- ✅ Función `abrirArchivo()` crea carpeta automáticamente
- ✅ Función `guardarArchivo()` crea carpeta al guardar
- ✅ Función `compilar()` usa el nombre del proyecto
- ✅ Todos los archivos se generan con el nombre del proyecto
- ✅ Mensajes actualizados para mostrar la carpeta

### **En test.java:**
- ✅ Detecta nombre del archivo automáticamente
- ✅ Crea carpeta del proyecto
- ✅ Genera archivos con nombre del proyecto
- ✅ Mensajes informativos mejorados

### **En WebPageLanguage.g:**
- ✅ Estilos CSS mejorados y modernos
- ✅ Degradados y animaciones
- ✅ Diseño más profesional

---

## 🎉 ¡Ahora Tu Compilador es Profesional!

Ya no más archivos desordenados. Cada proyecto en su carpeta, todo organizado, fácil de encontrar y compartir.

**¡Perfecto para tu reporte y para impresionar al profesor! 📚✨**
