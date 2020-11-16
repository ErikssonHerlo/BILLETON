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
public class Cuenta implements Serializable {
    private int noCuenta;
    private String fechaCreacion;
    private double saldo;
    private int codigoCliente;

    /**
     * Clase Cuenta, que contendra todos los datos de una Cuenta perteneciente a un Cliente creada por un
     * Gerente o desde la carga de Archivos
     * @param noCuenta
     * @param fechaCreacion
     * @param saldo
     * @param codigoCliente 
     */
    public Cuenta(int noCuenta, String fechaCreacion, double saldo, int codigoCliente) {
        this.noCuenta = noCuenta;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
        this.codigoCliente = codigoCliente;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    
}
