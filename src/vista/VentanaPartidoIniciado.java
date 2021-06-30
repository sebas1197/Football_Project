

package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import model.Equipo;
import model.Jugador;
import model.Partido;
import util.Temporal;
import util.Validations;

public class VentanaPartidoIniciado extends javax.swing.JFrame implements MouseListener{
    File file = new File("Jugadores.txt"); 
    File filePartido = new File("Partidos.txt");
    private final Jugador jugador = new Jugador();
    private final ArrayList<Jugador> jugadores = new ArrayList<>();
    private final Equipo equipo = new Equipo();
    private final Partido p = new Partido();
    Color colorCabecera = new Color(51,0,0);
    
    public VentanaPartidoIniciado() {
        initComponents();
        lblEquipo1.setText(Temporal.getEquipo1());
        lblEquipo2.setText(Temporal.getEquipo2());
        lblJHE1.setText("Jugadores Habilitados del equipo "+Temporal.getEquipo1());
        lblJDE1.setText("Jugadores Deshabilitados del equipo "+Temporal.getEquipo1());
        lblJHE2.setText("Jugadores Habilitados del equipo "+Temporal.getEquipo2());
        lblJDE2.setText("Jugadores Deshabilitados del equipo "+Temporal.getEquipo2());
        
        tblEquipo1NoSancionados.addMouseListener(this);
        tblEquipo2NoSancionados.addMouseListener(this);
        
        verJugadoresNoSancionadosEquipo1(jugadores);
        verJugadoresNoSancionadosEquipo2(jugadores);
        verJugadoresSancionadosEquipo1(jugadores);
        verJugadoresSancionadosEquipo2(jugadores);
    }
    
    private void verJugadoresNoSancionadosEquipo1(ArrayList<Jugador> jugadores){
        
        equipo.jugadoresEquipoNoSancionados(file, lblEquipo1.getText());
        jugadores = equipo.getJugadoresNoSancionados();
        String matriz[][] = new String[jugadores.size()][6];
                for (int i = 0; i < jugadores.size(); i++) {
                    matriz[i][0] = jugadores.get(i).getCedula();
                    matriz[i][1] = jugadores.get(i).getNombre();
                    matriz[i][2] = String.valueOf(jugadores.get(i).getNumeroJugador());
                    matriz[i][3] = jugadores.get(i).getPosicionJugador();
                    matriz[i][4] = "Asignar T. Amarilla";
                    matriz[i][5] = "Asignar T. Roja";
                }
        tblEquipo1NoSancionados.setModel(new javax.swing.table.DefaultTableModel(matriz,new String []{"","NOMBRE","#CAMISETA","POSICIÓN","T, AMARILLA","T. ROJA"}));
        tblEquipo1NoSancionados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblEquipo1NoSancionados.getColumnModel().getColumn(0).setMinWidth(0);
        tblEquipo1NoSancionados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblEquipo1NoSancionados.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblEquipo1NoSancionados.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblEquipo1NoSancionados.getColumnModel().getColumn(3).setPreferredWidth(30);
        tblEquipo1NoSancionados.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblEquipo1NoSancionados.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblEquipo1NoSancionados.getTableHeader().setOpaque(false);
        tblEquipo1NoSancionados.getTableHeader().setBackground(colorCabecera);
        tblEquipo1NoSancionados.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<6;x++){
            tblEquipo1NoSancionados.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        jugadores.clear();
    }
    
