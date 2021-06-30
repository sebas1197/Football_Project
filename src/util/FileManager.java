package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.Equipo;
import model.Jugador;
import model.Partido;

public class FileManager {
   public static void saveJugadorInFile(File file, Jugador jugador) {
        String letter = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                letter = jugador.getNombreEquipo()+";"+jugador.getCedula()+ ";"
                        + jugador.getNombre()+ ";"+jugador.getFechaNacimiento() 
                        + ";" + jugador.getNumeroJugador()+";"+jugador.getPosicionJugador()+
                        ";"+jugador.getFoto()+";"+jugador.getTarjetasAmarillas()+
                        ";"+jugador.getTarjetasRojas()+";"+jugador.getJugadorHabilitado()+
                        ";"+jugador.getFechaAmonestacion().get(Calendar.YEAR)+"-"
                        +(jugador.getFechaAmonestacion().get(Calendar.MONTH)+1)+"-"
                        +(jugador.getFechaAmonestacion().get(Calendar.DATE));
                bw.write(letter);
                bw.newLine();
                
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveArrayJugadorInFile(File file, ArrayList<Jugador> jugador) {
        String letter = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < jugador.size(); i++) {
                letter = jugador.get(i).getNombreEquipo()+";"+jugador.get(i).getCedula()+ ";"
                        + jugador.get(i).getNombre()+ ";"+jugador.get(i).getFechaNacimiento() 
                        + ";" + jugador.get(i).getNumeroJugador()+";"+jugador.get(i).getPosicionJugador()+
                        ";"+jugador.get(i).getFoto()+";"+jugador.get(i).getTarjetasAmarillas()+
                        ";"+jugador.get(i).getTarjetasRojas()+";"+jugador.get(i).getJugadorHabilitado()+
                        ";"+jugador.get(i).getFechaAmonestacion().get(Calendar.YEAR)+"-"
                        +(jugador.get(i).getFechaAmonestacion().get(Calendar.MONTH)+1)+"-"
                        +(jugador.get(i).getFechaAmonestacion().get(Calendar.DATE));
                bw.write(letter);
                bw.newLine();
                }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Jugador> createArrayJugadorOfFile(ArrayList<Jugador> jugador, File file) {
        String equipo,cedula,nombre,posicion,foto,habilitado;
        Date FechaN;
        
        int numeroCamiseta,tarjetasRojas,tarjetasAmarillas;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        long d;
        ArrayList<Calendar> dates = new ArrayList<>();
        java.sql.Date fecha;
        java.sql.Date fechaAmonestacion;
        try {
            String read;
            try (FileReader archedad = new FileReader(file)) {
                BufferedReader br = new BufferedReader(archedad);
                read = br.readLine();
                while ((read != null)) {
                    if (read.contains(";")) {
                        equipo = read.split(";")[0];
                        cedula = read.split(";")[1];
                        nombre = read.split(";")[2];
                        FechaN = formato.parse(read.split(";")[3]);
                        d=FechaN.getTime();
                        fecha =new java.sql.Date(d);
                        
                        numeroCamiseta = Integer.parseInt(read.split(";")[4]);
                        posicion = read.split(";")[5];
                        foto = read.split(";")[6];
                        tarjetasAmarillas = Integer.parseInt(read.split(";")[7]);
                        tarjetasRojas = Integer.parseInt(read.split(";")[8]);
                        habilitado = read.split(";")[9];
                        FechaN = formato.parse(read.split(";")[10]);
                        d=FechaN.getTime();
                        fechaAmonestacion = new java.sql.Date(d);
                        dates.add( Calendar.getInstance() );
                        dates.get(dates.size()-1).set(fechaAmonestacion.getYear()+1900,fechaAmonestacion.getMonth(),fechaAmonestacion.getDate());
                        jugador.add(new Jugador(cedula, nombre, fecha, equipo, numeroCamiseta, posicion, foto, tarjetasAmarillas, tarjetasRojas,habilitado,dates.get(dates.size()-1)));
                    }
                    read = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un Error");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
	return jugador;
	}
    
    public static void saveEquipoInFile(File file, Equipo equipo) {
        String letter = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                letter = equipo.getNombreEquipo()+ ";" + equipo.getPersonaResponsable();
                bw.write(letter);
                bw.newLine();
                
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }
    
     public static ArrayList<Equipo> createArrayEquipoOfFile(ArrayList<Equipo> equipo, File file) {
        String nombre;
        String personaResponsable;
        try {
            String read;
            try (FileReader archedad = new FileReader(file)) {
                BufferedReader br = new BufferedReader(archedad);
                read = br.readLine();
                while ((read != null)) {
                    if (read.contains(";")) {
                        nombre = read.split(";")[0];
                        personaResponsable = read.split(";")[1];
                        equipo.add(new Equipo(nombre, personaResponsable));
                    }
                    read = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un Error al crear el Array Equipo");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
	return equipo;
	}
    
    public static void saveArrayEquipoInFile(File file, ArrayList<Equipo> equipo) {
        String letter = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < equipo.size(); i++) {
                letter = equipo.get(i).getNombreEquipo()+ ";" + equipo.get(i).getPersonaResponsable();
                bw.write(letter);
                bw.newLine();
                }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void savePartidoInFile(File file,String equipo1,String equipo2,String fechaPartido) {
        String letter = "";
        int idPartido=0;
        idPartido=obtenerIdPartido(idPartido); 
        idPartido++;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                letter = idPartido+";"+fechaPartido+";"+equipo1+ ";" + equipo2+";0;0;NO INICIADO;PERDEDOR;PERDEDOR;0;0";
                bw.write(letter);
                bw.newLine();
                
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }
    //BUscar algo que lea solo el utlimo
    public static int obtenerIdPartido(int idPartido){
        File file = new File("Partidos.txt");
        try {
            String read;
            try (FileReader archedad = new FileReader(file)) {
                BufferedReader br = new BufferedReader(archedad);
                read = br.readLine();
                while ((read != null)) {
                    if (read.contains(";")) {
                        idPartido =Integer.parseInt(read.split(";")[0]);
                    }
                    read = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer El idPartido");
        }
        return idPartido;
    }
    
    public static ArrayList<Partido> createArrayPartidoOfFile(ArrayList<Partido> partido, File file) {
        String equipo1,equipo2,estadoPartido,estadoGanadorEquipo1,estadoGanadorEquipo2;
        int golesEquipo1,golesEquipo2;
        float PagoArbitrajeEquipo1;
        float PagoArbitrajeEquipo2;
        Date FechaN;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        long d;
        java.sql.Date fecha;
        ArrayList<Calendar> dates = new ArrayList<>(); // initial size
        int idPartido;
        try {
            String read;
            try (FileReader archedad = new FileReader(file)) {
                BufferedReader br = new BufferedReader(archedad);
                read = br.readLine();
                while ((read != null)) {
                    if (read.contains(";")) {
                        idPartido = Integer.parseInt(read.split(";")[0]);
                        FechaN = formato.parse(read.split(";")[1]);
                        d=FechaN.getTime();
                        fecha =new java.sql.Date(d);
                        dates.add( Calendar.getInstance() );
                        dates.get(dates.size()-1).set(fecha.getYear()+1900, fecha.getMonth(),fecha.getDate());
        
                        equipo1 = read.split(";")[2];
                        equipo2 = read.split(";")[3];
                        golesEquipo1 = Integer.parseInt(read.split(";")[4]);
                        golesEquipo2 = Integer.parseInt(read.split(";")[5]);
                        estadoPartido = read.split(";")[6];
                        estadoGanadorEquipo1 = read.split(";")[7];
                        estadoGanadorEquipo2 = read.split(";")[8];
                        PagoArbitrajeEquipo1 = Float.parseFloat(read.split(";")[9]);
                        PagoArbitrajeEquipo2 = Float.parseFloat(read.split(";")[10]);
                        partido.add(new Partido(idPartido,equipo1, equipo2, golesEquipo1, golesEquipo2, estadoGanadorEquipo1, estadoGanadorEquipo2, estadoPartido, PagoArbitrajeEquipo1, PagoArbitrajeEquipo2,dates.get(dates.size()-1)));
                    }
                    read = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un Error al crear el Array Equipo");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
	return partido;
	}
        
        public static void saveArrayPartidoInFile(File file, ArrayList<Partido> partido) {
        String letter = "";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < partido.size(); i++) {
                letter = partido.get(i).getIdPartido()+";"
                    +partido.get(i).getFechaPartido().get(Calendar.YEAR)+ "-"+
                    (partido.get(i).getFechaPartido().get(Calendar.MONTH)+1)+"-"+
                    partido.get(i).getFechaPartido().get(Calendar.DATE)+";"+
                    partido.get(i).getEquipo1()+";"+partido.get(i).getEquipo2()+";"+
                    partido.get(i).getGolesEquipo1()+";"+partido.get(i).getGolesEquipo2()+";"+
                    partido.get(i).getEstadoPartido()+";"+partido.get(i).getEstadoGanadorEquipo1()+";"+
                    partido.get(i).getEstadoGanadorEquipo2()+";"+partido.get(i).getPagoArbitrajeEquipo1()+";"+
                    partido.get(i).getPagoArbitrajeEquipo2();
                bw.write(letter);
                bw.newLine();
                }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }
        
        
     /*   public static void saveJugadoresPartidoInFile(File file, Jugador jugador,String idPartido) {
        String letter = "";
        try {//idPartido,Jugador,CostoPago
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                letter = idPartido +";"+jugador.getCedula()+ ";"+
                        jugador.getPagoTarjeta();
                bw.write(letter);
                bw.newLine();
                
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
    }*/
        /*public static ArrayList<Partido> createArrayJugadoresPartidoOfFile(ArrayList<Partido> partido, File file) {
        String equipo1,equipo2,estadoPartido,estadoGanadorEquipo1,estadoGanadorEquipo2;
        int golesEquipo1,golesEquipo2;
        float PagoArbitrajeEquipo1;
        float PagoArbitrajeEquipo2;
        Date FechaN;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        long d;
        java.sql.Date fecha;
        int idPartido;
        Calendar c = Calendar.getInstance();
            System.out.println("Ingreso al metodo que no se");
        try {
            String read;
            try (FileReader archedad = new FileReader(file)) {
                BufferedReader br = new BufferedReader(archedad);
                read = br.readLine();
                while ((read != null)) {
                    if (read.contains(";")) {
                        idPartido = Integer.parseInt(read.split(";")[0]);
                        FechaN = formato.parse(read.split(";")[1]);
                        d=FechaN.getTime();
                        fecha =new java.sql.Date(d);
                        c.set(fecha.getYear()+1900, fecha.getMonth(),fecha.getDate());
                        equipo1 = read.split(";")[2];
                        equipo2 = read.split(";")[3];
                        golesEquipo1 = Integer.parseInt(read.split(";")[4]);
                        golesEquipo2 = Integer.parseInt(read.split(";")[5]);
                        estadoPartido = read.split(";")[6];
                        estadoGanadorEquipo1 = read.split(";")[7];
                        estadoGanadorEquipo2 = read.split(";")[8];
                        PagoArbitrajeEquipo1 = Float.parseFloat(read.split(";")[9]);
                        PagoArbitrajeEquipo2 = Float.parseFloat(read.split(";")[10]);
                        partido.add(new Partido(idPartido,equipo1, equipo2, golesEquipo1, golesEquipo2, estadoGanadorEquipo1, estadoGanadorEquipo2, estadoPartido, PagoArbitrajeEquipo1, PagoArbitrajeEquipo2,c));
                    }
                    read = br.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un Error al crear el Array Equipo");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
	return partido;
	}*/
}
