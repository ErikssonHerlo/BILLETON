/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author erikssonherlo
 */
public class AccesoACodigoTransaccion {
    
    public int obtenerCodigoNuevaTransaccion() {

           int codigoTransaccion = 0;
        try {
            String query = "SELECT MAX(Codigo)+1 FROM Transaccion";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                
                codigoTransaccion = rs.getInt("MAX(Codigo)+1");

            }
            return codigoTransaccion;

        } catch (Exception e) {
            return codigoTransaccion;
        }

    }
    
}
