-- Script SQL generado automáticamente
-- Base de Datos: EscuelaDB
-- Tabla: Estudiantes
-- Fecha de generación: 2025-12-09T23:46:55.323617200

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS `EscuelaDB` 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE `EscuelaDB`;

-- Crear tabla Estudiantes
CREATE TABLE IF NOT EXISTS `Estudiantes` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255),
    `apellido` VARCHAR(255),
    `edad` INT(11),
    `correo` VARCHAR(255),
    `carrera` VARCHAR(255),
    `semestre` INT(11),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insertar datos de ejemplo
INSERT INTO `Estudiantes` (`nombre`, `apellido`, `edad`, `correo`, `carrera`, `semestre`) VALUES
    ('Ejemplo 1', 'Ejemplo 2', 1, 'Ejemplo 4', 'Ejemplo 5', 1);
