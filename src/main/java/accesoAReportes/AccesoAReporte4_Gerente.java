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
import reportesCajero.Reporte1Cajero;
import reportesGerente.Reporte2Gerente;
import reportesGerente.Reporte3Gerente;
import reportesGerente.Reporte4Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAReporte4_Gerente {
    
   
    
    public List<Reporte4Gerente> reporte4Gerente(){
        
        List<Reporte4Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT cl.Usuario_Codigo, cl.Nombre, c.No_Cuenta, c.Saldo_Cuenta, c.Fecha_Creacion FROM Cuenta c INNER JOIN Cliente cl ON c.Cliente_Usuario_Codigo = cl.Usuario_Codigo ORDER BY c.Saldo_Cuenta DESC LIMIT 10";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
      
        rs=enviar.executeQuery();   
         while (rs.next()) {
              busqueda.add( new Reporte4Gerente(rs.getInt("Usuario_Codigo"),
                      rs.getString("Nombre"),
                      rs.getInt("No_Cuenta"),
                      rs.getDouble("Saldo_Cuenta"),
                      rs.getString("Fecha_Creacion")));
                      
                        
                        
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
}
