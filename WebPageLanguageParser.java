// $ANTLR 3.5.2 WebPageLanguage.g 2025-12-09 23:46:35

    import java.io.*;
    import java.util.List;
    import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class WebPageLanguageParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "ID", "STRING", "WS", 
		"':'", "'DATE'", "'DECIMAL'", "'INT'", "'Inicio'", "'TEXT'", "'VARCHAR'", 
		"'acercade'", "'bdd'", "'campo'", "'crud'", "'doble'", "'domicilio'", 
		"'email'", "'fin'", "'fintabla'", "'imagenes'", "'inicio'", "'linea'", 
		"'menu'", "'opciones'", "'pagina'", "'seccion'", "'simple'", "'tabla'", 
		"'telefono'", "'texto'", "'titulo'"
	};
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int COMMENT=4;
	public static final int ID=5;
	public static final int STRING=6;
	public static final int WS=7;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public WebPageLanguageParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public WebPageLanguageParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return WebPageLanguageParser.tokenNames; }
	@Override public String getGrammarFileName() { return "WebPageLanguage.g"; }


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
	        css.append("    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n");
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
	        css.append("    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n");
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
	        css.append("    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n");
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
	        css.append("    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);\n");
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
	        css.append("    background: linear-gradient(135deg, #ffffff 0%, #f8f9fa 100%);\n");
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
	        css.append("    width: 100%;\n");
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
	        css.append("    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n");
	        css.append("    color: white;\n");
	        css.append("}\n\n");
	        css.append(".btn-success {\n");
	        css.append("    background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);\n");
	        css.append("    color: white;\n");
	        css.append("}\n\n");
	        css.append(".btn-warning {\n");
	        css.append("    background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);\n");
	        css.append("    color: white;\n");
	        css.append("}\n\n");
	        css.append(".btn-danger {\n");
	        css.append("    background: linear-gradient(135deg, #f85032 0%, #e73827 100%);\n");
	        css.append("    color: white;\n");
	        css.append("}\n\n");
	        css.append(".crud-table {\n");
	        css.append("    width: 100%;\n");
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
	        css.append("    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n");
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
	        css.append("    background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0%, rgba(118, 75, 162, 0.1) 100%);\n");
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
	        css.append("    background: linear-gradient(135deg, #d4edda 0%, #c3e6cb 100%);\n");
	        css.append("    color: #155724;\n");
	        css.append("    border-left: 5px solid #28a745;\n");
	        css.append("}\n\n");
	        css.append(".mensaje-error {\n");
	        css.append("    background: linear-gradient(135deg, #f8d7da 0%, #f5c6cb 100%);\n");
	        css.append("    color: #721c24;\n");
	        css.append("    border-left: 5px solid #dc3545;\n");
	        css.append("}\n\n");
	        css.append(".acercade {\n");
	        css.append("    margin-top: 50px;\n");
	        css.append("    padding: 30px;\n");
	        css.append("    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);\n");
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



	// $ANTLR start "pagina"
	// WebPageLanguage.g:427:1: pagina : 'Inicio' 'pagina' ( elemento )+ 'fin' 'pagina' ;
	public final void pagina() throws RecognitionException {
		try {
			// WebPageLanguage.g:428:5: ( 'Inicio' 'pagina' ( elemento )+ 'fin' 'pagina' )
			// WebPageLanguage.g:428:9: 'Inicio' 'pagina' ( elemento )+ 'fin' 'pagina'
			{
			match(input,12,FOLLOW_12_in_pagina29); 
			match(input,29,FOLLOW_29_in_pagina31); 
			 
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
			        
			// WebPageLanguage.g:442:9: ( elemento )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==18||(LA1_0 >= 26 && LA1_0 <= 27)||LA1_0==30||(LA1_0 >= 34 && LA1_0 <= 35)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// WebPageLanguage.g:442:10: elemento
					{
					pushFollow(FOLLOW_elemento_in_pagina53);
					elemento();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			match(input,22,FOLLOW_22_in_pagina65); 
			match(input,29,FOLLOW_29_in_pagina67); 

			            html.append("    </div>\n");
			            html.append("</body>\n");
			            html.append("</html>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "pagina"



	// $ANTLR start "elemento"
	// WebPageLanguage.g:451:1: elemento : ( linea | titulo | menu | seccionImagenes | texto | crud | seccionAcercaDe );
	public final void elemento() throws RecognitionException {
		try {
			// WebPageLanguage.g:452:5: ( linea | titulo | menu | seccionImagenes | texto | crud | seccionAcercaDe )
			int alt2=7;
			switch ( input.LA(1) ) {
			case 26:
				{
				alt2=1;
				}
				break;
			case 35:
				{
				alt2=2;
				}
				break;
			case 27:
				{
				alt2=3;
				}
				break;
			case 30:
				{
				int LA2_4 = input.LA(2);
				if ( (LA2_4==24) ) {
					alt2=4;
				}
				else if ( (LA2_4==15) ) {
					alt2=7;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 34:
				{
				alt2=5;
				}
				break;
			case 18:
				{
				alt2=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// WebPageLanguage.g:452:9: linea
					{
					pushFollow(FOLLOW_linea_in_elemento96);
					linea();
					state._fsp--;

					}
					break;
				case 2 :
					// WebPageLanguage.g:453:9: titulo
					{
					pushFollow(FOLLOW_titulo_in_elemento106);
					titulo();
					state._fsp--;

					}
					break;
				case 3 :
					// WebPageLanguage.g:454:9: menu
					{
					pushFollow(FOLLOW_menu_in_elemento116);
					menu();
					state._fsp--;

					}
					break;
				case 4 :
					// WebPageLanguage.g:455:9: seccionImagenes
					{
					pushFollow(FOLLOW_seccionImagenes_in_elemento126);
					seccionImagenes();
					state._fsp--;

					}
					break;
				case 5 :
					// WebPageLanguage.g:456:9: texto
					{
					pushFollow(FOLLOW_texto_in_elemento136);
					texto();
					state._fsp--;

					}
					break;
				case 6 :
					// WebPageLanguage.g:457:9: crud
					{
					pushFollow(FOLLOW_crud_in_elemento146);
					crud();
					state._fsp--;

					}
					break;
				case 7 :
					// WebPageLanguage.g:458:9: seccionAcercaDe
					{
					pushFollow(FOLLOW_seccionAcercaDe_in_elemento156);
					seccionAcercaDe();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "elemento"



	// $ANTLR start "linea"
	// WebPageLanguage.g:461:1: linea : ( 'linea' 'simple' | 'linea' 'doble' );
	public final void linea() throws RecognitionException {
		try {
			// WebPageLanguage.g:462:5: ( 'linea' 'simple' | 'linea' 'doble' )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==26) ) {
				int LA3_1 = input.LA(2);
				if ( (LA3_1==31) ) {
					alt3=1;
				}
				else if ( (LA3_1==19) ) {
					alt3=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// WebPageLanguage.g:462:9: 'linea' 'simple'
					{
					match(input,26,FOLLOW_26_in_linea175); 
					match(input,31,FOLLOW_31_in_linea177); 

					            html.append("        <hr>\n");
					        
					}
					break;
				case 2 :
					// WebPageLanguage.g:466:9: 'linea' 'doble'
					{
					match(input,26,FOLLOW_26_in_linea197); 
					match(input,19,FOLLOW_19_in_linea199); 

					            html.append("        <hr style=\"border-top: 4px double #333;\">\n");
					        
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "linea"



	// $ANTLR start "titulo"
	// WebPageLanguage.g:472:1: titulo : 'titulo' STRING ;
	public final void titulo() throws RecognitionException {
		Token STRING1=null;

		try {
			// WebPageLanguage.g:473:5: ( 'titulo' STRING )
			// WebPageLanguage.g:473:9: 'titulo' STRING
			{
			match(input,35,FOLLOW_35_in_titulo228); 
			STRING1=(Token)match(input,STRING,FOLLOW_STRING_in_titulo230); 

			            String tituloTexto = (STRING1!=null?STRING1.getText():null);
			            tituloTexto = tituloTexto.substring(1, tituloTexto.length()-1); // Quitar comillas
			            html.append("        <h1>" + tituloTexto + "</h1>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "titulo"



	// $ANTLR start "menu"
	// WebPageLanguage.g:481:1: menu : 'menu' 'opciones' ;
	public final void menu() throws RecognitionException {
		try {
			// WebPageLanguage.g:482:5: ( 'menu' 'opciones' )
			// WebPageLanguage.g:482:9: 'menu' 'opciones'
			{
			match(input,27,FOLLOW_27_in_menu259); 
			match(input,28,FOLLOW_28_in_menu261); 

			            html.append("        <nav class=\"menu\">\n");
			            html.append("            <ul>\n");
			            html.append("                <li><a href=\"#inicio\">Inicio</a></li>\n");
			            html.append("                <li><a href=\"#productos\">Productos</a></li>\n");
			            html.append("                <li><a href=\"#servicios\">Servicios</a></li>\n");
			            html.append("                <li><a href=\"#contacto\">Contacto</a></li>\n");
			            html.append("            </ul>\n");
			            html.append("        </nav>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "menu"



	// $ANTLR start "seccionImagenes"
	// WebPageLanguage.g:495:1: seccionImagenes : 'seccion' 'imagenes' ( imagen )+ ;
	public final void seccionImagenes() throws RecognitionException {
		try {
			// WebPageLanguage.g:496:5: ( 'seccion' 'imagenes' ( imagen )+ )
			// WebPageLanguage.g:496:9: 'seccion' 'imagenes' ( imagen )+
			{
			match(input,30,FOLLOW_30_in_seccionImagenes291); 
			match(input,24,FOLLOW_24_in_seccionImagenes293); 

			            html.append("        <div class=\"imagenes\">\n");
			        
			// WebPageLanguage.g:500:9: ( imagen )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==ID) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// WebPageLanguage.g:500:10: imagen
					{
					pushFollow(FOLLOW_imagen_in_seccionImagenes315);
					imagen();
					state._fsp--;

					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}


			            html.append("        </div>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "seccionImagenes"



	// $ANTLR start "imagen"
	// WebPageLanguage.g:506:1: imagen : ID ;
	public final void imagen() throws RecognitionException {
		Token ID2=null;

		try {
			// WebPageLanguage.g:507:5: ( ID )
			// WebPageLanguage.g:507:9: ID
			{
			ID2=(Token)match(input,ID,FOLLOW_ID_in_imagen346); 

			            String nombreImg = (ID2!=null?ID2.getText():null);
			            html.append("            <img src=\"" + nombreImg + "\" alt=\"Imagen\">\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "imagen"



	// $ANTLR start "texto"
	// WebPageLanguage.g:514:1: texto : 'texto' STRING ;
	public final void texto() throws RecognitionException {
		Token STRING3=null;

		try {
			// WebPageLanguage.g:515:5: ( 'texto' STRING )
			// WebPageLanguage.g:515:9: 'texto' STRING
			{
			match(input,34,FOLLOW_34_in_texto375); 
			STRING3=(Token)match(input,STRING,FOLLOW_STRING_in_texto377); 

			            String textoContenido = (STRING3!=null?STRING3.getText():null);
			            textoContenido = textoContenido.substring(1, textoContenido.length()-1);
			            html.append("        <div class=\"texto\">\n");
			            html.append("            <p>" + textoContenido + "</p>\n");
			            html.append("        </div>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "texto"



	// $ANTLR start "crud"
	// WebPageLanguage.g:525:1: crud : 'crud' 'inicio' bdd ( tabla )+ 'crud' 'fin' ;
	public final void crud() throws RecognitionException {
		try {
			// WebPageLanguage.g:526:5: ( 'crud' 'inicio' bdd ( tabla )+ 'crud' 'fin' )
			// WebPageLanguage.g:526:9: 'crud' 'inicio' bdd ( tabla )+ 'crud' 'fin'
			{
			match(input,18,FOLLOW_18_in_crud406); 
			match(input,25,FOLLOW_25_in_crud408); 

			            generarCRUD = true;
			            campos.clear();
			            tiposCampos.clear();
			            html.append("        <div class=\"crud-section\">\n");
			            html.append("            <h2>📊 Sistema de Gestión de Base de Datos</h2>\n");
			            html.append("            <div class=\"mensaje mensaje-exito\" style=\"background: #d4edda; padding: 15px; margin: 10px 0; border-radius: 5px;\">\n");
			            html.append("                <strong>✅ Sistema Híbrido Activo:</strong> Los datos se guardan automáticamente en tu navegador (LocalStorage). \n");
			            html.append("                Funciona sin necesidad de servidor. También se genera código PHP/MySQL para implementación real.\n");
			            html.append("            </div>\n");
			        
			pushFollow(FOLLOW_bdd_in_crud428);
			bdd();
			state._fsp--;

			// WebPageLanguage.g:539:9: ( tabla )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==32) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// WebPageLanguage.g:539:10: tabla
					{
					pushFollow(FOLLOW_tabla_in_crud439);
					tabla();
					state._fsp--;

					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			match(input,18,FOLLOW_18_in_crud451); 
			match(input,22,FOLLOW_22_in_crud453); 

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

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "crud"



	// $ANTLR start "bdd"
	// WebPageLanguage.g:654:1: bdd : 'bdd' ID ;
	public final void bdd() throws RecognitionException {
		Token ID4=null;

		try {
			// WebPageLanguage.g:655:5: ( 'bdd' ID )
			// WebPageLanguage.g:655:9: 'bdd' ID
			{
			match(input,16,FOLLOW_16_in_bdd482); 
			ID4=(Token)match(input,ID,FOLLOW_ID_in_bdd484); 

			            currentDatabase = (ID4!=null?ID4.getText():null);
			            html.append("            <p><strong>Base de Datos:</strong> " + currentDatabase + "</p>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "bdd"



	// $ANTLR start "tabla"
	// WebPageLanguage.g:662:1: tabla : 'tabla' ID ( campo )+ 'fintabla' ;
	public final void tabla() throws RecognitionException {
		Token ID5=null;

		try {
			// WebPageLanguage.g:663:5: ( 'tabla' ID ( campo )+ 'fintabla' )
			// WebPageLanguage.g:663:9: 'tabla' ID ( campo )+ 'fintabla'
			{
			match(input,32,FOLLOW_32_in_tabla513); 
			ID5=(Token)match(input,ID,FOLLOW_ID_in_tabla515); 

			            currentTable = (ID5!=null?ID5.getText():null);
			            html.append("            <h3>Tabla: " + currentTable + "</h3>\n");
			        
			// WebPageLanguage.g:668:9: ( campo )+
			int cnt6=0;
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==17) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// WebPageLanguage.g:668:10: campo
					{
					pushFollow(FOLLOW_campo_in_tabla536);
					campo();
					state._fsp--;

					}
					break;

				default :
					if ( cnt6 >= 1 ) break loop6;
					EarlyExitException eee = new EarlyExitException(6, input);
					throw eee;
				}
				cnt6++;
			}

			match(input,23,FOLLOW_23_in_tabla548); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "tabla"



	// $ANTLR start "campo"
	// WebPageLanguage.g:672:1: campo : ( 'campo' ID ':' tipoDato | 'campo' ID );
	public final void campo() throws RecognitionException {
		Token ID6=null;
		Token ID8=null;
		ParserRuleReturnScope tipoDato7 =null;

		try {
			// WebPageLanguage.g:673:5: ( 'campo' ID ':' tipoDato | 'campo' ID )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==17) ) {
				int LA7_1 = input.LA(2);
				if ( (LA7_1==ID) ) {
					int LA7_2 = input.LA(3);
					if ( (LA7_2==8) ) {
						alt7=1;
					}
					else if ( (LA7_2==17||LA7_2==23) ) {
						alt7=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// WebPageLanguage.g:673:9: 'campo' ID ':' tipoDato
					{
					match(input,17,FOLLOW_17_in_campo567); 
					ID6=(Token)match(input,ID,FOLLOW_ID_in_campo569); 
					match(input,8,FOLLOW_8_in_campo571); 
					pushFollow(FOLLOW_tipoDato_in_campo573);
					tipoDato7=tipoDato();
					state._fsp--;


					            String nombreCampo = (ID6!=null?ID6.getText():null);
					            String tipo = (tipoDato7!=null?input.toString(tipoDato7.start,tipoDato7.stop):null);
					            campos.add(nombreCampo);
					            tiposCampos.add(tipo);
					        
					}
					break;
				case 2 :
					// WebPageLanguage.g:680:9: 'campo' ID
					{
					match(input,17,FOLLOW_17_in_campo593); 
					ID8=(Token)match(input,ID,FOLLOW_ID_in_campo595); 

					            String nombreCampo = (ID8!=null?ID8.getText():null);
					            campos.add(nombreCampo);
					            tiposCampos.add("VARCHAR");
					        
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "campo"


	public static class tipoDato_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tipoDato"
	// WebPageLanguage.g:688:1: tipoDato : ( 'VARCHAR' | 'INT' | 'TEXT' | 'DATE' | 'DECIMAL' );
	public final WebPageLanguageParser.tipoDato_return tipoDato() throws RecognitionException {
		WebPageLanguageParser.tipoDato_return retval = new WebPageLanguageParser.tipoDato_return();
		retval.start = input.LT(1);

		try {
			// WebPageLanguage.g:689:5: ( 'VARCHAR' | 'INT' | 'TEXT' | 'DATE' | 'DECIMAL' )
			// WebPageLanguage.g:
			{
			if ( (input.LA(1) >= 9 && input.LA(1) <= 11)||(input.LA(1) >= 13 && input.LA(1) <= 14) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tipoDato"



	// $ANTLR start "seccionAcercaDe"
	// WebPageLanguage.g:696:1: seccionAcercaDe : 'seccion' 'acercade' ( infoAcercaDe )+ ;
	public final void seccionAcercaDe() throws RecognitionException {
		try {
			// WebPageLanguage.g:697:5: ( 'seccion' 'acercade' ( infoAcercaDe )+ )
			// WebPageLanguage.g:697:9: 'seccion' 'acercade' ( infoAcercaDe )+
			{
			match(input,30,FOLLOW_30_in_seccionAcercaDe683); 
			match(input,15,FOLLOW_15_in_seccionAcercaDe685); 

			            html.append("        <div class=\"acercade\">\n");
			            html.append("            <h2>Acerca de</h2>\n");
			        
			// WebPageLanguage.g:702:9: ( infoAcercaDe )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= 20 && LA8_0 <= 21)||LA8_0==33) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// WebPageLanguage.g:702:10: infoAcercaDe
					{
					pushFollow(FOLLOW_infoAcercaDe_in_seccionAcercaDe706);
					infoAcercaDe();
					state._fsp--;

					}
					break;

				default :
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
			}


			            html.append("        </div>\n");
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "seccionAcercaDe"



	// $ANTLR start "infoAcercaDe"
	// WebPageLanguage.g:708:1: infoAcercaDe : ( 'domicilio' ':' STRING | 'telefono' ':' STRING | 'email' ':' STRING );
	public final void infoAcercaDe() throws RecognitionException {
		Token STRING9=null;
		Token STRING10=null;
		Token STRING11=null;

		try {
			// WebPageLanguage.g:709:5: ( 'domicilio' ':' STRING | 'telefono' ':' STRING | 'email' ':' STRING )
			int alt9=3;
			switch ( input.LA(1) ) {
			case 20:
				{
				alt9=1;
				}
				break;
			case 33:
				{
				alt9=2;
				}
				break;
			case 21:
				{
				alt9=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// WebPageLanguage.g:709:9: 'domicilio' ':' STRING
					{
					match(input,20,FOLLOW_20_in_infoAcercaDe737); 
					match(input,8,FOLLOW_8_in_infoAcercaDe739); 
					STRING9=(Token)match(input,STRING,FOLLOW_STRING_in_infoAcercaDe741); 

					            String domicilio = (STRING9!=null?STRING9.getText():null);
					            domicilio = domicilio.substring(1, domicilio.length()-1);
					            html.append("            <p><strong>Domicilio:</strong> " + domicilio + "</p>\n");
					        
					}
					break;
				case 2 :
					// WebPageLanguage.g:715:9: 'telefono' ':' STRING
					{
					match(input,33,FOLLOW_33_in_infoAcercaDe761); 
					match(input,8,FOLLOW_8_in_infoAcercaDe763); 
					STRING10=(Token)match(input,STRING,FOLLOW_STRING_in_infoAcercaDe765); 

					            String telefono = (STRING10!=null?STRING10.getText():null);
					            telefono = telefono.substring(1, telefono.length()-1);
					            html.append("            <p><strong>Teléfono:</strong> " + telefono + "</p>\n");
					        
					}
					break;
				case 3 :
					// WebPageLanguage.g:721:9: 'email' ':' STRING
					{
					match(input,21,FOLLOW_21_in_infoAcercaDe785); 
					match(input,8,FOLLOW_8_in_infoAcercaDe787); 
					STRING11=(Token)match(input,STRING,FOLLOW_STRING_in_infoAcercaDe789); 

					            String email = (STRING11!=null?STRING11.getText():null);
					            email = email.substring(1, email.length()-1);
					            html.append("            <p><strong>Email:</strong> " + email + "</p>\n");
					        
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "infoAcercaDe"

	// Delegated rules



	public static final BitSet FOLLOW_12_in_pagina29 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_pagina31 = new BitSet(new long[]{0x0000000C4C040000L});
	public static final BitSet FOLLOW_elemento_in_pagina53 = new BitSet(new long[]{0x0000000C4C440000L});
	public static final BitSet FOLLOW_22_in_pagina65 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_pagina67 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_linea_in_elemento96 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_titulo_in_elemento106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_menu_in_elemento116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_seccionImagenes_in_elemento126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_texto_in_elemento136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_crud_in_elemento146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_seccionAcercaDe_in_elemento156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_linea175 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_31_in_linea177 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_linea197 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_linea199 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_titulo228 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_STRING_in_titulo230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_menu259 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_menu261 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_seccionImagenes291 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_seccionImagenes293 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_imagen_in_seccionImagenes315 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_ID_in_imagen346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_texto375 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_STRING_in_texto377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_crud406 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_crud408 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_bdd_in_crud428 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_tabla_in_crud439 = new BitSet(new long[]{0x0000000100040000L});
	public static final BitSet FOLLOW_18_in_crud451 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_crud453 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_16_in_bdd482 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ID_in_bdd484 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_tabla513 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ID_in_tabla515 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_campo_in_tabla536 = new BitSet(new long[]{0x0000000000820000L});
	public static final BitSet FOLLOW_23_in_tabla548 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_campo567 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ID_in_campo569 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_campo571 = new BitSet(new long[]{0x0000000000006E00L});
	public static final BitSet FOLLOW_tipoDato_in_campo573 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_campo593 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ID_in_campo595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_seccionAcercaDe683 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_seccionAcercaDe685 = new BitSet(new long[]{0x0000000200300000L});
	public static final BitSet FOLLOW_infoAcercaDe_in_seccionAcercaDe706 = new BitSet(new long[]{0x0000000200300002L});
	public static final BitSet FOLLOW_20_in_infoAcercaDe737 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_infoAcercaDe739 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_STRING_in_infoAcercaDe741 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_infoAcercaDe761 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_infoAcercaDe763 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_STRING_in_infoAcercaDe765 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_21_in_infoAcercaDe785 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_8_in_infoAcercaDe787 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_STRING_in_infoAcercaDe789 = new BitSet(new long[]{0x0000000000000002L});
}
