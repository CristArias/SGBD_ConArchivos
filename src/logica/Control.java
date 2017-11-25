/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.RandomAccessFile;
import libsArbol.ArbolBP;
import libsArbol.LlaveTabla;
import libsEst.ExceptionL;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class Control {
    
    ArbolBP objArbolBP = new ArbolBP();
    Persistencia per = new Persistencia();
    long cant_registros = 0;

    public ArbolBP getObjArbolBP() {
        return objArbolBP;
    }

    public void setObjArbolBP(ArbolBP objArbolBP) {
        this.objArbolBP = objArbolBP;
    }


    public void Obtener_Columnas() 
    {
        per.abrirArchivo(false);
        
        LlaveTabla lt;
        Tabla[] tablas = per.listar();
        for (int i=0; i < tablas.length; i++) 
        {
            lt = new LlaveTabla(tablas[i].getNomCol(), (i+1));
            objArbolBP.insertar_arbolbmas(lt);
        }
        per.cerrarArchivo();
    }
    
    public String leerRegistro(RandomAccessFile raf, int n)
    {
        /*try {
            
            raf.seek(raf.length()*(n-1));
            return raf.readLong()+ "~"+raf.readUTF()+"~"+raf.readUTF()+"~"+raf.readUTF();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } */       
        return null;
    }
    
    public boolean insertarEnTabla(String nomCol, String tipoDat, String nulleable)
    {
        if(this.getTabla(nomCol) == null)
        {
            Tabla objTabla = new Tabla(nomCol, tipoDat, nulleable);
            this.insertarColumnasArchivo(objTabla);
            LlaveTabla lt = new LlaveTabla(nomCol, (int) (per.getPosicion() / Tabla.tamanio));
            per.setPosicion(0);
            objArbolBP.insertar_arbolbmas(lt);
            
            return true;
        }
        return false;
    }
    
    private void insertarColumnasArchivo(Tabla objTabla)
    {
        per.abrirArchivo(true);
        
        per.insertar(per.totalRegistros()+1, objTabla);
        
        per.cerrarArchivo();
    }
    
    public Tabla getTabla(String elem)
    {
        LlaveTabla objLlaveTabla;
        try {
            objLlaveTabla = objArbolBP.buscar_ArbolBmas(elem);
            if(objLlaveTabla != null)
            {
                per.abrirArchivo(false);
                Tabla objTabla = per.leer(objLlaveTabla.getIndice());
                per.cerrarArchivo();
                return objTabla;
            }
        }
        catch (ExceptionL ex) {System.out.println(ex.getMensaje()+"--getTabla");}
        return null;
    }

    public void archivo(String nom)
    {
        per.establecerArchivo(nom);
    }
    
}
