/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libsArbol;

/**
 *
 * @author CristianAG
 */
public class Pagina<T> {
    
    public int orden; //Orden, cuantos datos puede guardar el arbol
    public int m; 
    public int n;//numero de hijos
    public int contador;  
    public Pagina[] hijos; //Hijos de la pagina
    public LlaveTabla[] vector_datos;//Guardo los datos de las paginas 
    private Pagina[] apuntadores; 
    private int cont;  
    private T[] info;     
    
    
    
    public Pagina(int orden){
        this.orden = orden;
        this.m = orden*2;
        this.n = this.m+1;
        this.vector_datos= new LlaveTabla[m];
        
        for(int i=0; i<vector_datos.length;i++)
            vector_datos[i]=null;
        this.hijos = new Pagina[this.n];
        
        
        for(int i=0; i<hijos.length;i++)
            hijos[i]=null;
    }
    
    public int getContador() {
        return contador;
    } 
    
    public int get_N() {
        return n;  
    }    
    
    public int getOrden() {
        return orden;
    }
   
    public LlaveTabla[] getVector_Datos() {
        return vector_datos;
    }

    
    public int get_M() {
        return m;
    }

    public Pagina[] getHijos() {
        return hijos;
    }
    
    
    public void dato(LlaveTabla[] dato) {
        this.vector_datos = dato;
    }

    //-----------------------
    public String toString(){
        String valor= "Datos de la pagina: ";
        int i=0;
        
        while(i<this.getContador()){
        valor+=" "+this.vector_datos[i++].toString();}
        return valor;
    }
    
    public void setOrden(int n) {
        this.orden = n;
    }
   
    
    public void setContador(int contador) {
        this.contador = contador;
    }

    
    public void setHijos(Pagina[] Hijos) {
        this.hijos = Hijos;
    }
    
    public Pagina[] getApuntadores() {
        return apuntadores;
    }
    
    public int getCont() { 
        return cont;
    }

    public T[] getInfo() {
        return info;
    }
    
    
}