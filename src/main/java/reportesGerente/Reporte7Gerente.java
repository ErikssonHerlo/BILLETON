/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesGerente;

/**
 *
 * @author erikssonherlo
 */
public class Reporte7Gerente {
    private int codigoCajero;
    private String nombreCajero;
    private String turno;
    private String horaEntrada;
    private String horaSalida;
    private int cantidad;

    public Reporte7Gerente(int codigoCajero, String nombreCajero, String turno, String horaEntrada, String horaSalida, int cantidad) {
        this.codigoCajero = codigoCajero;
        this.nombreCajero = nombreCajero;
        this.turno = turno;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cantidad = cantidad;
    }

    public int getCodigoCajero() {
        return codigoCajero;
    }

    public String getNombreCajero() {
        return nombreCajero;
    }

    public String getTurno() {
        return turno;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCodigoCajero(int codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
