
package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author CristianAG
 */
public class Archivo {
    
    private BufferedWriter archivoEscritura;
    private BufferedReader archivoLectura;
//    private ArrayList<Object> list;

    /**
     * Abre el archivo si existe
     *
     * @param nombre nombre del archivo que se va a abrir
     * @param escritura recive el parametro verdadero o falso, verdadero abre el
     * archivo para la escritura y falso para lectura
     * @throws IOException
     */
    public void abrirArchivo(String nombre, boolean escritura)
            throws IOException {
        if (escritura == true) {
            this.archivoEscritura = new BufferedWriter(new FileWriter(nombre, true));
        } else {
            File f = new File(nombre);
            if (!f.exists()) {
                f.createNewFile();
            }
            this.archivoLectura = new BufferedReader(new FileReader(nombre));
        }
    }

    public void escribirArchivo(String datos) throws IOException {
        archivoEscritura.write(datos);
        archivoEscritura.newLine();
    }

    public String leerArchivo() throws IOException {
        return archivoLectura.readLine();
    }

    public void cerrarArchivo() throws IOException {
        if (archivoEscritura != null) {
            archivoEscritura.close();
        }
        if (archivoLectura != null) {
            archivoLectura.close();
        }
    }

    public boolean puedeLeer() throws IOException {
        return archivoLectura.ready();
    }

    public String[] LeerPalabras(int cantidad) {
        String[] palabras = new String[cantidad];
        int i = 0;
        try {
            while (this.puedeLeer() && i < cantidad) {
                palabras[i] = this.leerArchivo();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabras;
    }

    public String leerArchivo(int cantidad) {
        String datos = "";
        int i = 0;
        try {
            while (this.puedeLeer() && i < cantidad) {
                datos += this.leerArchivo();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    //cuenta la cantidad de lineas que tiene el archivo
    public int contarLineas(String nombre) throws IOException {
        abrirArchivo(nombre, false);
        int lineas = 0;
        while (puedeLeer()) {
            leerArchivo();
            lineas++;
        }
        cerrarArchivo();
        return lineas;
    }

   
    
    public boolean existeArchivo(String nombre){
        File f = new File(nombre);
        return f.exists();
    }
    
    public void eliminarArchivo(String nombreArchivo){
        File file = new File(nombreArchivo);
        file.delete();
    }
    
}
