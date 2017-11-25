
package logica;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.Archivo;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class ControladorConex {
    
    private List<Tabla> columnas;
    private Gson gson;
    private Archivo archivo;
    
    //public String archCon = "datosConexion.txt";
    
   

    public ControladorConex() {
        this.columnas = new ArrayList<>();
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

//    guarda las conexiones de la lista al archivo
    public void guardarColumnas(String archCon) {
        this.eliminarArchivo(archCon);
        try {
            this.archivo.abrirArchivo(archCon, true);
            for (String dato : this.listAJson()) {
                this.archivo.escribirArchivo(dato);
            }
            this.archivo.cerrarArchivo();
        } catch (IOException ex) {
            Logger.getLogger(ControladorConex.class.getName()).log(Level.SEVERE, null, ex);
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

//    Guarda una columnas en la lista
    public void guardarColumna(Tabla columnas) {
        this.columnas.add(columnas);
    }
    
//    Obtiene la lista de conexiones
    public List<Tabla> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<Tabla> column) {
        this.columnas = column;
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
    
}
