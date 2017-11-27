
package logica;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import persistencia.Archivo;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class Contr {
    
    ControladorConex conex = new ControladorConex();
        
    /**
     * Determina qué operación se va a realizar (insert, delete, create, select)
     * @param consulta consulta que envia el usuario
     * @return entero con el tipo de consulta que va a realizar. 
     * 0 - error, 1 - create, 2 - insert, 3 - delete, 4 - select
     */    
    public int esOperacion(String consulta)
    {
        String MiCadena;        
        consulta = consulta.trim();//elimina espacios al inicio y al final        
        MiCadena = consulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
                
        //for(String columna: MiCadena2)
        //{
            if(MiCadena2[0].equalsIgnoreCase("CREATE"))
                return 1;
            
            if(MiCadena2[0].equalsIgnoreCase("INSERT"))
                return 2;
            
            if(MiCadena2[0].equalsIgnoreCase("DELETE"))
                return 3;
            
            if(MiCadena2[0].equalsIgnoreCase("SELECT"))
                return 4;
        //}
        return 0;
    }
    
// ============================================== Todos los métodos con CREATE TABLE =====================================
    //CREATE TABLE nombre_tabal ( columna_nombre1 tipodato NULL, columna_nombre1 tipodato NULL )
    //CREATE TABLE actor ( act_id tipodato NULL, act_nombre tipodato NULL )
    
    /**
     * retorna el nombre de la tabla que se desea crear
     * @param textodeconsulta consulta que realizo el usuario
     * @return nombre de la tabla
     */
    public String nombreTablaCreate(String textodeconsulta)
    {        
        if(textodeconsulta.equalsIgnoreCase(""))
            return  "EL CAMPO DE CONSULTA SE ENCUENTRA VACIO";
        
        String MiCadena = "";
        
        textodeconsulta = textodeconsulta.trim();//elimina espacios
        
        MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        
        String cadenita = MiCadena2[2];
        
        return cadenita;
    }
    
    public String[] camposCreate(String textodeconsulta)
    {                
        textodeconsulta = textodeconsulta.trim();//elimina espacios        
        String MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        String res = MiCadena2[1];
        
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("CREATE"))
                continue;
            if(columna.equalsIgnoreCase("TABLE"))
                continue;
            if(columna.equalsIgnoreCase(MiCadena2[2]))
                continue;   
            if(columna.equalsIgnoreCase("("))
                continue;
            if(columna.equalsIgnoreCase(")"))
                break;
            
            cadenita = cadenita + " " + columna;
        }
        
        String cadenaNueva = cadenita.replace(" ", ";");
        cadenita = cadenita.trim();
        MiCadena=MiCadena.replace(cadenita, cadenaNueva);
        
        MiCadena2 = cadenita.split(" ");
        
        String errores = "";
        System.out.println(MiCadena2.length+" tamaño");
        if(MiCadena2.length > 1)
        {
            int cont = 0;
            String var1 = MiCadena2[2];
            for(int i = 0; i < var1.length(); i++ )
            {
                if(var1.charAt(i) == ',')
                    cont = cont + 1;
            }
            
                        
            if(cont >= 1){
                String[] Columnas = MiCadena2[4].split(",");
                
                for(int i = 0; i < Columnas.length; i++)
                {
                    Columnas[i] = Columnas[i].trim();
                }
            }            
        }
        
        return SintaxisCreate(MiCadena);
    }
    
    public String[] SintaxisCreate(String consulta_usuario)
    {         
        System.out.println(consulta_usuario+" consulta llega");
            String[] campos = consulta_usuario.split(" ");
            String[] columnas = null;
            for(int i = 0; i < campos.length; i++)
            {                    
                if (campos.length > 1) 
                {
                    if (i == 2) {
                        columnas = campos[4].split(",");
                        //descomponer(columnas);                        
                    }
                }
            }               
//        System.out.println(columnas[0]); 
//        System.out.println(columnas[1]);
        return columnas;         
    }
    
    public void descomponer(String[] dats)
    {
        String[] values;
        System.out.println(dats[0]);
        System.out.println(dats.length);
        int tam = 0;
        while(tam < (dats.length))
        {
            values = dats[tam].split(";");
            for (int i = 1; i < values.length; i++) {
                System.out.println(values[i] + " " + i);
            }
            tam++;
        }                 
    }
    
// ============================================== Todos los métodos con INSERT =====================================
    
    public String nombreTablaInsert(String textodeconsulta)
    {
        
        if(textodeconsulta.equalsIgnoreCase(""))
            return  "EL CAMPO DE CONSULTA SE ENCUENTRA VACIO";
        
        String MiCadena = "";
        
        textodeconsulta = textodeconsulta.trim();//elimina espacios
        
        MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        String res = MiCadena2[2];
                
        return res;
    }
    
