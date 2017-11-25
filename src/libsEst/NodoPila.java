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
public class NodoPila<T> extends NodoDE<T> {
        
    int p;
    
    public NodoPila(){
        super();
        this.p=0; 
    }
	
    
    public NodoPila(T info, NodoPila<T> siguiente, NodoPila<T> anterior, int p){
        super(info,siguiente, anterior);
        this.p=p;
    }
    
    public String toString(){
        return (this.getElem()+"_"+this.get_Prioridad());
    }
    
    public void setElem(T nuevo){        
        super.setElem(nuevo);
    }
	
   
    public void setSig(NodoPila<T> nuevo){
        super.setSiguiente(nuevo);
    }
	
    public T getElem(){
        return super.getElem();
    }
	
    
    public NodoPila<T> getSiguiente(){        
        return ((NodoPila<T>)super.getSiguiente());
    }
	
    
    public int get_Prioridad(){
        return (this.p);
    }
    
}