/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import objetos.Cajero;
import objetos.Gerente;


/**
 *
 * @author erikssonherlo
 */
public class AccesoANuevoCajero {
    /**
     * Descripcion:
     * Metodo que recibe un Objeto de tipo Cajero, obtiene sus atributos y envia los datos de un Nuevo Cajero a la Base de Datos
     * 
     * Uso:
     * Utilizado para la Creacion de Nuevos Cajeros desde la Vista del Gerente, asi como desde la Carga del Archivo XML 
     * @param cajero
     * @return 
     */
        public boolean insertarNuevoCajero(Cajero cajero) {
        String queryDividido1 = "INSERT INTO Usuario(Codigo, Nombre, DPI, Direccion, Sexo, Password, Tipo_Usuario) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?)";

        String queryDividido2 = "INSERT INTO Cajero(Usuario_Codigo, Nombre, Turno, Hora_Entrada, Hora_Salida, Estado) "
                + "VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales del cajero a la Tabla Usuario
            enviarDividido1.setInt(1, cajero.getCodigo());
            enviarDividido1.setString(2, cajero.getNombre());
            enviarDividido1.setString(3, cajero.getDPI());
            enviarDividido1.setString(4, cajero.getDireccion());
            enviarDividido1.setString(5, cajero.getSexo());
            enviarDividido1.setString(6, cajero.getPassword());
            enviarDividido1.setInt(7, 2);
            enviarDividido1.executeUpdate();

            //Envia los Datos Complementarios del Usuario a la tabla cajero
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            enviarDividido2.setInt(1, cajero.getCodigo());
            enviarDividido2.setString(2, cajero.getNombre());
            enviarDividido2.setString(3, cajero.getTurno());
            enviarDividido2.setString(4, cajero.getHoraEntrada());
            enviarDividido2.setString(5, cajero.getHoraSalida());
            enviarDividido2.setBoolean(6, cajero.isEstado());
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
     * Utilizado para llevar un Registro de Creacion Nuevos Cajeros en el Historial del Cajero, desde la Vista del Gerente, asi como desde la Carga del Archivo XML
       * @param cajero
       * @return 
       */
       public boolean insertarCreacionHistorialCajero(Cajero cajero) {
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
            enviarDividido1.setString(10, "Creacion");
            enviarDividido1.setString(11, fechaCreacion.toString());
            enviarDividido1.executeUpdate();

          
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
}
