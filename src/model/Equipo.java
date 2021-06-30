
package model;

import java.io.File;
import java.util.ArrayList;
import util.FileManager;

public class Equipo {
    private String nombreEquipo;
    private String personaResponsable;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Jugador> jugadoresNoSancionados;
    private ArrayList<Jugador> jugadoresSancionados;
    
    public Equipo(){   
    }
    public Equipo(String nombreEquipo, String personaResponsable) {
        this.nombreEquipo = nombreEquipo;
        this.personaResponsable = personaResponsable;
    }

    public ArrayList<Jugador> getJugadoresNoSancionados() {
        return jugadoresNoSancionados;
    }

    public void setJugadoresNoSancionados(ArrayList<Jugador> jugadoresNoSancionados) {
        this.jugadoresNoSancionados = jugadoresNoSancionados;
    }

    public ArrayList<Jugador> getJugadoresSancionados() {
        return jugadoresSancionados;
    }

    public void setJugadoresSancionados(ArrayList<Jugador> jugadoresSancionados) {
        this.jugadoresSancionados = jugadoresSancionados;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPersonaResponsable() {
        return personaResponsable;
    }

    public void setPersonaResponsable(String personaResponsable) {
        this.personaResponsable = personaResponsable;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugador) {
        this.jugadores = jugador;
    }
    
    public void registarEquipo(File file,String nombreEquipo,String persResponsable){
        Equipo equipo = new Equipo(nombreEquipo,persResponsable);
        FileManager.saveEquipoInFile(file, equipo);
        
    }
    
    public boolean validarRegistroEquipo(File file,String nombreEquipo){
        ArrayList<Equipo> equipos = new ArrayList<>();
        FileManager.createArrayEquipoOfFile(equipos, file);
        for(int i=0;i<equipos.size();i++){
            if(equipos.get(i).getNombreEquipo().equals(nombreEquipo)){
                return false;
            }
        }
        return true;
    }
    
    public void eliminarJugadoresEquipo(File file,String nombreE){
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreE)){
                auxiliar.remove(i);
            }
        }
        FileManager.saveArrayJugadorInFile(file, auxiliar);
        auxiliar.clear();
    }
    
    public void jugadoresEquipo(File file,String nombreEquipo){
        jugadores = new ArrayList<>();
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreEquipo)){
                jugadores.add(new Jugador(auxiliar.get(i).getCedula(),
                auxiliar.get(i).getNombre(),auxiliar.get(i).getFechaNacimiento(),
                auxiliar.get(i).getNombreEquipo(),auxiliar.get(i).getNumeroJugador(),
                auxiliar.get(i).getPosicionJugador(),auxiliar.get(i).getFoto(),
                auxiliar.get(i).getTarjetasAmarillas(),auxiliar.get(i).getTarjetasRojas(),
                auxiliar.get(i).getJugadorHabilitado(),auxiliar.get(i).getFechaAmonestacion()));
            }
        }
    }
    
    public void jugadoresEquipoNoSancionados(File file,String nombreEquipo){
        jugadoresNoSancionados = new ArrayList<>();
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreEquipo) && 
                    auxiliar.get(i).getTarjetasAmarillas()!= 2 && auxiliar.get(i).getTarjetasRojas()!= 1){
                jugadoresNoSancionados.add(new Jugador(auxiliar.get(i).getCedula(),
                auxiliar.get(i).getNombre(),auxiliar.get(i).getFechaNacimiento(),
                auxiliar.get(i).getNombreEquipo(),auxiliar.get(i).getNumeroJugador(),
                auxiliar.get(i).getPosicionJugador(),auxiliar.get(i).getFoto(),
                auxiliar.get(i).getTarjetasAmarillas(),auxiliar.get(i).getTarjetasRojas(),
                auxiliar.get(i).getJugadorHabilitado(),auxiliar.get(i).getFechaAmonestacion()));
            }
        }
    }
    
     public void jugadoresEquipoNoDeben(File file,String nombreEquipo){
        jugadoresNoSancionados = new ArrayList<>();
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreEquipo) && 
                    auxiliar.get(i).getTarjetasAmarillas()== 0 && auxiliar.get(i).getTarjetasRojas()== 0){
                jugadoresNoSancionados.add(new Jugador(auxiliar.get(i).getCedula(),
                auxiliar.get(i).getNombre(),auxiliar.get(i).getFechaNacimiento(),
                auxiliar.get(i).getNombreEquipo(),auxiliar.get(i).getNumeroJugador(),
                auxiliar.get(i).getPosicionJugador(),auxiliar.get(i).getFoto(),
                auxiliar.get(i).getTarjetasAmarillas(),auxiliar.get(i).getTarjetasRojas(),
                auxiliar.get(i).getJugadorHabilitado(),auxiliar.get(i).getFechaAmonestacion()));
            }
        }
    }
     
    public void jugadoresEquipoSancionados(File file,String nombreEquipo){
        jugadoresSancionados = new ArrayList<>();
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreEquipo) && 
            auxiliar.get(i).getJugadorHabilitado().equals("H")&&
            (auxiliar.get(i).getTarjetasAmarillas()!= 0 || auxiliar.get(i).getTarjetasRojas()!= 0)){
                jugadoresSancionados.add(new Jugador(auxiliar.get(i).getCedula(),
                auxiliar.get(i).getNombre(),auxiliar.get(i).getFechaNacimiento(),
                auxiliar.get(i).getNombreEquipo(),auxiliar.get(i).getNumeroJugador(),
                auxiliar.get(i).getPosicionJugador(),auxiliar.get(i).getFoto(),
                auxiliar.get(i).getTarjetasAmarillas(),auxiliar.get(i).getTarjetasRojas(),
                auxiliar.get(i).getJugadorHabilitado(),auxiliar.get(i).getFechaAmonestacion()));
            }
        }
    }
    
    public void VerjugadoresEquipoSancionados(File file,String nombreEquipo){
        jugadoresSancionados = new ArrayList<>();
        
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreEquipo) && 
                    (auxiliar.get(i).getTarjetasAmarillas()!= 0 || auxiliar.get(i).getTarjetasRojas()!= 0)){
                jugadoresSancionados.add(new Jugador(auxiliar.get(i).getCedula(),
                auxiliar.get(i).getNombre(),auxiliar.get(i).getFechaNacimiento(),
                auxiliar.get(i).getNombreEquipo(),auxiliar.get(i).getNumeroJugador(),
                auxiliar.get(i).getPosicionJugador(),auxiliar.get(i).getFoto(),
                auxiliar.get(i).getTarjetasAmarillas(),auxiliar.get(i).getTarjetasRojas(),
                auxiliar.get(i).getJugadorHabilitado(),auxiliar.get(i).getFechaAmonestacion()));
            }
        }
    }
    
    public boolean verificarCantidadMinimaJugadores(File file,String nombreEquipo){
        int cont=0;
        ArrayList<Jugador> auxiliar = new ArrayList<>();
        FileManager.createArrayJugadorOfFile(auxiliar, file);;
        for(int i=0;i<auxiliar.size();i++){
            if(auxiliar.get(i).getNombreEquipo().equals(nombreEquipo) && 
                auxiliar.get(i).getTarjetasAmarillas()!= 2 && auxiliar.get(i).getTarjetasRojas()!= 1){
                    cont++;
            }
        }
        if(cont <7)
            return false;
        else
            return true;
    }
}
