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
public class ValidacionDepositos {
    private int noCuenta;
    private String nombre;
    private double monto;
    private int codigoCliente;
/**
 * METODO PARA VALIDAR UNA TRANSACCION
 * @param noCuenta
 * @param nombre
 * @param monto
 * @param codigoCliente 
 */
    public ValidacionDepositos(int noCuenta, String nombre, double monto, int codigoCliente) {
        this.noCuenta = noCuenta;
        this.nombre = nombre;
        this.monto = monto;
        this.codigoCliente = codigoCliente;
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

    public void setCodigoCliente(int codigoCliente) {
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

    public int getCodigoCliente() {
        return codigoCliente;
    }
    
    
    
}
