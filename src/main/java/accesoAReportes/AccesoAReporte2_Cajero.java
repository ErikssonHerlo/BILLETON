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

/**
 *
 * @author erikssonherlo
 */
public class AccesoAReporte2_Cajero {
     public List<Reporte1Cajero> reporte2Cajero(int codigoCajero, String horarioEntrada, String horarioSalida, String fechaInicio, String fechaFinal){
        
        List<Reporte1Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
        String query = "SELECT Codigo, Cuenta_No_Cuenta, Fecha, Hora, Tipo, Monto, Cajero_Usuario_Codigo FROM Transaccion WHERE Cajero_Usuario_Codigo = ? AND Fecha BETWEEN ? AND ? AND Hora BETWEEN ? AND ? ORDER BY Tipo, Fecha ASC";        
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero); 
       enviar.setString(2, fechaInicio);
       enviar.setString(3, fechaFinal);
       enviar.setString(4, horarioEntrada);
       enviar.setString(5, horarioSalida);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Reporte1Cajero(rs.getInt("Codigo"),
                        rs.getInt("Cuenta_No_Cuenta"),
                        rs.getString("Fecha"),
                        rs.getString("Hora"),
                        rs.getString("Tipo"),
                        rs.getDouble("Monto"),
                        rs.getInt("Cajero_Usuario_Codigo")));
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
     
     
       public double totalDepositos(int codigoCajero, String horarioEntrada, String horarioSalida, String fechaInicio, String fechaFinal){
        
        double totalDepositos = 0;
 
        try {
            String query = "SELECT SUM(Monto) FROM Transaccion WHERE Cajero_Usuario_Codigo = ? AND Fecha BETWEEN ? AND ? AND Hora BETWEEN ? AND ? AND Tipo = 'Credito'";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero); 
       enviar.setString(2, fechaInicio);
       enviar.setString(3, fechaFinal);
       enviar.setString(4, horarioEntrada);
       enviar.setString(5, horarioSalida);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                totalDepositos = rs.getDouble("SUM(Monto)");
            }
          
        } catch (Exception e) {

        }
          
    return totalDepositos;
    }
       
       public double totalRetiros(int codigoCajero, String horarioEntrada, String horarioSalida, String fechaInicio, String fechaFinal){
        
        double totalRetiros = 0;
 
        try {
             String query = "SELECT SUM(Monto) FROM Transaccion WHERE Cajero_Usuario_Codigo = ? AND Fecha BETWEEN ? AND ? AND Hora BETWEEN ? AND ? AND Tipo = 'Debito'";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero); 
       enviar.setString(2, fechaInicio);
       enviar.setString(3, fechaFinal);
       enviar.setString(4, horarioEntrada);
       enviar.setString(5, horarioSalida);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                 totalRetiros = rs.getDouble("SUM(Monto)");
            }
          
        } catch (Exception e) {

        }
          
    return totalRetiros;
    }
     
     public double saldoCaja(int codigoCajero, String horarioEntrada, String horarioSalida, String fechaInicio, String fechaFinal)
     {
         double saldoCaja = totalDepositos(codigoCajero, horarioEntrada, horarioSalida, fechaInicio, fechaFinal) - totalRetiros(codigoCajero, horarioEntrada, horarioSalida, fechaInicio, fechaFinal);
         System.out.println("Balance "+saldoCaja);
         return saldoCaja;
     
     }

}
