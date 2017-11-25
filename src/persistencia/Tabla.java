/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;

/**
 *
 * @author CristianAG
 */
public class Tabla {
    
    /*cedula = 9 bytes
      nombres = 25 + 2 (inicio de String) = 27 bytes
      apellidos = 25 + 2 (inicio de String) = 27 bytes
      numero de cuenta = 25 + 2 (inicio de String) = 27 bytes
      Total de cada registro = 89 bytes
    */
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
