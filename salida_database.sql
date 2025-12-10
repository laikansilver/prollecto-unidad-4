-- Script SQL generado automáticamente
-- Base de Datos: TiendaDB
-- Tabla: Productos
-- Fecha de generación: 2025-12-09T23:08:32.009105600

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS `TiendaDB` 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE `TiendaDB`;

-- Crear tabla Productos
CREATE TABLE IF NOT EXISTS `Productos` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255),
    `descripcion` TEXT,
    `precio` DECIMAL(10,2),
    `stock` INT(11),
    `categoria` VARCHAR(255),
    `fecha_ingreso` DATE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insertar datos de ejemplo
INSERT INTO `Productos` (`nombre`, `descripcion`, `precio`, `stock`, `categoria`, `fecha_ingreso`) VALUES
    ('Ejemplo 1', 'Ejemplo 2', 0.00, 1, 'Ejemplo 5', 'Ejemplo 6');
