import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.antlr.runtime.*;

public class AnalizadorGUI extends JFrame {
    private JTextArea areaCodigoFuente;
    private JTextArea areaResultados;
    private JTextArea areaHTML;
    private JTextArea areaCSS;
    private JTextArea areaCRUD;
    private JTextArea areaPHP;
    private JTextArea areaSQL;
    private JButton btnCompilar;
    private JButton btnLimpiar;
    private JButton btnAbrir;
    private JButton btnGuardar;
    private JButton btnVerHTML;
    private JButton btnVerPHP;
    private JButton btnProbarBD;
    private JTabbedPane tabbedPane;
    private String ultimoArchivo = null;
    private String carpetaTrabajo = null;
    private String nombreProyecto = null;
    
    public AnalizadorGUI() {
        setTitle("Compilador de Lenguaje Web CRUD - Ejercicio 10");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        cargarEjemplo();
    }
    
    private void initComponents() {
        // Panel principal con BorderLayout
        setLayout(new BorderLayout(10, 10));
        
        // ===== PANEL SUPERIOR: Título y botones =====
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(new EmptyBorder(10, 10, 5, 10));
        
        JLabel lblTitulo = new JLabel("Compilador de Lenguaje Web CRUD", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(41, 128, 185));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnAbrir = new JButton("Abrir Archivo");
        btnAbrir.setBackground(new Color(52, 152, 219));
        btnAbrir.setForeground(Color.WHITE);
        btnAbrir.setOpaque(true);
        btnAbrir.setBorderPainted(false);
        btnAbrir.setFocusPainted(false);
        btnAbrir.setFont(new Font("Arial", Font.BOLD, 12));
        btnAbrir.setPreferredSize(new Dimension(150, 35));
        btnAbrir.addActionListener(e -> abrirArchivo());
        
        btnGuardar = new JButton("Guardar Archivo");
        btnGuardar.setBackground(new Color(41, 128, 185));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setOpaque(true);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnGuardar.setPreferredSize(new Dimension(160, 35));
        btnGuardar.addActionListener(e -> guardarArchivo());
        
        btnCompilar = new JButton("► COMPILAR");
        btnCompilar.setBackground(new Color(39, 174, 96));
        
        btnCompilar.setForeground(Color.WHITE);
        btnCompilar.setOpaque(true);
        btnCompilar.setBorderPainted(false);
        btnCompilar.setFocusPainted(false);
        btnCompilar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCompilar.setPreferredSize(new Dimension(150, 35));
        btnCompilar.addActionListener(e -> compilar());
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(231, 76, 60));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        btnLimpiar.setPreferredSize(new Dimension(120, 35));
        btnLimpiar.addActionListener(e -> limpiar());
        
        btnVerHTML = new JButton("Ver en Navegador");
        btnVerHTML.setBackground(new Color(142, 68, 173));
        btnVerHTML.setForeground(Color.WHITE);
        btnVerHTML.setOpaque(true);
        btnVerHTML.setBorderPainted(false);
        btnVerHTML.setFocusPainted(false);
        btnVerHTML.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerHTML.setPreferredSize(new Dimension(180, 35));
        btnVerHTML.addActionListener(e -> abrirEnNavegador());
        
        btnVerPHP = new JButton("Abrir PHP");
        btnVerPHP.setBackground(new Color(120, 111, 166));
        btnVerPHP.setForeground(Color.WHITE);
        btnVerPHP.setOpaque(true);
        btnVerPHP.setBorderPainted(false);
        btnVerPHP.setFocusPainted(false);
        btnVerPHP.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerPHP.setPreferredSize(new Dimension(140, 35));
        btnVerPHP.addActionListener(e -> abrirPHP());
        
        btnProbarBD = new JButton("🗄️ Probar BD");
        btnProbarBD.setBackground(new Color(46, 204, 113));
        btnProbarBD.setForeground(Color.WHITE);
        btnProbarBD.setOpaque(true);
        btnProbarBD.setBorderPainted(false);
        btnProbarBD.setFocusPainted(false);
        btnProbarBD.setFont(new Font("Arial", Font.BOLD, 12));
        btnProbarBD.setPreferredSize(new Dimension(150, 35));
        btnProbarBD.addActionListener(e -> probarBaseDatos());
        
