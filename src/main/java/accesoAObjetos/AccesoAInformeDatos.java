/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Cajero;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAInformeDatos {

    /**
     * Metodo que recopila el Informe de Cantidad de Datos que fueron cargados a la Base de Datos desde la Carga del Archivo XML
     * @return 
     */
    
    public int[] informeCargaArchivo(){
    int[] informeDatos = new int[5];
    informeDatos[0] = informeCargaGerentes();
    informeDatos[1] = informeCargaCajeros();
    informeDatos[2] = informeCargaClientes();
    informeDatos[3] = informeCargaCuentas();
    informeDatos[4] = informeCargaTransacciones();
    return informeDatos;
    }
    
    
    
    /**
     * Metodo para realizar un Informe de la Cantidad de Clientes que fueron
     * Cargados a la Base de Datos desde la carga del archivo XML
     *
     * @return
     */
    public int informeCargaClientes() {

        int cantidadClientes = 0;

        try {
            String query = "SELECT COUNT(*) FROM Cliente";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                cantidadClientes = rs.getInt("COUNT(*)");

            }

        } catch (Exception e) {

        }

        return cantidadClientes;
    }
    
     /**
     * Metodo para realizar un Informe de la Cantidad de Gerentes que fueron
     * Cargados a la Base de Datos desde la carga del archivo XML
     *
     * @return
     */
    public int informeCargaGerentes() {

        int cantidadGerentes = 0;

        try {
            String query = "SELECT COUNT(*) FROM Gerente";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                cantidadGerentes = rs.getInt("COUNT(*)");

            }

        } catch (Exception e) {

        }

        return cantidadGerentes;
    }
    
     /**
     * Metodo para realizar un Informe de la Cantidad de Cajeros que fueron
     * Cargados a la Base de Datos desde la carga del archivo XML
     *
     * @return
     */
    public int informeCargaCajeros() {

        int cantidadCajeros = 0;

        try {
            String query = "SELECT COUNT(*) FROM Cajero";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                cantidadCajeros = rs.getInt("COUNT(*)");

            }

        } catch (Exception e) {

        }

        return cantidadCajeros;
    }
    
      /**
     * Metodo para realizar un Informe de la Cantidad de Cuentas que fueron
     * Cargados a la Base de Datos desde la carga del archivo XML
     *
     * @return
     */
    public int informeCargaCuentas() {

        int cantidadCuentas = 0;

        try {
            String query = "SELECT COUNT(*) FROM Cuenta";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                cantidadCuentas = rs.getInt("COUNT(*)");

            }

        } catch (Exception e) {

        }

        return cantidadCuentas;
    }
    
      /**
     * Metodo para realizar un Informe de la Cantidad de Transacciones que fueron
     * Cargados a la Base de Datos desde la carga del archivo XML
     *
     * @return
     */
    public int informeCargaTransacciones() {

        int cantidadTransacciones = 0;

        try {
            String query = "SELECT COUNT(*) FROM Transaccion";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                cantidadTransacciones = rs.getInt("COUNT(*)");

            }

        } catch (Exception e) {

        }

        return cantidadTransacciones;
    }
}
