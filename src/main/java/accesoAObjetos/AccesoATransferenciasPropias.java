/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import informesDeVisualizacion.InformeTransacciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Transaccion;
import informesDeVisualizacion.InformeVisualizacionCuenta;
import informesDeVisualizacion.ValidacionDepositos;
import informesDeVisualizacion.ValidacionRetiros;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author erikssonherlo
 */
public class AccesoATransferenciasPropias {
    
     public ValidacionRetiros obtenerCuentaTransaccion(int numeroCuenta, double monto){
        
        ValidacionRetiros validacion = null ;
 
        try {
            String query = "SELECT cu.No_Cuenta, cl.Nombre, cu.Saldo_Cuenta,  cu.Cliente_Usuario_Codigo FROM Cuenta cu INNER JOIN Cliente cl ON cu.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE cu.No_Cuenta = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1, numeroCuenta); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                validacion = new ValidacionRetiros(rs.getInt("No_Cuenta"),
                        rs.getString("Nombre"),
                        monto,
                        rs.getDouble("Saldo_Cuenta"),
                        rs.getInt("Cliente_Usuario_Codigo"));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return validacion;
    }
     
     
     public int cantidadCuentasCliente(int codigoUsuario){
     int cantidad = 1;
        try {
            String query = "SELECT COUNT(*) FROM Cuenta WHERE Cliente_Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1, codigoUsuario); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                cantidad = rs.getInt("COUNT(*)");
          
                
                              
            }
          
        } catch (Exception e) {

        }
        
        return cantidad;
     
     
     }
     
     
     
     
     public ValidacionRetiros obtenerCuentaVisualizarTransferenciaPropia(int codigoUsuario){
        
        ValidacionRetiros validacion = null ;
 
        try {
            String query = "SELECT cu.No_Cuenta, cl.Nombre, cu.Saldo_Cuenta, cu.Cliente_Usuario_Codigo FROM Cuenta cu INNER JOIN Cliente cl ON cu.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE cl.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1, codigoUsuario); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                validacion = new ValidacionRetiros(rs.getInt("No_Cuenta"),
                        rs.getString("Nombre"),
                        0.00,
                        rs.getDouble("Saldo_Cuenta"),
                        rs.getInt("Cliente_Usuario_Codigo"));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return validacion;
    }
     
      public boolean realizarRetiro(Transaccion transaccion) {
        
          String queryDividido1 = "INSERT INTO Transaccion(Codigo, Cuenta_No_Cuenta, Fecha, Hora, Tipo, Monto, Saldo_Cuenta_Anterior, Saldo_Cuenta_Actual, Cajero_Usuario_Codigo) "
                + "VALUES(?,?,?,?,?,?,(SELECT Saldo_Cuenta FROM Cuenta WHERE No_Cuenta = ?),(SELECT Saldo_Cuenta - ? FROM Cuenta WHERE No_Cuenta = ?),?)";
          
          String queryDividido2 = "UPDATE Cuenta SET Saldo_Cuenta = Saldo_Cuenta - ? WHERE No_Cuenta = ?";
        java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
          
        try {
            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales de una Transaccion a la Tabla Transaccion
            enviarDividido1.setInt(1, transaccion.getCodigo());
            enviarDividido1.setInt(2, transaccion.getNoCuenta());
            enviarDividido1.setString(3, fecha.toString());
            enviarDividido1.setString(4, LocalTime.now().toString());
            enviarDividido1.setString(5, transaccion.getTipo());
            enviarDividido1.setDouble(6, transaccion.getMonto());
            
            enviarDividido1.setInt(7, transaccion.getNoCuenta());
            
            enviarDividido1.setDouble(8, transaccion.getMonto());
            enviarDividido1.setInt(9, transaccion.getNoCuenta());
            
            enviarDividido1.setInt(10, transaccion.getCodigoCajero());
            enviarDividido1.executeUpdate();
            
            //ACTUALIZAMOS EL SALDO DE LA CUENTA BANCARIA
             PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            
            enviarDividido2.setDouble(1, transaccion.getMonto());
            enviarDividido2.setInt(2, transaccion.getNoCuenta());
            enviarDividido2.executeUpdate();
            
            
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
      
      /**
       * PRESENTAMOS UN INFORME LUEGO DE REALIZAR LA TRANSACCION
       * @param codigoTransaccion
       * @param nombre
       * @return 
       */
      public InformeTransacciones informeRetiro(int codigoTransaccion, String nombre){
        
        InformeTransacciones informe = null;
 
        try {
            String query = "SELECT Codigo, Cuenta_No_Cuenta,  Monto, Fecha, Hora, Tipo, Cajero_Usuario_Codigo FROM Transaccion WHERE Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1, codigoTransaccion); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                informe = new InformeTransacciones(rs.getInt("Codigo"),
                        rs.getInt("Cuenta_No_Cuenta"),
                        nombre, 
                        rs.getDouble("Monto"),
                        rs.getString("Fecha"),
                        rs.getString("Hora"),
                        rs.getString("Tipo"),
                        rs.getInt("Cajero_Usuario_Codigo"));
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return informe;
    }
    
}
