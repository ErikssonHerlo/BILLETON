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
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAEdicionGerente {
     public Gerente obtenerGerenteAEditar(int codigoGerente) {

        Gerente gerente = null;

        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, CAST(aes_decrypt(u.Password, 'AES') AS CHAR(100)) Password_Descript, u.Tipo_Usuario, g.Turno, g.Hora_Entrada, g.Hora_Salida, g.Estado FROM Usuario u INNER JOIN Gerente g ON u.Codigo = g.Usuario_Codigo WHERE g.Usuario_Codigo = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            enviar.setInt(1, codigoGerente);

            rs = enviar.executeQuery();

            while (rs.next()) {
                gerente = new Gerente(rs.getInt("Codigo"), 
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

        return gerente;
    }
     /**
      * METODO PARA EDITAR GERENTE DESDE SU VISTA 
      * @param gerente
      * @return 
      */
       public boolean editarGerente(Gerente gerente) {
        String queryDividido1 = "UPDATE Usuario SET Nombre = ?, DPI= ?, Direccion=?, Sexo=?, Password=aes_encrypt(?,'AES') WHERE Codigo = ?";
    
        String queryDividido2 = "UPDATE Gerente SET Nombre=?, Turno=?, Hora_Entrada=?, Hora_Salida=? WHERE Usuario_Codigo = ? ";

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales Editados del gerente a la Tabla Usuario
            
            enviarDividido1.setString(1, gerente.getNombre());
            enviarDividido1.setString(2, gerente.getDPI());
            enviarDividido1.setString(3, gerente.getDireccion());
            enviarDividido1.setString(4, gerente.getSexo());
            enviarDividido1.setString(5, gerente.getPassword());
            enviarDividido1.setInt(6, gerente.getCodigo());
            enviarDividido1.executeUpdate();

            //Envia los Datos Complementarios del Usuario a la tabla gerente
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            
            enviarDividido2.setString(1, gerente.getNombre());
            enviarDividido2.setString(2, gerente.getTurno());
            enviarDividido2.setString(3, gerente.getHoraEntrada());
            enviarDividido2.setString(4, gerente.getHoraSalida());
            enviarDividido2.setInt(5, gerente.getCodigo());
            enviarDividido2.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
        
     /**
     * Descripcion:
     * Metodo que recibe un Objeto de tipo Gerente, obtiene sus atributos y envia los datos de un Nuevo Gerente a la Base de Datos
     * para poder tener un Historial de Creacion del Gerente
     * 
     * Uso:
     * Utilizado para llevar un Registro de Edicion de Gerentes en el Historial del Gerente, desde la Vista del Gerente
       * @param gerente
       * @return 
       */
       public boolean insertarEdicionHistorialGerente(Gerente gerente) {
        String queryDividido1 = "INSERT INTO Historial_Gerente(Usuario_Codigo, Nombre, DPI, Direccion, Sexo, Password, Turno, Hora_Entrada, Hora_Salida, Tipo, Fecha_Cambio) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?,?,?,?,?)";
        java.sql.Date fechaCreacion = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos de Creacion de un Nuevo Gerente a la Tabla Historial_Gerente
            enviarDividido1.setInt(1, gerente.getCodigo());
            enviarDividido1.setString(2, gerente.getNombre());
            enviarDividido1.setString(3, gerente.getDPI());
            enviarDividido1.setString(4, gerente.getDireccion());
            enviarDividido1.setString(5, gerente.getSexo());
            enviarDividido1.setString(6, gerente.getPassword());
            enviarDividido1.setString(7, gerente.getTurno());
            enviarDividido1.setString(8, gerente.getHoraEntrada());
            enviarDividido1.setString(9, gerente.getHoraSalida());
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
