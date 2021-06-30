/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Equipo;
import model.Jugador;
import util.FileManager;
import util.Temporal;
import util.Validations;

public class VentanaJugador extends javax.swing.JFrame implements MouseListener{
    JFileChooser seleccionar = new JFileChooser();
    byte[]imagen;
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    File file = new File("Jugadores.txt"); 
    private Jugador jugador;
    private final ArrayList<Jugador> jugadores = new ArrayList<>();
    Temporal t = new Temporal();
    private final Equipo equipo = new Equipo();
    String arrayOpcionesJuego[] = {"Portero","Defensa","Medio","Delantero"};
    Color colorCabecera = new Color(51,0,0);
    
    public VentanaJugador() {
        initComponents();
        rellenaCombo();
        lblEquipo.setText(t.getTexto());
        verJugadores(jugadores);
        jButton2.setEnabled(false);//Desactivar Boton
        tblJugadores.addMouseListener(this);
        this.setLocationRelativeTo(null);
    }
    private void verJugadores(ArrayList<Jugador> jugadores){
        equipo.jugadoresEquipo(file, lblEquipo.getText());
        jugadores = equipo.getJugadores();
        String matriz[][] = new String[jugadores.size()][8];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getCedula();
            matriz[i][1] = jugadores.get(i).getNombre();
            matriz[i][2] = String.valueOf(jugadores.get(i).getFechaNacimiento());
            matriz[i][3] = String.valueOf(jugadores.get(i).getNumeroJugador());
            matriz[i][4] = jugadores.get(i).getPosicionJugador();
            matriz[i][5] = "EDITAR";
            matriz[i][6] = "VER FICHA";
            matriz[i][7] = "ELIMINAR";
            }
        tblJugadores.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"CÉDULA","NOMBRE","FECHA N.","#CAMISETA","POSICIÓN","EDITAR","VER FICHA","ELIMINAR"}));
        tblJugadores.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(3).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(5).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblJugadores.getColumnModel().getColumn(7).setPreferredWidth(30);
        tblJugadores.getTableHeader().setOpaque(false);
        tblJugadores.getTableHeader().setBackground(colorCabecera);
        tblJugadores.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<8;x++){
            tblJugadores.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        jugadores.clear();
                
    }
    private void inicializarVariablesNulas(){
        this.txtCamiseta.setText("");
        this.txtCedula.setText("");
        this.txtNombre.setText("");
        this.calendarFechaN.setDate(null);
        this.lblImagen.setIcon(null);
        this.txtCamiseta.setBackground(Color.WHITE);
        this.txtCedula.setBackground(Color.WHITE);
        this.txtNombre.setBackground(Color.WHITE);
        
    }
    
    private void rellenaCombo() {
        jComboBoxPosicion.removeAllItems();
        for (int i = 0; i < arrayOpcionesJuego.length; i++) {
            jComboBoxPosicion.addItem(arrayOpcionesJuego[i]);
        }
    }
    
    private void modificarCombo(String valor) {
        jComboBoxPosicion.removeAllItems();
        jComboBoxPosicion.addItem(valor);
        for (int i = 0; i < arrayOpcionesJuego.length; i++) {
            if(!arrayOpcionesJuego[i].equals(valor))
                jComboBoxPosicion.addItem(arrayOpcionesJuego[i]);
        }  
    }
    
    private void editarJugadorDatos(int fila) {
        String cedula = tblJugadores.getValueAt(fila, 0).toString();
        String nombre = tblJugadores.getValueAt(fila, 1).toString();
        String fechaNacimiento = tblJugadores.getValueAt(fila, 2).toString();
        String numeroCamiseta=tblJugadores.getValueAt(fila, 3).toString();
        String posicion = tblJugadores.getValueAt(fila, 4).toString();
        String direccion=System.getProperty("user.dir")+"/EQUIPOS/"+lblEquipo.getText()+"/"+cedula+".jpg";
        rsscalelabel.RSScaleLabel.setScaleLabel(lblImagen, direccion);
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaTxt;
        try {
            fechaTxt = formatoFecha.parse(fechaNacimiento);
            calendarFechaN.setDate(fechaTxt);
        } catch (ParseException e) {
            System.out.println("Error al convertir el jcalendar");
        }
        archivo = new File(direccion);
        imagen=abrirArchivo(archivo);
        txtCedula.setEditable(false);
        txtCedula.setText(cedula);
        txtNombre.setText(nombre);
        modificarCombo(posicion);
        txtCamiseta.setText(numeroCamiseta);
    }
    private void editarJugador() {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String posicion = (String) jComboBoxPosicion.getSelectedItem();
        Date date = calendarFechaN.getDate();
        int camiseta = Integer.parseInt(txtCamiseta.getText());
        jugador = new Jugador();
        jugador.editarJugador(file, cedula, nombre.toUpperCase(), posicion, camiseta, date);
            imagen=abrirArchivo(archivo);
        guardarArchivo(imagen);
    }
    
    private void eliminarJugador(int fila) {
        String cedula = tblJugadores.getValueAt(fila, 0).toString();
        jugador = new Jugador();
        jugador.eliminarJugador(file, cedula);
        String direccion=System.getProperty("user.dir")+"/EQUIPOS/"+lblEquipo.getText()+"/"+cedula+".jpg";
        File arch = new File(direccion);
        arch.delete();
    }
    private boolean validarCamposVacios(){
        if(this.txtCamiseta.getText().equals("")||this.txtCedula.getText().equals("")||
           this.txtNombre.getText().equals("")||this.calendarFechaN.getDate() == null)
            return true;
        else
            return false;
    }
    
    public byte[] abrirArchivo(File archivo){
        byte[]imagen = new byte[1024*100];
        try{
            entrada = new FileInputStream(archivo);
            entrada.read(imagen);
        }catch(Exception e){
            
        }
        return imagen;
    }
    
    public String guardarArchivo(byte[]imagen){
        File directorio = new File(System.getProperty("user.dir")+"/EQUIPOS/"+lblEquipo.getText()+"/"+txtCedula.getText()+".jpg");
        String mensaje = null;
        try {
            salida = new FileOutputStream(directorio);
            salida.write(imagen);
            mensaje ="Archivo guardado";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La imágen no se pudo guardar");
        }
        return mensaje;
    }
    
    public void guardarImagen(){
        File directorio = new File(System.getProperty("user.dir")+"/JUVENTUS/"+txtCedula);
        if(!directorio.exists()){
            System.out.println("error al crear dir");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBoxPosicion = new javax.swing.JComboBox<>();
        txtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnGuardarImagen = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtCamiseta = new javax.swing.JTextField();
        calendarFechaN = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        lblEquipo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBoxPosicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxPosicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 138, -1));

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
        getContentPane().add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 138, -1));

        jLabel2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 80, 20));

        jButton3.setBackground(new java.awt.Color(102, 102, 0));
        jButton3.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("REGRESAR");
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, -1, -1));

        jButton2.setBackground(new java.awt.Color(102, 0, 0));
        jButton2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDITAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 140, -1));

        jLabel5.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel5.setText("Número de Camiseta:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 217, 190, 22));

        btnGuardarImagen.setText("Imagen");
        btnGuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarImagenActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 138, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 138, -1));

        txtCamiseta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCamisetaKeyReleased(evt);
            }
        });
        getContentPane().add(txtCamiseta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 138, -1));
        getContentPane().add(calendarFechaN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 138, -1));

        jLabel4.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel4.setText("Posición:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 144, 140, 20));

        lblEquipo.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 36)); // NOI18N
        lblEquipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo.setText("EQUIPO");
        getContentPane().add(lblEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 520, -1));

        jLabel3.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel3.setText("Fecha de Nacimiento:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 180, 20));

        jLabel8.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel8.setText("Foto:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, -1));
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 150, 140));

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
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblJugadores.setGridColor(new java.awt.Color(51, 51, 0));
        tblJugadores.setSelectionBackground(new java.awt.Color(51, 51, 0));
        tblJugadores.setSelectionForeground(new java.awt.Color(255, 255, 204));
        jScrollPane3.setViewportView(tblJugadores);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 298, 707, 270));

        jLabel1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel1.setText("Cédula:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 64, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("INGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 140, -1));

        jLabel6.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoP.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 580));

        jLabel7.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel7.setText("Posición:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 144, 140, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jugador = new Jugador();
        if(validarCamposVacios() || !Validations.isNumeric(txtCamiseta.getText())||
        !Validations.validateString(txtNombre.getText())||!Validations.isNumeric(txtCedula.getText())){
            JOptionPane.showMessageDialog(null, "REVISE SUS ENTRADAS");    
        }else if(!jugador.isValidCi(txtCedula.getText())){
            JOptionPane.showMessageDialog(null, "Cédula invalida");
        }else{
            if(!jugador.validarJugadorRegistrado(file, txtCedula.getText())){
                JOptionPane.showMessageDialog(null, "ESTE JUGADOR YA SE HA REGSTRADO EN UN EQUIPO");
            }else{
                String foto="";
                Date date = calendarFechaN.getDate();
                int numeroCamiseta = Integer.parseInt(txtCamiseta.getText());
                jugador.registrarJugador(file,txtCedula.getText(),txtNombre.getText().toUpperCase(),date,t.getTexto(),numeroCamiseta,(String) jComboBoxPosicion.getSelectedItem(),foto,0,0);
                guardarArchivo(imagen);
                inicializarVariablesNulas();
                verJugadores(jugadores);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(validarCamposVacios() || !Validations.isNumeric(txtCamiseta.getText())||
        !Validations.validateString(txtNombre.getText())||!Validations.isNumeric(txtCedula.getText())){
            JOptionPane.showMessageDialog(null, "REVISE SUS ENTRADAS");    
        }else{
            editarJugador();
            JOptionPane.showMessageDialog(null, "CAMBIOS GUARDADOS");
            inicializarVariablesNulas();
            verJugadores(jugadores);
            jButton1.setEnabled(true);
            jButton2.setEnabled(false);
            txtCedula.setEditable(true);   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       VentanaEquipo v = new VentanaEquipo();
       v.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnGuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarImagenActionPerformed
        if(seleccionar.showDialog(null, null)==JFileChooser.APPROVE_OPTION){
            archivo = seleccionar.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith("jpg")||archivo.getName().endsWith("png")||archivo.getName().endsWith("gif"));
                imagen=abrirArchivo(archivo);
                rsscalelabel.RSScaleLabel.setScaleLabel(lblImagen, seleccionar.getSelectedFile().toString());
            }else{
                JOptionPane.showMessageDialog(null,"Archivo no comatible");
            }
        }
    }//GEN-LAST:event_btnGuardarImagenActionPerformed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        if(!Validations.isNumeric(txtCedula.getText())) { 
            txtCedula.setBackground(Color.RED);
        }else 
            txtCedula.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if(!Validations.validateString(txtNombre.getText())) { 
            txtNombre.setBackground(Color.RED);
        }else 
            txtNombre.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtCamisetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamisetaKeyReleased
        if(!Validations.isNumeric(txtCamiseta.getText())) { 
            txtCamiseta.setBackground(Color.RED);
        }else 
            txtCamiseta.setBackground(Color.GREEN);
    }//GEN-LAST:event_txtCamisetaKeyReleased

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
            java.util.logging.Logger.getLogger(VentanaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarImagen;
    private com.toedter.calendar.JDateChooser calendarFechaN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxPosicion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblEquipo;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTable tblJugadores;
    private javax.swing.JTextField txtCamiseta;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = tblJugadores.rowAtPoint(e.getPoint());
        int columna = tblJugadores.columnAtPoint(e.getPoint());
        if(columna==5){//EDITAR
            editarJugadorDatos(fila);
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
        }else if(columna==6){//FICHA
            Temporal.setCedulaJugador(tblJugadores.getValueAt(fila, 0).toString());
            VentanaFichaJugador v = new VentanaFichaJugador();
            v.setVisible(true);
            this.setVisible(false);
        }else if(columna==7){//ELIMINAR   
            eliminarJugador(fila);
            verJugadores(jugadores);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
