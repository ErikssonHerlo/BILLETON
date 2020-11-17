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

/**
 *
 * @author erikssonherlo
 */
public class AccesoAReporte2_Gerente {
        public double obtenerLimiteReporte2(int codigoGerente){
    double limiteReporte2 = 0;
    
     try {
            String query = " SELECT Limite_Reporte2 FROM Configuracion_Reportes WHERE Gerente_Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoGerente); 
     
        rs=enviar.executeQuery();   
        //SELECT t.Tipo, SUM(t.Monto) AS Suma, cl.Usuario_Codigo, cl.Nombre FROM Transaccion t INNER JOIN Cuenta c ON c.No_Cuenta = t.Cuenta_No_Cuenta INNER JOIN Cliente cl ON c.Cliente_Usuario_Codigo = cl.Usuario_Codigo GROUP BY t.Tipo, cl.Usuario_Codigo HAVING Suma >(SELECT Limite_Reporte3 FROM Configuracion_Reportes WHERE Gerente_Usuario_Codigo = 113456) ORDER BY Suma DESC;
            while (rs.next()) {
                limiteReporte2 = rs.getDouble("Limite_Reporte2");
                        
            }
          
        } catch (Exception e) {

        }
    
    return limiteReporte2;
    }
    
    
    public List<Reporte2Gerente> reporte2Gerente(double limiteReporte2){
        
        List<Reporte2Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = " SELECT cl.Usuario_Codigo, cl.Nombre, t.Cuenta_No_Cuenta, t.Codigo, t.Tipo, t.Monto, t.Fecha FROM Transaccion t INNER JOIN Cuenta c ON t.Cuenta_No_Cuenta = c.No_Cuenta INNER JOIN  Cliente cl ON c.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE t.Monto > ? ORDER BY t.Monto DESC";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setDouble(1, limiteReporte2); 
     
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Reporte2Gerente(rs.getInt("Usuario_Codigo"),
                        rs.getString("Nombre"),
                        rs.getInt("Cuenta_No_Cuenta"),
                        rs.getInt("Codigo"), 
                        rs.getString("Tipo"),
                        rs.getDouble("Monto"),
                        rs.getString("Fecha")));
                        
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
}
