/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;

/**
 *
 * @author erikssonherlo
 */
public class Cajero extends Usuario implements Serializable {
    private String turno;
    private String horaEntrada;
    private String horaSalida;
    private boolean estado;
/**
 * Clase Hija de la Clase Usuario, comparte los atributos Generales:
 * @param codigo
 * @param nombre
 * @param DPI
 * @param direccion
 * @param sexo
 * @param password
 * @param tipoUsuario = 2
 * Atributos Unicos de la Clase Cajero:
 * @param turno
 * @param horaEntrada
 * @param horaSalida
 * @param estado 
 */
    public Cajero(int codigo, String nombre, String DPI, String direccion, String sexo, String password, int tipoUsuario, String turno, String horaEntrada, String horaSalida, boolean estado) {
        super(codigo, nombre, DPI, direccion, sexo, password, tipoUsuario);
        this.turno = turno;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
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

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