        panelBotones.add(btnAbrir);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCompilar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVerHTML);
        panelBotones.add(btnProbarBD);
        panelBotones.add(btnVerPHP);
        
        panelSuperior.add(panelBotones, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);
        
        // ===== PANEL CENTRAL: Split vertical con área de trabajo arriba y consola abajo =====
        JSplitPane splitVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitVertical.setDividerLocation(500);
        splitVertical.setResizeWeight(0.7);
        
        // ===== ÁREA SUPERIOR: Split horizontal con código fuente y resultados =====
        JSplitPane splitHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitHorizontal.setDividerLocation(550);
        splitHorizontal.setResizeWeight(0.5);
        
        // Panel izquierdo: Código fuente
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(new EmptyBorder(5, 10, 5, 5));
        
        JLabel lblCodigoFuente = new JLabel("Código Fuente (Lenguaje Web CRUD)");
        lblCodigoFuente.setFont(new Font("Arial", Font.BOLD, 14));
        panelIzquierdo.add(lblCodigoFuente, BorderLayout.NORTH);
        
        areaCodigoFuente = new JTextArea();
        areaCodigoFuente.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaCodigoFuente.setTabSize(4);
        areaCodigoFuente.setLineWrap(true);
        areaCodigoFuente.setWrapStyleWord(true);
        JScrollPane scrollCodigo = new JScrollPane(areaCodigoFuente);
        scrollCodigo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelIzquierdo.add(scrollCodigo, BorderLayout.CENTER);
        
        splitHorizontal.setLeftComponent(panelIzquierdo);
        
        // Panel derecho: Tabs con resultados (HTML, CSS, CRUD)
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(new EmptyBorder(5, 5, 5, 10));
        
        JLabel lblResultados = new JLabel("Resultados de la Compilación");
        lblResultados.setFont(new Font("Arial", Font.BOLD, 14));
        panelDerecho.add(lblResultados, BorderLayout.NORTH);
        
        tabbedPane = new JTabbedPane();
        
        // Tab 1: HTML generado
        areaHTML = new JTextArea();
        areaHTML.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaHTML.setEditable(false);
        areaHTML.setBackground(new Color(250, 250, 250));
        JScrollPane scrollHTML = new JScrollPane(areaHTML);
        tabbedPane.addTab("🌐 HTML", scrollHTML);
        
        // Tab 2: CSS generado
        areaCSS = new JTextArea();
        areaCSS.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaCSS.setEditable(false);
        areaCSS.setBackground(new Color(250, 250, 250));
        JScrollPane scrollCSS = new JScrollPane(areaCSS);
        tabbedPane.addTab("🎨 CSS", scrollCSS);
        
        // Tab 3: Especificación CRUD
        areaCRUD = new JTextArea();
        areaCRUD.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaCRUD.setEditable(false);
        areaCRUD.setBackground(new Color(250, 250, 250));
        JScrollPane scrollCRUD = new JScrollPane(areaCRUD);
        tabbedPane.addTab("💾 CRUD Spec", scrollCRUD);
        
        // Tab 4: Código PHP
        areaPHP = new JTextArea();
        areaPHP.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaPHP.setEditable(false);
        areaPHP.setBackground(new Color(250, 250, 250));
        JScrollPane scrollPHP = new JScrollPane(areaPHP);
        tabbedPane.addTab("🐘 PHP", scrollPHP);
        
        // Tab 5: Script SQL
        areaSQL = new JTextArea();
        areaSQL.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaSQL.setEditable(false);
        areaSQL.setBackground(new Color(250, 250, 250));
        JScrollPane scrollSQL = new JScrollPane(areaSQL);
        tabbedPane.addTab("🗄️ SQL", scrollSQL);
        
        panelDerecho.add(tabbedPane, BorderLayout.CENTER);
        splitHorizontal.setRightComponent(panelDerecho);
        
        splitVertical.setTopComponent(splitHorizontal);
        
        // ===== ÁREA INFERIOR: Consola =====
        JPanel panelConsola = new JPanel(new BorderLayout());
        panelConsola.setBorder(new EmptyBorder(5, 10, 10, 10));
        
        JLabel lblConsola = new JLabel("📋 Consola");
        lblConsola.setFont(new Font("Arial", Font.BOLD, 14));
        panelConsola.add(lblConsola, BorderLayout.NORTH);
        
        areaResultados = new JTextArea();
        areaResultados.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaResultados.setEditable(false);
        areaResultados.setBackground(new Color(250, 250, 250));
        JScrollPane scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelConsola.add(scrollResultados, BorderLayout.CENTER);
        
        splitVertical.setBottomComponent(panelConsola);
        
        add(splitVertical, BorderLayout.CENTER);
        
        // Panel inferior: Información
        JPanel panelInferior = new JPanel();
        panelInferior.setBorder(new EmptyBorder(5, 10, 10, 10));
        JLabel lblInfo = new JLabel("Ejercicio 10 - Compilador Web CRUD | ANTLR 3.5.2 | Lenguajes y Autómatas II");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setForeground(Color.GRAY);
        panelInferior.add(lblInfo);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void cargarEjemplo() {
        String ejemplo = "Inicio pagina\n\n" +
                        "titulo \"🎓 Sistema de Gestión Escolar\"\n\n" +
                        "texto \"Bienvenido al Sistema Híbrido de Gestión. Prueba las funciones CRUD directamente en tu navegador.\"\n\n" +
                        "linea simple\n\n" +
                        "crud inicio\n" +
                        "    bdd EscuelaDB\n" +
                        "    tabla Estudiantes\n" +
                        "        campo id:INT\n" +
                        "        campo nombre:VARCHAR\n" +
                        "        campo apellido:VARCHAR\n" +
                        "        campo edad:INT\n" +
                        "        campo email:VARCHAR\n" +
                        "        campo carrera:VARCHAR\n" +
                        "        campo semestre:INT\n" +
                        "    fintabla\n" +
                        "crud fin\n\n" +
                        "linea simple\n\n" +
                        "seccion acercade\n" +
                        "    domicilio:\"Instituto Tecnológico de Morelia\"\n" +
                        "    telefono:\"443-312-1570\"\n" +
                        "    email:\"contacto@itmorelia.edu.mx\"\n\n" +
                        "fin pagina\n";
        areaCodigoFuente.setText(ejemplo);
    }
    
    private void compilar() {
        try {
            // Verificar si el archivo está guardado
            if (ultimoArchivo == null || carpetaTrabajo == null || nombreProyecto == null) {
                int respuesta = JOptionPane.showConfirmDialog(this,
                    "El código no ha sido guardado.\n" +
                    "¿Deseas guardar el archivo antes de compilar?\n\n" +
                    "Se creará una carpeta con el nombre del archivo y todos los\n" +
                    "archivos generados (HTML, CSS, PHP, SQL) se guardarán ahí.",
                    "Guardar antes de compilar",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                
                if (respuesta == JOptionPane.YES_OPTION) {
                    guardarArchivo();
                    // Si después de guardar aún no hay carpeta, cancelar compilación
                    if (carpetaTrabajo == null || nombreProyecto == null) {
                        areaResultados.append("✗ Compilación cancelada: No se guardó el archivo.\n");
                        return;
                    }
                } else if (respuesta == JOptionPane.NO_OPTION) {
                    // Usar nombre temporal
                    nombreProyecto = "proyecto_temporal";
                    carpetaTrabajo = nombreProyecto;
                    File dirProyecto = new File(carpetaTrabajo);
                    if (!dirProyecto.exists()) {
                        dirProyecto.mkdirs();
                    }
                    areaResultados.append("⚠ ADVERTENCIA: Compilando sin guardar.\n");
                    areaResultados.append("   Se creará carpeta temporal: " + nombreProyecto + "\n\n");
                } else {
                    areaResultados.append("✗ Compilación cancelada por el usuario.\n");
                    return;
                }
            }
            
            btnCompilar.setEnabled(false);
            areaResultados.append("=== Iniciando compilación ===\n");
            areaResultados.append("Proyecto: " + nombreProyecto + "\n");
            areaResultados.append("Carpeta: " + carpetaTrabajo + "\n\n");
            
            // Guardar código en archivo temporal en la carpeta de trabajo
            String codigoFuente = areaCodigoFuente.getText();
            String archivoTemporal = carpetaTrabajo + File.separator + "temp_codigo.txt";
            FileWriter fw = new FileWriter(archivoTemporal);
            fw.write(codigoFuente);
            fw.close();
            
            // Crear el flujo de entrada
            CharStream input = new ANTLRFileStream(archivoTemporal);
            
            // Crear el lexer
            WebPageLanguageLexer lexer = new WebPageLanguageLexer(input);
            
            // Crear el flujo de tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Crear el parser
            WebPageLanguageParser parser = new WebPageLanguageParser(tokens);
            
            // Analizar el programa
            areaResultados.append("✓ Analizando código fuente...\n");
            parser.pagina();
            
            // Verificar errores
            if (parser.getNumberOfSyntaxErrors() == 0) {
                areaResultados.append("✓ Análisis sintáctico completado sin errores\n\n");
                
                // Generar archivos con el nombre del proyecto
                String nombreSalida = carpetaTrabajo + File.separator + nombreProyecto;
                parser.guardarArchivos(nombreSalida);
                
                // Mostrar HTML generado
                areaHTML.setText(leerArchivo(carpetaTrabajo + File.separator + nombreProyecto + ".html"));
                
                // Mostrar CSS generado
                areaCSS.setText(leerArchivo(carpetaTrabajo + File.separator + nombreProyecto + ".css"));
                
                // Mostrar especificación CRUD
                String crudContent = leerArchivo(carpetaTrabajo + File.separator + nombreProyecto + "_crud.txt");
                areaCRUD.setText(crudContent.isEmpty() ? "No se generó especificación CRUD" : crudContent);
                
                // Mostrar PHP generado
                String phpContent = leerArchivo(carpetaTrabajo + File.separator + nombreProyecto + ".php");
                areaPHP.setText(phpContent.isEmpty() ? "No se generó código PHP" : phpContent);
                
                // Mostrar SQL generado
                String sqlContent = leerArchivo(carpetaTrabajo + File.separator + nombreProyecto + "_database.sql");
                areaSQL.setText(sqlContent.isEmpty() ? "No se generó script SQL" : sqlContent);
                
                areaResultados.append("=== ¡COMPILACIÓN EXITOSA! ===\n\n");
                areaResultados.append("📁 Carpeta del proyecto: " + new File(carpetaTrabajo).getAbsolutePath() + "\n\n");
                areaResultados.append("Archivos generados:\n");
                areaResultados.append("  ✓ " + nombreProyecto + ".html\n");
                areaResultados.append("  ✓ " + nombreProyecto + ".css\n");
                if (!crudContent.isEmpty()) {
                    areaResultados.append("  ✓ " + nombreProyecto + "_crud.txt\n");
                }
                if (!phpContent.isEmpty()) {
                    areaResultados.append("  ✓ " + nombreProyecto + ".php\n");
                    areaResultados.append("  ✓ " + nombreProyecto + "_config.php\n");
                }
                if (!sqlContent.isEmpty()) {
                    areaResultados.append("  ✓ " + nombreProyecto + "_database.sql\n");
                }
                areaResultados.append("\n");
                
                if (!phpContent.isEmpty()) {
                    areaResultados.append("📋 INSTRUCCIONES PARA USAR EL SISTEMA CRUD:\n");
                    areaResultados.append("1. Instalar XAMPP o WAMP (servidor web con PHP y MySQL)\n");
                    areaResultados.append("2. Importar '" + nombreProyecto + "_database.sql' en phpMyAdmin\n");
                    areaResultados.append("3. Configurar credenciales en '" + nombreProyecto + "_config.php' si es necesario\n");
                    areaResultados.append("4. Copiar carpeta '" + nombreProyecto + "' a htdocs de XAMPP\n");
                    areaResultados.append("5. Abrir en el navegador: localhost/" + nombreProyecto + "/" + nombreProyecto + ".php\n\n");
                }
                
                areaResultados.append("💡 Todos los archivos del proyecto están organizados en la carpeta '" + nombreProyecto + "'\n");
                
                tabbedPane.setSelectedIndex(0); // Cambiar a tab HTML
                
                JOptionPane.showMessageDialog(this,
                    "Compilación exitosa!\n\nArchivos generados correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } else {
                areaResultados.append("\n✗ ERROR: Se encontraron " + parser.getNumberOfSyntaxErrors() + 
                                    " error(es) de sintaxis\n");
                JOptionPane.showMessageDialog(this,
                    "Se encontraron errores de sintaxis.\nRevise la consola para más detalles.",
                    "Error de compilación",
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            areaResultados.append("\n✗ ERROR: " + ex.getMessage() + "\n");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error durante la compilación:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            btnCompilar.setEnabled(true);
        }
    }
    
    private String leerArchivo(String nombreArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            return "";
        }
    }
    
    private void limpiar() {
        areaCodigoFuente.setText("");
        areaResultados.setText("");
        areaHTML.setText("");
        areaCSS.setText("");
        areaCRUD.setText("");
        areaPHP.setText("");
        areaSQL.setText("");
    }
    
    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setDialogTitle("Abrir archivo de código");
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = fileChooser.getSelectedFile();
                ultimoArchivo = archivo.getAbsolutePath();
                
                // Obtener el nombre sin extensión
                String nombreArchivo = archivo.getName();
                int puntoIndex = nombreArchivo.lastIndexOf('.');
                if (puntoIndex > 0) {
                    nombreProyecto = nombreArchivo.substring(0, puntoIndex);
                } else {
                    nombreProyecto = nombreArchivo;
                }
                
                // Crear carpeta del proyecto si no existe
                File directorioBase = archivo.getParentFile();
                File directorioProyecto = new File(directorioBase, nombreProyecto);
                if (!directorioProyecto.exists()) {
                    directorioProyecto.mkdirs();
                }
                carpetaTrabajo = directorioProyecto.getAbsolutePath();
                
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                StringBuilder sb = new StringBuilder();
                String linea;
                while ((linea = br.readLine()) != null) {
                    sb.append(linea).append("\n");
                }
                br.close();
                areaCodigoFuente.setText(sb.toString());
                areaResultados.append("✓ Archivo cargado: " + archivo.getName() + "\n");
                areaResultados.append("  Proyecto: " + nombreProyecto + "\n");
                areaResultados.append("  Carpeta: " + carpetaTrabajo + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al abrir el archivo:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void guardarArchivo() {
        JFileChooser fileChooser = new JFileChooser(ultimoArchivo != null ? new File(ultimoArchivo).getParent() : ".");
        fileChooser.setDialogTitle("Guardar archivo de código");
        
        // Sugerir nombre si no hay archivo previo
        if (ultimoArchivo == null) {
            fileChooser.setSelectedFile(new File("mi_proyecto.txt"));
        } else {
            fileChooser.setSelectedFile(new File(ultimoArchivo));
        }
        
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = fileChooser.getSelectedFile();
                ultimoArchivo = archivo.getAbsolutePath();
                
                // Obtener el nombre sin extensión
                String nombreArchivo = archivo.getName();
                int puntoIndex = nombreArchivo.lastIndexOf('.');
                if (puntoIndex > 0) {
                    nombreProyecto = nombreArchivo.substring(0, puntoIndex);
                } else {
                    nombreProyecto = nombreArchivo;
                }
                
                // Crear carpeta del proyecto en la misma ubicación del archivo
                File directorioBase = archivo.getParentFile();
                File directorioProyecto = new File(directorioBase, nombreProyecto);
                if (!directorioProyecto.exists()) {
                    directorioProyecto.mkdirs();
                    areaResultados.append("✓ Carpeta creada: " + nombreProyecto + "\n");
                }
                carpetaTrabajo = directorioProyecto.getAbsolutePath();
                
                FileWriter fw = new FileWriter(archivo);
                fw.write(areaCodigoFuente.getText());
                fw.close();
                
                areaResultados.append("✓ Archivo guardado: " + archivo.getName() + "\n");
                areaResultados.append("  Proyecto: " + nombreProyecto + "\n");
                areaResultados.append("  Carpeta de salida: " + carpetaTrabajo + "\n");
                
                JOptionPane.showMessageDialog(this,
                    "Archivo guardado exitosamente.\n\n" +
                    "Los archivos compilados se generarán en:\n" +
                    carpetaTrabajo,
                    "Guardado",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al guardar el archivo:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void abrirEnNavegador() {
        try {
            if (nombreProyecto == null || carpetaTrabajo == null) {
                JOptionPane.showMessageDialog(this,
                    "Primero debes compilar el código para generar el archivo HTML.",
                    "Archivo no encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String archivoHTML = carpetaTrabajo + File.separator + nombreProyecto + ".html";
            File htmlFile = new File(archivoHTML);
            
            if (!htmlFile.exists()) {
                JOptionPane.showMessageDialog(this,
                    "El archivo HTML no existe.\nPrimero debes compilar el código.",
                    "Archivo no encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(htmlFile.toURI());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el navegador:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirPHP() {
        try {
            if (nombreProyecto == null || carpetaTrabajo == null) {
                JOptionPane.showMessageDialog(this,
                    "No se ha generado archivo PHP.\n" +
                    "Primero debes compilar un código con sección CRUD.",
                    "Archivo no encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String archivoPHP = carpetaTrabajo + File.separator + nombreProyecto + ".php";
            File phpFile = new File(archivoPHP);
            
            if (!phpFile.exists()) {
                JOptionPane.showMessageDialog(this,
                    "No se ha generado archivo PHP.\n" +
                    "Asegúrate de que tu código incluya una sección 'crud inicio...crud fin'.",
                    "Archivo no encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Mostrar mensaje con instrucciones
            String mensaje = "Archivo PHP generado exitosamente en:\n" +
                           carpetaTrabajo + File.separator + nombreProyecto + ".php\n\n" +
                           "INSTRUCCIONES PARA EJECUTAR:\n" +
                           "1. Instala XAMPP o WAMP\n" +
                           "2. Copia la carpeta completa '" + nombreProyecto + "'\n" +
                           "   a la carpeta htdocs de XAMPP\n" +
                           "3. Importa '" + nombreProyecto + "_database.sql' en phpMyAdmin\n" +
                           "4. Abre http://localhost/" + nombreProyecto + "/" + nombreProyecto + ".php en tu navegador\n\n" +
                           "¿Deseas abrir la carpeta del proyecto?";
            
            int respuesta = JOptionPane.showConfirmDialog(this,
                mensaje,
                "Sistema CRUD con PHP",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                Desktop.getDesktop().open(new File(carpetaTrabajo));
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void probarBaseDatos() {
        try {
            if (nombreProyecto == null || carpetaTrabajo == null) {
                int respuesta = JOptionPane.showConfirmDialog(this,
                    "No se ha compilado el código todavía.\n\n" +
                    "¿Deseas compilar ahora para probar el sistema de base de datos?",
                    "Compilar primero",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                
                if (respuesta == JOptionPane.YES_OPTION) {
                    compilar();
                    // Esperar un momento para que termine la compilación
                    Thread.sleep(1000);
                    if (nombreProyecto == null || carpetaTrabajo == null) {
                        return;
                    }
                } else {
                    return;
                }
            }
            
            String archivoHTML = carpetaTrabajo + File.separator + nombreProyecto + ".html";
            File htmlFile = new File(archivoHTML);
            
            if (!htmlFile.exists()) {
                JOptionPane.showMessageDialog(this,
                    "El archivo HTML no existe.\nPrimero debes compilar el código.",
                    "Archivo no encontrado",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Mostrar mensaje informativo
            String mensaje = "🎉 ¡Sistema CRUD Listo para Probar!\n\n" +
                           "✅ FUNCIONALIDADES DISPONIBLES:\n" +
                           "   • Agregar nuevos registros\n" +
                           "   • Editar registros existentes\n" +
                           "   • Eliminar registros\n" +
                           "   • Ver todos los registros\n" +
                           "   • Los datos se guardan automáticamente\n\n" +
                           "💡 TIP: Los datos se guardan en LocalStorage de tu navegador.\n" +
                           "   ¡Funciona sin necesidad de servidor!\n\n" +
                           "Se abrirá la página en tu navegador...";
            
            JOptionPane.showMessageDialog(this,
                mensaje,
                "Sistema de Base de Datos",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir en navegador
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(htmlFile.toURI());
            
            areaResultados.append("\n✓ Sistema de Base de Datos abierto en navegador\n");
            areaResultados.append("  Archivo: " + htmlFile.getAbsolutePath() + "\n");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al abrir el sistema de BD:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        // No usar Look and Feel del sistema para que los colores se vean correctamente
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            AnalizadorGUI gui = new AnalizadorGUI();
            gui.setVisible(true);
        });
    }
}
