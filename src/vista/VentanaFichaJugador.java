package vista;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import model.Jugador;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import util.Temporal;

public class VentanaFichaJugador extends javax.swing.JFrame {

    private final Jugador jugador = new Jugador();
    private final File fileJugadr = new File("Jugadores.txt");
    
    public VentanaFichaJugador() {
        initComponents();
        inicilizarDatos();
        this.setLocationRelativeTo(null);
    }
    
    private void inicilizarDatos(){
        jugador.obtenerDatos(fileJugadr, Temporal.getCedulaJugador());
        lblEquipo.setText(jugador.getNombreEquipo());
        lblNombre.setText(jugador.getNombre());
        lblFechaNacimiento.setText(String.valueOf(jugador.getFechaNacimiento()));
        lblPosicion.setText(jugador.getPosicionJugador());
        abrirFotoImagen(Temporal.getCedulaJugador(),jugador.getNombreEquipo());
        //QR
        ByteArrayOutputStream our = QRCode.from("Equipo:"+jugador.getNombreEquipo()+
        "\nNombre:"+jugador.getNombre()+"\nFecha Nacimiento:"+
                String.valueOf(jugador.getFechaNacimiento())+
                "\nPosicion:"+jugador.getPosicionJugador()).to(ImageType.PNG).stream();
        ImageIcon imageIcon = new ImageIcon(our.toByteArray());
        lblQr.setIcon(imageIcon);
    }
    
    private void abrirFotoImagen(String cedulaJugador,String nombreEquipo){
        String direccion=System.getProperty("user.dir")+"/EQUIPOS/"+nombreEquipo+"/"+cedulaJugador+".jpg";
        rsscalelabel.RSScaleLabel.setScaleLabel(lblImg, direccion);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblQr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblEquipo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblPosicion = new javax.swing.JLabel();
        lblImg = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(lblQr, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 130, 130));

        jLabel1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 0));
        jLabel1.setText("Equipo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 173, -1));

        jLabel2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 0));
        jLabel2.setText("Jugador:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 173, -1));

        btnRegresar.setBackground(new java.awt.Color(102, 102, 0));
        btnRegresar.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("REGRESAR");
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, -1, 31));

        jLabel3.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 0, 0));
        jLabel3.setText("Fecha de Nacimiento:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 0));
        jLabel4.setText("Posición");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 173, -1));

        lblEquipo.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        lblEquipo.setText("jLabel5");
        getContentPane().add(lblEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 177, -1));

        lblNombre.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        lblNombre.setText("jLabel6");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 177, -1));

        lblFechaNacimiento.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        lblFechaNacimiento.setText("jLabel7");
        getContentPane().add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 177, -1));

        lblPosicion.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 24)); // NOI18N
        lblPosicion.setText("jLabel8");
        getContentPane().add(lblPosicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 177, -1));
        getContentPane().add(lblImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 140));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VentanaJugador v = new VentanaJugador();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaFichaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaFichaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaFichaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaFichaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaFichaJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblEquipo;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPosicion;
    private javax.swing.JLabel lblQr;
    // End of variables declaration//GEN-END:variables
}
