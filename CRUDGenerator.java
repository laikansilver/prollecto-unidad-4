import java.util.List;

/**
 * Generador de código CRUD para Sistema Híbrido
 * - Genera HTML con JavaScript + LocalStorage (funciona inmediatamente)
 * - Genera código PHP + MySQL (para implementación real)
 * - Genera scripts SQL para crear base de datos
 */
public class CRUDGenerator {
    
    /**
     * Genera archivo de configuración PHP para conexión a MySQL
     */
    public static String generarConfigPHP(String database) {
        StringBuilder config = new StringBuilder();
        
        config.append("<?php\n");
        config.append("/**\n");
        config.append(" * Configuración de Base de Datos\n");
        config.append(" * Generado automáticamente por WebPageLanguage Compiler\n");
        config.append(" */\n\n");
        config.append("// Configuración de conexión a MySQL\n");
        config.append("define('DB_HOST', 'localhost');\n");
        config.append("define('DB_USER', 'root');\n");
        config.append("define('DB_PASS', '');\n");
        config.append("define('DB_NAME', '" + database + "');\n\n");
        config.append("// Crear conexión\n");
        config.append("$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);\n\n");
        config.append("// Verificar conexión\n");
        config.append("if ($conn->connect_error) {\n");
        config.append("    die(\"Error de conexión: \" . $conn->connect_error);\n");
        config.append("}\n\n");
        config.append("// Configurar charset UTF-8\n");
        config.append("$conn->set_charset('utf8');\n");
        config.append("?>\n");
        
        return config.toString();
    }
    
