import org.antlr.runtime.*;
import java.io.*;

public class test {
    public static void main(String[] args) {
        try {
            String archivoEntrada = "codigo_prueba.txt";
            String nombreProyecto = "proyecto";
            
            // Verificar argumentos
            if (args.length >= 1) {
                archivoEntrada = args[0];
            }
            if (args.length >= 2) {
                nombreProyecto = args[1];
            } else {
                // Si no se especifica nombre de proyecto, usar el nombre del archivo sin extensión
                int puntoIndex = archivoEntrada.lastIndexOf('.');
                int separadorIndex = Math.max(archivoEntrada.lastIndexOf('/'), archivoEntrada.lastIndexOf('\\'));
                if (puntoIndex > separadorIndex) {
                    nombreProyecto = archivoEntrada.substring(separadorIndex + 1, puntoIndex);
                } else {
                    nombreProyecto = archivoEntrada.substring(separadorIndex + 1);
                }
            }
            
            System.out.println("=== Compilador de Lenguaje Web CRUD ===");
            System.out.println("Archivo de entrada: " + archivoEntrada);
            System.out.println("Nombre del proyecto: " + nombreProyecto);
            System.out.println("========================================\n");
            
            // Crear carpeta del proyecto
            File dirProyecto = new File(nombreProyecto);
            if (!dirProyecto.exists()) {
                dirProyecto.mkdirs();
                System.out.println("✓ Carpeta creada: " + nombreProyecto + "/\n");
            } else {
                System.out.println("✓ Usando carpeta existente: " + nombreProyecto + "/\n");
            }
            
            // Crear el flujo de entrada
            CharStream input = new ANTLRFileStream(archivoEntrada);
            
            // Crear el lexer
            WebPageLanguageLexer lexer = new WebPageLanguageLexer(input);
            
            // Crear el flujo de tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Crear el parser
            WebPageLanguageParser parser = new WebPageLanguageParser(tokens);
            
            // Analizar el programa
            System.out.println("Analizando código fuente...\n");
            parser.pagina();
            
            // Verificar si hubo errores
            if (parser.getNumberOfSyntaxErrors() == 0) {
                System.out.println("\n✓ Análisis sintáctico completado sin errores\n");
                
                // Generar archivos de salida en la carpeta del proyecto
                String archivoSalida = nombreProyecto + File.separator + nombreProyecto;
                parser.guardarArchivos(archivoSalida);
                
                System.out.println("\n✓ Compilación exitosa!");
                System.out.println("\n📁 Carpeta del proyecto: " + dirProyecto.getAbsolutePath());
                System.out.println("\nArchivos generados:");
                System.out.println("  ✓ " + nombreProyecto + ".html");
                System.out.println("  ✓ " + nombreProyecto + ".css");
                
                // Verificar archivos adicionales
                File crudFile = new File(nombreProyecto + File.separator + nombreProyecto + "_crud.txt");
                File phpFile = new File(nombreProyecto + File.separator + nombreProyecto + ".php");
                File sqlFile = new File(nombreProyecto + File.separator + nombreProyecto + "_database.sql");
                
                if (crudFile.exists()) {
                    System.out.println("  ✓ " + nombreProyecto + "_crud.txt");
                }
                if (phpFile.exists()) {
                    System.out.println("  ✓ " + nombreProyecto + ".php");
                    System.out.println("  ✓ " + nombreProyecto + "_config.php");
                }
                if (sqlFile.exists()) {
                    System.out.println("  ✓ " + nombreProyecto + "_database.sql");
                }
                
                System.out.println("\nPuedes abrir " + nombreProyecto + "/" + nombreProyecto + ".html en tu navegador para ver el resultado.");
                
                if (phpFile.exists()) {
                    System.out.println("\n📋 Para usar el sistema PHP/MySQL:");
                    System.out.println("   1. Copia la carpeta '" + nombreProyecto + "' a htdocs de XAMPP");
                    System.out.println("   2. Importa '" + nombreProyecto + "_database.sql' en phpMyAdmin");
                    System.out.println("   3. Abre localhost/" + nombreProyecto + "/" + nombreProyecto + ".php");
                }
            } else {
                System.err.println("\n✗ Se encontraron " + parser.getNumberOfSyntaxErrors() + " error(es) de sintaxis");
                System.exit(1);
            }
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        } catch (RecognitionException e) {
            System.err.println("Error de reconocimiento: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
