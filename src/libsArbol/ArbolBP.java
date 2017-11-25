
package libsArbol;

import java.util.ArrayList;
import java.util.Iterator;
import libsEst.ExceptionL;
import libsEst.ListaB;
import libsEst.Pila;

/**
 *
 * @author CristianAG
 */
public class ArbolBP<T> extends ArbolB {

    private Pagina<T> pag;

    public ArbolBP() {
        super();
        this.pag = super.getRaiz();
    }

    public ArbolBP(int orden) {
        super(orden);
        this.pag = super.getRaiz();
    }

    public void Instanciar(Pagina p) {
        int i = 0;
        p.setContador(0);
        while (i < super.getMaxhijos()) {
            p.getHijos()[i++] = null;
        }
    }

    public Pagina<T> pag() {
        return pag;
    }

    //-----
    public boolean insertar_arbolbmas(LlaveTabla dato) {

        Pila pila = new Pila();//Guardo los datos que agrego vector una pila

        LlaveTabla[] guardar = new LlaveTabla[1];

        LlaveTabla[] guardar1 = new LlaveTabla[1];

        int posicion;
        int i, terminar, separar;
        Pagina p = null;
        Pagina aux = null, aux2;

        if (super.getRaiz() == null) {

            super.setRaiz(this.constructor_pagina(super.getN(), dato));
            pag = super.getRaiz();
            return (true);

        } else {

            posicion = buscar(super.getRaiz(), dato, pila);

            if (posicion == -1) {
                return (false);
            } else {

                terminar = separar = 0;

                while ((!pila.esVacia()) && (terminar == 0)) {

                    p = (Pagina) pila.desapilar();

                    if (p.getContador() == super.getM()) {

                        if (separar == 0) {

                            aux = Organizar_pagina(p, null, dato, guardar, separar);
                            separar = 1;
                        } else {

                            aux2 = Organizar_pagina(p, aux, guardar[0], guardar1, separar);
                            guardar[0] = guardar1[0];
                            aux = aux2;

                        }
                    } else {

                        if (separar == 1) {

                            separar = 0;
                            i = donde(p, guardar[0]);
                            i = insertarBmas(p, guardar[0], i);
                            super.hijoDer(p, i + 1);
                            p.getHijos()[i + 1] = aux;

                        } else {
                            posicion = insertarBmas(p, dato, posicion);
                        }
                        terminar = 1;
                    }
                }
                if ((separar == 1) && (terminar == 0)) {

                    this.setRaiz(this.constructor_pagina(super.getN(), guardar[0]));
                    super.getRaiz().getHijos()[0] = p;
                    super.getRaiz().getHijos()[1] = aux;

                }
            }
        }
        return true;
    }

    
    //------------------------
    //Metodo interno para insertar datos al arbol
    private int insertarBmas(Pagina page, LlaveTabla valor, int i) {
        int contador;

        if (page.getContador() != 0) {

            int compara = ((Comparable) page.getVector_Datos()[i].getNomCol()).compareTo(valor.getNomCol());
            if (compara < 0) {

                i++;
            } else {

                contador = page.getContador() - 1;

                while (contador >= i) {

                    page.getVector_Datos()[contador + 1] = page.getVector_Datos()[contador];
                    contador = contador - 1;

                }
            }
        }

        page.getVector_Datos()[i] = valor;
        page.setContador(page.getContador() + 1);

        return i;
    }

    
    //-------
    private Pagina constructor_pagina(int orden, LlaveTabla dato) {//Mbuscar_ArbolBmase permite crear una pagina para almacenar datos en ella
        Pagina page = new Pagina(orden);
        Instanciar(page);

        page.setContador(1);
        page.getVector_Datos()[0] = (dato);
        return page;
    }


    
    //-----------------
    //Busco los datos que se encuentren en la lista de datos de animal obtenida del arbolb+
    public LlaveTabla buscar_ArbolBmas(String elem) throws ExceptionL {

        ArrayList<LlaveTabla> lista = buscar_ArbolBmas();

        for (LlaveTabla lista1 : lista) {
            if (lista1.getNomCol().equals(elem)) {
                return lista1;
            } 
        }
        return null;
    }
    
