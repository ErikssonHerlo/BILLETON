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
public class Asociacion implements Serializable{
    
    private int codigo;
    private int cuentaA;
    private String clienteA;
    private String DPIClienteA;
    private int cuentaB;
    private String clienteB;
    private String DPIClienteB;

    /**
     * Clase Asociacion, que contendra todos los datos de una Asociacion entre dos Cuentas realizada por un
     * Cliente 
     * @param codigo
     * @param cuentaA
     * @param clienteA
     * @param DPIClienteA
     * @param cuentaB
     * @param clienteB
     * @param DPIClienteB 
     */
    public Asociacion(int codigo, int cuentaA, String clienteA, String DPIClienteA, int cuentaB, String clienteB, String DPIClienteB) {
        this.codigo = codigo;
        this.cuentaA = cuentaA;
        this.clienteA = clienteA;
        this.DPIClienteA = DPIClienteA;
        this.cuentaB = cuentaB;
        this.clienteB = clienteB;
        this.DPIClienteB = DPIClienteB;
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


    
}