    /**
     * Genera código PHP completo con operaciones CRUD
     */
    public static String generarPHP(String database, String table, List<String> campos, List<String> tipos) {
        StringBuilder php = new StringBuilder();
        
        php.append("<?php\n");
        php.append("/**\n");
        php.append(" * Sistema CRUD - " + table + "\n");
        php.append(" * Base de Datos: " + database + "\n");
        php.append(" * Generado automáticamente por WebPageLanguage Compiler\n");
        php.append(" */\n\n");
        php.append("require_once 'salida_config.php';\n\n");
        php.append("// Procesar operaciones CRUD\n");
        php.append("$mensaje = '';\n");
        php.append("$tipo_mensaje = '';\n\n");
        
        // Operación CREATE
        php.append("// ===== CREATE: Insertar nuevo registro =====\n");
        php.append("if (isset($_POST['accion']) && $_POST['accion'] == 'crear') {\n");
        php.append("    $sql = \"INSERT INTO " + table + " (");
        for (int i = 1; i < campos.size(); i++) { // Empezar en 1 para saltar el ID
            php.append(campos.get(i));
            if (i < campos.size() - 1) php.append(", ");
        }
        php.append(") VALUES (");
        for (int i = 1; i < campos.size(); i++) {
            php.append("?");
            if (i < campos.size() - 1) php.append(", ");
        }
        php.append(")\";\n");
        php.append("    \n");
        php.append("    $stmt = $conn->prepare($sql);\n");
        php.append("    ");
        
        // Generar bind_param
        String bindTypes = "";
        for (int i = 1; i < tipos.size(); i++) {
            String tipo = tipos.get(i);
            if (tipo.equals("INT") || tipo.equals("DECIMAL")) {
                bindTypes += "i";
            } else {
                bindTypes += "s";
            }
        }
        php.append("$stmt->bind_param(\"" + bindTypes + "\"");
        for (int i = 1; i < campos.size(); i++) {
            php.append(", $_POST['" + campos.get(i) + "']");
        }
        php.append(");\n");
        php.append("    \n");
        php.append("    if ($stmt->execute()) {\n");
        php.append("        $mensaje = 'Registro creado exitosamente';\n");
        php.append("        $tipo_mensaje = 'exito';\n");
        php.append("    } else {\n");
        php.append("        $mensaje = 'Error al crear registro: ' . $conn->error;\n");
        php.append("        $tipo_mensaje = 'error';\n");
        php.append("    }\n");
        php.append("    $stmt->close();\n");
        php.append("}\n\n");
        
        // Operación UPDATE
        php.append("// ===== UPDATE: Actualizar registro =====\n");
        php.append("if (isset($_POST['accion']) && $_POST['accion'] == 'actualizar') {\n");
        php.append("    $sql = \"UPDATE " + table + " SET ");
        for (int i = 1; i < campos.size(); i++) {
            php.append(campos.get(i) + " = ?");
            if (i < campos.size() - 1) php.append(", ");
        }
        php.append(" WHERE " + campos.get(0) + " = ?\";\n");
        php.append("    \n");
        php.append("    $stmt = $conn->prepare($sql);\n");
        php.append("    $stmt->bind_param(\"" + bindTypes + "i\"");
        for (int i = 1; i < campos.size(); i++) {
            php.append(", $_POST['" + campos.get(i) + "']");
        }
        php.append(", $_POST['" + campos.get(0) + "']");
        php.append(");\n");
        php.append("    \n");
        php.append("    if ($stmt->execute()) {\n");
        php.append("        $mensaje = 'Registro actualizado exitosamente';\n");
        php.append("        $tipo_mensaje = 'exito';\n");
        php.append("    } else {\n");
        php.append("        $mensaje = 'Error al actualizar registro: ' . $conn->error;\n");
        php.append("        $tipo_mensaje = 'error';\n");
        php.append("    }\n");
        php.append("    $stmt->close();\n");
        php.append("}\n\n");
        
        // Operación DELETE
        php.append("// ===== DELETE: Eliminar registro =====\n");
        php.append("if (isset($_GET['eliminar'])) {\n");
        php.append("    $id = intval($_GET['eliminar']);\n");
        php.append("    $sql = \"DELETE FROM " + table + " WHERE " + campos.get(0) + " = ?\";\n");
        php.append("    \n");
        php.append("    $stmt = $conn->prepare($sql);\n");
        php.append("    $stmt->bind_param(\"i\", $id);\n");
        php.append("    \n");
        php.append("    if ($stmt->execute()) {\n");
        php.append("        $mensaje = 'Registro eliminado exitosamente';\n");
        php.append("        $tipo_mensaje = 'exito';\n");
        php.append("    } else {\n");
        php.append("        $mensaje = 'Error al eliminar registro: ' . $conn->error;\n");
        php.append("        $tipo_mensaje = 'error';\n");
        php.append("    }\n");
        php.append("    $stmt->close();\n");
        php.append("}\n\n");
        
        // Operación READ
        php.append("// ===== READ: Obtener todos los registros =====\n");
        php.append("$sql = \"SELECT * FROM " + table + " ORDER BY " + campos.get(0) + " DESC\";\n");
        php.append("$result = $conn->query($sql);\n");
        php.append("?>\n\n");
        
        // HTML con PHP embebido
        php.append("<!DOCTYPE html>\n");
        php.append("<html lang=\"es\">\n");
        php.append("<head>\n");
        php.append("    <meta charset=\"UTF-8\">\n");
        php.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        php.append("    <title>Sistema CRUD - " + table + "</title>\n");
        php.append("    <link rel=\"stylesheet\" href=\"salida.css\">\n");
        php.append("</head>\n");
        php.append("<body>\n");
        php.append("    <div class=\"container\">\n");
        php.append("        <h1>Sistema de Gestión - " + table + "</h1>\n\n");
        
        // Mostrar mensaje
        php.append("        <?php if ($mensaje): ?>\n");
        php.append("            <div class=\"mensaje mensaje-<?php echo $tipo_mensaje; ?>\">\n");
        php.append("                <?php echo $mensaje; ?>\n");
        php.append("            </div>\n");
        php.append("        <?php endif; ?>\n\n");
        
        // Formulario CREATE
        php.append("        <div class=\"crud-section\">\n");
        php.append("            <h2>Agregar Nuevo Registro</h2>\n");
        php.append("            <form method=\"POST\" class=\"crud-form\">\n");
        php.append("                <input type=\"hidden\" name=\"accion\" value=\"crear\">\n");
        for (int i = 1; i < campos.size(); i++) {
            php.append("                <label for=\"" + campos.get(i) + "\">" + 
                      capitalizar(campos.get(i)) + ":</label>\n");
            php.append("                <input type=\"text\" id=\"" + campos.get(i) + 
                      "\" name=\"" + campos.get(i) + "\" required>\n");
        }
        php.append("                <button type=\"submit\" class=\"btn btn-success\">Guardar</button>\n");
        php.append("            </form>\n");
        php.append("        </div>\n\n");
        
        // Tabla READ
        php.append("        <div class=\"crud-section\">\n");
        php.append("            <h2>Registros Existentes</h2>\n");
        php.append("            <table class=\"crud-table\">\n");
        php.append("                <thead>\n");
        php.append("                    <tr>\n");
        for (String campo : campos) {
            php.append("                        <th>" + capitalizar(campo) + "</th>\n");
        }
        php.append("                        <th>Acciones</th>\n");
        php.append("                    </tr>\n");
        php.append("                </thead>\n");
        php.append("                <tbody>\n");
        php.append("                    <?php if ($result->num_rows > 0): ?>\n");
        php.append("                        <?php while($row = $result->fetch_assoc()): ?>\n");
        php.append("                            <tr>\n");
        for (String campo : campos) {
            php.append("                                <td><?php echo htmlspecialchars($row['" + 
                      campo + "']); ?></td>\n");
        }
        php.append("                                <td>\n");
        php.append("                                    <a href=\"?eliminar=<?php echo $row['" + 
                  campos.get(0) + "']; ?>\" class=\"btn btn-danger\" " +
                  "onclick=\"return confirm('¿Estás seguro?')\">Eliminar</a>\n");
        php.append("                                </td>\n");
        php.append("                            </tr>\n");
        php.append("                        <?php endwhile; ?>\n");
        php.append("                    <?php else: ?>\n");
        php.append("                        <tr>\n");
        php.append("                            <td colspan=\"" + (campos.size() + 1) + 
                  "\" style=\"text-align: center;\">No hay registros</td>\n");
        php.append("                        </tr>\n");
        php.append("                    <?php endif; ?>\n");
        php.append("                </tbody>\n");
        php.append("            </table>\n");
        php.append("        </div>\n");
        php.append("    </div>\n");
        php.append("</body>\n");
        php.append("</html>\n\n");
        php.append("<?php\n");
        php.append("$conn->close();\n");
        php.append("?>\n");
        
        return php.toString();
    }
    
