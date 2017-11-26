
package presentacion;

import javax.swing.JOptionPane;
import logica.Contr;
//import logica.ControladorConex;
import logica.ControladorSelect;
import com.google.gson.Gson;
import static java.awt.SystemColor.control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.Contr;
import logica.ControladorConex;
import persistencia.Archivo;
import persistencia.Dato;
import persistencia.Tabla;

/**
 *
 * @author CristianAG
 */
public class frm_consultas extends javax.swing.JFrame {
    
    Contr contr = new Contr();
    ControladorConex conex = new ControladorConex();
    Archivo arc = new Archivo();
    private Gson gson;

    /**
     * Creates new form frm_consultas
     */
    public frm_consultas() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Sistema Gestor de Bases de Datos. Cristian Arias");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_consulta = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxt_errores = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_resultado = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btn_consultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SGBD Consultas");

        jLabel2.setText("Digite su consulta:");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtxt_errores.setColumns(20);
        jtxt_errores.setRows(5);
        jScrollPane1.setViewportView(jtxt_errores);

        jLabel3.setText("Errores:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_resultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbl_resultado);

        jLabel4.setText("Resultado Consulta:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_consultar.setText("Consultar");
        btn_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(359, 359, 359)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_consulta)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_consultar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_consultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultarActionPerformed
        try {
            consultar();
        } catch (IOException ex) {
            Logger.getLogger(frm_consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_consultarActionPerformed

    public void consultar() throws IOException{
        //ResultSet tablas = contr.ObtenerDatosTablasUsuario();
        //metodo para saber si es SELECT, INSERT, DELETE o CREATE
        contr = new Contr();
        conex = new ControladorConex();
        int realizar = contr.esOperacion(this.txt_consulta.getText());        
        //String errores = contr.ConsultaSELECTAValidar(this.txt_consulta.getText()); //Logger.getLogger(consultas.class.getName()).log(Level.SEVERE, null, ex);         
        switch (realizar) {                
                case 1: opcion1Create();
                        break;  
                        
                case 2: opcion2Insert();
                        break;
                        
                case 3: opcion3delete();
                        break;
                
                case 4: opcion4Select();
                        break;
                                        
                default: System.out.println("No valida");
                        break;
            }
    }
    
    public void opcion1Create(){
        String consul = this.txt_consulta.getText().toUpperCase();                
        String nomTabla = contr.nombreTablaCreate(consul);
        conex.archivo(nomTabla);                        
        //control.insertarEnTabla("campo1", "varchar", "not null"); 
        String[] dats = contr.camposCreate(consul);                        
        String[] values;
        int tam = 0;
        //campos: nombre, tipo, nulleable. se hace por cada dato en el insert
        String camp1 = "";
        String camp2 = "";
        String camp3 = "";

        while (tam < (dats.length)) {
            values = dats[tam].split(";");
            for (int i = 1; i < values.length; i++) {
                if(i == 1)
                    camp1 = values[i];                
                if(i == 2)
                    camp2 = values[i];                
                if(i == 3)
                    camp3 = values[i];                                                     
            }           
            this.conex.guardarColumna(new Tabla(camp1, camp2, camp3));                                                        
            tam++;
        }                          
        this.conex.guardarColumnas(nomTabla);        
        JOptionPane.showMessageDialog(this, "La tabla " + nomTabla + ", fué creada satisfactoriamente", "Informe de creación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void opcion2Insert(){
        String consul = this.txt_consulta.getText().toUpperCase();
        String insertTab = contr.nombreTablaInsert(consul);
                        System.out.println(insertTab);
                        String nomTabIns = insertTab+".txt";
                        String nomTabDato = insertTab+"Dato.txt";
                        arc.abrirArchivo(nomTabIns, false);
                        while(arc.puedeLeer())
                        {
                            String linea = arc.leerArchivo();
                            Tabla tab = gson.fromJson(linea, Tabla.class);
                            System.out.println(tab.toString());                           
                        }
                        arc.cerrarArchivo();
                        
                        //Comprueba que esite la tabla para hacer el proceso de insersión
//                        if(conex.existeArch(nomTabIns))
//                        {
//                            System.out.println("exite");
//                            String colTab = contr.columnasInsert(consul);
//                            String colIns = contr.campsInsert(consul);
//                            System.out.println(colTab);
//                            System.out.println(colIns);
//                            
//                            //Guarda los datos a insertar
//                            int tama = 0;
//                            //cmapos: nombre, tipo, nulleable. se hace por cada dato en el insert                           
//                            String[] colsIn = colIns.split(",");
//                            ArrayList<String> datos = new ArrayList<String>();
//                            
//                            //Busca las columnas de las tablas
//                            //cmapos: nombre, tipo, nulleable. se hace por cada dato en el insert                           
//                            String[] colsCam = colTab.split(",");
//                            ArrayList<String> datosC = new ArrayList<String>();
//                            boolean valr = false;
//                            
//                            while (tama < (colsIn.length)) 
//                            {
//                                System.out.println(colsIn[tama]);
//                                datos.add(colsIn[tama]);  
//                                                               
//                                System.out.println(colsCam[tama]);
////                                valr = contr.TipoDato(nomTabIns, colsCam[tama], colsIn[tama]);
////                                if(valr)
////                                {
////                                    System.out.println("compatibles"); 
////                                }                                   
//                                tama++;
//                                
//                            }  
//                            
//                            
//                            
//                              
//                            
////                            datos.add(campo);
//                            System.out.println(datosC + "datos");
////                            this.conex.guardarDato(new Dato(datos));
////                            this.conex.guardarColumnass(nomTabDato);
//                            
//                            
//                        }
//                        else
//                            System.out.println("No existe la tabla");
                        
                        
                        
                        boolean esta = false;
       
//                        for (Tabla tab : this.conex.getColumnas()) {
////                            if (tab.getNomCol().equals(usuario)) {
////                                esta = true;
////                            }
//                            System.out.println("");
//                        }

                        if (esta) {
//                            JOptionPane.showMessageDialog(this, "¡¡ La conexión ya está creada, intenta de nuevo !!", "Advetencia", JOptionPane.WARNING_MESSAGE);
                            
                        } else if (!esta) {
//                            this.contrCon.guardarConexion(new DatosConexion(usuario, host, puerto, pass));
//                            this.contrCon.guardarConexiones();
                            //contr.crearUser(/*usuario, pass*/);
//                            JOptionPane.showMessageDialog(this, "¡¡ Conexión creada con éxito !!", "Información Conexión", JOptionPane.INFORMATION_MESSAGE);
                            
                        }
                        
                        //contr.TipoDato(nomTabIns, nomCmple, nomCmple);
    }
    
    public void opcion3delete(){
        String consul = this.txt_consulta.getText().toUpperCase();        
        //System.out.println("Aun no implementado Delete");
        String deleteTab = contr.nombreTablaDelete(consul);
        System.out.println(deleteTab);

        String cond = contr.condicionWhere(consul);
        System.out.println(cond);
    }
    
    public void opcion4Select(){
        String consul = this.txt_consulta.getText().toUpperCase();        
        ControladorSelect controlSelect = new ControladorSelect();
        
        String errores = controlSelect.ConsultaSELECTAValidar(consul);  
        String nom = controlSelect.nombreTablaSelect(consul);
        System.out.println(nom);

        if(errores == null){
            this.jtxt_errores.setText("Consulta exitosa.");
        }else{
            this.jtxt_errores.setText(errores);
        }
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_consultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_consultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtxt_errores;
    private javax.swing.JTable tbl_resultado;
    private javax.swing.JTextField txt_consulta;
    // End of variables declaration//GEN-END:variables
}
