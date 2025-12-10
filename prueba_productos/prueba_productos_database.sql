-- Script SQL generado automáticamente
-- Base de Datos: InventarioDB
-- Tabla: Productos
-- Fecha de generación: 2025-12-09T23:55:20.049329

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS `InventarioDB` 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE `InventarioDB`;

-- Crear tabla Productos
CREATE TABLE IF NOT EXISTS `Productos` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255),
    `precio` DECIMAL(10,2),
    `stock` INT(11),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insertar datos de ejemplo
INSERT INTO `Productos` (`nombre`, `precio`, `stock`) VALUES
    ('Ejemplo 1', 0.00, 1);
