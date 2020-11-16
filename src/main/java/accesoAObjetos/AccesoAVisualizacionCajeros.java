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
import objetos.Cajero;
import objetos.Cliente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAVisualizacionCajeros {
    
    /**
     * METODO UTILIZADO PARA LA BUSQUEDA GENERAL DE CAJEROS
     * 
     * @return 
     */
  public List<Cajero> busquedaGeneralCajeros(){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
  
  /**
   * METODO PARA BUSCAR CAJEROS POR CODIGO
   * @param codigoCajero
   * @return 
   */
  public List<Cajero> busquedaCajerosCodigo(int codigoCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND c.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
  
  /**
   * BUSQUEDA DE CAJEROS POR NOMBRE
   * @param codigoCajero
   * @return 
   */
  public List<Cajero> busquedaCajerosNombre(String nombreCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND u.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setString(1,'%'+nombreCajero+'%');
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
  /**
   * BUSQUEDA DE CAJEROS POR TURNO
   * @param turnoCajero
   * @return 
   */
    public List<Cajero> busquedaCajerosTurno(String turnoCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND c.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setString(1,turnoCajero);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
    
    
    /**
     * SEGUNDA PARTE DEL FILTRADO
     */
    
    /**
     * BUSQUEDA DE CAJEROS POR CODIGO + NOMBRE
     * @param codigoCajero
     * @param nombreCliente
     * @return 
     */
    
    public List<Cajero> busquedaCajerosC_N(int codigoCajero, String nombreCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND c.Usuario_Codigo = ? AND u.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero);  
       enviar.setString(2,'%'+nombreCajero+'%');
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
    
    /**
     * BUSQUEDA DE CAJEROS POR CODIGO + TURNO
     * @param codigoCajero
     * @param turnoCajero
     * @return 
     */
    public List<Cajero> busquedaCajerosC_T(int codigoCajero, String turnoCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND c.Usuario_Codigo = ? AND c.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero);  
       enviar.setString(2,turnoCajero);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
    
    /**
     * BUSQUEDA POR NOMBRE + TURNO
     * @param nombreCajero
     * @param turnoCajero
     * @return 
     */
        public List<Cajero> busquedaCajerosN_T(String nombreCajero, String turnoCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND u.Nombre LIKE ? AND c.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
         
       enviar.setString(1,'%'+nombreCajero+'%');
       enviar.setString(2, turnoCajero);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
        
        /**
         * BUSQUEDA DE CAJEROS POR CODIGO + NOMBRE + TURNO
         * @param codigoCajero
         * @param nombreCajero
         * @param turnoCajero
         * @return 
         */
         public List<Cajero> busquedaCajerosC_N_T(int codigoCajero, String nombreCajero, String turnoCajero){
        
        List<Cajero> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, c.Turno, c.Hora_Entrada, c.Hora_Salida FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo <> 101 AND c.Usuario_Codigo = ? AND u.Nombre LIKE ? AND c.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoCajero);
       enviar.setString(2,'%'+nombreCajero+'%');
       enviar.setString(3, turnoCajero);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Cajero(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        2,
                        rs.getString("Turno"),
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"), 
                        true));
          
                
                              
            }
          
        } catch (Exception e) {

        }
          
    return busqueda;
    }
}
