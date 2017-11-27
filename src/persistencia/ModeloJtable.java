/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CristianAG
 */
public class ModeloJtable {
    
    public DefaultTableModel modelo;

    public DefaultTableModel getModelo(String cabecera[]){
        String datos [][] = {};
        modelo = new DefaultTableModel(datos,cabecera);
        
        return modelo;
    }
    
    public void adicionar_Datos_fila(Object datos[]){
        modelo.addRow(datos);
    }   
    
}
