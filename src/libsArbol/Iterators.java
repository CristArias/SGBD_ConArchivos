
package libsArbol;

import java.util.Iterator;
import libsEst.NodoDE;

/**
 *
 * @author CristianAG
 */
public class Iterators<T> implements Iterator<T>
{
    
   
    public NodoDE<T> cabeza;   
    public NodoDE<T> pos;   
    
    public Iterators(NodoDE<T> cab) {
        this.cabeza=cab;                       
        this.pos=this.cabeza.getSiguiente();

    }
    
    @Override
    public T next() {
        if(!this.hasNext())
            return (null);
        this.pos=this.pos.getSiguiente();
        return(this.pos.getAnterior().getElem());
    }
    
    
    
    @Override
    public boolean hasNext() {
            return (this.pos!=this.cabeza);
    }   

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
