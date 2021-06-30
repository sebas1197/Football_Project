package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import util.FileManager;

public class Partido {
    //private ArrayList<Jugador>jugadores;
    private int idPartido;
    private Calendar fechaPartido;
    private String equipo1;
    private String equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private String estadoGanadorEquipo1;
    private String estadoGanadorEquipo2;
    private String estadoPartido;
    private float PagoArbitrajeEquipo1;
    private float PagoArbitrajeEquipo2;
    private ArrayList<Partido> partido;
    
    public Partido(){
    }

    public Partido(int idPartido,String equipo1, String equipo2, int golesEquipo1, int golesEquipo2, String estadoGanadorEquipo1, String estadoGanadorEquipo2, String estadoPartido, float PagoArbitrajeEquipo1, float PagoArbitrajeEquipo2,Calendar fechaPartido) {
        this.idPartido = idPartido;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.estadoGanadorEquipo1 = estadoGanadorEquipo1;
        this.estadoGanadorEquipo2 = estadoGanadorEquipo2;
        this.estadoPartido = estadoPartido;
        this.PagoArbitrajeEquipo1 = PagoArbitrajeEquipo1;
        this.PagoArbitrajeEquipo2 = PagoArbitrajeEquipo2;
        this.fechaPartido = fechaPartido;
    }

    public Calendar getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(Calendar fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }


    public String getEstadoGanadorEquipo1() {
        return estadoGanadorEquipo1;
    }

    public void setEstadoGanadorEquipo1(String estadoGanadorEquipo1) {
        this.estadoGanadorEquipo1 = estadoGanadorEquipo1;
    }

    public String getEstadoGanadorEquipo2() {
        return estadoGanadorEquipo2;
    }

    public void setEstadoGanadorEquipo2(String estadoGanadorEquipo2) {
        this.estadoGanadorEquipo2 = estadoGanadorEquipo2;
    }

    public String getEstadoPartido() {
        return estadoPartido;
    }

    public void setEstadoPartido(String estadoPartido) {
        this.estadoPartido = estadoPartido;
    }

    public float getPagoArbitrajeEquipo1() {
        return PagoArbitrajeEquipo1;
    }

    public void setPagoArbitrajeEquipo1(int PagoArbitrajeEquipo1) {
        this.PagoArbitrajeEquipo1 = PagoArbitrajeEquipo1;
    }

    public float getPagoArbitrajeEquipo2() {
        return PagoArbitrajeEquipo2;
    }

    public void setPagoArbitrajeEquipo2(int PagoArbitrajeEquipo2) {
        this.PagoArbitrajeEquipo2 = PagoArbitrajeEquipo2;
    }
    
    public void crearPartido(File file,String equipo1,String equipo2){
        String fecha;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        fecha = year+"-"+(mes+1)+"-"+day;
        FileManager.savePartidoInFile(file, equipo1, equipo2,fecha);
    }
    
    public int asignarTarjetasAmarillas(String cedula,File file){
        ArrayList<Jugador> jugador = new ArrayList<>();
        int tarjetaAmarilla=0;
        FileManager.createArrayJugadorOfFile(jugador, file);
        for(int i=0;i<jugador.size();i++){
            if(jugador.get(i).getCedula().equals(cedula)){
                tarjetaAmarilla = jugador.get(i).getTarjetasAmarillas();
                jugador.get(i).setTarjetasAmarillas(++tarjetaAmarilla);
                break;
          }
        }
        FileManager.saveArrayJugadorInFile(file, jugador);
        jugador.clear();
        return tarjetaAmarilla;
    }
    
