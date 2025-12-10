# 🗄️ CÓMO FUNCIONA LA BASE DE DATOS - EXPLICACIÓN COMPLETA

---

## 🎯 DOS FORMAS DE VER Y MODIFICAR DATOS

Tu sistema genera **DOS VERSIONES** que funcionan diferente:

---

## 📱 VERSIÓN 1: HTML + JAVASCRIPT (MODO INMEDIATO)

### ¿Qué archivo abrir?
```
salida.html ← Doble click aquí
```

### ¿Cómo se ve?
```
┌─────────────────────────────────────────────────┐
│  🎓 Sistema de Gestión Escolar                  │
├─────────────────────────────────────────────────┤
│                                                 │
│  ➕ AGREGAR/EDITAR REGISTRO                     │
│  ┌─────────────────────────────────────────┐   │
│  │ Nombre:    [___________________]        │   │
│  │ Apellido:  [___________________]        │   │
│  │ Edad:      [___]                        │   │
│  │ Correo:    [___________________]        │   │
│  │ Carrera:   [___________________]        │   │
│  │ Semestre:  [___]                        │   │
│  │                                         │   │
│  │        [💾 Guardar]  [❌ Cancelar]      │   │
│  └─────────────────────────────────────────┘   │
│                                                 │
│  📋 REGISTROS GUARDADOS                        │
│  Total de registros: 3                         │
│  ┌──────────────────────────────────────────┐  │
│  │ ID │ Nombre │ Apellido │ Edad │ Acciones│  │
│  ├────┼────────┼──────────┼──────┼─────────┤  │
│  │ 1  │ Juan   │ Pérez    │ 20   │ [✏️][❌]│  │
│  │ 2  │ María  │ García   │ 22   │ [✏️][❌]│  │
│  │ 3  │ Pedro  │ López    │ 21   │ [✏️][❌]│  │
│  └────┴────────┴──────────┴──────┴─────────┘  │
└─────────────────────────────────────────────────┘
```

### ¿Dónde están los datos?
```
Los datos están en: LocalStorage del navegador

Para verlos técnicamente:
1. Abre salida.html
2. Presiona F12 (Herramientas de desarrollador)
3. Ve a "Application" o "Almacenamiento"
4. Busca "Local Storage"
5. Verás: "Estudiantes_data" con tus registros
```

### Ejemplo de cómo se guardan:
```json
[
  {
    "id": 1234567890,
    "nombre": "Juan",
    "apellido": "Pérez",
    "edad": 20,
    "correo": "juan@correo.com",
    "carrera": "Sistemas",
    "semestre": 5
  },
  {
    "id": 1234567891,
    "nombre": "María",
    "apellido": "García",
    "edad": 22,
    "correo": "maria@correo.com",
    "carrera": "Industrial",
    "semestre": 7
  }
]
```

### ¿Cómo modificar datos?
```
1. Click en botón [✏️ Editar]
   → Los datos se cargan en el formulario de arriba
   → El botón cambia a "Actualizar"

2. Modificas lo que quieras
   → Cambias el nombre, edad, etc.

3. Click en [💾 Actualizar]
   → Se guarda automáticamente
   → La tabla se actualiza al instante
```

### ¿Cómo eliminar datos?
```
1. Click en botón [❌ Eliminar]
   → Aparece confirmación: "¿Estás seguro?"

2. Click en "Aceptar"
   → Se borra del LocalStorage
   → Desaparece de la tabla
   → Contador se actualiza
```

---

## 💻 VERSIÓN 2: PHP + MYSQL (MODO SERVIDOR)

### ¿Qué archivos usar?
```
1. salida_database.sql    ← Crear la base de datos
2. salida_config.php      ← Configurar conexión
3. salida.php             ← Abrir en navegador
```

### CONFIGURACIÓN PASO A PASO

#### **Paso 1: Instalar XAMPP**
```
1. Descargar de: https://www.apachefriends.org/
2. Instalar en C:\xampp\
3. Abrir XAMPP Control Panel
4. Iniciar "Apache" y "MySQL"
```

#### **Paso 2: Crear Base de Datos**
```
1. Abrir navegador
2. Ir a: http://localhost/phpmyadmin
3. Click en "Nueva" (crear base de datos)
4. Nombre: EscuelaDB
5. Collation: utf8mb4_unicode_ci
6. Click en "Crear"
7. Click en "Importar"
8. Seleccionar: salida_database.sql
9. Click en "Continuar"
```

**Resultado**: Se crea la tabla con esta estructura:
```sql
CREATE TABLE Estudiantes (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    edad INT(11),
    correo VARCHAR(255),
    carrera VARCHAR(255),
    semestre INT(11)
);
```

#### **Paso 3: Configurar Conexión**
Editar `salida_config.php`:
```php
<?php
define('DB_HOST', 'localhost');
define('DB_USER', 'root');         ← Tu usuario MySQL
define('DB_PASS', '');             ← Tu contraseña MySQL (vacío por defecto)
define('DB_NAME', 'EscuelaDB');    ← Nombre de tu base de datos
?>
```

