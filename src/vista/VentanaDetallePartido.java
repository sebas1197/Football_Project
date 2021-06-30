package vista;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Equipo;
import model.Jugador;
import model.Partido;
import util.Temporal;

public class VentanaDetallePartido extends javax.swing.JFrame implements MouseListener{
    File file = new File("Jugadores.txt");
    File filePartido = new File("Partidos.txt");
    private Jugador jugador;
    private final ArrayList<Jugador> jugadores = new ArrayList<>();
    private final Equipo equipo = new Equipo();
    private final Partido p = new  Partido();
    Color colorCabecera = new Color(51,0,0);
    
    
    public VentanaDetallePartido() {
        initComponents();
        lblEquipo1.setText(Temporal.getEquipo1());
        lblEquipo2.setText(Temporal.getEquipo2());    
        verJugadoresEquipo1(jugadores);
        verJugadoresEquipo2(jugadores);
        verJugadoresSancionadosEquipo1(jugadores);
        verJugadoresSancionadosEquipo2(jugadores);
        verPagoArbitraje();
        validarInicioPartido();
        desactivarBotonPE1();
        desactivarBotonPE2();
        tblE1Sancionados.addMouseListener(this);
        tblE2Sancionados.addMouseListener(this);
        this.setLocationRelativeTo(null);
    }
    
