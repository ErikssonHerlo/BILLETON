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
import objetos.Cajero;
import objetos.LimiteReportes;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAEdicionLimiteReportes {
    
      public LimiteReportes obtenerLimitesReporteAEditar(int codigoGerente) {

        LimiteReportes limite = null;

        try {
            String query = "SELECT Limite_Reporte2, Limite_Reporte3, Gerente_Usuario_Codigo FROM Configuracion_Reportes WHERE Gerente_Usuario_Codigo = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            enviar.setInt(1, codigoGerente);

            rs = enviar.executeQuery();

            while (rs.next()) {
                limite = new LimiteReportes(rs.getDouble("Limite_Reporte2"),
                        rs.getDouble("Limite_Reporte3"), 
                        rs.getInt("Gerente_Usuario_Codigo"));

            }

        } catch (Exception e) {

        }

        return limite;
    }
      
      
      
      
       public boolean editarLimiteReportes(LimiteReportes limite) {
        String queryDividido1 = "UPDATE Configuracion_Reportes SET Limite_Reporte2 = ?, Limite_Reporte3= ? WHERE Gerente_Usuario_Codigo = ?";
      
        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Editados a la Tabla Configuracion de Reportes
            
            enviarDividido1.setDouble(1, limite.getLimiteReporte2());
            enviarDividido1.setDouble(2, limite.getLimiteReporte3());
            enviarDividido1.setInt(3, limite.getCodigoGerente());
            enviarDividido1.executeUpdate();

            
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
      
}
