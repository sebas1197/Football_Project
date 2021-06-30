
package vista;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Equipo;
import util.FileManager;
import util.Temporal;
import util.Validations;


public class VentanaEquipo extends javax.swing.JFrame implements MouseListener{
    File file = new File("Equipos.txt");
    File fileJugadores = new File("Jugadores.txt");
    ArrayList<Equipo> equipos = new ArrayList<>();
    Equipo equipo;
    String nombreEquipoModificar;
    Color colorCabecera = new Color(51,0,0);
    
    public VentanaEquipo() {
        initComponents();
        this.setLocationRelativeTo(null);
        jButton2.setEnabled(false);
        verEquipos(equipos);
        tblJugadores.addMouseListener(this);
    }
    private void verEquipos(ArrayList<Equipo> equipo){
        FileManager.createArrayEquipoOfFile(equipo, file);
        String matriz[][] = new String[equipo.size()][5];
        for (int i = 0; i < equipo.size(); i++) {
            matriz[i][0] = equipo.get(i).getNombreEquipo();
            matriz[i][1] = equipo.get(i).getPersonaResponsable();
            matriz[i][2] = "EDITAR";
            matriz[i][3] = "JUGADORES";
            matriz[i][4] = "ELIMINAR";
        }
        equipo.clear();

        tblJugadores.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"EQUIPO","PERSONA ENCARGADA","EDITAR","JUGADORES","ELIMINAR"}));
        tblJugadores.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblJugadores.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblJugadores.getColumnModel().getColumn(2).setPreferredWidth(25);
        tblJugadores.getColumnModel().getColumn(3).setPreferredWidth(32);
        tblJugadores.getColumnModel().getColumn(4).setPreferredWidth(25);
        tblJugadores.getTableHeader().setOpaque(false);
        tblJugadores.getTableHeader().setBackground(colorCabecera);
        tblJugadores.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<5;x++){
            tblJugadores.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
                
    }
    private void inicializarVariables(){
        this.txtNombreEquipo.setText("");
        this.txtPersonaResponsable.setText("");
        nombreEquipoModificar="";
        txtPersonaResponsable.setBackground(Color.WHITE);
        txtNombreEquipo.setBackground(Color.WHITE);
    }
    
    
    private void validarSeleccionMouse(int fila) {
        Equipo e = new Equipo(tblJugadores.getValueAt(fila, 0).toString(),tblJugadores.getValueAt(fila, 1).toString());//0 Equipo,1 Persona Enc.
        String info="INFO PERSONA\n";
        info+="Documento: "+e.getNombreEquipo()+"\n";
        info+="Nombre: "+e.getPersonaResponsable()+"\n";
        JOptionPane.showMessageDialog(null, info);
    }
    private void editarEquipoDatos(int fila) {
        String nombreEquipo = tblJugadores.getValueAt(fila, 0).toString();
        String personaResponsable = tblJugadores.getValueAt(fila, 1).toString();
        txtNombreEquipo.setText(nombreEquipo);
        nombreEquipoModificar = nombreEquipo;
        txtPersonaResponsable.setText(personaResponsable);
    }
    private void editarEquipo() {
        FileManager.createArrayEquipoOfFile(equipos, file);
        String nombreEquipo = txtNombreEquipo.getText();
        String personaResponsable = txtPersonaResponsable.getText();
        for (int i = 0; i <equipos.size() ; i++) {
            if(nombreEquipoModificar.equals(equipos.get(i).getNombreEquipo())){
                equipos.get(i).setNombreEquipo(nombreEquipo.toUpperCase());
                equipos.get(i).setPersonaResponsable(personaResponsable.toUpperCase());
                break;
            }
        }
        FileManager.saveArrayEquipoInFile(file, equipos);
        equipos.clear();
    }
    
    private void eliminarEquipo(int fila) {
        FileManager.createArrayEquipoOfFile(equipos, file);
        String nombreEquipo = tblJugadores.getValueAt(fila, 0).toString();
        String personaResponsable = tblJugadores.getValueAt(fila, 1).toString();
        for (int i = 0; i <equipos.size() ; i++) {
            if(nombreEquipo.equals(equipos.get(i).getNombreEquipo())){
                equipos.remove(i);
                break;
            }
        }
        FileManager.saveArrayEquipoInFile(file, equipos);
        File directorio = new File(System.getProperty("user.dir")+"/EQUIPOS/"+nombreEquipo);
        directorio.delete();
        equipo = new Equipo();
        equipo.eliminarJugadoresEquipo(fileJugadores, nombreEquipo);
        equipos.clear();
    }
    private void listarJugadores(int fila){
        Temporal t = new Temporal();
        t.setTexto(tblJugadores.getValueAt(fila, 0).toString());//EQUIPO
        VentanaJugador v = new VentanaJugador();
        v.setVisible(true);
        this.setVisible(false); 
    }
    
    private boolean validarCamposVacios(){
        if(this.txtNombreEquipo.getText().equals("")||this.txtPersonaResponsable.getText().equals(""))
            return true;
        else
            return false;
    }
    
    private void crearDirectorio(){
        File directorio = new File(System.getProperty("user.dir")+"/EQUIPOS/"+txtNombreEquipo.getText().toUpperCase());
            if (!directorio.exists()) {
                directorio.mkdirs();
            /*if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }*/
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreEquipo = new javax.swing.JTextField();
        txtPersonaResponsable = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblJugadores.setBackground(new java.awt.Color(0, 0, 0));
        tblJugadores.setForeground(new java.awt.Color(255, 255, 255));
        tblJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "EQUIPO", "PERSONA ENCARGADA", "EDITAR", "ELIMINAR"
            }
        ));
        tblJugadores.setGridColor(new java.awt.Color(51, 51, 0));
        tblJugadores.setSelectionBackground(new java.awt.Color(51, 51, 0));
        tblJugadores.setSelectionForeground(new java.awt.Color(255, 204, 204));
        jScrollPane1.setViewportView(tblJugadores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 530, 320));

        jLabel1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel1.setText("NOMBRE DEL EQUIPO:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel2.setText("PERSONA RESOPNSABLE:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        txtNombreEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEquipoActionPerformed(evt);
            }
        });
        txtNombreEquipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreEquipoKeyReleased(evt);
            }
        });
        getContentPane().add(txtNombreEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 180, -1));

        txtPersonaResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPersonaResponsableKeyReleased(evt);
            }
        });
        getContentPane().add(txtPersonaResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 180, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("AGREGAR EQUIPO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        jButton2.setBackground(new java.awt.Color(102, 102, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDITAR CAMBIOS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 470));

        jMenu1.setText("Opcion");

        jMenuItem1.setText("Partido");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Ayuda");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(validarCamposVacios()||!Validations.validateString(txtNombreEquipo.getText())
                || !Validations.validateString(txtPersonaResponsable.getText())){
            JOptionPane.showMessageDialog(null, "REVISE SUS ENTRADAS");
        }else{
            equipo = new Equipo();
            if(!equipo.validarRegistroEquipo(file, txtNombreEquipo.getText().toUpperCase())){
                JOptionPane.showMessageDialog(null, "ESTE EQUIPO YA ESTA REGISTRADO");
            }else{
                equipo.registarEquipo(file, txtNombreEquipo.getText().toUpperCase(),txtPersonaResponsable.getText().toUpperCase());
                crearDirectorio();
                inicializarVariables();
                verEquipos(equipos);
            }    
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNombreEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEquipoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(validarCamposVacios()||!Validations.validateString(txtNombreEquipo.getText())
                || !Validations.validateString(txtPersonaResponsable.getText())){
            JOptionPane.showMessageDialog(null, "REVISE SUS ENTRADAS");
        }else{
            editarEquipo();
            JOptionPane.showMessageDialog(null, "CAMBIOS GUARDADOS");
            inicializarVariables();
            verEquipos(equipos);
            jButton1.setEnabled(true);
            jButton2.setEnabled(false);
            txtNombreEquipo.setEditable(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        VentanaPartido v = new VentanaPartido();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtNombreEquipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEquipoKeyReleased
        if(!Validations.validateString(txtNombreEquipo.getText())) { 
            txtNombreEquipo.setBackground(Color.RED);
        }else 
            txtNombreEquipo.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtNombreEquipoKeyReleased

    private void txtPersonaResponsableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonaResponsableKeyReleased
        if(!Validations.validateString(txtPersonaResponsable.getText())) { 
            txtPersonaResponsable.setBackground(Color.RED);
        }else 
            txtPersonaResponsable.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtPersonaResponsableKeyReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        VentanaAyuda v = new VentanaAyuda();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaEquipo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblJugadores;
    private javax.swing.JTextField txtNombreEquipo;
    private javax.swing.JTextField txtPersonaResponsable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = tblJugadores.rowAtPoint(e.getPoint());
        int columna = tblJugadores.columnAtPoint(e.getPoint());
        if(columna==2){//EDITAR
            editarEquipoDatos(fila);
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            txtNombreEquipo.setEditable(false);
        }else if(columna==3){//JUGADORES
            listarJugadores(fila);
        }else if(columna==4){//ELIMINAR
            eliminarEquipo(fila);
            verEquipos(equipos);  
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
