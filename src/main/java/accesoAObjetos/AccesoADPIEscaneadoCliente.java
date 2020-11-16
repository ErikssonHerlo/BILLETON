/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author erikssonherlo
 */
public class AccesoADPIEscaneadoCliente {
    /**
        * METODO DE RECUPERACION DEL DPI DEL CLIENTE
        */
       
       public InputStream visualizarDPICliente(int codigoCliente){
       
           InputStream DPIEscaneado = InputStream.nullInputStream();
             try {
            String query = "SELECT DPI_Escaneado FROM Cliente WHERE Usuario_Codigo = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;
            enviar.setInt(1, codigoCliente);
            rs = enviar.executeQuery();

            rs.next();
                return rs.getBlob(1).getBinaryStream();


        } catch (Exception e) {
            return null;
        }

       }
       
       
            public InputStream visualizarDPIHistorialCliente(int codigoCliente){
       
           InputStream DPIEscaneado = InputStream.nullInputStream();
             try {
            String query = "SELECT DPI_Escaneado FROM Historial_Cliente WHERE Usuario_Codigo = ? LIMIT 1";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;
            enviar.setInt(1, codigoCliente);
            rs = enviar.executeQuery();

            rs.next();
                return rs.getBlob(1).getBinaryStream();


        } catch (Exception e) {
            return null;
        }

       }
}
