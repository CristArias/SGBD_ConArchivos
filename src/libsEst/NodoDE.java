/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libsEst;

/**
 *
 * @author CristianAG
 */
public class NodoDE<T> implements java.io.Serializable { //Nodo doblemente enlazado
    
    private T elem;      
    private NodoDE<T> anterior;    
    private NodoDE<T> siguiente;   

    
    
    public NodoDE() {
        
        this.elem=null;
        this.anterior=null;
        this.siguiente=null; 
        
    }

   //Constructor del nodo doblemente enlazado
    public NodoDE(T info, NodoDE<T> sig, NodoDE<T> ant){        
        this.elem=info;
        this.siguiente=sig;
        this.anterior=ant;        
    }
    
    public NodoDE<T> getSiguiente(){        
        return (this.siguiente);        
    }
      
    public T getElem(){        
        return(this.elem);        
    }
    
    
    public NodoDE<T> getAnterior(){        
        return (this.anterior);        
    }
    
    
    public void setElem(T info){        
        this.elem = info;            
    }

    public void setAnterior(NodoDE<T> ant){        
        this.anterior=ant;        
    }
    
    
    public void setSiguiente(NodoDE<T> sig){        
        this.siguiente=sig;	        
    }
    
}
