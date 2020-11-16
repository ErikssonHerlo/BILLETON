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
public class ReporteCreacionNuevoCliente {
    private int codigoCliente;
    private String nombreCliente;
    private int numeroCuentaCliente;
    private double saldoCuenta;
/**
 * OBJETO DE TIPO REPORTE UTILIZADO PARA EL INFORME DE CREACION DE NUEVO CLIENTE DESDE LA VISTA DEL GERENTE
 * @param codigoCliente
 * @param nombreCliente
 * @param numeroCuentaCliente
 * @param saldoCuenta 
 */
    public ReporteCreacionNuevoCliente(int codigoCliente, String nombreCliente, int numeroCuentaCliente, double saldoCuenta) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.numeroCuentaCliente = numeroCuentaCliente;
        this.saldoCuenta = saldoCuenta;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getNumeroCuentaCliente() {
        return numeroCuentaCliente;
    }

    public double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setNumeroCuentaCliente(int numeroCuentaCliente) {
        this.numeroCuentaCliente = numeroCuentaCliente;
    }

    public void setSaldoCuenta(double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }
    
}
