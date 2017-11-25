/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libsEst;

import java.util.Iterator;
import libsArbol.Iterators;

/**
 *
 * @author CristianAG
 */
public class ListaB<T> implements Iterable<T> {

    private NodoDE<T> cabeza;
    private int tamanio = 0;

    public ListaB() {
        this.cabeza = new NodoDE<>(null, null, null);
        this.cabeza.setSiguiente(cabeza);
        cabeza.setAnterior(cabeza);
    }

    public void insertarAlInicio(T dato) {
        NodoDE<T> valor = new NodoDE<>(dato, cabeza.getSiguiente(), cabeza);
        cabeza.setSiguiente(valor);
        valor.getSiguiente().setAnterior(valor);

        this.tamanio++;
    }

    public void insertarAlFinal(T dato) {
        NodoDE<T> valor = new NodoDE<>(dato, cabeza, cabeza.getAnterior());
        cabeza.getAnterior().setSiguiente(valor);
        cabeza.setAnterior(valor);

        this.tamanio++;
    }

    public boolean esta(T info) {
        return (this.Obtener_Indice(info) != -1);
    }

    public void insertar(T info) {
        if (this.esVacia()) {
            this.insertarAlInicio(info);
        } else {
            NodoDE<T> x = this.cabeza;
            NodoDE<T> y = x;
            x = x.getSiguiente();
            while (x != this.cabeza) {
                Comparable comparador = (Comparable) info;
                int rta = comparador.compareTo(x.getElem());
                if (rta < 0) {
                    break;
                }
                y = x;
                x = x.getSiguiente();
            }
            if (x == cabeza.getSiguiente()) {
                this.insertarAlInicio(info);
            } else {
                y.setSiguiente(new NodoDE<>(info, x, y));
                x.setAnterior(y.getSiguiente());
                this.tamanio++;
            }
        }
    }

    public T eliminar_dato(int i) throws ExceptionL {
        NodoDE<T> valor;
        if (i == 0) {
            valor = this.cabeza.getSiguiente();
            this.cabeza.setSiguiente(valor.getSiguiente());
            this.cabeza.getSiguiente().setAnterior(this.cabeza);
            valor.setSiguiente(null);
            valor.setAnterior(null);
            this.tamanio--;
            return (valor.getElem());
        }
        valor = this.posicion(i - 1);
        if (valor == null) {
            return (null);
        }
        NodoDE<T> y = valor.getSiguiente();
        valor.setSiguiente(y.getSiguiente());
        y.getSiguiente().setAnterior(valor);
        y.setSiguiente(null);
        y.setAnterior(null);
        this.tamanio--;
        return (y.getElem());
    }

    public void vaciar() {
        this.cabeza = new NodoDE<>(null, null, null);
        this.cabeza.setSiguiente(cabeza);
        cabeza.setAnterior(cabeza);

        this.tamanio = 0;
    }

    public int Obtener_Indice(T dato) {
        int i = 0;
        for (NodoDE<T> valor = this.cabeza.getSiguiente(); valor != this.cabeza; valor = valor.getSiguiente()) {
            if (valor.getElem().equals(dato)) {
                return (i);
            }
            i++;
        }
        return (-1);
    }

    public T Obtener(int i) throws ExceptionL {
        NodoDE<T> x = this.posicion(i);
        if (x == null) {
            return (null);
        }
        return (x.getElem());
        
    }

    public void set_dato(int i, T dato) throws ExceptionL {
        NodoDE<T> dato1 = this.posicion(i);
        if (dato1 != null) {
            dato1.setElem(dato);
        }
    }

    public int getLongitud() {
        return (this.tamanio);
    }

    public boolean esVacia() {
        return (cabeza == cabeza.getSiguiente() || this.getLongitud() == 0);
    }

    @Override
    public Iterator<T> iterator() {
        return (new Iterators<>(this.cabeza));
    }

    public Object[] agregar_Vec() {
        if (this.esVacia()) {
            return (null);
        }
        Object vector[] = new Object[this.getLongitud()];
        Iterator<T> it = this.iterator();
        int i = 0;

        while (it.hasNext()) {
            vector[i++] = it.next();
        }
        return (vector);
    }

    @Override
    public String toString() {
        if (this.esVacia()) {
            return ("Lista Vacia");
        }
        String r = "";
        for (NodoDE<T> x = this.cabeza.getSiguiente(); x.getElem() != null; x = x.getSiguiente()) {
            r += x.getElem().toString() + "<->";
        }
        return (r);
    }

    @SuppressWarnings("empty-statement")
    private NodoDE<T> posicion(int i) throws ExceptionL {
        if (i < 0 || i >= this.tamanio) {
            System.err.println("Error indice no valido en una Lista Circular Doblemente Enlazada");
            return (null);
        }
        NodoDE<T> dato = cabeza.getSiguiente();
        for (; i-- > 0; dato = dato.getSiguiente());

        return dato;
    }

}
