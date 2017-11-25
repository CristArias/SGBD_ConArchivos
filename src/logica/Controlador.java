
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author CristianAG
 */
public class Controlador {
    
    public String TextoVacio(String url, String usuario, String contraseña )
    {
        String vacio = "";
        if(url.trim().length() == 0)
            vacio = vacio + "El campo url se encuentra vacio\n";
        if(usuario.trim().length() == 0)
            vacio = vacio + "El campo usuario se encuentra vacio\n";
        if(contraseña.trim().length() == 0)
            vacio = vacio + "El campo contraseña se encuentra vacio\n";
        
        return vacio;
    }
    
     public boolean SoloLetras(String texto)
    {   
        if((texto.compareTo("A") < 0 || texto.compareTo("Z") > 0) && (texto.compareTo("a") < 0 || texto.compareTo("z") > 0))        
            return true;
        return false;
    }
    
    
    public String Sintaxis(String consulta_usuario)
    {
        String errores = "";
         
            
            String[] campos = consulta_usuario.split(" ");
            if(campos.length  <= 3 )
                errores = errores + "La consulta básica se encuentra incompleta\n";
               
            for(int i = 0; i < campos.length; i++)//if (campos.length > 3) 
            {
                    //SELECT este en posición correspondiente y este bien escrito
                    if(i == 0 && !campos[i].equalsIgnoreCase("SELECT"))
                        errores = errores + "Se esperaba un SELECT como comando inicial de la consulta \n";
                    //FROM este en posición correspondiente y este bien escrito
                    if(i == 2 && !campos[i].equalsIgnoreCase("FROM"))
                        errores = errores + "La sentencia FROM no se encuentra especificada \n";
                    //if(i == 3 && !TablaExiste(tablas, campos[i]))
                        //errores = errores + "La tabla no existe \n";
                    else
                    {
                        //VERIFIQUE QUE LAS COLUMNAS EXISTAN 
                        if(campos.length > 1)
                        {
                            if(i == 3 && !campos[1].equals("*") )
                            {
                                String[] columnas = campos[1].split(",");
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
                    if(campos.length < 8 && campos.length > 4 && i == 4)
                        errores = errores + "La sentencia para ejecutar condición se encuentra incompleta\n";
                    
                    if(i == 4 && !campos[i].equalsIgnoreCase("WHERE") )
                        errores = errores + "La sentencia where no se encuentra especificada\n"; 
                    
                    if(i==6)
                    {
                        if(!campos[i].equalsIgnoreCase("<") && !campos[i].equalsIgnoreCase(">") && !campos[i].equalsIgnoreCase("=") && !campos[i].equals("<=") && !campos[i].equals(">=") 
                                && !campos[i].equals("><") && !campos[i].equalsIgnoreCase("IS NULL") && !campos[i].equalsIgnoreCase("IS NOT NULL") ) 
                            errores = errores + campos[i] + " Operador logico del WHERE no es válido\n";
                    }
                    
                    int esColumna = 0;
                    
                    if(campos.length == 8 && i == 7)
                    {
                       esColumna = 5;//CualEsColumna(tablas, campos[3], campos[5], campos[7]);
                        
                        if(esColumna == 0)
                            errores = errores + "La condición debe contener por lo menos una columna de la tabla para hacer la comparación\n";
                        
                        int pos = 0;
                        if(esColumna == 5)
                           pos = 7; 
                        if(esColumna == 7)
                            pos= 5;


                        if(campos[pos].charAt(0) == '\'')
                                if( (campos[pos].charAt(campos[pos].length()-1) != '\'') )
                                    errores = errores + "La condicion no se ha cerrado en comilla simple\n";

                        if( (campos[pos].charAt(campos[pos].length()-1) == '\'') )
                            if(campos[pos].charAt(0) != '\'')
                                errores = errores + "La condicion no ha abierto en comilla simple\n";

                        if( (campos[pos].charAt(0) == '\"') || (campos[pos].charAt(campos[pos].length()-1) == '\"') )
                                errores = errores + campos[pos] +" Identificador inválido, se debe usar comilla simple, no doble\n";

                        /*if( !TipoDato(tablas, campos[3], campos[esColumna], campos[pos]))
                                    errores = errores + "Los campos a comparar no son del mismo tipo\n";
                        */
                    
                    }
                        // decide la variable de la condición a evaluar en el WHERE
                        
                    
                    
            }
                        
                   
                    
//                }
           
        return errores;         
    }
    
    
    public String ConsultaAValidar(String textodeconsulta) throws SQLException
    {
        
        if(textodeconsulta.equalsIgnoreCase(""))
            return  "EL CAMPO DE CONSULTA SE ENCUENTRA VACIO";
        
        String errores = "";
        String MiCadena = "";
        
        textodeconsulta = textodeconsulta.trim();
        
        MiCadena = textodeconsulta.replaceAll(" +", " ");
        String[] MiCadena2 = MiCadena.split(" ");        
        
        String cadenita = "";
        for(String columna: MiCadena2)
        {
            if(columna.equalsIgnoreCase("Select"))
                //if(!columna.equalsIgnoreCase("From"))
                    continue;
            
            //if(columna.equalsIgnoreCase("Select"))
                if(columna.equalsIgnoreCase("From"))
                    break;
            
            cadenita = cadenita + " " + columna;
        }
        
        String cadenaNueva = cadenita.replace(" ", "");
        cadenita = cadenita.trim();
        MiCadena=MiCadena.replace(cadenita, cadenaNueva);
        
        MiCadena2 = MiCadena.split(" ");
        
        if(MiCadena2.length > 1)
        {
            if(MiCadena2[1].length() == 1)
            {
                if(!MiCadena2[1].equals("*"))
                    errores = errores + MiCadena2[1] + "Nombre de columna incorrecto";
            }
            else
            {
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

                if(Columnas.length < cont)
                    errores = errores + "¡La sentencia de las columnas se encuentra erronea!";

            }
        }
        return Sintaxis(/*tablas, */MiCadena);
    }
   
}
