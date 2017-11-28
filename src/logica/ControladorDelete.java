
package logica;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import persistencia.Archivo;
import persistencia.Dato;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class ControladorDelete {
    
    private Gson gson = new Gson(); 
    
    
    public String SintaxisDELETE(String consulta_usuario){
        String errores = "";
        System.out.println(consulta_usuario + "hol5s");
            
            String[] campos = consulta_usuario.split(" ");
            System.out.println(campos + "hol5s");
            if(campos.length  <= 6 )
                return errores + "La consulta se encuentra incompleta.";
            
            System.out.println(campos.length +" tam consul");
            
            boolean isWhere = false;
            String nombreTabla = "";            
            String nombreColumnaWhere = "";
            boolean where4 = false;
            boolean where5 = false;
            boolean where6 = false;    
            
            for(int i = 0; i < campos.length; i++)
            {   
                    //DELETE este en posición correspondiente y este bien escrito
                    if(i == 0 && !campos[i].equalsIgnoreCase("DELETE"))
                        errores = errores + "Se esperaba un DELETE como comando inicial de la consulta.\n";
                                      
                    //FROM este en posición correspondiente y este bien escrito
                    if(i == 1 && !campos[i].equalsIgnoreCase("FROM"))
                        errores = errores + "La sentencia FROM no se encuentra especificada \n";
                    
                    if(i == 2){                        
                        if(esTabla(campos[i]))
                        {
                            nombreTabla = campos[i];
                        }
                        else{
                            errores = errores + "error. La tabla "+ campos[i] +" no existe, verifique su consulta.\n";
                        }                        
                    }
                    
                    if(i == 3 && campos[i].equalsIgnoreCase("WHERE")){isWhere = true;}
                    else { 
                        if(i == 3)
                            errores = errores + "error. Se esperaba una cláusula WHERE.\n"; 
                    }
                                        
                    if(isWhere){
                        if(i == 4){
                            if(!esColumnaTabla(nombreTabla, campos[i]))
                                errores = errores + " error. Columna \'"+ campos[i] +"\' desconocida.\n";
                            nombreColumnaWhere = campos[i];
                            where4 = true;
                        }
                        if(i == 5){
                            if(!campos[i].equalsIgnoreCase(">") &&
                               !campos[i].equalsIgnoreCase(">=") &&  
                               !campos[i].equalsIgnoreCase("=") && 
                               !campos[i].equalsIgnoreCase("<") && 
                               !campos[i].equalsIgnoreCase("<=") && 
                               !campos[i].equalsIgnoreCase("<>")){
                                errores = errores + " error, se esperaba un operador de comparacion.\n";
                            }
                            where5 = true;                            
                        }
                        if(i == 6){  
                            int res = validarCampoComparacion(nombreTabla, nombreColumnaWhere, campos[i]);
                            if(res == 0){
                                errores = errores + " error, el tipo de dato ingresado: \'"+campos[i]+"\', no es valido para comparar\n";
                            }
                            if(res < 0){
                                errores = errores + " error, el tipo de dato ingresado: \'"+campos[i]+"\', no es valido para comparar, debe adicionar comillas\n";
                            }
                            where6 = true;
                        }   
                    }                    
            }
            
        if(isWhere){
            if(!where4 && !where5 && !where6){
                errores = errores + " error. faltan algunas sentencias asociadas a la clausula WHERE.";
            }
            
        }       

    
        return errores;
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
     * @return 1 si la cadena valor es valida, 0 de lo contrario, 
     * -1 si el campo es varchar y a la cadena le hace faltan comillas
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
    
    public ArrayList<Dato> consultaWhere(String consulta){
        String sep = System.getProperty("file.separator");
        String[] cadena = consulta.split(" ");       
        String path = Archivo.obtenerRutaDirectorioTablaEspecifica(cadena[2]);        
        
        //obtener los datos de los archivos de la tabla
        String metadatos = path + sep + cadena[2] +".meta.txt";
        String datos = path + sep + cadena[2] +".datos.txt";
        System.out.println(metadatos+" metadat");
        List<Tabla> listaMetadatos = obtenerMetadatos(metadatos);
        List<Dato> listaDatos = obtenerDatos(datos);
        
        //obtener las columnas de la consulta
        String[] campos = consulta.split(" ");               
        String columnas = campos[6];            
        List<Integer> posDatos = new ArrayList<>();                        
                           
        for (int i = 0; i < listaMetadatos.size(); i++) {
            if(listaMetadatos.get(i).getNomCol().equals(campos[4]))
                posDatos.add(i);
        }
       
        //obtener el indice de las columnas que se desean consultar        
        for (int i = 0; i < listaMetadatos.size(); i++) {
            if(listaMetadatos.get(i).getNomCol().equals(columnas)) 
                posDatos.add(i);                                
           }   
        
        ArrayList<Dato> resultado = new ArrayList<>();        
        //adicionar una cabecera para mostrar los datos 
        ArrayList<String> cabecera =  new ArrayList<>();
        cabecera.addAll(Arrays.asList(columnas));            
        resultado.add(new Dato(cabecera));
        
        //adicionar los datos de la lista de datos a la lista de resultados
        boolean cond = false;
        for (Dato dato : listaDatos) {
            ArrayList<String> aux =  new ArrayList<>();
            for (int i = 1; i < posDatos.size(); i++) {
                cond = validacion(dato.getDatos().get(posDatos.get(0)), campos[5], campos[6]);
                if(cond)
                    aux.add(dato.getDatos().get(posDatos.get(i)));
            }
            if(!aux.isEmpty())
                resultado.add(new Dato(aux));
        }    
        EliminarRegistro(datos, resultado);
        System.out.println(resultado.get(0).getDatos()+" resul");
        return resultado;        
    }
    
    /**
     * validar la condicion del where de la cansulta
     * @param columna valor de la columna
     * @param condicion expresion booleana
     * @param valor valor a evaluar
     * @return 
     */
    private boolean validacion(String columna, String condicion, String valor) {        
        boolean cond = false;
        switch(condicion){
            case ">":
                cond = columna.compareTo(valor) > 0;
                break;
            case ">=":
                cond = columna.compareTo(valor) >= 0;
                break;
            case "=":
                cond = columna.equals(valor);
                break;
            case "<":
                cond = columna.compareTo(valor) < 0;;            
                break;
            case "<=":
                cond = columna.compareTo(valor) >= 0;;
                break;
            case "<>":
                cond = !columna.equals(valor);
                break;                
        }
        return cond;                
    }
    
    /**
     * obtener los metadatos de una tabla
     * @param path ruta cmpleta del archivo de metadatos
     * @return lista con los metadatos de la tabla
     */
    public ArrayList<Tabla> obtenerMetadatos(String path){        
        String sep = System.getProperty("file.separator");
        Archivo arch = new Archivo();
        ArrayList<Tabla> listaTabla = new ArrayList<>();
        Tabla tabla;
        arch.abrirArchivo(path, false);
        while(arch.puedeLeer()){
            String leer = arch.leerArchivo();        
            tabla = gson.fromJson(leer, Tabla.class);
            //System.out.println(tabla+" tablaaaa");
            listaTabla.add(tabla);
        } 
        return listaTabla;
    }
    
    /**
     * obtener los datos de una tabla
     * @param path ruta completa del archivo de datos
     * @return lista con los datos de la tabla
     */
    public ArrayList<Dato> obtenerDatos(String path){        
        String sep = System.getProperty("file.separator");
        Archivo arch = new Archivo();
        ArrayList<Dato> listaTabla = new ArrayList<>();
        Dato tabla;
        arch.abrirArchivo(path, false);
        while(arch.puedeLeer()){
            String leer = arch.leerArchivo();
            tabla = gson.fromJson(leer, Dato.class);           
            listaTabla.add(tabla);
        } 
        return listaTabla;
    }  
    
//    *Eliminar un registro dentro de un fichero de texto, 
//        *consiste en leer un archivo y escribir su contenido en uno 
//        *nuevo llamado X, excepto la linea a eliminar.Luego se borra 
//        *el fichero inicial y se renombra el nuevo fichero con el 
//        *nombre del archivo inicial 
//        ***********************************************************
//        *PARÁMETROS:
//        *FficheroAntiguo:Objeto File del fichero a eliminar el reg
//        *Satigualinea:Linea que se busca para eliminar
//        ***********************************************************/    
       public void EliminarRegistro(String path, ArrayList<Dato> Satigualinea)
       {        
           BufferedReader fich;
        
        /*Crea un objeto File para el fichero nuevo*/
        File FficheroNuevo = new File("datos.txt");
        Archivo arch = new Archivo();
        try {
            /*Si existe el fichero inical*/
            if(arch.existeArchivo(path)){
                /*Abro un flujo de lectura*/
                fich = new BufferedReader(new FileReader(path));
                String Slinea;
                
                /*Recorro el fichero de texto linea a linea*/
                while((Slinea = fich.readLine())!=null) { 
                     /*Si la linea obtenida es distinta al la buscada
                     *para eliminar*/
                    if (!Slinea.toUpperCase().trim().equals(Satigualinea)) {
                       /*la escribo en el fichero nuevo*/ 
                       EcribirFichero(FficheroNuevo, Slinea);
                    }else{
                        /*Si es igual simple mete no hago nada*/
                    }             
                }
                /*Obtengo el nombre del fichero inicial*/
//                String SnomAntiguo = path.getName();
                /*Borro el fichero inicial*/
                arch.eliminarArchivo(path);
                /*renombro el nuevo fichero con el nombre del fichero inicial*/
//                FficheroNuevo.renameTo("pas");
                /*Cierro el flujo de lectura*/
                fich.close();
            }else{
                System.out.println("Fichero No Existe");
            }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }   
        /**
         *
         * @param Ffichero
         * @param SCadena
         */
        public void EcribirFichero(File Ffichero, String SCadena)
        {
            try {
              //Si no Existe el fichero lo crea
               if(!Ffichero.exists()){
                   Ffichero.createNewFile();
               }
              /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8. 
               *Además  en el pedazo de sentencia "FileOutputStream(Ffichero,true)",
               *true es por si existe el fichero seguir añadiendo texto y no borrar lo que tenia*/
              BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Ffichero,true), "utf-8"));
              /*Escribe en el fichero la cadena que recibe la función. 
               *el string "\r\n" significa salto de linea*/
              Fescribe.write(SCadena + "\r\n");
              //Cierra el flujo de escritura
              Fescribe.close();
            } catch (Exception ex) {
              //Captura un posible error le imprime en pantalla 
              System.out.println(ex.getMessage());
            } 
       }
    
    
}
