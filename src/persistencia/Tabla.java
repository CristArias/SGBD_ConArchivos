
package persistencia;

/**
 *
 * @author CristianAG
 */
public class Tabla {
    
     public static final int tamanio = 89;
    
    private String nomCol;
    private String tipoDato;
    private String nulleable;

    public Tabla() {
        this.nomCol = "";
        this.tipoDato = "";
        this.nulleable = "not null";
    }
    
    

    public Tabla(String nomCol, String tipoDato, String nulleabl) {
        this.nomCol = nomCol;
        this.tipoDato = tipoDato;
        this.nulleable = nulleabl;                
    }
    
    public Tabla(String nomCol, String tipoDato) {
        this.nomCol = nomCol;
        this.tipoDato = tipoDato;
        this.nulleable = "not null";                
    }

    public String getNomCol() {
        return nomCol;
    }

    public void setNomCol(String nomCol) {
        this.nomCol = nomCol;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getNulleable() {
        return nulleable;
    }

    public void setNulleable(String nulleable) {
        this.nulleable = nulleable;
    }

    @Override
    public String toString() {
        return "Tabla{" + "nomCol=" + nomCol + ", tipoDato=" + tipoDato + ", nulleable=" + nulleable + '}';
    }
    
}
