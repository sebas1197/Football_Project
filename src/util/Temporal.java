package util;

public class Temporal {
    public static String texto ="";
    public static String equipo1 ="";
    public static String equipo2 ="";
    public static int idPartido;
    public static String cedulaJugador;
    public static String estadoPatido;

    public static String getEstadoPatido() {
        return estadoPatido;
    }

    public static void setEstadoPatido(String estadoPatido) {
        Temporal.estadoPatido = estadoPatido;
    }
    
    public static String getCedulaJugador() {
        return cedulaJugador;
    }

    public static void setCedulaJugador(String cedulaJugador) {
        Temporal.cedulaJugador = cedulaJugador;
    }

    public static int getIdPartido() {
        return idPartido;
    }

    public static void setIdPartido(int idPartido) {
        Temporal.idPartido = idPartido;
    }

    public static String getEquipo1() {
        return equipo1;
    }

    public static void setEquipo1(String equipo1) {
        Temporal.equipo1 = equipo1;
    }

    public static String getEquipo2() {
        return equipo2;
    }

    public static void setEquipo2(String equipo2) {
        Temporal.equipo2 = equipo2;
    }
    
    public static String getTexto() {
        return texto;
    }

    public static void setTexto(String texto) {
        Temporal.texto = texto;
    }
    
}
