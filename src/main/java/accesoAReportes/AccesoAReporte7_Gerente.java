/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAReportes;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import objetos.Cajero;
import reportesCajero.Reporte1Cajero;
import reportesGerente.Reporte7Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAReporte7_Gerente {
     public List<Reporte7Gerente> reporte7Gerente(String fechaInicio, String fechaFinal){
        
        List<Reporte7Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT ca.Usuario_Codigo, ca.Nombre, ca.Turno, ca.Hora_Entrada, ca.Hora_Salida, COUNT(t.Cajero_Usuario_Codigo) AS Cantidad FROM Transaccion t INNER JOIN Cajero ca ON t.Cajero_Usuario_Codigo = ca.Usuario_Codigo WHERE t.Fecha>? AND t.Fecha<? GROUP BY t.Cajero_Usuario_Codigo ORDER BY Cantidad DESC LIMIT 1";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
      
       enviar.setString(1,fechaInicio);
       enviar.setString(2, fechaFinal);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add( new Reporte7Gerente(rs.getInt("Usuario_Codigo"),
                        rs.getString("Nombre"),
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"),
                        rs.getInt("Cantidad")));
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
     
     
      

}
