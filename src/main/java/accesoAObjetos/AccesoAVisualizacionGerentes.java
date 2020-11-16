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
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAVisualizacionGerentes {
    
    
    /**
     * BUSQUEDA GENERAL DE GERENTES
     * @return 
     */
     public List<Gerente> busquedaGeneralGerentes(){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
   * METODO PARA BUSCAR GERENTES POR CODIGO
   * @param codigoCajero
   * @return 
   */
  public List<Gerente> busquedaGerentesCodigo(int codigoGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND g.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoGerente); 
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
   * BUSQUEDA DE GERENTES POR NOMBRE
   * @param codigoGerente
   * @return 
   */
  public List<Gerente> busquedaGerentesNombre(String nombreGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND u.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setString(1,'%'+nombreGerente+'%');
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
   * BUSQUEDA DE GERENTES POR TURNO
   * @param turnoGerente
   * @return 
   */
    public List<Gerente> busquedaGerentesTurno(String turnoGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND g.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setString(1,turnoGerente);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
     * BUSQUEDA DE GERENTES POR CODIGO + NOMBRE
     * @param codigoGerente
     * @param nombreCliente
     * @return 
     */
    
    public List<Gerente> busquedaGerentesC_N(int codigoGerente, String nombreGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND g.Usuario_Codigo = ? AND u.Nombre LIKE ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoGerente);  
       enviar.setString(2,'%'+nombreGerente+'%');
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
     * BUSQUEDA DE GERENTES POR CODIGO + TURNO
     * @param codigoGerente
     * @param turnoGerente
     * @return 
     */
    public List<Gerente> busquedaGerentesC_T(int codigoGerente, String turnoGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND g.Usuario_Codigo = ? AND g.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoGerente);  
       enviar.setString(2,turnoGerente);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
     * @param nombreGerente
     * @param turnoGerente
     * @return 
     */
        public List<Gerente> busquedaGerentesN_T(String nombreGerente, String turnoGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND u.Nombre LIKE ? AND g.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
         
       enviar.setString(1,'%'+nombreGerente+'%');
       enviar.setString(2, turnoGerente);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
         * BUSQUEDA DE GERENTES POR CODIGO + NOMBRE + TURNO
         * @param codigoGerente
         * @param nombreGerente
         * @param turnoGerente
         * @return 
         */
         public List<Gerente> busquedaGerentesC_N_T(int codigoGerente, String nombreGerente, String turnoGerente){
        
        List<Gerente> busqueda = new ArrayList<>();
        busqueda.clear();
 
        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, g.Turno, g.Hora_Entrada, g.Hora_Salida FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo AND g.Usuario_Codigo = ? AND u.Nombre LIKE ? AND g.Turno = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
       enviar.setInt(1, codigoGerente);
       enviar.setString(2,'%'+nombreGerente+'%');
       enviar.setString(3, turnoGerente);
       rs=enviar.executeQuery();   
        
            while (rs.next()) {
                busqueda.add(new Gerente(rs.getInt("Codigo"),
                        rs.getString("Nombre"), 
                        rs.getString("DPI"),
                        rs.getString("Direccion"),
                        rs.getString("Sexo"),
                        "",
                        1,
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
