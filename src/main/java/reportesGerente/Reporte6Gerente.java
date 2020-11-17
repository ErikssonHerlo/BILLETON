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
public class Reporte6Gerente {
  private int codigo;
  private int noCuenta;
  private String fecha;
  private String hora;
  private String tipo;
  private Double monto;
  private Double saldoAnterior;
  private Double saldoActual;
  private int codigoCliente;
  private String nombreCliente;

    public Reporte6Gerente(int codigo, int noCuenta, String fecha, String hora, String tipo, Double monto, Double saldoAnterior, Double saldoActual, int codigoCliente, String nombreCliente) {
        this.codigo = codigo;
        this.noCuenta = noCuenta;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.saldoAnterior = saldoAnterior;
        this.saldoActual = saldoActual;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
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

    public Double getMonto() {
        return monto;
    }

    public Double getSaldoAnterior() {
        return saldoAnterior;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
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

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setSaldoAnterior(Double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
  
  
  
  
}
