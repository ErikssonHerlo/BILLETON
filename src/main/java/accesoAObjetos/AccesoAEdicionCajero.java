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
import java.util.Calendar;
import objetos.Cajero;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAEdicionCajero {
     public Cajero obtenerCajeroAEditar(int codigoCajero) {

        Cajero cajero = null;

        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, CAST(aes_decrypt(u.Password, 'AES') AS CHAR(100)) Password_Descript, u.Tipo_Usuario, c.Turno, c.Hora_Entrada, c.Hora_Salida, c.Estado FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            enviar.setInt(1, codigoCajero);

            rs = enviar.executeQuery();

            while (rs.next()) {
                cajero = new Cajero(rs.getInt("Codigo"), 
                        rs.getString("Nombre"), 
                        rs.getString("DPI"), 
                        rs.getString("Direccion"), 
                        rs.getString("Sexo"), 
                        rs.getString("Password_Descript"), 
                        rs.getInt("Tipo_Usuario"), 
                        rs.getString("Turno"), 
                        rs.getString("Hora_Entrada"),
                        rs.getString("Hora_Salida"),
                        rs.getBoolean("Estado"));

            }

        } catch (Exception e) {

        }

        return cajero;
    }
     /**
      * METODO PARA LA EDICION DEL CAJERO
      * @param cajero
      * @return 
      */
     
     public boolean editarCajero(Cajero cajero) {
        String queryDividido1 = "UPDATE Usuario SET Nombre = ?, DPI= ?, Direccion=?, Sexo=?, Password=aes_encrypt(?,'AES') WHERE Codigo = ?";
    
        String queryDividido2 = "UPDATE Cajero SET Nombre=?, Turno=?, Hora_Entrada=?, Hora_Salida=? WHERE Usuario_Codigo = ? ";

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales Editados del cajero a la Tabla Usuario
            
            enviarDividido1.setString(1, cajero.getNombre());
            enviarDividido1.setString(2, cajero.getDPI());
            enviarDividido1.setString(3, cajero.getDireccion());
            enviarDividido1.setString(4, cajero.getSexo());
            enviarDividido1.setString(5, cajero.getPassword());
            enviarDividido1.setInt(6, cajero.getCodigo());
            enviarDividido1.executeUpdate();

            //Envia los Datos Complementarios del Usuario a la tabla cajero
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            
            enviarDividido2.setString(1, cajero.getNombre());
            enviarDividido2.setString(2, cajero.getTurno());
            enviarDividido2.setString(3, cajero.getHoraEntrada());
            enviarDividido2.setString(4, cajero.getHoraSalida());
            enviarDividido2.setInt(5, cajero.getCodigo());
            enviarDividido2.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
        
     /**
     * Descripcion:
     * Metodo que recibe un Objeto de tipo Cajero, obtiene sus atributos y envia los datos de un Nuevo Cajero a la Base de Datos
     * para poder tener un Historial de Creacion del Cajero
     * 
     * Uso:
     * Utilizado para llevar un Registro de Edicion de Cajeros en el Historial del Cajero, desde la Vista del Gerente
       * @param cajero
       * @return 
       */
       public boolean insertarEdicionHistorialCajero(Cajero cajero) {
        String queryDividido1 = "INSERT INTO Historial_Cajero(Usuario_Codigo, Nombre, DPI, Direccion, Sexo, Password, Turno, Hora_Entrada, Hora_Salida, Tipo, Fecha_Cambio) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?,?,?,?,?)";
        java.sql.Date fechaCreacion = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos de Creacion de un Nuevo Gerente a la Tabla Historial_Cajero
            enviarDividido1.setInt(1, cajero.getCodigo());
            enviarDividido1.setString(2, cajero.getNombre());
            enviarDividido1.setString(3, cajero.getDPI());
            enviarDividido1.setString(4, cajero.getDireccion());
            enviarDividido1.setString(5, cajero.getSexo());
            enviarDividido1.setString(6, cajero.getPassword());
            enviarDividido1.setString(7, cajero.getTurno());
            enviarDividido1.setString(8, cajero.getHoraEntrada());
            enviarDividido1.setString(9, cajero.getHoraSalida());
            enviarDividido1.setString(10, "Edicion");
            enviarDividido1.setString(11, fechaCreacion.toString());
            enviarDividido1.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
}
