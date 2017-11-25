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
public class Pila<T> {

    NodoP<T> tope;
    int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void apilar(T info) {//Insertar datos a la pila
        if (this.esVacia()) {
            this.tope = new NodoP<T>(info, null);
        } else {
            this.tope = new NodoP<T>(info, this.tope);
        }
        this.tamanio++;
    }

    public T desapilar() {//Eliminar datos de la pila
        if (this.esVacia()) {
            return (null);
        }

        NodoP<T> dato = this.tope;
        this.tope = tope.getSig();
        this.tamanio--;
        if (tamanio == 0) {
            this.tope = null;
        }

        return (dato.getInfo());
    }

    public String toString() {
        String palabra = "";
        NodoP<T> p = tope;
        while (p != null) {
            palabra += p.getInfo().toString() + "->";
            p = p.getSig();
        }
        return palabra;
    }

    public T getTope() {
        return (this.tope.getInfo());
    }

    //Vaciar la pila
    public void vaciar() {
        this.tope = null;
        this.tamanio = 0;
    }

    public int getTamanio() {
        return (this.tamanio);
    }

    public boolean esVacia() {
        return (this.tope == null || this.tamanio == 0);
    }

}