/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informesDeVisualizacion;

/**
 *
 * @author erikssonherlo
 */
public class ValidacionRetiros {
    private int noCuenta;
    private String nombre;
    private double monto;
    private double saldoCuenta;
    private int codigoCliente;

    public ValidacionRetiros(int noCuenta, String nombre, double monto, double saldoCuenta, int codigoCliente) {
        this.noCuenta = noCuenta;
        this.nombre = nombre;
        this.monto = monto;
        this.saldoCuenta = saldoCuenta;
        this.codigoCliente = codigoCliente;
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

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public int getCodigoCliente() {
        return codigoCliente;
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

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    
}
