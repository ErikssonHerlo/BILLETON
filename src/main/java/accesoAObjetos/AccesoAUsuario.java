/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objetos.Cajero;
import objetos.Cliente;
import objetos.ClienteELIMINAR;
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAUsuario {
    /**
     * METODOS PARA EL INICIO DE SESION
     */
        /**
         * Metodo de Validacion, recibe los parametros desde el formulario, verifica si existe el usuario
         * y si este existe, verifica que su contraseña sea correcta
         * @param codigoUsuario
         * @param password
         * @return
         * @throws SQLException 
         */
        public Gerente loginGerente(int codigoUsuario, String password) throws SQLException {
        Gerente gerente= obtenerGerente(codigoUsuario);
        if (gerente != null && gerente.getPassword().equals(password)) {
            return gerente;
        }
        return null;
    }
        /**
         * Obtenemos el Gerente con una busqueda entorno a su codigo
         * y Desencriptamos su contraseña para compararla con la que ingresara desde el formulario
         * @param codigoUsuario
         * @return 
         */
      public Gerente obtenerGerente(int codigoUsuario){
        
        Gerente gerente = null;
 
        try {
        String query ="SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, CAST(aes_decrypt(u.Password, 'AES') AS CHAR(100)) Password_Descript, u.Tipo_Usuario, g.Turno, g.Hora_Entrada, g.Hora_Salida, g.Estado FROM Usuario u INNER JOIN Gerente g ON g.Usuario_Codigo = u.Codigo WHERE g.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        enviar.setInt(1, codigoUsuario);
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                gerente = new Gerente(rs.getInt("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Direccion"), rs.getString("Sexo"), rs.getString("Password_Descript"), rs.getInt("Tipo_Usuario"),rs.getString("Turno"),rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"),rs.getBoolean("Estado"));
                
            }
          
        } catch (Exception e) {

        }
          
    return gerente;
    }
      
      /**
         * Metodo de Validacion, recibe los parametros desde el formulario, verifica si existe el usuario
         * y si este existe, verifica que su contraseña sea correcta
         * @param codigoUsuario
         * @param password
         * @return
         * @throws SQLException 
         */
        public Cajero loginCajero(int codigoUsuario, String password) throws SQLException {
        Cajero cajero= obtenerCajero(codigoUsuario);
        if (cajero != null && cajero.getPassword().equals(password)) {
            return cajero;
        }
        return null;
    }
        /**
         * Obtenemos el Cajero con una busqueda entorno a su codigo
         * y Desencriptamos su contraseña para compararla con la que ingresara desde el formulario
         * @param codigoUsuario
         * @return 
         */
      public Cajero obtenerCajero(int codigoUsuario){
        
        Cajero cajero = null;
 
        try {
        String query ="SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, CAST(aes_decrypt(u.Password, 'AES') AS CHAR(100)) Password_Descript, u.Tipo_Usuario, c.Turno, c.Hora_Entrada, c.Hora_Salida, c.Estado FROM Usuario u INNER JOIN Cajero c ON c.Usuario_Codigo = u.Codigo WHERE c.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        enviar.setInt(1, codigoUsuario);
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                cajero = new Cajero(rs.getInt("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Direccion"), rs.getString("Sexo"), rs.getString("Password_Descript"), rs.getInt("Tipo_Usuario"),rs.getString("Turno"),rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"),rs.getBoolean("Estado"));
                
            }
          
        } catch (Exception e) {

        }
          
    return cajero;
    }
      
            /**
         * Metodo de Validacion, recibe los parametros desde el formulario, verifica si existe el usuario
         * y si este existe, verifica que su contraseña sea correcta
         * @param codigoUsuario
         * @param password
         * @return
         * @throws SQLException 
         */
        public Cliente loginCliente(int codigoUsuario, String password) throws SQLException {
        Cliente cliente= obtenerCliente(codigoUsuario);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }
        /**
         * Obtenemos el Cliente con una busqueda entorno a su codigo
         * y Desencriptamos su contraseña para compararla con la que ingresara desde el formulario
         * @param codigoUsuario
         * @return 
         */
      public Cliente obtenerCliente(int codigoUsuario){
        
        Cliente cliente= null;
 
        try {
        String query ="SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, CAST(aes_decrypt(u.Password, 'AES') AS CHAR(100)) Password_Descript, u.Tipo_Usuario, cl.Nacimiento, cl.DPI_Escaneado, cl.Estado FROM Usuario u INNER JOIN Cliente cl ON cl.Usuario_Codigo = u.Codigo WHERE cl.Usuario_Codigo = ?";
        PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
        ResultSet rs = null;
        
        
        enviar.setInt(1, codigoUsuario);
        
        rs=enviar.executeQuery();   
        
            while (rs.next()) {
                cliente = new Cliente(rs.getInt("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Direccion"), rs.getString("Sexo"), rs.getString("Password_Descript"), rs.getInt("Tipo_Usuario"),rs.getString("Nacimiento"),rs.getBinaryStream("DPI_Escaneado"),rs.getBoolean("Estado"));
                
            }
          
        } catch (Exception e) {

        }
          
    return cliente;
    }
      
      
}