    /**
     * Genera script SQL para crear base de datos y tabla
     */
    public static String generarSQL(String database, String table, List<String> campos, List<String> tipos) {
        StringBuilder sql = new StringBuilder();
        
        sql.append("-- Script SQL generado automáticamente\n");
        sql.append("-- Base de Datos: " + database + "\n");
        sql.append("-- Tabla: " + table + "\n");
        sql.append("-- Fecha de generación: " + java.time.LocalDateTime.now() + "\n\n");
        
        // Crear base de datos
        sql.append("-- Crear base de datos si no existe\n");
        sql.append("CREATE DATABASE IF NOT EXISTS `" + database + "` \n");
        sql.append("DEFAULT CHARACTER SET utf8mb4 \n");
        sql.append("DEFAULT COLLATE utf8mb4_unicode_ci;\n\n");
        
        sql.append("-- Usar la base de datos\n");
        sql.append("USE `" + database + "`;\n\n");
        
        // Crear tabla
        sql.append("-- Crear tabla " + table + "\n");
        sql.append("CREATE TABLE IF NOT EXISTS `" + table + "` (\n");
        
        for (int i = 0; i < campos.size(); i++) {
            String campo = campos.get(i);
            String tipo = tipos.get(i);
            
            sql.append("    `" + campo + "` ");
            
            // Convertir tipos
            switch (tipo) {
                case "INT":
                    sql.append("INT(11)");
                    if (i == 0) { // Primer campo es ID auto-increment
                        sql.append(" NOT NULL AUTO_INCREMENT");
                    }
                    break;
                case "VARCHAR":
                    sql.append("VARCHAR(255)");
                    break;
                case "TEXT":
                    sql.append("TEXT");
                    break;
                case "DATE":
                    sql.append("DATE");
                    break;
                case "DECIMAL":
                    sql.append("DECIMAL(10,2)");
                    break;
                default:
                    sql.append("VARCHAR(255)");
            }
            
            if (i < campos.size() - 1) {
                sql.append(",\n");
            }
        }
        
        // Clave primaria
        sql.append(",\n    PRIMARY KEY (`" + campos.get(0) + "`)\n");
        sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;\n\n");
        
        // Datos de ejemplo
        sql.append("-- Insertar datos de ejemplo\n");
        sql.append("INSERT INTO `" + table + "` (");
        for (int i = 1; i < campos.size(); i++) {
            sql.append("`" + campos.get(i) + "`");
            if (i < campos.size() - 1) sql.append(", ");
        }
        sql.append(") VALUES\n");
        sql.append("    (");
        for (int i = 1; i < campos.size(); i++) {
            String tipo = tipos.get(i);
            if (tipo.equals("INT")) {
                sql.append("1");
            } else if (tipo.equals("DECIMAL")) {
                sql.append("0.00");
            } else {
                sql.append("'Ejemplo " + i + "'");
            }
            if (i < campos.size() - 1) sql.append(", ");
        }
        sql.append(");\n");
        
        return sql.toString();
    }
    
