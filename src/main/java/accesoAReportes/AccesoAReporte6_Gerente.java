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
import reportesGerente.Reporte6Gerente;
import reportesGerente.Reporte7Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAReporte6_Gerente {
     public List<Reporte6Gerente> reporte6GerenteNombreLimite(String nombre, double limite1, double limite2){
        
        List<Reporte6Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT t.Codigo, t.Cuenta_No_Cuenta, t.Fecha, t.Hora, t.Tipo, t.Monto, t.Saldo_Cuenta_Anterior, t.Saldo_Cuenta_Actual, cl.Usuario_Codigo ,cl.Nombre FROM Transaccion t INNER JOIN Cuenta c ON t.Cuenta_No_Cuenta = c.No_Cuenta INNER JOIN Cliente cl ON c.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE cl.Nombre LIKE ? AND t.Monto> ? AND t.Monto<?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
      
       enviar.setString(1,'%'+nombre+'%');
       enviar.setDouble(2, limite1);
       enviar.setDouble(3, limite2);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add( new Reporte6Gerente(rs.getInt("Codigo"),
                        rs.getInt("Cuenta_No_Cuenta"),
                        rs.getString("Fecha"),
                        rs.getString("Hora"), 
                        rs.getString("Tipo"),
                        rs.getDouble("Monto"),
                        rs.getDouble("Saldo_Cuenta_Anterior"),
                        rs.getDouble("Saldo_Cuenta_Actual"),
                        rs.getInt("Usuario_Codigo"),
                        rs.getString("Nombre")));
               
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
     
     
      

}