// ********************************** columnas del insert ***********************************
    //insert into actor ( col1, col2, col2 ) values ( "nom", "ape", 1 )
    
    public String campsInsert(String textodeconsulta)
    {
        String MiCadena = "";
        String Cad = "";
        String cadenita2 = "";
        
        textodeconsulta = textodeconsulta.trim();//elimina espacios
        
        Cad = textodeconsulta.replaceAll(" +", " ");
        
        String[] MiCadena3 = Cad.split(" ");  
        int con = 0;
        int con2 = 0;
        for (int i = MiCadena3.length-1; i > 0; i--) {
//        for (int i = 0; i < MiCadena3.length; i++) {
                       
            if(MiCadena3[i].equalsIgnoreCase(")"))
                continue;
            if(MiCadena3[i].equalsIgnoreCase("("))
                continue;
            if(MiCadena3[i].equalsIgnoreCase("VALUES"))
            {
                con = i+1;
                break;
            }           
            
        } 
        
        for (int i = con; i < MiCadena3.length; i++) 
        {
            if(MiCadena3[i].equalsIgnoreCase(")"))
                continue;
            if(MiCadena3[i].equalsIgnoreCase("("))
                continue;
            cadenita2 = cadenita2 + MiCadena3[i];           
        }    
        
        return cadenita2;
    
    }
    
    public String columnasInsert(String textodeconsulta)
    {
        String MiCadena = "";
        
        textodeconsulta = textodeconsulta.trim();//elimina espacios
        
        MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        String res = MiCadena2[1];
        
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("INSERT"))
                continue;
            if(columna.equalsIgnoreCase("INTO"))
                continue;
            if(columna.equalsIgnoreCase(MiCadena2[2]))
                continue;   
            if(columna.equalsIgnoreCase("("))
                continue;
            if(columna.equalsIgnoreCase(")"))
                continue;
            if(columna.equalsIgnoreCase("VALUES"))
                break;
            
            cadenita = cadenita + " " + columna;
        }        
        
        return cadenita;
    }
    
    public String SintaxisInsert(String consulta_usuario)
    {
        String errores = "";
         
            
            String[] campos = consulta_usuario.split(" ");
            if(campos.length  <= 5 )
                errores = errores + "La consulta básica se encuentra incompleta\n";
            System.out.println(campos.length+" esta");
            for(int i = 0; i < campos.length; i++)//if (campos.length > 3) 
            {
                System.out.println(campos[i] + " **" + i);
                    //SELECT este en posición correspondiente y este bien escrito
                    if(i == 0 && !campos[i].equalsIgnoreCase("INSERT"))
                        errores = errores + "Se esperaba un INSERT como comando inicial de la consulta \n";
                    //FROM este en posición correspondiente y este bien escrito
                    if(i == 1 && !campos[i].equalsIgnoreCase("INTO"))
                        errores = errores + "La sentencia INTO no se encuentra especificada \n";
                    //if(i == 3 && !TablaExiste(tablas, campos[i]))
                        //errores = errores + "La tabla no existe \n";
                    if(i == 6 && !campos[i].equalsIgnoreCase("VALUES"))
                        errores = errores + "La sentencia VALUES no se encuentra especificada";
                    
                    else
                    {
                        //VERIFIQUE QUE LAS COLUMNAS EXISTAN 
                        if(campos.length > 1)
                        {
                            if(i == 2)
                            {
                                String[] columnas = campos[8].split(",");
                                int cont =0;
                                for (int j = 0; j < columnas.length; j++) 
                                {
                                    errores = errores + columnas[j] + "  Identificador de columna inválido \n";
                                    /*if (!ColumnaExiste(tablas,campos[3], columnas[j])) 
                                    {
                                        cont++;
                                        errores = errores + columnas[j] + "  Identificador de columna inválido \n";
                                    }*/
                                }
                            }
                        }
                    }
//                    if(campos.length < 8 && campos.length > 4 && i == 4)
//                        errores = errores + "La sentencia para ejecutar condición se encuentra incompleta\n";
//                    
//                    if(i == 4 && !campos[i].equalsIgnoreCase("WHERE") )
//                        errores = errores + "La sentencia where no se encuentra especificada\n"; 
//                    
//                    if(i==6)
//                    {
//                        if(!campos[i].equalsIgnoreCase("<") && !campos[i].equalsIgnoreCase(">") && !campos[i].equalsIgnoreCase("=") && !campos[i].equals("<=") && !campos[i].equals(">=") 
//                                && !campos[i].equals("><") && !campos[i].equalsIgnoreCase("IS NULL") && !campos[i].equalsIgnoreCase("IS NOT NULL") ) 
//                            errores = errores + campos[i] + " Operador logico del WHERE no es válido\n";
//                    }
//                    
//                    int esColumna = 0;
                    
//                    if(campos.length == 8 && i == 7)
//                    {
//                       esColumna = 5;//CualEsColumna(tablas, campos[3], campos[5], campos[7]);
//                        
//                        if(esColumna == 0)
//                            errores = errores + "La condición debe contener por lo menos una columna de la tabla para hacer la comparación\n";
//                        
//                        int pos = 0;
//                        if(esColumna == 5)
//                           pos = 7; 
//                        if(esColumna == 7)
//                            pos= 5;
//
//
//                        if(campos[pos].charAt(0) == '\'')
//                                if( (campos[pos].charAt(campos[pos].length()-1) != '\'') )
//                                    errores = errores + "La condicion no se ha cerrado en comilla simple\n";
//
//                        if( (campos[pos].charAt(campos[pos].length()-1) == '\'') )
//                            if(campos[pos].charAt(0) != '\'')
//                                errores = errores + "La condicion no ha abierto en comilla simple\n";
//
//                        if( (campos[pos].charAt(0) == '\"') || (campos[pos].charAt(campos[pos].length()-1) == '\"') )
//                                errores = errores + campos[pos] +" Identificador inválido, se debe usar comilla simple, no doble\n";
//
//                        /*if( !TipoDato(tablas, campos[3], campos[esColumna], campos[pos]))
//                                    errores = errores + "Los campos a comparar no son del mismo tipo\n";
//                        */
//                    
//                    }
                        // decide la variable de la condición a evaluar en el WHERE                   
            }           
        return errores;         
    }
    
