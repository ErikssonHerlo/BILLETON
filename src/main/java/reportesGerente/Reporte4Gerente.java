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
public class Reporte4Gerente {
    private int codigoCliente;
    private String nombreCliente;
    private int numeroCuenta;
    private double saldoCuenta;
    private String fechaCreacion;

    public Reporte4Gerente(int codigoCliente, String nombreCliente, int numeroCuenta, double saldoCuenta, String fechaCreacion) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.saldoCuenta = saldoCuenta;
        this.fechaCreacion = fechaCreacion;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}