    //-------------------
    //Agrego los datos vector una lista de String para buscar los animales    
    public ArrayList<LlaveTabla> buscar_ArbolBmas() throws ExceptionL {
        ListaB<T> busqueda = Recorrido_Niveles();
        ArrayList<LlaveTabla> aux = new ArrayList<>();
        LlaveTabla palabra;

        for (int i = 0; i < busqueda.getLongitud(); i++) {
                palabra = (LlaveTabla)busqueda.Obtener(i);
                aux.add(palabra);
        }
        return aux;
    }

    
    //-------------
    //Me permite buscar la pos correspondiente para el dato de una pagina
    private int buscar(Pagina page, LlaveTabla dato ,Pila pila) {

        int contador = 0;
        boolean buscar_dato = false;
        int pos = -1;

        while ((page != null) && (!buscar_dato)) {

            pila.apilar(page);
            contador = 0;

            int comparable = ((Comparable) page.getVector_Datos()[contador].getNomCol()).compareTo(dato.getNomCol());

            while ((comparable < 0) && (contador < (page.getContador() - 1))) {

                contador++;
                comparable = ((Comparable) page.getVector_Datos()[contador].getNomCol()).compareTo(dato.getNomCol());

            }

            if ((comparable > 0)) {
                page = page.getHijos()[contador];
            } else if (comparable < 0) {
                if (page.getHijos()[0] != null) {
                    page = page.getHijos()[contador + 1];
                } else {
                    page = null;
                }
            } else if (page.getHijos()[0] != null) {
                page = page.getHijos()[contador + 1];
            } else {
                buscar_dato = true;
            }
        }

        if (!buscar_dato) {
            pos = contador;
        }
        return pos;
    }

    
    
    //------------
    //Reorganizo los datos de una pagina para poder mantener el orden correspondiente
    private Pagina Organizar_pagina(Pagina page, Pagina page2, LlaveTabla dato, LlaveTabla[] seleccionar, int separar) {

        LlaveTabla[] vector = new LlaveTabla[super.getMaxhijos()];
        int i = 0;
        boolean s = false;
        Pagina[] b = new Pagina[super.getMaxhijos() + 1];
        Pagina aux = null;

        if (separar == 0) {

            aux = page.getHijos()[super.getM()];
            page.getHijos()[super.getM()] = null;

        }

        while ((i < super.getM()) && (!s)) {

            int comparables = ((Comparable)page.getVector_Datos()[i].getNomCol()).compareTo(dato.getNomCol());
            if (comparables < 0) {

                vector[i] = page.getVector_Datos()[i];
                b[i] = page.getHijos()[i];
                page.getHijos()[i++] = null;

            } else {
                s = true;
            }
        }

        vector[i] = dato;
        b[i] = page.getHijos()[i];
        page.getHijos()[i] = null;
        b[++i] = page2;

        while ((i <= super.getM())) {

            vector[i] = page.getVector_Datos()[i - 1];
            b[i + 1] = page.getHijos()[i];
            page.getHijos()[i++] = null;

        }

        Pagina aux2 = new Pagina(super.getN());
        Instanciar(aux2);
        i = 0;
        if (separar == 0) {

            page.setContador(super.getN());
            aux2.setContador(super.getN() + 1);
            aux2.getVector_Datos()[0] = vector[super.getN()];

            while (i < super.getN()) {

                page.getVector_Datos()[i] = vector[i];
                page.getHijos()[i] = b[i];
                aux2.getVector_Datos()[i + 1] = vector[i + super.getN() + 1];
                aux2.getHijos()[i] = b[i + super.getN() + 1];
                i++;
            }
            aux2.getHijos()[super.getM()] = aux;
            page.getHijos()[super.getM()] = aux2;

        } else {

            page.setContador(super.getN());
            aux2.setContador(super.getN());

            while (i < super.getN()) {

                page.getVector_Datos()[i] = vector[i];
                page.getHijos()[i] = b[i];
                aux2.getVector_Datos()[i] = vector[i + super.getN() + 1];
                aux2.getHijos()[i] = b[i + super.getN() + 1];
                i++;

            }
        }

        page.getHijos()[super.getN()] = b[super.getN()];
        aux2.getHijos()[super.getN()] = b[super.getMaxhijos()];
        seleccionar[0] = vector[super.getN()];

        return aux2;
    }


    @Override
    public boolean esVacio() {

        return (super.esVacio());

    }

    public String listar_page() {

        return (listar_page(this.pag));

    }

    public String listar_page(Pagina dato) {

        String palabra = "";
        int i;

        while (dato != null) {

            i = 0;
            while (i < dato.getContador()) {
                palabra += dato.getVector_Datos()[i++].toString() + ";";
            }
            dato = dato.getHijos()[super.getM()];

        }
        return palabra;
    }
    

    @Override
    public int getAltura() {
        return (super.getAltura());
    }

    @Override
    public Iterator<T> getHojas() {
        return (super.getHojas());
    }

}