// ============================================== Todos los métodos con DELETE =====================================

// ************************* Determina el nombre de la tabla que se va a eliminar *****************************
    public String nombreTablaDelete(String textodeconsulta)
    {
        
        if(textodeconsulta.equalsIgnoreCase(""))
            return  "EL CAMPO DE CONSULTA SE ENCUENTRA VACIO";
        
        String MiCadena = "";
        
        textodeconsulta = textodeconsulta.trim();//elimina espacios
        
        MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        String res = MiCadena2[1];
        
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("DELETE"))
                continue;
            if(columna.equalsIgnoreCase("FROM"))
                continue;
            
            if(columna.equalsIgnoreCase("WHERE"))
                break;
            
            cadenita = columna;
        }
        
        return cadenita;
    }
    
// ***************************** condición del where *************************************
    
    public String condicionWhere(String textodeconsulta)
    {
        
        if(textodeconsulta.equalsIgnoreCase(""))
            return  "EL CAMPO DE CONSULTA SE ENCUENTRA VACIO";
        
        String MiCadena = "";
        
        textodeconsulta = textodeconsulta.trim();//elimina espacios
        
        MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        String res = MiCadena2[1];
        
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("DELETE"))
                continue;
            if(columna.equalsIgnoreCase("FROM"))
                continue;
            if(columna.equalsIgnoreCase(MiCadena2[2]))
                continue;            
            if(columna.equalsIgnoreCase("WHERE"))
                continue;
            
            cadenita = cadenita + " " + columna;
        }
        
        return cadenita;
    }


    
//============================================== validar campos =========================================
    
    private Gson gson = new Gson();
    /**
     * validar si un valor ingresado corresponde al valor que desea comparar
     * @param nombreTabla nombre de la tabla
     * @param nombreColumna nombre de la columna
     * @param valor valor que se desea evaluar
     * @return TRUE si la cadena valor es valida, FALSE de lo contrario
     */
    public int validarCampoComparacion(String nombreTabla, String nombreColumna, String valor) {
        String sep = System.getProperty("file.separator");
        String path = Archivo.obtenerRutaDirectorioTablaEspecifica(nombreTabla);        
        Archivo arch = new Archivo();
        Tabla tabla = new Tabla();
        arch.abrirArchivo(path + sep + nombreTabla +".meta.txt", false);
        while(arch.puedeLeer()){            
            String leer = arch.leerArchivo();        
            tabla = gson.fromJson(leer, Tabla.class);
            if (tabla.getNomCol().trim().equalsIgnoreCase(nombreColumna.trim())){
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
    public int validarTipoDato(String tipoDato, String valor) {
        if(tipoDato.contains("VARCHAR")){
            String[] num = tipoDato.split("\\(");
            String[] limites = num[1].split("\\)");
            //se suma dos ya que debe tener en cuenta las comillas
            int limite = Integer.parseInt(limites[0]) + 2;
            if(valor.charAt(0) != '\''){
                return -2; //Si falta comilla de inicio del varchar
            }
            if(valor.charAt(valor.length()-1) != '\''){
                return -1; //Si falta comilla del final del varchar
            }            
            if(valor.length() < limite) 
                return 1; //Datos correctos
            else 
                return 2; //Datos incorrectos                     
        }
        
        if(tipoDato.equalsIgnoreCase("INT")){
            try{
                Integer.parseInt(valor);
                return 1;
            }catch(NumberFormatException nfe){
                return 3;//error en entero
            }
        }
        if(tipoDato.equalsIgnoreCase("FLOAT")){
            try{
                Float.parseFloat(valor);
                return 1;
            }catch(NumberFormatException nfe){
                return 4;//error en float
            }
        }
        if(tipoDato.equalsIgnoreCase("DATE")){
            return validarFecha(valor);
        }
        return 0;//Datos no reconocidos
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
            return 5;//fecha invalida
        }
        return 1;
    }    
    
}