#### **Paso 4: Copiar Archivos**
```
Copiar estos archivos a: C:\xampp\htdocs\miproyecto\

- salida.php
- salida_config.php
- salida.css
```

#### **Paso 5: Abrir en Navegador**
```
Ir a: http://localhost/miproyecto/salida.php
```

---

## 🔄 CÓMO SE VEN Y MODIFICAN LOS DATOS EN PHP

### La Página PHP se ve IGUAL que la HTML:
```
┌─────────────────────────────────────────────────┐
│  🎓 Sistema de Gestión - Estudiantes           │
├─────────────────────────────────────────────────┤
│  ✅ Registro creado exitosamente               │ ← Mensaje después de guardar
├─────────────────────────────────────────────────┤
│  ➕ AGREGAR NUEVO REGISTRO                     │
│  [Formulario igual que en HTML...]            │
└─────────────────────────────────────────────────┘
```

### ¿Qué pasa cuando AGREGAS un registro?
```
1. Llenas el formulario:
   Nombre: Carlos
   Apellido: Ruiz
   Edad: 23
   Correo: carlos@test.com
   Carrera: Mecánica
   Semestre: 4

2. Click en [💾 Guardar]

3. PHP ejecuta este código:
   INSERT INTO Estudiantes 
   (nombre, apellido, edad, correo, carrera, semestre)
   VALUES ('Carlos', 'Ruiz', 23, 'carlos@test.com', 'Mecánica', 4)

4. Resultado:
   ✅ Se guarda en MySQL
   ✅ Aparece mensaje: "Registro creado exitosamente"
   ✅ La tabla se actualiza y muestra el nuevo registro
```

### ¿Qué pasa cuando EDITAS un registro?
```
1. Click en botón [Editar] del registro con ID=5

2. El formulario se llena con los datos actuales:
   Nombre: Carlos
   Apellido: Ruiz
   Edad: 23
   [... demás campos ...]

3. Modificas lo que quieras:
   Edad: 23 → 24

4. Click en [💾 Actualizar]

5. PHP ejecuta:
   UPDATE Estudiantes 
   SET nombre='Carlos', apellido='Ruiz', edad=24, ...
   WHERE id=5

6. Resultado:
   ✅ Se actualiza en MySQL
   ✅ Mensaje: "Registro actualizado exitosamente"
   ✅ La tabla muestra el cambio
```

### ¿Qué pasa cuando ELIMINAS un registro?
```
1. Click en botón [Eliminar] del registro con ID=5

2. Confirmación JavaScript: "¿Estás seguro?"

3. Si aceptas, PHP ejecuta:
   DELETE FROM Estudiantes WHERE id=5

4. Resultado:
   ✅ Se borra de MySQL
   ✅ Mensaje: "Registro eliminado exitosamente"
   ✅ Desaparece de la tabla
```

---

## 🔍 CÓMO VER LOS DATOS DIRECTAMENTE EN LA BASE DE DATOS

### Opción 1: phpMyAdmin (Visual)
```
1. Abrir: http://localhost/phpmyadmin
2. Click en "EscuelaDB" (izquierda)
3. Click en "Estudiantes" (tu tabla)
4. Ver todos los registros:

┌────┬─────────┬──────────┬──────┬─────────────────┬───────────┬──────────┐
│ id │ nombre  │ apellido │ edad │ correo          │ carrera   │ semestre │
├────┼─────────┼──────────┼──────┼─────────────────┼───────────┼──────────┤
│ 1  │ Juan    │ Pérez    │ 20   │ juan@test.com   │ Sistemas  │ 5        │
│ 2  │ María   │ García   │ 22   │ maria@test.com  │ Industrial│ 7        │
│ 3  │ Pedro   │ López    │ 21   │ pedro@test.com  │ Mecánica  │ 6        │
└────┴─────────┴──────────┴──────┴─────────────────┴───────────┴──────────┘

5. Para editar desde phpMyAdmin:
   - Click en [✏️ Editar] en la fila
   - Modificar valores
   - Click en "Continuar"

6. Para eliminar desde phpMyAdmin:
   - Click en [❌ Eliminar] en la fila
   - Confirmar
```

### Opción 2: MySQL Command Line
```cmd
1. Abrir CMD o PowerShell
2. Ir a: cd C:\xampp\mysql\bin
3. Ejecutar: mysql -u root -p
4. Usar la base de datos: USE EscuelaDB;
5. Ver registros: SELECT * FROM Estudiantes;

Resultado:
+----+---------+----------+------+------------------+-----------+----------+
| id | nombre  | apellido | edad | correo           | carrera   | semestre |
+----+---------+----------+------+------------------+-----------+----------+
|  1 | Juan    | Pérez    |   20 | juan@test.com    | Sistemas  |        5 |
|  2 | María   | García   |   22 | maria@test.com   | Industrial|        7 |
+----+---------+----------+------+------------------+-----------+----------+

6. Agregar registro manualmente:
   INSERT INTO Estudiantes (nombre, apellido, edad, correo, carrera, semestre)
   VALUES ('Ana', 'Martínez', 19, 'ana@test.com', 'Civil', 3);

7. Actualizar registro:
   UPDATE Estudiantes SET edad=21 WHERE id=1;

8. Eliminar registro:
   DELETE FROM Estudiantes WHERE id=3;
```

