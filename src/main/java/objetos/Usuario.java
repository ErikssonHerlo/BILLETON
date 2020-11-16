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
public class Usuario implements Serializable{
    private int codigo;
    private String nombre;
    private String DPI;
    private String direccion;
    private String sexo;
    private String password;
    private int tipoUsuario;

    /**
     * Clase Padre o super Clase: 
     * Uso de Herencia en las Clases Gerente, Cajero y Cliente
     * Parametros Generales:
     * @param codigo
     * @param nombre
     * @param DPI
     * @param direccion
     * @param sexo
     * @param password
     * @param tipoUsuario:  Gerente = 1
     *                      Cajero = 2
     *                      Cliente = 3
     */
    public Usuario(int codigo, String nombre, String DPI, String direccion, String sexo, String password, int tipoUsuario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.DPI = DPI;
        this.direccion = direccion;
        this.sexo = sexo;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDPI() {
        return DPI;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public String getPassword() {
        return password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
           
    
}
