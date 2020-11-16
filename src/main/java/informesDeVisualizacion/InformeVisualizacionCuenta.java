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
public class InformeVisualizacionCuenta {
    private int noCuenta;
    private String nombre;
    private int codigoUsuario;
    private String fechaCreacion;
/**
 * OBJETO UTILIZADO PARA LA VISUALIZACION DE CUENTAS BANCARIAS
 * @param noCuenta
 * @param nombre
 * @param codigoUsuario
 * @param fechaCreacion 
 */
    public InformeVisualizacionCuenta(int noCuenta, String nombre, int codigoUsuario, String fechaCreacion) {
        this.noCuenta = noCuenta;
        this.nombre = nombre;
        this.codigoUsuario = codigoUsuario;
        this.fechaCreacion = fechaCreacion;
    }

    public int getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(int noCuenta) {
        this.noCuenta = noCuenta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
    
    
}
