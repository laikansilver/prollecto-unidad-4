grammar WebPageLanguage;

@header {
    import java.io.*;
    import java.util.List;
    import java.util.ArrayList;
}

@members {
    // Variables para generar HTML, CSS, PHP y SQL
    StringBuilder html = new StringBuilder();
    StringBuilder css = new StringBuilder();
    StringBuilder crudSpec = new StringBuilder();
    StringBuilder phpCode = new StringBuilder();
    StringBuilder sqlScript = new StringBuilder();
    String currentDatabase = "";
    String currentTable = "";
    List<String> campos = new ArrayList<String>();
    List<String> tiposCampos = new ArrayList<String>();
    boolean generarCRUD = false;
    String cssFileName = "pagina.css";  // Nombre del CSS (se actualizará al guardar)
    
    // Método para obtener el HTML generado
    public String getHTML() {
        return html.toString();
    }
    
    // Método para obtener el CSS generado
    public String getCSS() {
        return css.toString();
    }
    
    // Método para obtener la especificación CRUD
    public String getCRUDSpec() {
        return crudSpec.toString();
    }
    
    // Método para obtener el código PHP
    public String getPHPCode() {
        return phpCode.toString();
    }
    
    // Método para obtener el script SQL
    public String getSQLScript() {
        return sqlScript.toString();
    }
    
    // Método para guardar archivos
    public void guardarArchivos(String baseFileName) {
        try {
            // Extraer solo el nombre del archivo sin la ruta
            File f = new File(baseFileName);
            String fileName = f.getName();
            cssFileName = fileName + ".css";
            
            // Actualizar la referencia del CSS en el HTML
            String htmlContent = html.toString();
            htmlContent = htmlContent.replace("pagina.css", cssFileName);
            
            // Guardar HTML
            FileWriter htmlFile = new FileWriter(baseFileName + ".html");
            htmlFile.write(htmlContent);
            htmlFile.close();
            
            // Guardar CSS
            FileWriter cssFile = new FileWriter(baseFileName + ".css");
            cssFile.write(css.toString());
            cssFile.close();
            
            // Guardar especificación CRUD
            if (crudSpec.length() > 0) {
                FileWriter crudFile = new FileWriter(baseFileName + "_crud.txt");
                crudFile.write(crudSpec.toString());
                crudFile.close();
            }
            
            // Guardar código PHP si hay CRUD
            if (phpCode.length() > 0) {
                FileWriter phpFile = new FileWriter(baseFileName + ".php");
                phpFile.write(phpCode.toString());
                phpFile.close();
                
                FileWriter configFile = new FileWriter(baseFileName + "_config.php");
                configFile.write(generarConfigPHP());
                configFile.close();
            }
            
            // Guardar script SQL si hay CRUD
            if (sqlScript.length() > 0) {
                FileWriter sqlFile = new FileWriter(baseFileName + "_database.sql");
                sqlFile.write(sqlScript.toString());
                sqlFile.close();
            }
            
            System.out.println("Archivos generados exitosamente:");
            System.out.println("- " + baseFileName + ".html");
            System.out.println("- " + baseFileName + ".css");
            if (crudSpec.length() > 0) {
                System.out.println("- " + baseFileName + "_crud.txt");
            }
            if (phpCode.length() > 0) {
                System.out.println("- " + baseFileName + ".php");
                System.out.println("- " + baseFileName + "_config.php");
            }
            if (sqlScript.length() > 0) {
                System.out.println("- " + baseFileName + "_database.sql");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar archivos: " + e.getMessage());
        }
    }
    
    // Método para generar archivo de configuración PHP
    private String generarConfigPHP() {
        return CRUDGenerator.generarConfigPHP(currentDatabase);
    }
    
    // Método para generar código PHP con operaciones CRUD
    private void generarPHP() {
        phpCode.append(CRUDGenerator.generarPHP(currentDatabase, currentTable, campos, tiposCampos));
    }
    
    // Método para generar script SQL
    private void generarSQL() {
        sqlScript.append(CRUDGenerator.generarSQL(currentDatabase, currentTable, campos, tiposCampos));
    }
    
    // Método para inicializar CSS base
    private void inicializarCSS() {
        css.append("/* CSS Generado Automáticamente - Diseño Moderno */\n\n");
        css.append("* {\n");
        css.append("    margin: 0;\n");
        css.append("    padding: 0;\n");
        css.append("    box-sizing: border-box;\n");
        css.append("}\n\n");
        css.append("body {\n");
        css.append("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
        css.append("    line-height: 1.6;\n");
        css.append("    background: linear-gradient(135deg, #667eea 0\%, #764ba2 100\%);\n");
        css.append("    min-height: 100vh;\n");
        css.append("    padding: 20px;\n");
        css.append("}\n\n");
        css.append(".container {\n");
        css.append("    max-width: 1200px;\n");
        css.append("    margin: 0 auto;\n");
        css.append("    background: white;\n");
        css.append("    padding: 40px;\n");
        css.append("    border-radius: 20px;\n");
        css.append("    box-shadow: 0 20px 60px rgba(0,0,0,0.3);\n");
        css.append("    animation: slideDown 0.5s ease-out;\n");
        css.append("}\n\n");
        css.append("@keyframes slideDown {\n");
        css.append("    from {\n");
        css.append("        opacity: 0;\n");
        css.append("        transform: translateY(-30px);\n");
        css.append("    }\n");
        css.append("    to {\n");
        css.append("        opacity: 1;\n");
        css.append("        transform: translateY(0);\n");
        css.append("    }\n");
        css.append("}\n\n");
        css.append("hr {\n");
        css.append("    border: none;\n");
        css.append("    height: 3px;\n");
        css.append("    background: linear-gradient(90deg, transparent, #667eea, transparent);\n");
        css.append("    margin: 30px 0;\n");
        css.append("}\n\n");
        css.append("h1 {\n");
        css.append("    color: #333;\n");
        css.append("    text-align: center;\n");
        css.append("    margin: 30px 0;\n");
        css.append("    font-size: 2.5em;\n");
        css.append("    font-weight: 700;\n");
        css.append("    background: linear-gradient(135deg, #667eea 0\%, #764ba2 100\%);\n");
        css.append("    -webkit-background-clip: text;\n");
        css.append("    -webkit-text-fill-color: transparent;\n");
        css.append("    background-clip: text;\n");
        css.append("    animation: fadeIn 0.8s ease-out;\n");
        css.append("}\n\n");
        css.append("@keyframes fadeIn {\n");
        css.append("    from { opacity: 0; }\n");
        css.append("    to { opacity: 1; }\n");
        css.append("}\n\n");
        css.append("h2 {\n");
        css.append("    color: #667eea;\n");
        css.append("    margin: 25px 0 15px 0;\n");
        css.append("    font-size: 1.8em;\n");
        css.append("    font-weight: 600;\n");
        css.append("}\n\n");
        css.append("h3 {\n");
        css.append("    color: #764ba2;\n");
        css.append("    margin: 20px 0 10px 0;\n");
        css.append("    font-size: 1.4em;\n");
        css.append("}\n\n");
        css.append(".menu {\n");
        css.append("    background: linear-gradient(135deg, #667eea 0\%, #764ba2 100\%);\n");
        css.append("    padding: 15px;\n");
        css.append("    margin: 30px 0;\n");
        css.append("    border-radius: 15px;\n");
        css.append("    box-shadow: 0 8px 20px rgba(102, 126, 234, 0.3);\n");
        css.append("}\n\n");
        css.append(".menu ul {\n");
        css.append("    list-style: none;\n");
        css.append("    display: flex;\n");
        css.append("    justify-content: center;\n");
        css.append("    flex-wrap: wrap;\n");
        css.append("}\n\n");
        css.append(".menu li {\n");
        css.append("    margin: 8px 15px;\n");
        css.append("}\n\n");
        css.append(".menu a {\n");
        css.append("    color: white;\n");
        css.append("    text-decoration: none;\n");
        css.append("    padding: 10px 20px;\n");
        css.append("    border-radius: 8px;\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("    font-weight: 500;\n");
        css.append("    display: inline-block;\n");
        css.append("}\n\n");
        css.append(".menu a:hover {\n");
        css.append("    background: rgba(255,255,255,0.2);\n");
        css.append("    transform: translateY(-2px);\n");
        css.append("    box-shadow: 0 4px 12px rgba(0,0,0,0.2);\n");
        css.append("}\n\n");
        css.append(".imagenes {\n");
        css.append("    display: flex;\n");
        css.append("    justify-content: center;\n");
        css.append("    gap: 25px;\n");
        css.append("    margin: 30px 0;\n");
        css.append("    flex-wrap: wrap;\n");
        css.append("}\n\n");
        css.append(".imagenes img {\n");
        css.append("    max-width: 300px;\n");
        css.append("    height: auto;\n");
        css.append("    border-radius: 15px;\n");
        css.append("    box-shadow: 0 10px 30px rgba(0,0,0,0.2);\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("}\n\n");
        css.append(".imagenes img:hover {\n");
        css.append("    transform: scale(1.05) translateY(-5px);\n");
        css.append("    box-shadow: 0 15px 40px rgba(0,0,0,0.3);\n");
        css.append("}\n\n");
        css.append(".texto {\n");
        css.append("    margin: 25px 0;\n");
        css.append("    padding: 20px;\n");
        css.append("    background: linear-gradient(135deg, #f5f7fa 0\%, #c3cfe2 100\%);\n");
        css.append("    border-left: 5px solid #667eea;\n");
        css.append("    border-radius: 10px;\n");
        css.append("    box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("}\n\n");
        css.append(".texto:hover {\n");
        css.append("    transform: translateX(5px);\n");
        css.append("    box-shadow: 0 8px 20px rgba(0,0,0,0.15);\n");
        css.append("}\n\n");
        css.append(".crud-section {\n");
        css.append("    margin: 40px 0;\n");
        css.append("    padding: 30px;\n");
        css.append("    background: linear-gradient(135deg, #ffffff 0\%, #f8f9fa 100\%);\n");
        css.append("    border: none;\n");
        css.append("    border-radius: 20px;\n");
        css.append("    box-shadow: 0 10px 30px rgba(0,0,0,0.1);\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("}\n\n");
        css.append(".crud-section:hover {\n");
        css.append("    box-shadow: 0 15px 40px rgba(0,0,0,0.15);\n");
        css.append("    transform: translateY(-2px);\n");
        css.append("}\n\n");
        css.append(".crud-form {\n");
        css.append("    background: white;\n");
        css.append("    padding: 30px;\n");
        css.append("    border-radius: 15px;\n");
        css.append("    margin: 25px 0;\n");
        css.append("    box-shadow: 0 5px 20px rgba(102, 126, 234, 0.1);\n");
        css.append("}\n\n");
        css.append(".crud-form input, .crud-form select, .crud-form textarea {\n");
        css.append("    width: 100\%;\n");
        css.append("    padding: 12px 15px;\n");
        css.append("    margin: 10px 0;\n");
        css.append("    border: 2px solid #e0e0e0;\n");
        css.append("    border-radius: 10px;\n");
        css.append("    font-size: 15px;\n");
        css.append("    font-family: inherit;\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("    background: #f8f9fa;\n");
        css.append("}\n\n");
        css.append(".crud-form input:focus, .crud-form select:focus, .crud-form textarea:focus {\n");
        css.append("    outline: none;\n");
        css.append("    border-color: #667eea;\n");
        css.append("    background: white;\n");
        css.append("    box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);\n");
        css.append("    transform: translateY(-2px);\n");
        css.append("}\n\n");
        css.append(".crud-form textarea {\n");
        css.append("    min-height: 100px;\n");
        css.append("    resize: vertical;\n");
        css.append("}\n\n");
        css.append(".crud-form label {\n");
        css.append("    display: block;\n");
        css.append("    margin-top: 15px;\n");
        css.append("    font-weight: 600;\n");
        css.append("    color: #555;\n");
        css.append("    font-size: 14px;\n");
        css.append("}\n\n");
        css.append(".btn {\n");
        css.append("    padding: 12px 28px;\n");
        css.append("    border: none;\n");
        css.append("    border-radius: 10px;\n");
        css.append("    cursor: pointer;\n");
        css.append("    margin: 8px 5px;\n");
        css.append("    font-weight: 600;\n");
        css.append("    font-size: 15px;\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("    box-shadow: 0 4px 15px rgba(0,0,0,0.1);\n");
        css.append("    letter-spacing: 0.5px;\n");
        css.append("}\n\n");
        css.append(".btn:hover {\n");
        css.append("    transform: translateY(-3px);\n");
        css.append("    box-shadow: 0 8px 25px rgba(0,0,0,0.2);\n");
        css.append("}\n\n");
        css.append(".btn:active {\n");
        css.append("    transform: translateY(-1px);\n");
        css.append("    box-shadow: 0 4px 15px rgba(0,0,0,0.15);\n");
        css.append("}\n\n");
        css.append(".btn-primary {\n");
        css.append("    background: linear-gradient(135deg, #667eea 0\%, #764ba2 100\%);\n");
        css.append("    color: white;\n");
        css.append("}\n\n");
        css.append(".btn-success {\n");
        css.append("    background: linear-gradient(135deg, #11998e 0\%, #38ef7d 100\%);\n");
        css.append("    color: white;\n");
        css.append("}\n\n");
        css.append(".btn-warning {\n");
        css.append("    background: linear-gradient(135deg, #f093fb 0\%, #f5576c 100\%);\n");
        css.append("    color: white;\n");
        css.append("}\n\n");
        css.append(".btn-danger {\n");
        css.append("    background: linear-gradient(135deg, #f85032 0\%, #e73827 100\%);\n");
        css.append("    color: white;\n");
        css.append("}\n\n");
        css.append(".crud-table {\n");
        css.append("    width: 100\%;\n");
        css.append("    border-collapse: separate;\n");
        css.append("    border-spacing: 0;\n");
        css.append("    margin: 25px 0;\n");
        css.append("    border-radius: 15px;\n");
        css.append("    overflow: hidden;\n");
        css.append("    box-shadow: 0 5px 20px rgba(0,0,0,0.1);\n");
        css.append("}\n\n");
        css.append(".crud-table th, .crud-table td {\n");
        css.append("    padding: 15px;\n");
        css.append("    text-align: left;\n");
        css.append("}\n\n");
        css.append(".crud-table th {\n");
        css.append("    background: linear-gradient(135deg, #667eea 0\%, #764ba2 100\%);\n");
        css.append("    color: white;\n");
        css.append("    font-weight: 600;\n");
        css.append("    text-transform: uppercase;\n");
        css.append("    font-size: 14px;\n");
        css.append("    letter-spacing: 1px;\n");
        css.append("}\n\n");
        css.append(".crud-table tbody tr {\n");
        css.append("    background: white;\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("}\n\n");
        css.append(".crud-table tbody tr:nth-child(even) {\n");
        css.append("    background: #f8f9fa;\n");
        css.append("}\n\n");
        css.append(".crud-table tbody tr:hover {\n");
        css.append("    background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0\%, rgba(118, 75, 162, 0.1) 100\%);\n");
        css.append("    transform: scale(1.01);\n");
        css.append("    box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
        css.append("}\n\n");
        css.append(".mensaje {\n");
        css.append("    padding: 15px 20px;\n");
        css.append("    margin: 20px 0;\n");
        css.append("    border-radius: 12px;\n");
        css.append("    font-weight: 500;\n");
        css.append("    animation: slideIn 0.4s ease-out;\n");
        css.append("    box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
        css.append("}\n\n");
        css.append("@keyframes slideIn {\n");
        css.append("    from {\n");
        css.append("        opacity: 0;\n");
        css.append("        transform: translateX(-20px);\n");
        css.append("    }\n");
        css.append("    to {\n");
        css.append("        opacity: 1;\n");
        css.append("        transform: translateX(0);\n");
        css.append("    }\n");
        css.append("}\n\n");
        css.append(".mensaje-exito {\n");
        css.append("    background: linear-gradient(135deg, #d4edda 0\%, #c3e6cb 100\%);\n");
        css.append("    color: #155724;\n");
        css.append("    border-left: 5px solid #28a745;\n");
        css.append("}\n\n");
        css.append(".mensaje-error {\n");
        css.append("    background: linear-gradient(135deg, #f8d7da 0\%, #f5c6cb 100\%);\n");
        css.append("    color: #721c24;\n");
        css.append("    border-left: 5px solid #dc3545;\n");
        css.append("}\n\n");
        css.append(".acercade {\n");
        css.append("    margin-top: 50px;\n");
        css.append("    padding: 30px;\n");
        css.append("    background: linear-gradient(135deg, #f5f7fa 0\%, #c3cfe2 100\%);\n");
        css.append("    border-radius: 20px;\n");
        css.append("    box-shadow: 0 10px 30px rgba(0,0,0,0.1);\n");
        css.append("}\n\n");
        css.append(".acercade h2 {\n");
        css.append("    margin-bottom: 20px;\n");
        css.append("}\n\n");
        css.append(".acercade p {\n");
        css.append("    margin: 12px 0;\n");
        css.append("    padding: 10px;\n");
        css.append("    background: white;\n");
        css.append("    border-radius: 8px;\n");
        css.append("    transition: all 0.3s ease;\n");
        css.append("}\n\n");
        css.append(".acercade p:hover {\n");
        css.append("    transform: translateX(5px);\n");
        css.append("    box-shadow: 0 3px 10px rgba(0,0,0,0.1);\n");
        css.append("}\n\n");
    }
}

// Regla principal
pagina
    :   'Inicio' 'pagina' 
        { 
            html.append("<!DOCTYPE html>\n");
            html.append("<html lang=\"es\">\n");
            html.append("<head>\n");
            html.append("    <meta charset=\"UTF-8\">\n");
            html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            html.append("    <title>Página Generada</title>\n");
            html.append("    <link rel=\"stylesheet\" href=\"pagina.css\">\n");
            html.append("</head>\n");
            html.append("<body>\n");
            html.append("    <div class=\"container\">\n");
            inicializarCSS();
        }
        (elemento)+
        'fin' 'pagina'
        {
            html.append("    </div>\n");
            html.append("</body>\n");
            html.append("</html>\n");
        }
    ;

elemento
    :   linea
    |   titulo
    |   menu
    |   seccionImagenes
    |   texto
    |   crud
    |   seccionAcercaDe
    ;

linea
    :   'linea' 'simple'
        {
            html.append("        <hr>\n");
        }
    |   'linea' 'doble'
        {
            html.append("        <hr style=\"border-top: 4px double #333;\">\n");
        }
    ;

titulo
    :   'titulo' STRING
        {
            String tituloTexto = $STRING.text;
            tituloTexto = tituloTexto.substring(1, tituloTexto.length()-1); // Quitar comillas
            html.append("        <h1>" + tituloTexto + "</h1>\n");
        }
    ;

menu
    :   'menu' 'opciones' 
        {
            html.append("        <nav class=\"menu\">\n");
            html.append("            <ul>\n");
            html.append("                <li><a href=\"#inicio\">Inicio</a></li>\n");
            html.append("                <li><a href=\"#productos\">Productos</a></li>\n");
            html.append("                <li><a href=\"#servicios\">Servicios</a></li>\n");
            html.append("                <li><a href=\"#contacto\">Contacto</a></li>\n");
            html.append("            </ul>\n");
            html.append("        </nav>\n");
        }
    ;

seccionImagenes
    :   'seccion' 'imagenes' 
        {
            html.append("        <div class=\"imagenes\">\n");
        }
        (imagen)+
        {
            html.append("        </div>\n");
        }
    ;

imagen
    :   ID
        {
            String nombreImg = $ID.text;
            html.append("            <img src=\"" + nombreImg + "\" alt=\"Imagen\">\n");
        }
    ;

texto
    :   'texto' STRING
        {
            String textoContenido = $STRING.text;
            textoContenido = textoContenido.substring(1, textoContenido.length()-1);
            html.append("        <div class=\"texto\">\n");
            html.append("            <p>" + textoContenido + "</p>\n");
            html.append("        </div>\n");
        }
    ;

crud
    :   'crud' 'inicio'
        {
            generarCRUD = true;
            campos.clear();
            tiposCampos.clear();
            html.append("        <div class=\"crud-section\">\n");
            html.append("            <h2>📊 Sistema de Gestión de Base de Datos</h2>\n");
            html.append("            <div class=\"mensaje mensaje-exito\" style=\"background: #d4edda; padding: 15px; margin: 10px 0; border-radius: 5px;\">\n");
            html.append("                <strong>✅ Sistema Híbrido Activo:</strong> Los datos se guardan automáticamente en tu navegador (LocalStorage). \n");
            html.append("                Funciona sin necesidad de servidor. También se genera código PHP/MySQL para implementación real.\n");
            html.append("            </div>\n");
        }
        bdd
        (tabla)+
        'crud' 'fin'
        {
            // Generar formulario HTML con JavaScript
            html.append("            <div class=\"crud-section\">\n");
            html.append("                <h3>➕ Agregar/Editar Registro</h3>\n");
            html.append("                <form id=\"crudForm\" class=\"crud-form\">\n");
            
            for (int i = 1; i < campos.size(); i++) { // Empezar en 1 para saltar el ID
                String campo = campos.get(i);
                String tipo = tiposCampos.get(i);
                String label = campo.substring(0,1).toUpperCase() + campo.substring(1);
                
                html.append("                    <label for=\"" + campo + "\">" + label + ":</label>\n");
                
                if (tipo.equals("TEXT")) {
                    html.append("                    <textarea id=\"" + campo + "\" name=\"" + campo + "\" required></textarea>\n");
                } else if (tipo.equals("DATE")) {
                    html.append("                    <input type=\"date\" id=\"" + campo + "\" name=\"" + campo + "\" required>\n");
                } else if (tipo.equals("INT") || tipo.equals("DECIMAL")) {
                    html.append("                    <input type=\"number\" id=\"" + campo + "\" name=\"" + campo + "\" step=\"" + (tipo.equals("DECIMAL") ? "0.01" : "1") + "\" required>\n");
                } else {
                    html.append("                    <input type=\"text\" id=\"" + campo + "\" name=\"" + campo + "\" required>\n");
                }
            }
            
            html.append("                    <div style=\"margin-top: 15px;\">\n");
            html.append("                        <button type=\"submit\" id=\"btnGuardar\" class=\"btn btn-success\">💾 Guardar</button>\n");
            html.append("                        <button type=\"button\" id=\"btnCancelar\" class=\"btn btn-warning\" onclick=\"limpiarFormulario()\" style=\"display: none;\">❌ Cancelar</button>\n");
            html.append("                    </div>\n");
            html.append("                </form>\n");
            html.append("            </div>\n\n");
            
            // Generar tabla para mostrar registros
            html.append("            <div class=\"crud-section\">\n");
            html.append("                <h3>📋 Registros Guardados</h3>\n");
            html.append("                <p><strong>Total de registros:</strong> <span id=\"totalRegistros\">0</span></p>\n");
            html.append("                <table class=\"crud-table\">\n");
            html.append("                    <thead>\n");
            html.append("                        <tr>\n");
            
            for (String campo : campos) {
                String label = campo.substring(0,1).toUpperCase() + campo.substring(1);
                html.append("                            <th>" + label + "</th>\n");
            }
            html.append("                            <th>Acciones</th>\n");
            html.append("                        </tr>\n");
            html.append("                    </thead>\n");
            html.append("                    <tbody id=\"tablaRegistros\">\n");
            html.append("                        <tr><td colspan=\"" + (campos.size() + 1) + "\" style=\"text-align: center;\">Cargando...</td></tr>\n");
            html.append("                    </tbody>\n");
            html.append("                </table>\n");
            html.append("            </div>\n");
            
            html.append("        </div>\n\n");
            
            // Agregar JavaScript al final del HTML
            html.append("        <script>\n");
            html.append(CRUDGenerator.generarJavaScriptLocalStorage(currentTable, campos, tiposCampos));
            html.append("        </script>\n");
            
            // Generar especificación CRUD
            crudSpec.append("=== 📊 ESPECIFICACIÓN CRUD - SISTEMA HÍBRIDO ===\n\n");
            crudSpec.append("🎯 MODO DE OPERACIÓN: Sistema Híbrido\n");
            crudSpec.append("   - Frontend con LocalStorage (funciona inmediatamente)\n");
            crudSpec.append("   - Backend PHP/MySQL (para implementación real)\n\n");
            crudSpec.append("📦 Base de Datos: " + currentDatabase + "\n");
            crudSpec.append("📋 Tabla: " + currentTable + "\n\n");
            crudSpec.append("🔧 Campos:\n");
            for (int i = 0; i < campos.size(); i++) {
                crudSpec.append("   " + (i+1) + ". " + campos.get(i) + " (" + tiposCampos.get(i) + ")");
                if (i == 0) crudSpec.append(" [PRIMARY KEY, AUTO_INCREMENT]");
                crudSpec.append("\n");
            }
            crudSpec.append("\n✅ Operaciones CRUD Implementadas:\n");
            crudSpec.append("   ✓ CREATE - Agregar nuevos registros\n");
            crudSpec.append("   ✓ READ   - Listar y visualizar registros\n");
            crudSpec.append("   ✓ UPDATE - Modificar registros existentes\n");
            crudSpec.append("   ✓ DELETE - Eliminar registros\n\n");
            
            crudSpec.append("📁 ARCHIVOS GENERADOS:\n\n");
            crudSpec.append("1. HTML CON JAVASCRIPT + LOCALSTORAGE (Funciona YA):\n");
            crudSpec.append("   • salida.html - Página completa con CRUD funcional\n");
            crudSpec.append("   • salida.css  - Estilos incluidos\n");
            crudSpec.append("   ➜ Simplemente abre salida.html en tu navegador\n\n");
            
            crudSpec.append("2. CÓDIGO PHP + MYSQL (Para servidor real):\n");
            crudSpec.append("   • salida.php        - Interfaz CRUD con PHP\n");
            crudSpec.append("   • salida_config.php - Configuración de conexión\n");
            crudSpec.append("   • salida_database.sql - Script para crear BD\n\n");
            
            crudSpec.append("🚀 INSTRUCCIONES DE USO:\n\n");
            crudSpec.append("OPCIÓN A - USO INMEDIATO (Recomendado para pruebas):\n");
            crudSpec.append("   1. Abre 'salida.html' en cualquier navegador\n");
            crudSpec.append("   2. ¡Listo! Puedes agregar, editar y eliminar registros\n");
            crudSpec.append("   3. Los datos se guardan en tu navegador (LocalStorage)\n\n");
            
            crudSpec.append("OPCIÓN B - IMPLEMENTACIÓN CON SERVIDOR REAL:\n");
            crudSpec.append("   1. Instala XAMPP o WAMP (servidor Apache + PHP + MySQL)\n");
            crudSpec.append("   2. En phpMyAdmin, importa 'salida_database.sql'\n");
            crudSpec.append("   3. Edita 'salida_config.php' con tus credenciales de MySQL\n");
            crudSpec.append("   4. Copia archivos .php a la carpeta htdocs de XAMPP\n");
            crudSpec.append("   5. Abre http://localhost/salida.php en tu navegador\n\n");
            
            crudSpec.append("💡 VENTAJAS DEL SISTEMA HÍBRIDO:\n");
            crudSpec.append("   ✓ Funciona inmediatamente sin configuración\n");
            crudSpec.append("   ✓ Ideal para demostraciones y pruebas\n");
            crudSpec.append("   ✓ Incluye código para implementación real\n");
            crudSpec.append("   ✓ Perfecto para reportes y evidencias\n");
            
            generarPHP();
            generarSQL();
        }
    ;

bdd
    :   'bdd' ID
        {
            currentDatabase = $ID.text;
            html.append("            <p><strong>Base de Datos:</strong> " + currentDatabase + "</p>\n");
        }
    ;

tabla
    :   'tabla' ID
        {
            currentTable = $ID.text;
            html.append("            <h3>Tabla: " + currentTable + "</h3>\n");
        }
        (campo)+
        'fintabla'
    ;

campo
    :   'campo' ID ':' tipoDato
        {
            String nombreCampo = $ID.text;
            String tipo = $tipoDato.text;
            campos.add(nombreCampo);
            tiposCampos.add(tipo);
        }
    |   'campo' ID
        {
            String nombreCampo = $ID.text;
            campos.add(nombreCampo);
            tiposCampos.add("VARCHAR");
        }
    ;

tipoDato
    :   'VARCHAR'
    |   'INT'
    |   'TEXT'
    |   'DATE'
    |   'DECIMAL'
    ;

seccionAcercaDe
    :   'seccion' 'acercade'
        {
            html.append("        <div class=\"acercade\">\n");
            html.append("            <h2>Acerca de</h2>\n");
        }
        (infoAcercaDe)+
        {
            html.append("        </div>\n");
        }
    ;

infoAcercaDe
    :   'domicilio' ':' STRING
        {
            String domicilio = $STRING.text;
            domicilio = domicilio.substring(1, domicilio.length()-1);
            html.append("            <p><strong>Domicilio:</strong> " + domicilio + "</p>\n");
        }
    |   'telefono' ':' STRING
        {
            String telefono = $STRING.text;
            telefono = telefono.substring(1, telefono.length()-1);
            html.append("            <p><strong>Teléfono:</strong> " + telefono + "</p>\n");
        }
    |   'email' ':' STRING
        {
            String email = $STRING.text;
            email = email.substring(1, email.length()-1);
            html.append("            <p><strong>Email:</strong> " + email + "</p>\n");
        }
    ;

// TOKENS (ANTLR 3 syntax)
STRING  :   '"' ( ~('"'|'\r'|'\n') )* '"' ;
ID      :   ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'.')* ;
WS      :   (' '|'\t'|'\r'|'\n')+ { skip(); } ;
COMMENT :   '//' (~('\r'|'\n'))* { skip(); } ;

