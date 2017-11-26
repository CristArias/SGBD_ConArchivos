
package logica;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Archivo;
import persistencia.Dato;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class ControladorConex {
    
    Persistencia per = new Persistencia();
    
    private List<Tabla> columnas;
    private Gson gson;
    private Archivo archivo;
    private List<Dato> datos;
    
    //public String archCon = "datosConexion.txt";
    
   

    public ControladorConex() {
        this.columnas = new ArrayList<>();
        this.datos = new ArrayList<>();
        this.gson = new Gson();
        this.archivo = new Archivo();
    }

//    Convierte un arreglo de String a Lista de conexiones
    private void jsonAList(String[] conexiones) {
        this.columnas.clear();
        for (String conexion : conexiones) {
            Tabla conn = gson.fromJson(conexion, Tabla.class);            
            this.columnas.add(conn);
        }
    }
    
    private void jsonAListt(String[] conexiones) {
        this.datos.clear();
        for (String conexion : conexiones) {
            Dato conn = gson.fromJson(conexion, Dato.class);
            this.datos.add(conn);
        }
    }

//    convierte la lista de conexiones en un arreglo de tipo string, todos en formato JSON
    private String[] listAJson() {
        String[] datos = new String[this.columnas.size()];
        int i = 0;
        for (Tabla conexion : this.columnas) {

            datos[i] = this.gson.toJson(conexion);
            i++;
        }
//        return this.gson.toJson(this.conexiones);
        return datos;
    }
    
    private String[] listAJsonn() {
        String[] datoss = new String[this.datos.size()];
        int i = 0;
        for (Dato conexion : this.datos) {

            datoss[i] = this.gson.toJson(conexion);
            i++;
        }
//        return this.gson.toJson(this.conexiones);
        return datoss;
    }

//    guarda las conexiones de la lista al archivo
    public void guardarColumnas(String archCon) {        
        String sep = System.getProperty("file.separator");
        String rutaTablas = Archivo.obtenerRutaDirectorioTablaEspecifica(archCon);
        //comprobar que existe el folder, si no existe se crea uno nuevo
        File file = new File(rutaTablas);
        if(!file.exists()){
            file.mkdirs();
        }               
        //this.eliminarArchivo(archCon);
        //creando archivo de metadatos
        try {
            this.archivo.abrirArchivo(rutaTablas + sep + archCon + ".meta.txt", true);
            String[] json = this.listAJson();
            for (String dato : json) {
                this.archivo.escribirArchivo(dato);
            }
        } catch (IOException ex) {
            System.out.println("error creando archivo de metadatos");
            Logger.getLogger(ControladorConex.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.archivo.cerrarArchivo();
        }
        //creando archivo de datos        
        this.archivo.abrirArchivo(rutaTablas + sep + archCon + ".datos.txt", true);        
        this.archivo.cerrarArchivo();
    }         
    
    public void guardarColumnass(String archCon) {
        
        String sep = System.getProperty("file.separator");
        String rutaTablas = Archivo.obtenerRutaDirectorioTablaEspecifica(archCon);
        try {
            this.archivo.abrirArchivo(rutaTablas + sep + archCon + ".datos.txt", true);
            String[] json = this.listAJsonn();
            for (String dato : json) {
                this.archivo.escribirArchivo(dato);
            }
        } catch (IOException ex) {
            System.out.println("error creando archivo de metadatos");
            Logger.getLogger(ControladorConex.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.archivo.cerrarArchivo();
        }
    }

//    Carga las conexiones que estan en el archio a una lista
    public void cargarColumnas(String archCon) {
        try {
            if (this.archivo.existeArchivo(archCon)) {
                int cantidadLineas = this.archivo.contarLineas(archCon);
                this.archivo.abrirArchivo(archCon, false);
                jsonAList(this.archivo.LeerPalabras(cantidadLineas));
                this.archivo.cerrarArchivo();
            } else {
                guardarColumnas(archCon);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorConex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarColumnass(String archCon) {
        try {
            if (this.archivo.existeArchivo(archCon)) {
                int cantidadLineas = this.archivo.contarLineas(archCon);
                this.archivo.abrirArchivo(archCon, false);
                jsonAListt(this.archivo.LeerPalabras(cantidadLineas));
                this.archivo.cerrarArchivo();
            } else {
                guardarColumnass(archCon);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorConex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Guarda una columnas en la lista
        public void guardarColumna(Tabla columnas) {
        this.columnas.add(columnas);
    }
    
    public void guardarDato(Dato datos) {
        this.datos.add(datos);
    }
    
//    Obtiene la lista de conexiones
    public List<Tabla> getColumnas() {
        return columnas;
    }
    
    public List<Dato> getColumnass() {
        return datos;
    }

    public void setColumnas(List<Tabla> column) {
        this.columnas = column;
    }
    
    public void setColumnass(List<Dato> dat) {
        this.datos = dat;
    }


    public void eliminarArchivo(String archCon) {
        this.archivo.eliminarArchivo(archCon);

    }
    
    public boolean existeArch(String arch)
    {
        boolean exist = archivo.existeArchivo(arch); 
        if(exist)
            return true;
        else
            return false;
    }
    
    public void archivo(String nom)
    {
        per.establecerArchivo(nom);
    }   
    
}