    public void modificarGolesPartido(int id,File file,int golEquipo1,int golEquipo2){
        partido = new ArrayList<>();
        String equipo1Resultado="PERDEDOR";
        String equipo2Resultado="PERDEDOR";
        FileManager.createArrayPartidoOfFile(partido, file);
        if(golEquipo1 > golEquipo2 ){
            equipo1Resultado="GANADOR";
        }else if(golEquipo2 > golEquipo1){
            equipo2Resultado="GANADOR";
        }else if(golEquipo2 == golEquipo1){
            equipo1Resultado="GANADOR";
            equipo2Resultado="GANADOR";
        }
        for (int i = 0; i < partido.size(); i++) {
            if(partido.get(i).getIdPartido()==id){
                partido.get(i).setGolesEquipo1(golEquipo1);
                partido.get(i).setGolesEquipo2(golEquipo2);
                partido.get(i).setEstadoPartido("FINALIZADO");
                partido.get(i).setEstadoGanadorEquipo1(equipo1Resultado);
                partido.get(i).setEstadoGanadorEquipo2(equipo2Resultado);
            }
        }
        FileManager.saveArrayPartidoInFile(file,partido);
        partido.clear();
    }
    public boolean verificarPagoArbitraje(File file,int idPartido){
        partido = new ArrayList<>();
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartido){
                if(partido.get(i).getPagoArbitrajeEquipo1()==0 || partido.get(i).getPagoArbitrajeEquipo2()==0)
                    return false;
            }
        }
        partido.clear();
        return true;
    }
    
    public String obtenerPagoArbitrajeEquipo1(File file,int idPartido){
        partido = new ArrayList<>();
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartido){
                if(partido.get(i).getPagoArbitrajeEquipo1()==0)
                    return "NO PAGADO";
            }
        }
        partido.clear();
        return "PAGADO";
    }
    
    public String obtenerPagoArbitrajeEquipo2(File file,int idPartido){
        partido = new ArrayList<>();
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartido){
                if(partido.get(i).getPagoArbitrajeEquipo2()==0)
                    return "NO PAGADO";
            }
        }
        partido.clear();
        return "PAGADO";
    }
    
    public void pagarArbitrajeEquipo1(File file,int idPartido){
        partido = new ArrayList<>();
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartido){
                partido.get(i).setPagoArbitrajeEquipo1(10);
            }
        }
        FileManager.saveArrayPartidoInFile(file, partido);
        partido.clear();
    }
    
     public void pagarArbitrajeEquipo2(File file,int idPartido){
        partido = new ArrayList<>();
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartido){
                partido.get(i).setPagoArbitrajeEquipo2(10);
            }
        }
        FileManager.saveArrayPartidoInFile(file, partido);
        partido.clear();
    }
     
     public boolean verificarPagoArbitrajeE1(File file, int idPartid){
        partido = new ArrayList<>();
        boolean checkPago=true;
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartid){
                if(partido.get(i).getPagoArbitrajeEquipo1()==0){
                    checkPago=false;
                    break;
                }
            }
        }
        partido.clear();
        return checkPago;
     }
     
     public boolean verificarPagoArbitrajeE2(File file, int idPartid){
        partido = new ArrayList<>();
        boolean checkPago=true;
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartid){
                if(partido.get(i).getPagoArbitrajeEquipo2()==0){
                    checkPago=false;
//                    break;
                }
            }
        }
        partido.clear();
        return checkPago;
     }
     
     
     public void finalizarPartidoInasistencia(File file, int idPartid,String equipoG){
        partido = new ArrayList<>();
        FileManager.createArrayPartidoOfFile(partido, file);
        for(int i=0;i<partido.size();i++){
            if(partido.get(i).getIdPartido()== idPartid){
                partido.get(i).setEstadoPartido("FINALIZADO");
                if(equipoG.equals("E1")){
                    partido.get(i).setEstadoGanadorEquipo1("GANADOR");
                }else if(equipoG.equals("E2")){
                    partido.get(i).setEstadoGanadorEquipo2("GANADOR");
                }
                break;
            }
        }
        FileManager.saveArrayPartidoInFile(file, partido);
        partido.clear();
     }
}