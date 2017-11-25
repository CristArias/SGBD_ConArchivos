
package libsArbol;

/**
 *
 * @author CristianAG
 */
public class LlaveTabla {
    
    private String nomCol;
    private int indice;

    public LlaveTabla() {
    }

    public LlaveTabla(String nomCol, int indice) {
        this.nomCol = nomCol;
        this.indice = indice;
    }

    public String getNomCol() {
        return nomCol;
    }

    public void setNomCol(String nomCol) {
        this.nomCol = nomCol;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    @Override
    public String toString() {
        return "LlaveTabla{" + "nomCol=" + nomCol + ", indice=" + indice + '}';
    }   
    
}
