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
public class SolicitudAsociacion implements Serializable {
    
    private int codigo;
    private int cuentaA;
    private String clienteA;
    private String DPIClienteA;
    private int cuentaB;
    private String clienteB;
    private String DPIClienteB;
    private int noIntento;
    private String estado;

    /**
     * Clase SolicitudAsociacion, que contendra todos los datos de una Solicitud de Asociacion entre dos Cuentas realizada por un
     * Cliente, pendiente de la respuesta del Cliente B 
     * @param codigo
     * @param cuentaA
     * @param clienteA
     * @param DPIClienteA
     * @param cuentaB
     * @param clienteB
     * @param DPIClienteB
     * @param noIntento
     * @param estado 
     */
    public SolicitudAsociacion(int codigo, int cuentaA, String clienteA, String DPIClienteA, int cuentaB, String clienteB, String DPIClienteB, int noIntento, String estado) {
        this.codigo = codigo;
        this.cuentaA = cuentaA;
        this.clienteA = clienteA;
        this.DPIClienteA = DPIClienteA;
        this.cuentaB = cuentaB;
        this.clienteB = clienteB;
        this.DPIClienteB = DPIClienteB;
        this.noIntento = noIntento;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCuentaA() {
        return cuentaA;
    }

    public String getClienteA() {
        return clienteA;
    }

    public String getDPIClienteA() {
        return DPIClienteA;
    }

    public int getCuentaB() {
        return cuentaB;
    }

    public String getClienteB() {
        return clienteB;
    }

    public String getDPIClienteB() {
        return DPIClienteB;
    }

    public int getNoIntento() {
        return noIntento;
    }

    public String getEstado() {
        return estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCuentaA(int cuentaA) {
        this.cuentaA = cuentaA;
    }

    public void setClienteA(String clienteA) {
        this.clienteA = clienteA;
    }

    public void setDPIClienteA(String DPIClienteA) {
        this.DPIClienteA = DPIClienteA;
    }

    public void setCuentaB(int cuentaB) {
        this.cuentaB = cuentaB;
    }

    public void setClienteB(String clienteB) {
        this.clienteB = clienteB;
    }

    public void setDPIClienteB(String DPIClienteB) {
        this.DPIClienteB = DPIClienteB;
    }

    public void setNoIntento(int noIntento) {
        this.noIntento = noIntento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
