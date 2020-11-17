/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informesDeVisualizacion;

import java.io.Serializable;

/**
 *
 * @author erikssonherlo
 */
public class InformeTransacciones implements Serializable {
      private int codigo;
    private int noCuenta;
    private String nombre;
    private double monto;
    private String fecha;
    private String hora;
    private String tipo;
    private int codigoCajero;

    /**
     * INFORME DE DEPOSITO REALIZADO
     * @param codigo
     * @param noCuenta
     * @param nombre
     * @param monto
     * @param fecha
     * @param hora
     * @param tipo
     * @param codigoCajero 
     */
    public InformeTransacciones(int codigo, int noCuenta, String nombre, double monto, String fecha, String hora, String tipo, int codigoCajero) {
        this.codigo = codigo;
        this.noCuenta = noCuenta;
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.codigoCajero = codigoCajero;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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

    public void setCodigoCajero(int codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMonto() {
        return monto;
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

    public int getCodigoCajero() {
        return codigoCajero;
    }




}
