# 🚀 GUÍA RÁPIDA - 3 MINUTOS

## ¿QUÉ HACE ESTE SISTEMA?

**Escribes código simple** → **Genera automáticamente una aplicación web con base de datos**

---

## ⚡ INICIO EN 3 PASOS

### 1️⃣ EJECUTAR
```batch
Doble click en: IniciarGUI.bat
```

### 2️⃣ COMPILAR
```
Click en botón: ► COMPILAR
```

### 3️⃣ PROBAR
```
Click en botón: 🗄️ Probar BD
```

---

## 🎯 ¿QUÉ OBTENGO?

### ✅ FUNCIONA AL INSTANTE
- Abres el HTML en tu navegador
- Agregas, editas, eliminas datos
- Todo funciona SIN configurar servidor
- Los datos se guardan automáticamente

### 📦 TAMBIÉN RECIBES
- Código PHP completo
- Script SQL para MySQL
- Archivo de configuración
- Documentación completa

---

## 📝 EJEMPLO MÍNIMO

```
Inicio pagina

titulo "Mi Sistema"

crud inicio
    bdd MiDB
    tabla Productos
        campo id:INT
        campo nombre:VARCHAR
        campo precio:DECIMAL
    fintabla
crud fin

fin pagina
```

**Resultado**: ¡Sistema CRUD completo funcionando!

---

## 🎮 BOTONES PRINCIPALES

| Botón | Función |
|-------|---------|
| **📁 Abrir Archivo** | Cargar código existente |
| **💾 Guardar Archivo** | Guardar tu código |
| **▶️ COMPILAR** | Generar todos los archivos |
| **🗄️ Probar BD** | Ver sistema funcionando |

---

## 📂 ARCHIVOS QUE SE GENERAN

```
📁 Tu carpeta
├── 📄 salida.html          ← ¡Abre este en tu navegador!
├── 🎨 salida.css           ← Estilos
├── 🐘 salida.php           ← Para servidor real
├── ⚙️ salida_config.php    ← Configuración MySQL
├── 🗄️ salida_database.sql ← Script de BD
└── 📋 salida_crud.txt      ← Documentación
```

---

## 💡 TIPOS DE CAMPO

```
campo id:INT           → Números enteros
campo nombre:VARCHAR   → Texto corto
campo descripcion:TEXT → Texto largo
campo precio:DECIMAL   → Números con decimales
campo fecha:DATE       → Fechas
```

---

## 🎨 OPERACIONES DISPONIBLES

### ➕ CREAR
Formulario para agregar nuevos registros

### 📖 LEER
Tabla con todos los registros

### ✏️ ACTUALIZAR
Click en "Editar" para modificar

### ❌ ELIMINAR
Click en "Eliminar" (con confirmación)

---

## 🔥 TRUCOS RÁPIDOS

### ¿Quieres cambiar el ejemplo?
```
Click en "Abrir Archivo"
Selecciona: ejemplo_crud_estudiantes.txt
            ejemplo_crud_productos.txt
            ejemplo_crud_empleados.txt
```

### ¿Quieres ver el código PHP generado?
```
Click en pestaña: 🐘 PHP
```

### ¿Quieres ver el SQL generado?
```
Click en pestaña: 🗄️ SQL
```

---

## ✅ CHECKLIST PARA TU REPORTE

- [ ] Ejecutar `IniciarGUI.bat`
- [ ] Compilar código de ejemplo
- [ ] Click en "🗄️ Probar BD"
- [ ] Agregar 3-5 registros de prueba
- [ ] Captura de pantalla del navegador
- [ ] Captura de código fuente
- [ ] Captura de pestaña PHP
- [ ] Captura de pestaña SQL
- [ ] Captura de consola con éxito

---

## 🏆 CARACTERÍSTICAS

✅ Sin configuración necesaria  
✅ Funciona inmediatamente  
✅ Base de datos automática  
✅ CRUD completo  
✅ Diseño profesional  
✅ Código PHP incluido  
✅ Scripts SQL incluidos  

---

## 🎓 PARA IMPLEMENTACIÓN REAL

Si quieres usar con MySQL real:

1. Instala XAMPP
2. Importa `salida_database.sql` en phpMyAdmin
3. Copia archivos .php a `C:\xampp\htdocs`
4. Abre `http://localhost/salida.php`

**Más detalles**: Ver `README_SISTEMA_HIBRIDO.md`

---

## 💬 ¿DUDAS?

### "No se genera el PHP"
→ Asegúrate de incluir `crud inicio...crud fin`

### "Los datos desaparecen"
→ En HTML usan LocalStorage (por navegador)
→ Para persistencia real usa la versión PHP

### "Error al compilar"
→ Guarda el archivo primero

---

## 🎉 ¡ESO ES TODO!

**En solo 3 minutos tienes**:
- ✅ Sistema CRUD funcionando
- ✅ Código generado automáticamente
- ✅ Listo para demostración
- ✅ Perfecto para tu reporte

**¡A PROBAR!** 🚀
