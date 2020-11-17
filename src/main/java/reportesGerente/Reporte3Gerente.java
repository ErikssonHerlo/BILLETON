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
public class Reporte3Gerente {
    private int codigoCliente;
    private String nombreCliente;
    private double suma;
    private String tipo;

    public Reporte3Gerente(int codigoCliente, String nombreCliente, double suma, String tipo) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.suma = suma;
        this.tipo = tipo;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getSuma() {
        return suma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
