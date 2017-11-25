package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class Persistencia {
    
    private RandomAccessFile archivo;
    private File fil;
    private String path;
    private long posicion;
    private long[] indices;

    public long getPosicion() {
        return posicion;
    }

    public void setPosicion(long posicion) {
        this.posicion = posicion;
    }

    
    
    public void establecerArchivo(String nom)
    {
        this.path = nom;
    }

    public void abrirArchivo(boolean escritura)
    {
        try{ 
            if(escritura)
            {
                fil = new File(this.path);
                archivo = new RandomAccessFile(fil, "rw");
            }
            else
            {
                fil = new File(this.path);
                archivo = new RandomAccessFile(fil, "r");
            }
        }
        catch (FileNotFoundException ex) {System.out.println(ex.getMessage()+"--abrirArchivo--");}
    }
    
    public void cerrarArchivo()
    {
        try {
            this.archivo.close();
        }
        catch (IOException ex) {System.out.println(ex.getMessage()+"--cerrarArchivo--");}
    }
    
    public void insertar(int pos, Tabla tabla) 
    {
        posicion = (pos - 1) * Tabla.tamanio;
        try {
            //File f = new File(this.path);
            //archivo = new RandomAccessFile(f, "rw");
            if (pos >= 1 && pos <= (totalRegistros() + 1))
            {
                archivo.seek((pos - 1) * Tabla.tamanio);

                archivo.writeUTF(tabla.getNomCol());
                archivo.writeUTF(tabla.getTipoDato());
                archivo.writeUTF(tabla.getNulleable());
            }
            //System.out.println("pos " + posicion);
            //archivo.close();
        } catch (IOException ex) {System.out.println(ex.getMessage() + " -- insertar --Clase Control");}
    }

    public int totalRegistros() 
    {
        //File fil = new File(this.path);
        int totalRegs;
        
        totalRegs = (int) fil.length() / Tabla.tamanio;
        
        return totalRegs;
    }

    public Tabla[] listar() 
    {
        Tabla[] tabla = new Tabla[totalRegistros()];
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = leer(i + 1);
        }

        return tabla;
    }

    public Tabla leer(int pos) 
    {
        posicion = Tabla.tamanio * (pos - 1);
        Tabla tabla = null;
        //File fil = new File(this.path);
        try {

            //archivo = new RandomAccessFile(fil, "r");

            if (pos >= 0 && pos <= totalRegistros()) 
            {
                archivo.seek(Tabla.tamanio * (pos - 1));

                String nombreCol = archivo.readUTF();
                String tipoDat = archivo.readUTF();
                String nulleable = archivo.readUTF();

                tabla = new Tabla(nombreCol, tipoDat, nulleable);
                //archivo.close();
            } else {
                //archivo.close();
            }
        } catch (FileNotFoundException ex) {System.out.println(ex.getMessage() + " --leer---");
        } catch (IOException ex) {System.out.println(ex.getMessage() + "--leer--");}
        return tabla;
    }
    
}
