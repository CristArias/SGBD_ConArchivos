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
public class NodoP<T> {
    
   private T dato;
   private NodoP<T> sig;
    
   
    public NodoP(){
        this.dato=null;
        this.sig=null;        
    }
	
    
    public NodoP(T info, NodoP<T> sig){
        this.dato=info;
        this.sig=sig;
    }
	
    
    public T getInfo(){
        return this.dato;
    }
	
    
    public NodoP<T> getSig(){        
        return this.sig;        
    }
	
    public void setInfo(T nuevo){        
        this.dato=nuevo;
    }
	
    
    public void setSig(NodoP<T> nuevo){
        this.sig=nuevo;
    } 
}
