package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Equipo;
import model.Partido;
import util.FileManager;
import util.Temporal;

public class VentanaPartido extends javax.swing.JFrame implements MouseListener {
    File fileEquipo = new File("Equipos.txt");
    File filePartido = new File("Partidos.txt");
    ArrayList<Equipo> equipo1 = new ArrayList<>();
    private ArrayList<Partido> partidos = new ArrayList<>();
    private Partido partido;
    Color colorCabecera = new Color(51,0,0);
    
    public VentanaPartido() {
        initComponents();
        rellenaCombo1();
        rellenaCombo2();
        this.setLocationRelativeTo(null);
        verTablaPartidos(partidos);
        tblPartido.addMouseListener(this);
        jComboBoxEquipo2.setEnabled(false);
        jComboBoxEquipo1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jComboBoxEquipo2.setEnabled(true);
                rellenaCombo2((String) jComboBoxEquipo1.getSelectedItem());
            }
        });
    }
    private void rellenaCombo1() {
        FileManager.createArrayEquipoOfFile(equipo1, fileEquipo);
        jComboBoxEquipo1.removeAllItems();
        jComboBoxEquipo1.addItem("SELECCIONE UN EQUIPO");
        for (int i = 0; i < equipo1.size(); i++) {
            jComboBoxEquipo1.addItem(equipo1.get(i).getNombreEquipo());
        }
    }
    
    private void rellenaCombo2() {
        jComboBoxEquipo2.removeAllItems();
        for (int i = 0; i < equipo1.size(); i++) {
            jComboBoxEquipo2.addItem(equipo1.get(i).getNombreEquipo());
        }
    }
    
    private void rellenaCombo2(String seleccionEnCombo1) {	
        jComboBoxEquipo2.removeAllItems();
        for (int i = 0; i < equipo1.size(); i++) {
            if(!equipo1.get(i).getNombreEquipo().equals(seleccionEnCombo1))
                jComboBoxEquipo2.addItem(equipo1.get(i).getNombreEquipo());
        }       
    }
    
     private void verTablaPartidos(ArrayList<Partido> partidos){
        FileManager.createArrayPartidoOfFile(partidos, filePartido);
        String matriz[][] = new String[partidos.size()][8];
        for (int i = 0; i < partidos.size(); i++) {
            matriz[i][0] = String.valueOf(partidos.get(i).getIdPartido());
            matriz[i][1] = String.valueOf(partidos.get(i).getFechaPartido().get(Calendar.YEAR)
            +"-"+(partidos.get(i).getFechaPartido().get(Calendar.MONTH)+1)+"-"+
            partidos.get(i).getFechaPartido().get(Calendar.DATE));
            matriz[i][2] = partidos.get(i).getEquipo1();
            matriz[i][3] = partidos.get(i).getEquipo2();
            matriz[i][4] = partidos.get(i).getEstadoPartido();
            if(partidos.get(i).getEstadoPartido().equals("FINALIZADO")&&partidos.get(i).getEstadoGanadorEquipo1().equals("PERDEDOR")&&partidos.get(i).getEstadoGanadorEquipo2().equals("PERDEDOR")){
                matriz[i][5] = "NINGÚNO SE PRESENTO";
            }else if(partidos.get(i).getEstadoPartido().equals("FINALIZADO")&&partidos.get(i).getEstadoGanadorEquipo1().equals("GANADOR")&&partidos.get(i).getEstadoGanadorEquipo2().equals("PERDEDOR")){
                matriz[i][5] = partidos.get(i).getEquipo1();
            }else if(partidos.get(i).getEstadoPartido().equals("FINALIZADO")&&partidos.get(i).getEstadoGanadorEquipo1().equals("PERDEDOR")&&partidos.get(i).getEstadoGanadorEquipo2().equals("GANADOR")){
                matriz[i][5] = partidos.get(i).getEquipo2();
            }else if(partidos.get(i).getEstadoPartido().equals("FINALIZADO")&&partidos.get(i).getEstadoGanadorEquipo1().equals("GANADOR")&&partidos.get(i).getEstadoGanadorEquipo2().equals("GANADOR")){
                matriz[i][5] = "EMPATE";
            }else
                matriz[i][5] = "NO HA COMENZADO";
            matriz[i][6] = partidos.get(i).getGolesEquipo1()+" - "+partidos.get(i).getGolesEquipo2();
            matriz[i][7] = "DETALLES";
            }
        tblPartido.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"#P.","Fecha","EQUIPO","EQUIPO","ESTADO","GANADOR","RESULTADO","DETALLES"}));
        tblPartido.getColumnModel().getColumn(0).setPreferredWidth(1);
        tblPartido.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblPartido.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblPartido.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblPartido.getColumnModel().getColumn(4).setPreferredWidth(30);
        tblPartido.getColumnModel().getColumn(5).setPreferredWidth(90);
        tblPartido.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblPartido.getColumnModel().getColumn(7).setPreferredWidth(30);
        partidos.clear();
        tblPartido.getTableHeader().setOpaque(false);
        tblPartido.getTableHeader().setBackground(colorCabecera);
        tblPartido.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<8;x++){
            tblPartido.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxEquipo1 = new javax.swing.JComboBox<>();
        jComboBoxEquipo2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPartido = new javax.swing.JTable();
        jButtonCrearPartido = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBoxEquipo1.setBackground(new java.awt.Color(0, 0, 51));
        jComboBoxEquipo1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxEquipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEquipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEquipo1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxEquipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 270, -1));

        jComboBoxEquipo2.setBackground(new java.awt.Color(0, 0, 51));
        jComboBoxEquipo2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxEquipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxEquipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 270, -1));

        tblPartido.setBackground(new java.awt.Color(0, 0, 0));
        tblPartido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblPartido.setForeground(new java.awt.Color(255, 255, 255));
        tblPartido.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPartido.setToolTipText("");
        tblPartido.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPartido.setGridColor(new java.awt.Color(51, 51, 0));
        tblPartido.setSelectionBackground(new java.awt.Color(51, 51, 0));
        tblPartido.setSelectionForeground(new java.awt.Color(204, 255, 204));
        tblPartido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblPartido);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 870, 340));

        jButtonCrearPartido.setBackground(new java.awt.Color(0, 0, 51));
        jButtonCrearPartido.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCrearPartido.setText("CREAR PARTIDO");
        jButtonCrearPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearPartidoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCrearPartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 156, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/partido.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenu1.setText("Opcion");

        jMenuItem1.setText("Equipo");
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

    private void jComboBoxEquipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEquipo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxEquipo1ActionPerformed

    private void jButtonCrearPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearPartidoActionPerformed
        partido = new Partido();
        String equipo= (String) jComboBoxEquipo1.getSelectedItem();
        if(equipo.equals("SELECCIONE UN EQUIPO")){
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN EQUIPO");
        }else{
        partido.crearPartido(filePartido,(String) jComboBoxEquipo1.getSelectedItem(),(String)jComboBoxEquipo2.getSelectedItem());
        verTablaPartidos(partidos);    
        }
    }//GEN-LAST:event_jButtonCrearPartidoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        VentanaEquipo v = new VentanaEquipo();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPartido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrearPartido;
    private javax.swing.JComboBox<String> jComboBoxEquipo1;
    private javax.swing.JComboBox<String> jComboBoxEquipo2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPartido;
    // End of variables declaration//GEN-END:variables
    private void detallesPartido(int fila){
        Temporal t = new Temporal();
        Temporal.setIdPartido(Integer.parseInt(tblPartido.getValueAt(fila, 0).toString()));//idPartido
        Temporal.setEstadoPatido(tblPartido.getValueAt(fila, 4).toString());//idPartido
        Temporal.setEquipo1(tblPartido.getValueAt(fila, 2).toString());//EQUIPO1
        Temporal.setEquipo2(tblPartido.getValueAt(fila, 3).toString());//EQUIPO2
        VentanaDetallePartido v = new VentanaDetallePartido();
        v.setVisible(true);
        this.setVisible(false); 
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = tblPartido.rowAtPoint(e.getPoint());
        int columna = tblPartido.columnAtPoint(e.getPoint());
        if(columna==7){//DETALLES
            detallesPartido(fila);
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
