/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import objetos.Cajero;
import informesDeVisualizacion.InformeVisualizacionCuenta;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAVisualizacionCuentas {
    
    /**
     * BUSQUEDA GENERAL DE CUENTAS PARA VISUALIZACION
     * @return 
     */
    public List<InformeVisualizacionCuenta> busquedaGeneralCuentas(){
        
        List<InformeVisualizacionCuenta> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT cu.No_Cuenta, cl.Nombre,  cu.Cliente_Usuario_Codigo, cu.Fecha_Creacion  FROM Cuenta cu INNER JOIN Cliente cl ON cu.Cliente_Usuario_Codigo = cl.Usuario_Codigo";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new InformeVisualizacionCuenta(rs.getInt("No_Cuenta"),
                        rs.getString("Nombre"),
                        rs.getInt("Cliente_Usuario_Codigo"),
                        rs.getString("Fecha_Creacion")));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
  
    
    /**
     * BUSQUEDA DE CUENTAS POR NUMERO DE CUENTA
     * @param numeroCuenta
     * @return 
     */
      public List<InformeVisualizacionCuenta> busquedaCuentasPorNCuenta(int numeroCuenta){
        
        List<InformeVisualizacionCuenta> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT cu.No_Cuenta, cl.Nombre,  cu.Cliente_Usuario_Codigo, cu.Fecha_Creacion  FROM Cuenta cu INNER JOIN Cliente cl ON cu.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE cu.No_Cuenta = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1, numeroCuenta); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new InformeVisualizacionCuenta(rs.getInt("No_Cuenta"),
                        rs.getString("Nombre"),
                        rs.getInt("Cliente_Usuario_Codigo"),
                        rs.getString("Fecha_Creacion")));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
    
    
    /**
     * BUSQUEDA DE CUENTAS POR NOMBRE
     * @param nombre
     * @return 
     */
    
    public List<InformeVisualizacionCuenta> busquedaCuentasPorNombre(String nombre){
        
        List<InformeVisualizacionCuenta> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT cu.No_Cuenta, cl.Nombre,  cu.Cliente_Usuario_Codigo, cu.Fecha_Creacion  FROM Cuenta cu INNER JOIN Cliente cl ON cu.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE cl.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setString(1,'%'+nombre+'%');
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new InformeVisualizacionCuenta(rs.getInt("No_Cuenta"),
                        rs.getString("Nombre"),
                        rs.getInt("Cliente_Usuario_Codigo"),
                        rs.getString("Fecha_Creacion")));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
    
    /**
     * BUSQUEDA POR CODIGO USUARIO
     * @param codigoUsuario
     * @return 
     */
     public List<InformeVisualizacionCuenta> busquedaCuentasPorCodigoUsuario(int codigoUsuario){
        
        List<InformeVisualizacionCuenta> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT cu.No_Cuenta, cl.Nombre,  cu.Cliente_Usuario_Codigo, cu.Fecha_Creacion  FROM Cuenta cu INNER JOIN Cliente cl ON cu.Cliente_Usuario_Codigo = cl.Usuario_Codigo WHERE cu.Cliente_Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1,codigoUsuario);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new InformeVisualizacionCuenta(rs.getInt("No_Cuenta"),
                        rs.getString("Nombre"),
                        rs.getInt("Cliente_Usuario_Codigo"),
                        rs.getString("Fecha_Creacion")));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
    
    
    
    
    
  
  
}
