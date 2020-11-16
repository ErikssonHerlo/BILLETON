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
public class Transaccion implements Serializable{
    private int codigo;
    private int noCuenta;
    private String fecha;
    private String hora;
    private String tipo;
    private double monto;
    private int codigoCajero;
/**
 * Clase Transaccion, que contendra todos los datos de una Transaccion perteneciente a un Cliente Realizada por un
 * Cajero o desde la carga de Archivos
 * @param codigo
 * @param noCuenta
 * @param fecha
 * @param hora
 * @param tipo: Credito o Debito
 * @param monto
 * @param codigoCajero 
 */
    public Transaccion(int codigo, int noCuenta, String fecha, String hora, String tipo, double monto, int codigoCajero) {
        this.codigo = codigo;
        this.noCuenta = noCuenta;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.codigoCajero = codigoCajero;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public int getCodigoCajero() {
        return codigoCajero;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setCodigoCajero(int codigoCajero) {
        this.codigoCajero = codigoCajero;
    }
    
    
}
