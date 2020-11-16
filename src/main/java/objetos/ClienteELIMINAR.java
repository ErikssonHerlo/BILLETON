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
public class ClienteELIMINAR extends Usuario implements Serializable{
    
    private String nacimiento;
    private String DPIEscaneado;
    private boolean estado;

    /**
     * Clase Hija de la Clase Usuario, comparte los atributos Generales:
     * @param codigo
     * @param nombre
     * @param DPI
     * @param direccion
     * @param sexo
     * @param password
     * @param tipoUsuario = 3
     * Atributos Unicos de la Clase Cliente:
     * @param nacimiento
     * @param DPIEscaneado
     * @param estado 
     */
    
    public ClienteELIMINAR(int codigo, String nombre, String DPI, String direccion, String sexo, String password, int tipoUsuario, String nacimiento, String DPIEscaneado, boolean estado) {
        super(codigo, nombre, DPI, direccion, sexo, password, tipoUsuario);
        this.nacimiento = nacimiento;
        this.DPIEscaneado = DPIEscaneado;
        this.estado = estado;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public String getDPIEscaneado() {
        return DPIEscaneado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setDPIEscaneado(String DPIEscaneado) {
        this.DPIEscaneado = DPIEscaneado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