---

## 📊 FLUJO COMPLETO DE DATOS

### En el NAVEGADOR (salida.html):
```
Usuario → Formulario → JavaScript → LocalStorage → Tabla
  ↓                                      ↓
Escribe                              Se guarda
  ↓                                      ↓
Click Guardar                      Leer datos
  ↓                                      ↓
Validación                         Mostrar en tabla
```

### En el SERVIDOR (salida.php):
```
Usuario → Formulario → PHP → MySQL → PHP → Tabla
  ↓                      ↓      ↓      ↓
Escribe              INSERT  Guarda  SELECT
  ↓                      ↓      ↓      ↓
Click Guardar        SQL    Base    Mostrar
                            Datos
```

---

## 🎯 RESUMEN RÁPIDO

### MODO HTML (LocalStorage):
```
✅ Abrir: salida.html
✅ Datos: En tu navegador
✅ Modificar: Directamente en la página
✅ Ver datos técnicamente: F12 → Local Storage
✅ Ventaja: Sin configuración
```

### MODO PHP (MySQL):
```
✅ Abrir: http://localhost/salida.php
✅ Datos: En base de datos MySQL
✅ Modificar: En la página o phpMyAdmin
✅ Ver datos: phpMyAdmin o MySQL Command Line
✅ Ventaja: Base de datos real
```

---

## 💡 ¿CUÁL USAR PARA TU PROYECTO?

### Para DEMOSTRACIÓN y REPORTE:
→ **USA LA VERSIÓN HTML** (salida.html)
- ✅ Funciona al instante
- ✅ No necesitas configurar nada
- ✅ Puedes mostrar funcionamiento completo
- ✅ Perfecto para capturas de pantalla

### Para IMPLEMENTACIÓN REAL:
→ **USA LA VERSIÓN PHP** (salida.php)
- ✅ Base de datos real
- ✅ Datos persistentes
- ✅ Múltiples usuarios
- ✅ Para producción

---

## 🚀 PRUEBA AHORA

### Prueba Rápida (3 minutos):
```
1. En la GUI, click: ► COMPILAR
2. Click: 🗄️ Probar BD
3. Se abre el navegador
4. Agrega un estudiante:
   - Nombre: Tu nombre
   - Apellido: Tu apellido
   - Edad: Tu edad
   - Correo: tu@correo.com
   - Carrera: Tu carrera
   - Semestre: 5
5. Click: [💾 Guardar]
6. ¡Ves el registro en la tabla!
7. Click: [✏️ Editar]
8. Cambia la edad
9. Click: [💾 Actualizar]
10. ¡Ves el cambio!
```

---

## 🎓 PARA TU REPORTE

### Captura de Pantalla 1: Agregar Registro
- Formulario lleno
- Click en Guardar
- Mensaje de éxito

### Captura de Pantalla 2: Ver Registros
- Tabla con varios registros
- Contador de registros

### Captura de Pantalla 3: Editar Registro
- Formulario con datos cargados
- Botón "Actualizar"

### Captura de Pantalla 4: Código PHP
- Pestaña PHP en la GUI
- Muestra operaciones CRUD

### Captura de Pantalla 5: Base de Datos
- Si usas PHP: phpMyAdmin con registros
- Si usas HTML: F12 mostrando LocalStorage

---

## ❓ PREGUNTAS FRECUENTES

**P: ¿Los datos se pierden si cierro el navegador?**
R: En HTML: NO, LocalStorage persiste. En PHP: NO, están en MySQL.

**P: ¿Puedo ver los datos desde otra computadora?**
R: En HTML: NO, solo en tu PC. En PHP: SÍ, desde cualquier PC en la red.

**P: ¿Necesito internet?**
R: NO para ninguna de las dos versiones.

**P: ¿Qué pasa si agrego un registro en HTML y luego uso PHP?**
R: Son independientes. HTML usa LocalStorage, PHP usa MySQL.

---

```
╔════════════════════════════════════════════╗
║  ¡Ahora entiendes cómo funciona todo!     ║
║                                            ║
║  ✅ HTML = LocalStorage (Inmediato)       ║
║  ✅ PHP = MySQL (Real)                    ║
║  ✅ Ambos tienen CRUD completo            ║
║  ✅ Ambos se generan automáticamente      ║
╚════════════════════════════════════════════╝
```

🚀 **¡Pruébalo ahora en la GUI que acabas de abrir!**