    /**
     * Genera JavaScript para CRUD con LocalStorage (funciona sin servidor)
     */
    public static String generarJavaScriptLocalStorage(String table, List<String> campos, List<String> tipos) {
        StringBuilder js = new StringBuilder();
        
        js.append("// ===== SISTEMA CRUD CON LOCALSTORAGE =====\n");
        js.append("// Funciona sin servidor - Los datos se guardan en el navegador\n\n");
        
        js.append("const STORAGE_KEY = '" + table + "_data';\n");
        js.append("let registros = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];\n");
        js.append("let editandoId = null;\n\n");
        
        // Función para guardar
        js.append("function guardarEnLocalStorage() {\n");
        js.append("    localStorage.setItem(STORAGE_KEY, JSON.stringify(registros));\n");
        js.append("}\n\n");
        
        // Función CREATE
        js.append("function agregarRegistro() {\n");
        js.append("    const nuevo = {\n");
        js.append("        id: Date.now(),\n");
        for (int i = 1; i < campos.size(); i++) {
            js.append("        " + campos.get(i) + ": document.getElementById('" + 
                     campos.get(i) + "').value,\n");
        }
        js.append("    };\n");
        js.append("    registros.push(nuevo);\n");
        js.append("    guardarEnLocalStorage();\n");
        js.append("    mostrarRegistros();\n");
        js.append("    limpiarFormulario();\n");
        js.append("    mostrarMensaje('Registro agregado exitosamente', 'exito');\n");
        js.append("}\n\n");
        
        // Función UPDATE
        js.append("function actualizarRegistro() {\n");
        js.append("    const index = registros.findIndex(r => r.id === editandoId);\n");
        js.append("    if (index !== -1) {\n");
        for (int i = 1; i < campos.size(); i++) {
            js.append("        registros[index]." + campos.get(i) + " = document.getElementById('" + 
                     campos.get(i) + "').value;\n");
        }
        js.append("        guardarEnLocalStorage();\n");
        js.append("        mostrarRegistros();\n");
        js.append("        limpiarFormulario();\n");
        js.append("        mostrarMensaje('Registro actualizado exitosamente', 'exito');\n");
        js.append("    }\n");
        js.append("}\n\n");
        
        // Función DELETE
        js.append("function eliminarRegistro(id) {\n");
        js.append("    if (confirm('¿Estás seguro de eliminar este registro?')) {\n");
        js.append("        registros = registros.filter(r => r.id !== id);\n");
        js.append("        guardarEnLocalStorage();\n");
        js.append("        mostrarRegistros();\n");
        js.append("        mostrarMensaje('Registro eliminado exitosamente', 'exito');\n");
        js.append("    }\n");
        js.append("}\n\n");
        
        // Función para editar
        js.append("function editarRegistro(id) {\n");
        js.append("    const registro = registros.find(r => r.id === id);\n");
        js.append("    if (registro) {\n");
        js.append("        editandoId = id;\n");
        for (int i = 1; i < campos.size(); i++) {
            js.append("        document.getElementById('" + campos.get(i) + 
                     "').value = registro." + campos.get(i) + ";\n");
        }
        js.append("        document.getElementById('btnGuardar').textContent = 'Actualizar';\n");
        js.append("        document.getElementById('btnCancelar').style.display = 'inline-block';\n");
        js.append("    }\n");
        js.append("}\n\n");
        
        // Función READ/Display
        js.append("function mostrarRegistros() {\n");
        js.append("    const tbody = document.getElementById('tablaRegistros');\n");
        js.append("    tbody.innerHTML = '';\n");
        js.append("    \n");
        js.append("    if (registros.length === 0) {\n");
        js.append("        tbody.innerHTML = '<tr><td colspan=\"" + (campos.size() + 1) + 
                 "\" style=\"text-align: center;\">No hay registros</td></tr>';\n");
        js.append("        return;\n");
        js.append("    }\n");
        js.append("    \n");
        js.append("    registros.forEach(reg => {\n");
        js.append("        const tr = document.createElement('tr');\n");
        js.append("        tr.innerHTML = `\n");
        for (String campo : campos) {
            js.append("            <td>${reg." + campo + "}</td>\n");
        }
        js.append("            <td>\n");
        js.append("                <button onclick=\"editarRegistro(${reg.id})\" class=\"btn btn-warning\">Editar</button>\n");
        js.append("                <button onclick=\"eliminarRegistro(${reg.id})\" class=\"btn btn-danger\">Eliminar</button>\n");
        js.append("            </td>\n");
        js.append("        `;\n");
        js.append("        tbody.appendChild(tr);\n");
        js.append("    });\n");
        js.append("    \n");
        js.append("    document.getElementById('totalRegistros').textContent = registros.length;\n");
        js.append("}\n\n");
        
        // Funciones auxiliares
        js.append("function limpiarFormulario() {\n");
        for (int i = 1; i < campos.size(); i++) {
            js.append("    document.getElementById('" + campos.get(i) + "').value = '';\n");
        }
        js.append("    editandoId = null;\n");
        js.append("    document.getElementById('btnGuardar').textContent = 'Guardar';\n");
        js.append("    document.getElementById('btnCancelar').style.display = 'none';\n");
        js.append("}\n\n");
        
        js.append("function mostrarMensaje(texto, tipo) {\n");
        js.append("    const div = document.createElement('div');\n");
        js.append("    div.className = 'mensaje mensaje-' + tipo;\n");
        js.append("    div.textContent = texto;\n");
        js.append("    document.querySelector('.container').insertBefore(div, document.querySelector('.crud-section'));\n");
        js.append("    setTimeout(() => div.remove(), 3000);\n");
        js.append("}\n\n");
        
        js.append("function procesarFormulario(e) {\n");
        js.append("    e.preventDefault();\n");
        js.append("    if (editandoId) {\n");
        js.append("        actualizarRegistro();\n");
        js.append("    } else {\n");
        js.append("        agregarRegistro();\n");
        js.append("    }\n");
        js.append("}\n\n");
        
        js.append("// Inicializar al cargar la página\n");
        js.append("document.addEventListener('DOMContentLoaded', function() {\n");
        js.append("    mostrarRegistros();\n");
        js.append("    document.getElementById('crudForm').addEventListener('submit', procesarFormulario);\n");
        js.append("});\n");
        
        return js.toString();
    }
    
    private static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}
