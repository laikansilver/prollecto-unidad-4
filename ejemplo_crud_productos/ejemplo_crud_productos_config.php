<?php
/**
 * Configuración de Base de Datos
 * Generado automáticamente por WebPageLanguage Compiler
 */

// Configuración de conexión a MySQL
define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'TiendaDB');

// Crear conexión
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

// Verificar conexión
if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
}

// Configurar charset UTF-8
$conn->set_charset('utf8');
?>
