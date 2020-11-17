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

/**
 *
 * @author erikssonherlo
 */
public class AccesoAReporte3_Gerente {
    
    public double obtenerLimiteReporte3(int codigoGerente){
    double limiteReporte3 = 0;
    
     try {
            String query = " SELECT Limite_Reporte3 FROM Configuracion_Reportes WHERE Gerente_Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoGerente); 
     
        rs=enviar.executeQuery();   
        while (rs.next()) {
                limiteReporte3 = rs.getDouble("Limite_Reporte3");
                        
            }
          
        } catch (Exception e) {

        }
    
    return limiteReporte3;
    }
    
    public List<Reporte3Gerente> reporte3Gerente(double limiteReporte3){
        
        List<Reporte3Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT cl.Usuario_Codigo, cl.Nombre, SUM(t.Monto) AS Suma, t.Tipo FROM Transaccion t INNER JOIN Cuenta c ON c.No_Cuenta = t.Cuenta_No_Cuenta INNER JOIN Cliente cl ON c.Cliente_Usuario_Codigo = cl.Usuario_Codigo GROUP BY t.Tipo, cl.Usuario_Codigo HAVING Suma >? ORDER BY Suma DESC";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setDouble(1, limiteReporte3); 
     
        rs=enviar.executeQuery();   
         while (rs.next()) {
                busqueda.add( new Reporte3Gerente(rs.getInt("Usuario_Codigo"),
                        rs.getString("Nombre"),
                        rs.getDouble("Suma"), 
                        rs.getString("Tipo")));
                        
                        
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
}