    private void validarInicioPartido(){
        if(!p.verificarPagoArbitraje(filePartido, Temporal.getIdPartido()) ||
        !equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo1())||
                !equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo2())){
                jButtonInicioPartido.setEnabled(false);
                
        }else{
            jButtonInicioPartido.setEnabled(true);
            jButtonFinalizarPartido.setEnabled(false);
        }
        if(Temporal.getEstadoPatido().equals("FINALIZADO")){
            jButtonInicioPartido.setEnabled(false);
            jButtonFinalizarPartido.setEnabled(false);    
        }
    }
    
    private void desactivarBotonPE1(){
        if(p.obtenerPagoArbitrajeEquipo1(filePartido,Temporal.getIdPartido()).equals("NO PAGADO"))
            jButtonPAE1.setEnabled(true);
        else
            jButtonPAE1.setEnabled(false);
    }
    private void desactivarBotonPE2(){
        if(p.obtenerPagoArbitrajeEquipo2(filePartido,Temporal.getIdPartido()).equals("NO PAGADO"))
            jButtonPAE2.setEnabled(true);
        else
            jButtonPAE2.setEnabled(false);
    }
    private void verJugadoresEquipo1(ArrayList<Jugador> jugadores){
        equipo.jugadoresEquipoNoDeben(file, lblEquipo1.getText());
        jugadores = equipo.getJugadoresNoSancionados();
        String matriz[][] = new String[jugadores.size()][3];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getNombre();
            matriz[i][1] = String.valueOf(jugadores.get(i).getNumeroJugador());
            matriz[i][2] = jugadores.get(i).getPosicionJugador();
            }
        tblEquipo1.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"NOMBRE","#CAMISETA","POSICIÓN"}));
        tblEquipo1.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblEquipo1.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblEquipo1.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblEquipo1.getTableHeader().setOpaque(false);
        tblEquipo1.getTableHeader().setBackground(colorCabecera);
        tblEquipo1.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<3;x++){
            tblEquipo1.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        jugadores.clear();
    }

    private void verJugadoresEquipo2(ArrayList<Jugador> jugadores){
        equipo.jugadoresEquipoNoDeben(file, lblEquipo2.getText());
        jugadores = equipo.getJugadoresNoSancionados();
        String matriz[][] = new String[jugadores.size()][3];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getNombre();
            matriz[i][1] = String.valueOf(jugadores.get(i).getNumeroJugador());
            matriz[i][2] = jugadores.get(i).getPosicionJugador();
            }
        tblEquipo2.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"NOMBRE","#CAMISETA","POSICIÓN"}));
        tblEquipo2.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblEquipo2.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblEquipo2.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblEquipo2.getTableHeader().setOpaque(false);
        tblEquipo2.getTableHeader().setBackground(colorCabecera);
        tblEquipo2.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<3;x++){
            tblEquipo2.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        jugadores.clear();
    }
    private void verJugadoresSancionadosEquipo1(ArrayList<Jugador> jugadores){
        equipo.VerjugadoresEquipoSancionados(file, lblEquipo1.getText());
        jugadores = equipo.getJugadoresSancionados();
        int costoGenerado;
        String matriz[][] = new String[jugadores.size()][7];
        for (int i = 0; i < jugadores.size(); i++) {
            matriz[i][0] = jugadores.get(i).getCedula();
            matriz[i][1] = jugadores.get(i).getNombre();
            matriz[i][2] = String.valueOf(jugadores.get(i).getTarjetasAmarillas());
            costoGenerado=10*jugadores.get(i).getTarjetasAmarillas()+50*jugadores.get(i).getTarjetasRojas();
            matriz[i][3] = String.valueOf(jugadores.get(i).getTarjetasRojas());
            matriz[i][4] = String.valueOf(costoGenerado);
            matriz[i][5] = String.valueOf(jugadores.get(i).getFechaAmonestacion().get(Calendar.YEAR)
                    +"-"+(jugadores.get(i).getFechaAmonestacion().get(Calendar.MONTH)+1)+"-"+
                    jugadores.get(i).getFechaAmonestacion().get(Calendar.DATE));
            matriz[i][6] = "PAGAR";
        }
        tblE1Sancionados.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"","JUGADOR","T.A","T.R.","CANTIDAD PAGAR","SUSPENSIÓN","REALIZAR PAGO"}));
        tblE1Sancionados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblE1Sancionados.getColumnModel().getColumn(0).setMinWidth(0);
        tblE1Sancionados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblE1Sancionados.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblE1Sancionados.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblE1Sancionados.getColumnModel().getColumn(3).setPreferredWidth(5);
        tblE1Sancionados.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblE1Sancionados.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblE1Sancionados.getColumnModel().getColumn(6).setPreferredWidth(50);
        
        tblE1Sancionados.getTableHeader().setOpaque(false);
        tblE1Sancionados.getTableHeader().setBackground(colorCabecera);
        tblE1Sancionados.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<7;x++){
            tblE1Sancionados.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        jugadores.clear();
    }
    
    private void verJugadoresSancionadosEquipo2(ArrayList<Jugador> jugadores){
        equipo.VerjugadoresEquipoSancionados(file, lblEquipo2.getText());
        jugadores = equipo.getJugadoresSancionados();
        int costoGenerado;
        String matriz[][] = new String[jugadores.size()][7];
        for (int i = 0; i < jugadores.size(); i++) {
           matriz[i][0] = jugadores.get(i).getCedula();
            matriz[i][1] = jugadores.get(i).getNombre();
            matriz[i][2] = String.valueOf(jugadores.get(i).getTarjetasAmarillas());
            costoGenerado=10*jugadores.get(i).getTarjetasAmarillas()+50*jugadores.get(i).getTarjetasRojas();
            matriz[i][3] = String.valueOf(jugadores.get(i).getTarjetasRojas());
            matriz[i][4] = String.valueOf(costoGenerado);
            matriz[i][5] = String.valueOf(jugadores.get(i).getFechaAmonestacion().get(Calendar.YEAR)
                    +"-"+(jugadores.get(i).getFechaAmonestacion().get(Calendar.MONTH)+1)+"-"+
                    jugadores.get(i).getFechaAmonestacion().get(Calendar.DATE));
            matriz[i][6] = "PAGAR";
        }
        tblE2Sancionados.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{"","JUGADOR","T.A","T.R.","CANTIDAD PAGAR","SUSPENSIÓN","REALIZAR PAGO"}));
        tblE2Sancionados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblE2Sancionados.getColumnModel().getColumn(0).setMinWidth(0);
        tblE2Sancionados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblE2Sancionados.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblE2Sancionados.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblE2Sancionados.getColumnModel().getColumn(3).setPreferredWidth(5);
        tblE2Sancionados.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblE2Sancionados.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblE2Sancionados.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblE2Sancionados.getTableHeader().setOpaque(false);
        tblE2Sancionados.getTableHeader().setBackground(colorCabecera);
        tblE2Sancionados.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<7;x++){
            tblE2Sancionados.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
        jugadores.clear();
    }
    private void verPagoArbitraje(){
        String matriz[][] = new String[1][2];
        matriz[0][0] = p.obtenerPagoArbitrajeEquipo1(filePartido,Temporal.getIdPartido());  
        matriz[0][1] = p.obtenerPagoArbitrajeEquipo2(filePartido,Temporal.getIdPartido());
        tblPagoArbitraje.setModel(new javax.swing.table.DefaultTableModel( matriz,new String []{Temporal.getEquipo1(),Temporal.getEquipo2()}));
        tblPagoArbitraje.getColumnModel().getColumn(0).setPreferredWidth(200);
        tblPagoArbitraje.getColumnModel().getColumn(1).setPreferredWidth(200);   
        tblPagoArbitraje.getTableHeader().setOpaque(false);
        tblPagoArbitraje.getTableHeader().setBackground(colorCabecera);
        tblPagoArbitraje.getTableHeader().setForeground(Color.WHITE);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int x=0;x<2;x++){
           tblPagoArbitraje.getColumnModel().getColumn(x).setCellRenderer( rightRenderer );
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEquipo1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEquipo2 = new javax.swing.JTable();
        lblEquipo1 = new javax.swing.JLabel();
        lblEquipo2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonFinalizarPartido = new javax.swing.JButton();
        jButtonInicioPartido = new javax.swing.JButton();
        jButtonPAE1 = new javax.swing.JButton();
        jButtonPAE2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPagoArbitraje = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblE2Sancionados = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblE1Sancionados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEquipo1.setBackground(new java.awt.Color(204, 255, 153));
        tblEquipo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblEquipo1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEquipo1.setGridColor(new java.awt.Color(255, 255, 255));
        tblEquipo1.setSelectionBackground(new java.awt.Color(153, 153, 0));
        jScrollPane1.setViewportView(tblEquipo1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 295, 264));

        tblEquipo2.setBackground(new java.awt.Color(204, 255, 153));
        tblEquipo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblEquipo2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEquipo2.setGridColor(new java.awt.Color(255, 255, 255));
        tblEquipo2.setSelectionBackground(new java.awt.Color(153, 153, 0));
        jScrollPane2.setViewportView(tblEquipo2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 310, 264));

        lblEquipo1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 36)); // NOI18N
        lblEquipo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo1.setText("EQUIPO 1 ");
        getContentPane().add(lblEquipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 370, -1));

        lblEquipo2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 36)); // NOI18N
        lblEquipo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEquipo2.setText("EQUIPO 2");
        getContentPane().add(lblEquipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 320, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 0));
        jButton1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("REGRESAR");
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 133, -1));

        jButtonFinalizarPartido.setBackground(new java.awt.Color(255, 153, 153));
        jButtonFinalizarPartido.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButtonFinalizarPartido.setText("FINALIZAR PARTIDO");
        jButtonFinalizarPartido.setHideActionText(true);
        jButtonFinalizarPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarPartidoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFinalizarPartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 150, -1));

        jButtonInicioPartido.setBackground(new java.awt.Color(153, 255, 153));
        jButtonInicioPartido.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButtonInicioPartido.setText("INICIAR PARTIDO");
        jButtonInicioPartido.setToolTipText("Botón que permite Iniciar el Partido");
        jButtonInicioPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioPartidoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonInicioPartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 150, -1));

        jButtonPAE1.setBackground(new java.awt.Color(0, 0, 51));
        jButtonPAE1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButtonPAE1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPAE1.setText("PAGAR ARBITRAJE");
        jButtonPAE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPAE1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPAE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        jButtonPAE2.setBackground(new java.awt.Color(0, 0, 51));
        jButtonPAE2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jButtonPAE2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPAE2.setText("PAGAR ARBITRAJE");
        jButtonPAE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPAE2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPAE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));

        tblPagoArbitraje.setBackground(new java.awt.Color(204, 255, 153));
        tblPagoArbitraje.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPagoArbitraje.setGridColor(new java.awt.Color(51, 51, 0));
        jScrollPane3.setViewportView(tblPagoArbitraje);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 319, 40));

        tblE2Sancionados.setBackground(new java.awt.Color(0, 0, 0));
        tblE2Sancionados.setForeground(new java.awt.Color(255, 255, 255));
        tblE2Sancionados.setModel(new javax.swing.table.DefaultTableModel(
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
        tblE2Sancionados.setGridColor(new java.awt.Color(51, 51, 0));
        jScrollPane4.setViewportView(tblE2Sancionados);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 560, 153));

        tblE1Sancionados.setBackground(new java.awt.Color(0, 0, 0));
        tblE1Sancionados.setForeground(new java.awt.Color(255, 255, 255));
        tblE1Sancionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "JUGADOR", "T AMAR", "T. ROJA", "DIAS DE S", "PAGOl"
            }
        ));
        tblE1Sancionados.setGridColor(new java.awt.Color(51, 51, 0));
        jScrollPane5.setViewportView(tblE1Sancionados);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 490, 153));

        jLabel2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jLabel2.setText("JUGADORES HABILITADOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 150, -1));

        jLabel4.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jLabel4.setText("PAGO DE ARBITRAJE DE 10$ POR EQUIPO:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 260, -1));

        jLabel5.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 18)); // NOI18N
        jLabel5.setText("JUGADORES HABILITADOS");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 150, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoP.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VentanaPartido v = new VentanaPartido();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonFinalizarPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarPartidoActionPerformed
        if(!p.verificarPagoArbitrajeE1(filePartido, Temporal.getIdPartido()) || 
          !equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo1())){
            if((p.verificarPagoArbitrajeE2(filePartido, Temporal.getIdPartido())
            &&equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo2()))){
            p.finalizarPartidoInasistencia(filePartido,Temporal.getIdPartido(),"E2");
            }else if((!p.verificarPagoArbitrajeE2(filePartido, Temporal.getIdPartido())
            || !equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo2()))){
            p.finalizarPartidoInasistencia(filePartido,Temporal.getIdPartido(),"");
            }      
        }else if(!p.verificarPagoArbitrajeE2(filePartido, Temporal.getIdPartido()) || 
                !equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo2())){
            if(p.verificarPagoArbitrajeE1(filePartido, Temporal.getIdPartido())
                &&equipo.verificarCantidadMinimaJugadores(file, Temporal.getEquipo1())){
            p.finalizarPartidoInasistencia(filePartido,Temporal.getIdPartido(),"E1");
            }
        }else{
            p.finalizarPartidoInasistencia(filePartido,Temporal.getIdPartido(),"");
        }
        VentanaPartido v = new VentanaPartido();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonFinalizarPartidoActionPerformed

    private void jButtonPAE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPAE1ActionPerformed
        p.pagarArbitrajeEquipo1(filePartido,Temporal.getIdPartido());
        verPagoArbitraje();
        jButtonPAE1.setEnabled(false);
        JOptionPane.showMessageDialog(null, "PAGO REALIZADO DE 10$\n ARBITRAJE DEL EQUIPO:\n"+Temporal.getEquipo1());
        validarInicioPartido();
    }//GEN-LAST:event_jButtonPAE1ActionPerformed

    private void jButtonPAE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPAE2ActionPerformed
        p.pagarArbitrajeEquipo2(filePartido,Temporal.getIdPartido());
        verPagoArbitraje();
        jButtonPAE2.setEnabled(false);
        JOptionPane.showMessageDialog(null, "PAGO REALIZADO DE 10$\n ARBITRAJE DEL EQUIPO:\n"+Temporal.getEquipo2());
        validarInicioPartido();
    }//GEN-LAST:event_jButtonPAE2ActionPerformed

    private void jButtonInicioPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioPartidoActionPerformed
        VentanaPartidoIniciado v = new VentanaPartidoIniciado();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonInicioPartidoActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaDetallePartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallePartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallePartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaDetallePartido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaDetallePartido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonFinalizarPartido;
    private javax.swing.JButton jButtonInicioPartido;
    private javax.swing.JButton jButtonPAE1;
    private javax.swing.JButton jButtonPAE2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEquipo1;
    private javax.swing.JLabel lblEquipo2;
    private javax.swing.JTable tblE1Sancionados;
    private javax.swing.JTable tblE2Sancionados;
    private javax.swing.JTable tblEquipo1;
    private javax.swing.JTable tblEquipo2;
    private javax.swing.JTable tblPagoArbitraje;
    // End of variables declaration//GEN-END:variables
    private void jTable1MouseClicked(MouseEvent evt) {                                     
     JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            int column = source.columnAtPoint( evt.getPoint() );
            String s=source.getModel().getValueAt(row, column)+"";

            JOptionPane.showMessageDialog(null, s);
    } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
        jugador =new Jugador();
        String nombreJugador;
        if(e.getComponent().equals(tblE1Sancionados)){
            int fila = tblE1Sancionados.rowAtPoint(e.getPoint());
            int columna = tblE1Sancionados.columnAtPoint(e.getPoint());
            if(columna==6){//PAGAR TARJETA JUGADOR
            String cedula = tblE1Sancionados.getValueAt(fila, 0).toString();
            nombreJugador = jugador.pagarTarjetas(file, cedula);
            JOptionPane.showMessageDialog(null, "El jugador "+nombreJugador+ " Ha pagado sus deudas"); 
            verJugadoresEquipo1(jugadores);
            verJugadoresSancionadosEquipo1(jugadores);
            } 
        }else if(e.getComponent().equals(tblE2Sancionados)){
            int fil = tblE2Sancionados.rowAtPoint(e.getPoint());
            int col = tblE2Sancionados.columnAtPoint(e.getPoint());
            if(col==6){//PAGAR TARJETA JUGADOR equipo2
            String cedu = tblE2Sancionados.getValueAt(fil, 0).toString();
            nombreJugador = jugador.pagarTarjetas(file, cedu);
            JOptionPane.showMessageDialog(null, "El jugador "+nombreJugador+ " Ha pagado sus deudas"); 
            verJugadoresEquipo2(jugadores);
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
