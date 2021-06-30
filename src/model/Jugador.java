package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import util.FileManager;

public class Jugador{
    private String cedula;
    private String nombre;
    private Date fechaNacimiento;
    private String nombreEquipo;
    private String posicionJugador;
    private int numeroJugador;
    private String foto;
    private int tarjetasAmarillas;
    private int tarjetasRojas;
    private Calendar fechaAmonestacion;
    private float pagoTarjetas;
    private String jugadorHabilitado;
    
    public Jugador(){
        
    }
    
    public Jugador(String cedula, String nombre, Date fechaNacimiento,
            String nombreEquipo,int numeroJugador,String posicionJugador, 
            String foto, int tarjetasAmarillas, int tarjetasRojas,String jugadorHabilitado,Calendar fechaAmonestacion) {
        //super(cedula, nombre, fechaNacimiento);
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;this.nombreEquipo = nombreEquipo;
        this.posicionJugador = posicionJugador;
        this.numeroJugador = numeroJugador;
        this.foto = foto;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
        this.jugadorHabilitado = jugadorHabilitado;
        this.fechaAmonestacion = fechaAmonestacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    /*public Jugador(String cedula, String nombre, Date fechaNacimiento,float pagoTarjetas,Calendar fechafechaAmonestacion) {
        //super(cedula, nombre, fechaNacimiento);
        this.fechaAmonestacion= fechaAmonestacion;
        this.pagoTarjetas = pagoTarjetas;
    }*/

    public Calendar getFechaAmonestacion() {
        return fechaAmonestacion;
    }

    public void setFechaAmonestacion(Calendar fechaAmonestacion) {
        this.fechaAmonestacion = fechaAmonestacion;
    }

    public String getJugadorHabilitado() {
        return jugadorHabilitado;
    }

    public void setJugadorHabilitado(String jugadorHabilitado) {
        this.jugadorHabilitado = jugadorHabilitado;
    }
    
    
    public float getPagoTarjetas() {
        return pagoTarjetas;
    }

    public void setPagoTarjetas(float pagoTarjetas) {
        this.pagoTarjetas = pagoTarjetas;
    }
    
    public String getPosicionJugador() {
        return posicionJugador;
    }

    public void setPosicionJugador(String posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    public int getNumeroJugador() {
        return numeroJugador;
    }

    public void setNumeroJugador(int numeroJugador) {
        this.numeroJugador = numeroJugador;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public float getPagoTarjeta() {
        return pagoTarjetas;
    }

    public void setPagoTarjeta(float pagoTarjetas) {
        this.pagoTarjetas = pagoTarjetas;
    }
    
    public void registrarJugador(File file,String txtCedula,String txtNombre,Date date,
                String equipo,int numeroCamiseta,String posicion,String foto,int tarjAmarilla,int tarjRoja){
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        Calendar fechaA =Calendar.getInstance();
        Jugador jugador = new Jugador(txtCedula,txtNombre, fecha,equipo, numeroCamiseta,posicion, foto,
                tarjAmarilla, tarjRoja,"H", fechaA);
        FileManager.saveJugadorInFile(file,jugador);
    }
    
    public void eliminarJugador(File file,String txtCedula){
        ArrayList<Jugador> jugadores = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(jugadores, file);
        for (int i = 0; i <jugadores.size() ; i++) {
            if(txtCedula.equals(jugadores.get(i).getCedula())){
                jugadores.remove(i);
                break;
            }
        }
        FileManager.saveArrayJugadorInFile(file,jugadores);
        jugadores.clear();
    }
    
    public void editarJugador(File file,String txtCedula,String nombreJ,String posicion,int camiseta,Date date){
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(jugadores, file);
        for (int i = 0; i <jugadores.size() ; i++) {
            if(txtCedula.equals(jugadores.get(i).getCedula())){
                jugadores.get(i).setNombre(nombreJ);
                jugadores.get(i).setPosicionJugador(posicion);
                jugadores.get(i).setNumeroJugador(camiseta);
                jugadores.get(i).setFechaNacimiento(fecha);
                break;
            }
        }
        FileManager.saveArrayJugadorInFile(file, jugadores);
        jugadores.clear();
    }
    
    public void obtenerDatos(File file,String cedula){
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getCedula().equals(cedula)){
               this.nombreEquipo = auxiliar.get(i).getNombreEquipo(); 
               this.nombre = auxiliar.get(i).getNombre();
               this.fechaNacimiento = auxiliar.get(i).getFechaNacimiento();
               this.posicionJugador = auxiliar.get(i).getPosicionJugador();
               this.numeroJugador = auxiliar.get(i).getNumeroJugador();
            }
        }
    }
    
    public boolean validarJugadorRegistrado(File file,String cedulajugador){
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getCedula().equals(cedulajugador)){
                return false;
            }
        }
        return true;
    }
    
     public boolean isValidCi(String cedula){
        int[] vector;
        int aux=0,restar=0,superior;        
        vector=new int[10];  
        if(cedula.length()==10){
        for(int i=0;i<9;i++){
            vector[i]=Integer.parseInt(Character.toString(cedula.charAt(i)));            
            if(i%2==0){
                vector[i]=vector[i]*2;
                if(vector[i]>=10){
                    vector[i]=vector[i]-9;
                }
            }
            aux+=vector[i];             
        }
        superior=((aux/10)*10)+10;
        restar=superior-aux; 
        if(restar>=10)
            restar=restar-10;
       
        if(restar==Integer.parseInt(Character.toString(cedula.charAt(9)))){
            return true;            
            }
        }
        return false;
    }
    
    public void asignarDiasSancion(File file,String cedulajugador,String colorTarjeta){
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        Calendar calendar = Calendar.getInstance();
        int diasSancion;
        if(colorTarjeta.equals("TA"))
            diasSancion = 8;
        else
            diasSancion = 30;
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getCedula().equals(cedulajugador)){
                calendar = auxiliar.get(i).getFechaAmonestacion();
                calendar.add(Calendar.DAY_OF_YEAR,diasSancion);
                auxiliar.get(i).setFechaAmonestacion(calendar);
                System.out.println(calendar);
            }
        }
        FileManager.saveArrayJugadorInFile(file, auxiliar);
        auxiliar.clear();
    }
    
    public void asignarTarjetaRoja(File file,String cedulajugador){
        ArrayList<Jugador> jugador = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(jugador, file);
        for(int i=0;i<jugador.size();i++){
            if(jugador.get(i).getCedula().equals(cedulajugador)){
                jugador.get(i).setTarjetasRojas(1);
                break;
          }
        }
        FileManager.saveArrayJugadorInFile(file, jugador);
        jugador.clear();
    }
    
    public String pagarTarjetas(File file,String cedulaJugador){
        String nombreJugador="";
        ArrayList<Jugador> jugador = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(jugador, file);
        for(int i=0;i<jugador.size();i++){
            if(jugador.get(i).getCedula().equals(cedulaJugador)){
                nombreJugador =jugador.get(i).getNombre();
                jugador.get(i).setTarjetasAmarillas(0);
                jugador.get(i).setTarjetasRojas(0);
                break;
          }
        }
        FileManager.saveArrayJugadorInFile(file, jugador);
        jugador.clear();
        return nombreJugador;
    }
}
