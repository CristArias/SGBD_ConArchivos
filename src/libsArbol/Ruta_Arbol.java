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
public class Ruta_Arbol {//Esta clase me permite llegar por medio del apuntador a un elemento del arbol que desee obtener
    
    
    private Pagina page; 
    private int apuntador; 
   
    public Ruta_Arbol() {
    }

    public Ruta_Arbol(Pagina p, int v) {
        this.page = p;
        this.apuntador = v;
    }

    public Pagina getPage() {
        return page;
    }

    
    public int getApuntador() {
        return apuntador;
    }

    
    public void setPage(Pagina p) {
        this.page = p;
    }

    public void setApuntador(int v) {
        this.apuntador = v;
    }
}
