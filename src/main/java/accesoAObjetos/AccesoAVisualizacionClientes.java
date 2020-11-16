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
import java.util.ArrayList;
import java.util.List;
import objetos.Cliente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAVisualizacionClientes {
      /**
     * METODO PARA LA BUSQUEDA GENERAL DE LOS Clientes
     * @return 
     */
         public List<Cliente> busquedaGeneralClientes(){
        
        List<Cliente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, cl.Nacimiento FROM Usuario u INNER JOIN Cliente cl ON u.Codigo = cl.Usuario_Codigo";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cliente(rs.getInt("Codigo"),
                        rs.getString("Nombre"),
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        3,
                        rs.getString("Nacimiento"),
                        InputStream.nullInputStream(),
                        true));
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
         
         
         /**
          * METODO PARA BUSCAR CLIENTES POR CODIGO DE USUARIO
          * @param codigoCliente
          * @return 
          */
           public List<Cliente> busquedaCodigoClientes(int codigoCliente){
        
        List<Cliente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, cl.Nacimiento FROM Usuario u INNER JOIN Cliente cl ON u.Codigo = cl.Usuario_Codigo WHERE cl.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setInt(1, codigoCliente);
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cliente(rs.getInt("Codigo"),
                        rs.getString("Nombre"),
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        3,
                        rs.getString("Nacimiento"),
                        InputStream.nullInputStream(),
                        true));
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
           
           
           /**
            * METODO UTILIZADO PARA BUSCAR CLIENTES EN BASE A SU NOMBRE
            * @param nombreCliente
            * @return 
            */
            public List<Cliente> busquedaNombreClientes(String nombreCliente){
        
        List<Cliente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, cl.Nacimiento FROM Usuario u INNER JOIN Cliente cl ON u.Codigo = cl.Usuario_Codigo WHERE u.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        enviar.setString(1,'%'+nombreCliente+'%');
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cliente(rs.getInt("Codigo"),
                        rs.getString("Nombre"),
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        3,
                        rs.getString("Nacimiento"),
                        InputStream.nullInputStream(),
                        true));
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
            /**
             * METODO PARA BUSCAR CLIENTES POR CODIGO Y NOMBRE
             * @param codigoCliente
             * @param nombreCliente
             * @return 
             */
                public List<Cliente> busquedaCodigoNombreClientes(int codigoCliente, String nombreCliente){
        
        List<Cliente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, cl.Nacimiento FROM Usuario u INNER JOIN Cliente cl ON u.Codigo = cl.Usuario_Codigo WHERE cl.Usuario_Codigo = ? AND u.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        enviar.setInt(1, codigoCliente);
        enviar.setString(2,'%'+nombreCliente+'%');
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cliente(rs.getInt("Codigo"),
                        rs.getString("Nombre"),
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        3,
                        rs.getString("Nacimiento"),
                        InputStream.nullInputStream(),
                        true));
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
   
}
