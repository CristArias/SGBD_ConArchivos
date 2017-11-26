package logica;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import persistencia.Archivo;
import persistencia.Tabla;

/**
 *
 * @author Danilo López - dlopezs@unicauca.edu.co
 */
public class ControladorSelect {      
    
    private Gson gson = new Gson();
    
    /**
     * Aplica un split al texto de la consulta y mira las columnas
     * @param consulta_usuario consulta que ingreso el usuario
     * @return 
     */
    public String ConsultaSELECTAValidar(String consulta_usuario)
    {
        
        if(consulta_usuario.equalsIgnoreCase(""))
            return  "EL CAMPO DE CONSULTA SE ENCUENTRA VACIO";
        
        String errores = "";                 
        consulta_usuario = consulta_usuario.trim();//elimina espacios
        
        String MiCadena = consulta_usuario.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");
        
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("SELECT"))
                continue;
            
            if(columna.equalsIgnoreCase("FROM"))
                break;
            
            cadenita = cadenita + " " + columna;///cada una de las columnas que esta en la consulta las concatena
            //System.out.println(cadenita);
        }        
        String cadenaNueva = cadenita.replace(" ", "");
        cadenita = cadenita.trim();
        MiCadena=MiCadena.replace(cadenita, cadenaNueva);
        
        MiCadena2 = MiCadena.split(" ");
        
        if(MiCadena2.length > 1)
        {
//            if(MiCadena2[1].length() == 1)
//            {
//                if(!MiCadena2[1].equals("*")){
//                    errores = errores + MiCadena2[1] + " Nombre de columna incorrecto";
//                    System.out.println("=== errores " + errores);
//                }
//            }
//            else
//            {
                int cont = 0;
                String var1 = MiCadena2[1];
                for(int i = 0; i < var1.length(); i++ )
                {
                    if(var1.charAt(i) == ',')
                        cont = cont + 1;
                }
                String[] Columnas = MiCadena2[1].split(",");

                for(int i = 0; i < Columnas.length; i++)
                {
                    Columnas[i] = Columnas[i].trim();
                }

                if(Columnas.length < cont){
                    System.out.println("error  = " + errores + "¡La sentencia de las columnas se encuentra erronea!");
                    return errores + "¡La sentencia de las columnas se encuentra erronea!";                    
                }
        }
        return SintaxisSELECT(/*tablas, */MiCadena);
    }
    
    public String nombreTablaSelect(String consulta)
    {
        String MiCadena = "";        
        consulta = consulta.trim();//elimina espacios        
        MiCadena = consulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");                
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("FROM"))
                continue;           
            if(columna.equalsIgnoreCase("WHERE"))
                break;            
            cadenita = columna;// Nombre de la tabla para el select
        }
        return cadenita;
    }
    
    /**
    * Verificar sintaxis del SELECT
    * @param consulta_usuario consulta que ingreso el usuario
    * @return mensaje de error si algún componente de la cadena no crresponde 
    * con la sintaxis correcta, null si la consulta en la variable 
    * consula_usuario es correcta
    */
    public String SintaxisSELECT(String consulta_usuario){
        String errores = "";
        //System.out.println(consulta_usuario + "hol");
            
            String[] campos = consulta_usuario.split(" ");
            if(campos.length  <= 3 )
                return errores + "La consulta se encuentra incompleta.";
               
            boolean isWhere = false;
            String nombreColumnas = "";
            String nombreTabla = "";
            String nombreTabla2 = "";
            String nombreColumnaWhere = "";
            boolean where5 = false;
            boolean where6 = false;
            boolean where7 = false;    
            boolean consultaInnerFull = false;
            boolean hayOrder = false;
            boolean consultaInnerFullOrder = false;
            
            for(int i = 0; i < campos.length; i++)//if (!campos.length >= 3) 
            {                                                
                System.out.println(campos[i] + " +" + i);
                    
                    //SELECT este en posición correspondiente y este bien escrito
                    if(i == 0 && !campos[i].equalsIgnoreCase("SELECT"))
                        return errores + "Se esperaba un SELECT como comando inicial de la consulta.\n";
                                         
                    if(i == 1){
                        nombreColumnas = campos[i];
                    }
                    //FROM este en posición correspondiente y este bien escrito
                    if(i == 2 && !campos[i].equalsIgnoreCase("FROM"))
                        return errores + "La sentencia FROM no se encuentra especificada \n";
                    
                    if(i == 3){                        
                        if(esTabla(campos[i])){
                            nombreTabla = campos[i];
                            String[] cols = nombreColumnas.split(",");
                            for (String col : cols) {
                                if(!esColumnaTabla(campos[i], col))
                                    return "error. Columna \'"+ col +"\' desconocida.";
                            }
                        }else{
                            return "error. La tabla no existe, verifique su consulta.";
                        }                        
                    }
                    
                    if(i == 4 && campos[i].equalsIgnoreCase("WHERE")){isWhere = true;}
                    else{ 
                        if(i == 4 && campos[i].equalsIgnoreCase("INNER")){ 
                            isWhere = false;
                        }else {
                            if(i == 4)
                                return "error, se esperaba la clausula INNER ó WHERE.";
                        }
                    }
                                        
                    if(isWhere){
                        if(i == 5){
                            if(!esColumnaTabla(nombreTabla, campos[i]))
                                return "error. Columna \'"+ campos[i] +"\' desconocida.";
                            nombreColumnaWhere = campos[i];
                            where5 = true;
                        }
                        if(i == 6){
                            if(!campos[i].equalsIgnoreCase(">") &&
                               !campos[i].equalsIgnoreCase(">=") &&  
                               !campos[i].equalsIgnoreCase("=") && 
                               !campos[i].equalsIgnoreCase("<") && 
                               !campos[i].equalsIgnoreCase("<=") && 
                               !campos[i].equalsIgnoreCase("<>")){
                                return "error, se esperaba un operador de comparacion.";
                            }
                            where6 = true;                            
                        }
                        if(i == 7){  
                            int res = validarCampoComparacion(nombreTabla, nombreColumnaWhere, campos[i]);
                            if(res == 0){
                                return "error, el tipo de dato ingresado: \'"+campos[i]+"\', no es valido para comparar";
                            }
                            if(res < 0){
                                return "error, el tipo de dato ingresado: \'"+campos[i]+"\', no es valido para comparar, debe adicionar comillas";
                            }
                            where7 = true;
                        }   
                    }
                    else{
                        if(i == 5 && !campos[i].equalsIgnoreCase("JOIN")){
                            return "error. se esperaba la clausula JOIN.";
                        }

                        if(i == 6){
                            //validar que exista la tabla del join, retornar la
                            if(!esTabla(campos[i])){
                                return "error. La tabla \'"+campos[i]+"\' no existe, verifique su consulta";
                            }
                            nombreTabla2 = campos[i];
                        }
                        if(i == 7 && !campos[i].equalsIgnoreCase("ON")){
                            return "error, se esperaba la clausula ON.";
                        }                    

                        if(i > 8){                            
                            String[] conds = campos[i].split(".");
                            if(esTabla(conds[0])){
                                if(!esColumnaTabla(conds[0], conds[1])){
                                    return "error. la tabla "+conds[0]+" no posee la columna "+conds[1];
                                }                                
                            }else{
                                return "error. la tabla "+conds[0]+" no existe";
                            }                            
                        }

                        if(i == 9 && !campos[i].equalsIgnoreCase(">") &&
                               !campos[i].equalsIgnoreCase(">=") &&  
                               !campos[i].equalsIgnoreCase("=") && 
                               !campos[i].equalsIgnoreCase("<") && 
                               !campos[i].equalsIgnoreCase("<=") && 
                               !campos[i].equalsIgnoreCase("<>")){
                            return "error, se esperaba un perador booleano";
                        }
                        
                        if(i == 10){
                            String[] conds = campos[i].split(".");
                            if(esTabla(conds[0])){
                                if(!esColumnaTabla(conds[0], conds[1])){
                                    return "error. la tabla "+conds[0]+" no posee la columna "+conds[1];
                                }                                
                            }else{
                                return "error. la tabla "+conds[0]+" no existe";
                            }
                            consultaInnerFull = true;                            
                        }
                        
                        if(i == 11 && !campos[i].equalsIgnoreCase("ORDER")){
                            hayOrder = true;
                            return "error. se esperaba la clausula ORDER.";
                        }
                        
                        if(i == 12 && !campos[i].equalsIgnoreCase("BY")){
                            return "error. se esperaba la clausula BY.";
                        }

                        if(i == 13){                                         
                            //validar que la columna este en la tabla
                            if(esColumnaTabla(nombreTabla, campos[i]) || esColumnaTabla(nombreTabla2, campos[i])){
                                return "error";
                            }
                            consultaInnerFullOrder = true;
                        }
                        
                    }
            }           
        if(isWhere){
            if(where5 && where6 && where7){
                return null;
            }
            else return "error. faltan algunas sentencias asociadas a la clausula WHERE.";
        }else{
            if(consultaInnerFull){
                if(hayOrder){
                    if(consultaInnerFullOrder){
                        return null;
                    }else{
                        return "error. faltan algunas sentencias asociadas a la clausula ORDER.";
                    }            
                }
                return null;
            }
            else{
                return "error. faltan algunas sentencias asociadas a la clausula INNER.";
            }
        }
    }
    
    /**
     * funciona para saber si una tabla se encuentra en el SGBD
     * @param nombreTabla nombre de la tabal que se dese buscar
     * @return TRUE si la tabla existe, FALSE de lo contrario
     */
    public boolean esTabla(String nombreTabla){        
        File file = new File(Archivo.obtenerRutaDirectorioTablaEspecifica(nombreTabla));
        return file.exists();        
    }
    
    /**
     * validar si una tabla contiene el nombre de la columna
     * @param nombreTabla nombre de la tabla que se dese buscar
     * @param nombreColumna nombre de columna que se desea buscar
     * @return TRUE si la columna pertenece a la tabla, FALSE de lo contrario
     */
    public boolean esColumnaTabla(String nombreTabla, String nombreColumna){        
        String sep = System.getProperty("file.separator");
        String path = Archivo.obtenerRutaDirectorioTablaEspecifica(nombreTabla);        
        Archivo arch = new Archivo();
        
        arch.abrirArchivo(path + sep + nombreTabla +".meta.txt", false);
        while(arch.puedeLeer()){            
            String leer = arch.leerArchivo();        
            Tabla tabla = gson.fromJson(leer, Tabla.class);
            if (tabla.getNomCol().equalsIgnoreCase(nombreColumna)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * validar si un valor ingresado corresponde al valor que desea comparar
     * @param nombreTabla nombre de la tabla
     * @param nombreColumna nombre de la columna
     * @param valor valor que se desea evaluar
     * @return TRUE si la cadena valor es valida, FALSE de lo contrario
     */
    private int validarCampoComparacion(String nombreTabla, String nombreColumna, String valor) {
        String sep = System.getProperty("file.separator");
        String path = Archivo.obtenerRutaDirectorioTablaEspecifica(nombreTabla);        
        Archivo arch = new Archivo();
        Tabla tabla = new Tabla();
        arch.abrirArchivo(path + sep + nombreTabla +".meta.txt", false);
        while(arch.puedeLeer()){            
            String leer = arch.leerArchivo();        
            tabla = gson.fromJson(leer, Tabla.class);
            if (tabla.getNomCol().equalsIgnoreCase(nombreColumna)){
                break;
            }
        }
        
        return validarTipoDato(tabla.getTipoDato(), valor);        
    }

    /**
     * validar que un string tenga el mismo tipo de dato de la columna que se 
     * desea evaluar.
     * @param tipoDato tipo de dato de la columna.
     * @param valor valor que se desea evaluar.
     * @return TRUE si el valor ingresado es correcto, FALSE de lo contrario.
     */
    private int validarTipoDato(String tipoDato, String valor) {
        if(tipoDato.contains("VARCHAR")){
            String[] num = tipoDato.split("\\(");
            String[] limites = num[1].split("\\)");
            //se suma dos ya que debe tener en cuenta las comillas
            int limite = Integer.parseInt(limites[0]) + 2;
            if(valor.charAt(0) != '\''){
                return -1;
            }
            if(valor.charAt(valor.length()-1) != '\''){
                return -1;
            }            
            if(valor.length() < limite) 
                return 1;
            else 
                return 0;                       
        }
        if(tipoDato.equalsIgnoreCase("INT")){
            try{
                Integer.parseInt(valor);
                return 1;
            }catch(NumberFormatException nfe){
                return 0;
            }
        }
        if(tipoDato.equalsIgnoreCase("FLOAT")){
            try{
                Float.parseFloat(valor);
                return 1;
            }catch(NumberFormatException nfe){
                return 0;
            }
        }
        if(tipoDato.equalsIgnoreCase("DATE")){
            return validarFecha(valor);
        }
        return 0;
    }
    
    /**
     * permite ver si una cadena tiene el formato correcto de fecha
     * @param fecha cadena que se desea evaluar
     * @return TRUE cuando la fecha es valida, FALSE de lo contrario
     */
    public int validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return 0;
        }
        return 1;
    }    
}