    private void verJugadoresNoSancionadosEquipo2(ArrayList<Jugador> jugadores){
        equipo.jugadoresEquipoNoSancionados(file, lblEquipo2.getText());
        jugadores = equipo.getJugadoresNoSancionados();
        String matriz[][] = new String[jugadores.size()][6];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getCedula();
            matriz[i][1] = jugadores.get(i).getNombre();
            matriz[i][2] = String.valueOf(jugadores.get(i).getNumeroJugador());
            matriz[i][3] = jugadores.get(i).getPosicionJugador();
            matriz[i][4] = " Asignar T. Amarilla";
            matriz[i][5] = " Asignar T. Roja";
        }
        tblEquipo2NoSancionados.setModel(new javax.swing.table.DefaultTableModel(matriz,new String []{"","NOMBRE","#CAMISETA","POSICIÓN","T, AMARILLA","T. ROJA"}));
        tblEquipo2NoSancionados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblEquipo2NoSancionados.getColumnModel().getColumn(0).setMinWidth(0);
        tblEquipo2NoSancionados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblEquipo2NoSancionados.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblEquipo2NoSancionados.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblEquipo2NoSancionados.getColumnModel().getColumn(3).setPreferredWidth(30);
        jugadores.clear();
        tblEquipo2NoSancionados.getTableHeader().setOpaque(false);
        tblEquipo2NoSancionados.getTableHeader().setBackground(colorCabecera);
        tblEquipo2NoSancionados.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<6;x++){
            tblEquipo2NoSancionados.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
    }
    
    private void verJugadoresSancionadosEquipo1(ArrayList<Jugador> jugadores){
        equipo.jugadoresEquipoSancionados(file, lblEquipo1.getText());
        jugadores = equipo.getJugadoresSancionados();
        String matriz[][] = new String[jugadores.size()][4];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getNombre();
            matriz[i][1] = String.valueOf(jugadores.get(i).getNumeroJugador());
            matriz[i][2] = String.valueOf(jugadores.get(i).getTarjetasAmarillas());
            matriz[i][3] = String.valueOf(jugadores.get(i).getTarjetasRojas());
        }
        tblJugadoresSancionadosE1.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"NOMBRE","# CAMISETA","#T.AMARILLAS","#T.ROJAS"}));
        tblJugadoresSancionadosE1.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblJugadoresSancionadosE1.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblJugadoresSancionadosE1.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblJugadoresSancionadosE1.getColumnModel().getColumn(3).setPreferredWidth(30);
        jugadores.clear();
        tblJugadoresSancionadosE1.getTableHeader().setOpaque(false);
        tblJugadoresSancionadosE1.getTableHeader().setBackground(colorCabecera);
        tblJugadoresSancionadosE1.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<4;x++){
            tblJugadoresSancionadosE1.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
    }
    
    private void verJugadoresSancionadosEquipo2(ArrayList<Jugador> jugadores){
        equipo.jugadoresEquipoSancionados(file, lblEquipo2.getText());
        jugadores = equipo.getJugadoresSancionados();
        String matriz[][] = new String[jugadores.size()][4];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getNombre();
            matriz[i][1] = String.valueOf(jugadores.get(i).getNumeroJugador());
            matriz[i][2] = String.valueOf(jugadores.get(i).getTarjetasAmarillas());
            matriz[i][3] = String.valueOf(jugadores.get(i).getTarjetasRojas());
        }
        tblJugadoresSancionadosE2.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"NOMBRE","# CAMISETA","#T.AMARILLAS","#T.ROJAS"}));
        tblJugadoresSancionadosE2.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblJugadoresSancionadosE2.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblJugadoresSancionadosE2.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblJugadoresSancionadosE2.getColumnModel().getColumn(3).setPreferredWidth(30);
        jugadores.clear();
        tblJugadoresSancionadosE2.getTableHeader().setOpaque(false);
        tblJugadoresSancionadosE2.getTableHeader().setBackground(colorCabecera);
        tblJugadoresSancionadosE2.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<4;x++){
            tblJugadoresSancionadosE2.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
                
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEquipo1NoSancionados = new javax.swing.JTable();
        lblJHE1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblJugadoresSancionadosE1 = new javax.swing.JTable();
        lblJDE1 = new javax.swing.JLabel();
        lblEquipo1 = new javax.swing.JLabel();
        lblEquipo2 = new javax.swing.JLabel();
        txtMEquipo2 = new javax.swing.JTextField();
        txtMEquipo1 = new javax.swing.JTextField();
        lblJDE2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblJugadoresSancionadosE2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lblJHE2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEquipo2NoSancionados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEquipo1NoSancionados.setBackground(new java.awt.Color(0, 0, 51));
        tblEquipo1NoSancionados.setForeground(new java.awt.Color(255, 255, 255));
        tblEquipo1NoSancionados.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEquipo1NoSancionados.setGridColor(new java.awt.Color(255, 255, 204));
        tblEquipo1NoSancionados.setSelectionBackground(new java.awt.Color(102, 0, 0));
        tblEquipo1NoSancionados.setSelectionForeground(new java.awt.Color(255, 255, 204));
        jScrollPane1.setViewportView(tblEquipo1NoSancionados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, 577, 268));

        lblJHE1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        lblJHE1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJHE1.setText("JUGADORES DEL EQUIPO 1");
        getContentPane().add(lblJHE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 47, 577, -1));

        tblJugadoresSancionadosE1.setBackground(new java.awt.Color(0, 0, 0));
        tblJugadoresSancionadosE1.setForeground(new java.awt.Color(255, 255, 255));
        tblJugadoresSancionadosE1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblJugadoresSancionadosE1.setGridColor(new java.awt.Color(255, 255, 204));
        tblJugadoresSancionadosE1.setSelectionBackground(new java.awt.Color(102, 102, 0));
        tblJugadoresSancionadosE1.setSelectionForeground(new java.awt.Color(255, 255, 204));
        jScrollPane4.setViewportView(tblJugadoresSancionadosE1);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 402, 421, 150));

        lblJDE1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        lblJDE1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJDE1.setText("JUGADORES SANCIONADOS EQUIPO1");
        getContentPane().add(lblJDE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 580, 30));

        lblEquipo1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        lblEquipo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo1.setText("EQUIPO 1");
        getContentPane().add(lblEquipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 577, -1));

        lblEquipo2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        lblEquipo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo2.setText("EQUIPO 2");
        getContentPane().add(lblEquipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 469, -1));

        txtMEquipo2.setText("0");
        txtMEquipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMEquipo2ActionPerformed(evt);
            }
        });
        getContentPane().add(txtMEquipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 54, -1));

        txtMEquipo1.setText("0");
        txtMEquipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMEquipo1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtMEquipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 56, -1));

        lblJDE2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        lblJDE2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJDE2.setText("JUGADORES SANCIONADOS EQUIPO 2");
        getContentPane().add(lblJDE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, 470, 22));

        tblJugadoresSancionadosE2.setBackground(new java.awt.Color(0, 0, 0));
        tblJugadoresSancionadosE2.setForeground(new java.awt.Color(255, 255, 255));
        tblJugadoresSancionadosE2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblJugadoresSancionadosE2.setGridColor(new java.awt.Color(255, 255, 204));
        tblJugadoresSancionadosE2.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tblJugadoresSancionadosE2.setSelectionForeground(new java.awt.Color(102, 102, 0));
        jScrollPane5.setViewportView(tblJugadoresSancionadosE2);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 400, 320, 160));

        jLabel5.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel5.setText("Marcador");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, -1, 20));

        lblJHE2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        lblJHE2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJHE2.setText("JUGADORES DE EQUIPO2");
        getContentPane().add(lblJHE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 469, -1));

        jButton1.setBackground(new java.awt.Color(0, 0, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("FINALIZAR PARTIDO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 160, -1));

        tblEquipo2NoSancionados.setBackground(new java.awt.Color(0, 0, 51));
        tblEquipo2NoSancionados.setForeground(new java.awt.Color(255, 255, 255));
        tblEquipo2NoSancionados.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEquipo2NoSancionados.setGridColor(new java.awt.Color(255, 255, 204));
        tblEquipo2NoSancionados.setSelectionBackground(new java.awt.Color(102, 0, 0));
        tblEquipo2NoSancionados.setSelectionForeground(new java.awt.Color(255, 255, 204));
        jScrollPane3.setViewportView(tblEquipo2NoSancionados);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, 469, 268));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!Validations.isNumericPositive(txtMEquipo1.getText())||!Validations.isNumericPositive(txtMEquipo2.getText()))
            JOptionPane.showMessageDialog(null, "VERIFIQUE LAS ENTRADAS DE GOLES");
        else{
        p.modificarGolesPartido(Temporal.getIdPartido(), filePartido, Integer.parseInt(txtMEquipo1.getText()),Integer.parseInt(txtMEquipo2.getText()));
        
        VentanaPartido v = new VentanaPartido();
        v.setVisible(true);
        this.setVisible(false);
        }
        //Temporal.getIdPartido();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMEquipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMEquipo2ActionPerformed
      
    }//GEN-LAST:event_txtMEquipo2ActionPerformed

    private void txtMEquipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMEquipo1ActionPerformed
     
    }//GEN-LAST:event_txtMEquipo1ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPartidoIniciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPartidoIniciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPartidoIniciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPartidoIniciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPartidoIniciado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEquipo1;
    private javax.swing.JLabel lblEquipo2;
    private javax.swing.JLabel lblJDE1;
    private javax.swing.JLabel lblJDE2;
    private javax.swing.JLabel lblJHE1;
    private javax.swing.JLabel lblJHE2;
    private javax.swing.JTable tblEquipo1NoSancionados;
    private javax.swing.JTable tblEquipo2NoSancionados;
    private javax.swing.JTable tblJugadoresSancionadosE1;
    private javax.swing.JTable tblJugadoresSancionadosE2;
    private javax.swing.JTextField txtMEquipo1;
    private javax.swing.JTextField txtMEquipo2;
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
    int fila;
    int columna;
    if(e.getComponent().equals(tblEquipo1NoSancionados)){
    fila = tblEquipo1NoSancionados.rowAtPoint(e.getPoint());
    columna = tblEquipo1NoSancionados.columnAtPoint(e.getPoint());
        if(columna==4){//ASIGNAR TARJETA AMARILLA EQUIPO1
            String cedula = tblEquipo1NoSancionados.getValueAt(fila, 0).toString();
            int tarjetasAmarillas;
            tarjetasAmarillas = p.asignarTarjetasAmarillas(cedula, file);
            if(tarjetasAmarillas==2){
                jugador.asignarDiasSancion(file, cedula,"TA");
            }
            verJugadoresNoSancionadosEquipo1(jugadores);
            verJugadoresSancionadosEquipo1(jugadores);
        }else if(columna==5){//TARJETA ROJA  
                String cedula = tblEquipo1NoSancionados.getValueAt(fila, 0).toString();
                jugador.asignarTarjetaRoja(file,cedula);
                verJugadoresNoSancionadosEquipo1(jugadores);
                verJugadoresSancionadosEquipo1(jugadores);
            }        
    }else if(e.getComponent().equals(tblEquipo2NoSancionados)){
        fila = tblEquipo2NoSancionados.rowAtPoint(e.getPoint());
        columna = tblEquipo2NoSancionados.columnAtPoint(e.getPoint());
        if(columna==4){//TARJETA AMARILLA EQUIPO2
            String cedu = tblEquipo2NoSancionados.getValueAt(fila, 0).toString();
            p.asignarTarjetasAmarillas(cedu, file);
            verJugadoresNoSancionadosEquipo2(jugadores);
            verJugadoresSancionadosEquipo2(jugadores);
        }else if(columna==5){//TARJETA ROJA  
            String cedula = tblEquipo2NoSancionados.getValueAt(fila, 0).toString();
            jugador.asignarTarjetaRoja(file,cedula);
            verJugadoresNoSancionadosEquipo2(jugadores);
            verJugadoresSancionadosEquipo2(jugadores);
            }
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
