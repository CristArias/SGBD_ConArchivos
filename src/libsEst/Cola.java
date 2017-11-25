
package libsEst;

/**
 *
 * @author CristianAG
 */
public class Cola<T> {
    
    ////////////////////////////////////////////////////////////
    // Cola - Atributos ////////////////////////////////////////
    ////////////////////////////////////////////////////////////
    
    /**
     * Nodo incial de la Cola
     */
    private NodoDE<T> inicio;          
    
    /**
     * Tamaño de la cola
     */
    private int tamanio;        
    
    
      
    ////////////////////////////////////////////////////////////
    // Cola - Implementacion de Metodos ////////////////////////
    //////////////////////////////////////////////////////////// 
    
    /**
     * Constructor de la Clase Cola, por defecto el primer y ultimo nodo es NULL y su tamaño es 0. <br>
     * <b>post: </b> Se construyo una Cola sin elementos.
     */
    public Cola(){
        this.inicio=new NodoDE<T> (null,null,null);
        this.inicio.setSiguiente(inicio);
        inicio.setAnterior(inicio); 
        this.tamanio=0;
    }
    
    /**
     * Metodo que permite agregar un elemento a la Cola. <br>
     * <b>post: </b> Se inserto un nuevo elemento a la Cola.<br>
     * @param info es de tipo T y contiene la informacion a en colar
     */
    public void enColar(T info){
        NodoDE<T>x=new NodoDE<T>(info,inicio,inicio.getAnterior());
        inicio.getAnterior().setSiguiente(x);
        inicio.setAnterior(x);
        this.aumentarTamanio();
    }

    /**
     * Metodo que permite retirar el primer elemento que fue insertado en la Cola. <br>
     * <b>post: </b> Se elimina el primer elemento que fue insertado en la cola.<br>
     * @return un tipo T que contiene la informacion del nodo retirado.
     */
    public T deColar(){ 
        if(this.esVacia())
            return (null);
        NodoDE<T> x=this.inicio.getSiguiente();	   
        this.inicio.setSiguiente(x.getSiguiente());
        x.getSiguiente().setAnterior(inicio);
        x.setSiguiente(null);
        x.setAnterior(null);
        this.tamanio--;
        return(x.getElem());     
    }

    /**
     * Metodo que permite elimar todos los datos que contiene la Cola. <br>
     * <b>post: </b> Se elimino todos los datos que se encontraban en la Cola.<br>
     */
    public void vaciar(){
        this.inicio.setSiguiente(this.inicio);
        this.inicio.setAnterior(this.inicio); 
        this.tamanio=0;
    }
    
    /**
     * Metodo que permite conocer el primer elemento que fue insertado en la Cola. <br>
     * <b>post: </b> Se obtiene el primer elemento que fue insertado en la Cola.<br>
     * @return El primer elemento que fue insertado en la cola
     */
    protected NodoDE<T> getInicio(){
        return this.inicio;
    }
    
    
    /**
     * Metodo que permite conocer el primer elemento que fue insertado en la Cola. <br>
     * <b>post: </b> Se obtiene el primer elemento que fue insertado en la Cola.<br>
     * @return El primer elemento que fue insertado en la cola
     */
    public T getInfoInicio(){
        return this.inicio.getSiguiente().getElem();
    }
    
    
    /**
     * Metodo que permite aumentar el tamaño de la Cola para dar uso en Cola de Prioridad. <br>
     */
    protected void aumentarTamanio() {
        this.tamanio++;
    }
    
    /**
     * Metodo que permite modificar el Nodo inicial de la Cola para uso de la Cola de prioridad. <br>
     * @param ini Representa el nuevo Nodo inicial de la cola.
     */
    protected void setInicio(NodoDE<T> ini){
        this.inicio =  ini;
    }

    /**
     * Metodo que retorna el tamaño de la cola<br>
     * <b>post: </b> Se retorno el numero de elementos existentes en la Cola.<br>
     * @return un tipo integer qeu contiene el tamaño de la cola
     */
    public int getTamanio(){
        return(this.tamanio);
    }

    /**
     * Metodo que retorna si la cola esta vacia o no<br>
     * <b>post: </b> Retorna si la Cola se encuentra vacia, retorna false si hay elementos en la Cola.<br>
     * @return un tipo boolean, true si es vacio y false si contiene nodos
     */
    public boolean esVacia(){
         return(this.getTamanio()==0);           
    }

    /**
     * Convierte la pila a una cadena de String. <br>
     * <b>post: </b> Se retorno la representacion en String de la pila. 
     * El String tiene el formato "e1->e2->e3..->en", donde e1, e2, ..., en son los los elementos de la Pila. <br>
     * @return La representacion en String de la Pila.
     */
    @Override
    public String toString( )
    {
        String msj ="";
        NodoDE<T> c = this.inicio.getSiguiente();
        while(c != inicio){
            msj += c.getElem().toString()+",";
            c = c.getSiguiente();
        }
        return msj;
    }   
    
}
