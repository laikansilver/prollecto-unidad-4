<?php
/**
 * Sistema CRUD - Productos
 * Base de Datos: InventarioDB
 * Generado automáticamente por WebPageLanguage Compiler
 */

require_once 'salida_config.php';

// Procesar operaciones CRUD
$mensaje = '';
$tipo_mensaje = '';

// ===== CREATE: Insertar nuevo registro =====
if (isset($_POST['accion']) && $_POST['accion'] == 'crear') {
    $sql = "INSERT INTO Productos (nombre, precio, stock) VALUES (?, ?, ?)";
    
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("sii", $_POST['nombre'], $_POST['precio'], $_POST['stock']);
    
    if ($stmt->execute()) {
        $mensaje = 'Registro creado exitosamente';
        $tipo_mensaje = 'exito';
    } else {
        $mensaje = 'Error al crear registro: ' . $conn->error;
        $tipo_mensaje = 'error';
    }
    $stmt->close();
}

// ===== UPDATE: Actualizar registro =====
if (isset($_POST['accion']) && $_POST['accion'] == 'actualizar') {
    $sql = "UPDATE Productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
    
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("siii", $_POST['nombre'], $_POST['precio'], $_POST['stock'], $_POST['id']);
    
    if ($stmt->execute()) {
        $mensaje = 'Registro actualizado exitosamente';
        $tipo_mensaje = 'exito';
    } else {
        $mensaje = 'Error al actualizar registro: ' . $conn->error;
        $tipo_mensaje = 'error';
    }
    $stmt->close();
}

// ===== DELETE: Eliminar registro =====
if (isset($_GET['eliminar'])) {
    $id = intval($_GET['eliminar']);
    $sql = "DELETE FROM Productos WHERE id = ?";
    
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $id);
    
    if ($stmt->execute()) {
        $mensaje = 'Registro eliminado exitosamente';
        $tipo_mensaje = 'exito';
    } else {
        $mensaje = 'Error al eliminar registro: ' . $conn->error;
        $tipo_mensaje = 'error';
    }
    $stmt->close();
}

// ===== READ: Obtener todos los registros =====
$sql = "SELECT * FROM Productos ORDER BY id DESC";
$result = $conn->query($sql);
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema CRUD - Productos</title>
    <link rel="stylesheet" href="salida.css">
</head>
<body>
    <div class="container">
        <h1>Sistema de Gestión - Productos</h1>

        <?php if ($mensaje): ?>
            <div class="mensaje mensaje-<?php echo $tipo_mensaje; ?>">
                <?php echo $mensaje; ?>
            </div>
        <?php endif; ?>

        <div class="crud-section">
            <h2>Agregar Nuevo Registro</h2>
            <form method="POST" class="crud-form">
                <input type="hidden" name="accion" value="crear">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
                <label for="precio">Precio:</label>
                <input type="text" id="precio" name="precio" required>
                <label for="stock">Stock:</label>
                <input type="text" id="stock" name="stock" required>
                <button type="submit" class="btn btn-success">Guardar</button>
            </form>
        </div>

        <div class="crud-section">
            <h2>Registros Existentes</h2>
            <table class="crud-table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <?php if ($result->num_rows > 0): ?>
                        <?php while($row = $result->fetch_assoc()): ?>
                            <tr>
                                <td><?php echo htmlspecialchars($row['id']); ?></td>
                                <td><?php echo htmlspecialchars($row['nombre']); ?></td>
                                <td><?php echo htmlspecialchars($row['precio']); ?></td>
                                <td><?php echo htmlspecialchars($row['stock']); ?></td>
                                <td>
                                    <a href="?eliminar=<?php echo $row['id']; ?>" class="btn btn-danger" onclick="return confirm('¿Estás seguro?')">Eliminar</a>
                                </td>
                            </tr>
                        <?php endwhile; ?>
                    <?php else: ?>
                        <tr>
                            <td colspan="5" style="text-align: center;">No hay registros</td>
                        </tr>
                    <?php endif; ?>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

<?php
$conn->close();
?>
