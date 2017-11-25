
package libsArbol;

import java.util.Iterator;
import libsEst.Cola;
import libsEst.ListaB;

/**
 *
 * @author CristianAG
 */
public class ArbolB<T> { //Clase para usar la clase arbolb+

    int orden;
    int m;
    int max_hijos;//numero maximo de hijos
    Pagina raiz;//Raiz del arbolb

    public ArbolB() {
        this.raiz = null;
        this.orden = 2;
        this.m = orden * 2;

        this.max_hijos = (this.m) + 1;
    }

    public ArbolB(int orden) {
        if (orden <= 0) {
            System.err.println("Tamano del orden del arbol no es válido");
            return;
        }
        this.raiz = null;
        this.orden = orden;
        this.m = orden * 2;
        this.max_hijos = (this.m) + 1;
    }

    public Pagina getRaiz() {
        return raiz;
    }

    protected void setRaiz(Pagina raiz) {
        this.raiz = raiz;
    }

    public int getN() {
        return orden;
    }

    public int getM() {
        return m;
    }

    public int getMaxhijos() {
        return max_hijos;
    }

    public void setN(int n) {
        this.orden = n;
    }

    ///------------------
    protected int donde(Pagina p, LlaveTabla x) {
        int i;
        i = 0;
        int compara = ((Comparable) p.getVector_Datos()[i].getNomCol()).compareTo(x.getNomCol());
        while ((compara < 0) && (i < (p.getContador() - 1))) {
            i++;
            compara = ((Comparable) p.getVector_Datos()[i].getNomCol()).compareTo(x.getNomCol());
        }
        return i;
    }

    protected void hijosizq(Pagina p, int i, int j) {
        while (i < j) {
            p.getHijos()[i] = p.getHijos()[i + 1];
            i++;
        }
        p.getHijos()[i] = null;
    }

    protected void hijoDer(Pagina p, int i) {
        int j;
        j = p.getContador();
        while (j > i) {
            p.getHijos()[j] = p.getHijos()[j - 1];
            j--;
        }
    }

    private void getHojas(Pagina<T> page, ListaB<T> l) {
        if (page == null) {
            return;
        }
        if (this.esHoja(page)) {
            for (int j = 0; j < page.getContador(); j++) {
                l.insertarAlFinal((T)page.getVector_Datos()[j]);
            }
        }
        for (int i = 0; i < page.getContador() + 1; i++) {
            getHojas(page.getHijos()[i], l);
        }
    }

    //----------------------------
    public ListaB<T> Recorrido_Niveles() { //Metodo opcional permite recorrer el arbol por niveles
        ListaB<T> listaRecorrido = new ListaB<>();

        if (!esVacio()) {

            Cola<Pagina<T>> colaRecorrido = new Cola<>();//Uso una cola para almacenar los datos
            colaRecorrido.enColar(this.getRaiz());

            Pagina<T> page;
            while (!colaRecorrido.esVacia()) {

                page = colaRecorrido.deColar();
                if (page != null) {
                    for (int i = 0; i < page.getContador(); i++) {
                        listaRecorrido.insertarAlFinal((T)page.getVector_Datos()[i]); //Listo los datos obtenidos por cada nivel
                    }

                    for (int j = 0; j < page.getContador() + 1; j++) {
                        colaRecorrido.enColar(page.getHijos()[j]);
                    }

                }
            }
        }

        return listaRecorrido;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public Iterator<T> getHojas() {
        ListaB<T> l = new ListaB();
        getHojas(this.raiz, l);
        return (l.iterator());
    }

    public int contarHojas() {
        return contar_hojas(this.raiz);
    }

    private int contar_hojas(Pagina<T> r) {
        if (r == null) {
            return 0;
        }
        int cont = 0;
        if (this.esHoja(r)) {
            cont++;
        }
        for (int i = 0; i < r.getContador() + 1; i++) {
            cont += contar_hojas(r.getHijos()[i]);
        }
        return (cont);
    }

    protected boolean esHoja(Pagina page) {
        int j = 0;
        while ((page.getHijos()[j] == null) && (j < (page.getContador() - 1))) {
            j++;
        }
        return (page.getHijos()[j] == null);
    }

    public int getPeso() {
        if (this.esVacio()) {
            return (0);
        }
        return (getPeso(this.getRaiz()));
    }

    private int getPeso(Pagina<T> r) {
        if (r == null) {
            return 0;
        }
        int cont = 0;
        cont += r.getContador();
        for (int i = 0; i < r.getContador() + 1; i++) {
            cont += getPeso(r.getHijos()[i]);
        }
        return (cont);
    }

    public int getAltura() {
        return (getAltura(this.getRaiz()));
    }

    private int getAltura(Pagina<T> r) {
        int alt = 0;
        while (r != null) {
            alt++;
            r = r.getHijos()[0];
        }
        return (alt);
    }

}

