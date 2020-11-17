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
public class Reporte2Gerente {
    private int codigoCliente;
    private String nombreCliente;
    private int numeroCuenta;
    private int codigoTransaccion;
    private String tipo;
    private double monto;
    private String fecha;
/**
 * OBJETO UTILIZADO PARA LA VISUALIZACION DEL REPORTE NO. 2 DE LOS CLIENTES
 * @param codigoCliente
 * @param nombreCliente
 * @param numeroCuenta
 * @param codigoTransaccion
 * @param tipo
 * @param monto
 * @param fecha 
 */
    public Reporte2Gerente(int codigoCliente, String nombreCliente, int numeroCuenta, int codigoTransaccion, String tipo, double monto, String fecha) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.codigoTransaccion = codigoTransaccion;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
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

    public void setCodigoTransaccion(int codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public int getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }
    
    
}
