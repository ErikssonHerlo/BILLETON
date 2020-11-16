/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import configuracion.Constantes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoANuevoGerente {

    /**
     * Descripcion: Metodo que recibe un Objeto de tipo Gerente, obtiene sus
     * atributos y envia los datos de un Nuevo Gerente a la Base de Datos
     *
     * Uso: Utilizado para la Creacion de Nuevos Gerentes desde la Vista del
     * Gerente, asi como desde la Carga del Archivo XML
     *
     * @param gerente
     * @return
     */
    public boolean insertarNuevoGerente(Gerente gerente) {
        String queryDividido1 = "INSERT INTO Usuario(Codigo, Nombre, DPI, Direccion, Sexo, Password, Tipo_Usuario) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?)";

        String queryDividido2 = "INSERT INTO Gerente(Usuario_Codigo, Nombre, Turno, Hora_Entrada, Hora_Salida, Estado) "
                + "VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales del Gerente a la Tabla Usuario
            enviarDividido1.setInt(1, gerente.getCodigo());
            enviarDividido1.setString(2, gerente.getNombre());
            enviarDividido1.setString(3, gerente.getDPI());
            enviarDividido1.setString(4, gerente.getDireccion());
            enviarDividido1.setString(5, gerente.getSexo());
            enviarDividido1.setString(6, gerente.getPassword());
            enviarDividido1.setInt(7, 1);
            enviarDividido1.executeUpdate();

            //Envia los Datos Complementarios del Usuario a la tabla Gerente
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            enviarDividido2.setInt(1, gerente.getCodigo());
            enviarDividido2.setString(2, gerente.getNombre());
            enviarDividido2.setString(3, gerente.getTurno());
            enviarDividido2.setString(4, gerente.getHoraEntrada());
            enviarDividido2.setString(5, gerente.getHoraSalida());
            enviarDividido2.setBoolean(6, gerente.isEstado());
            enviarDividido2.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }

    /**
     * Descripcion: Metodo que recibe un Objeto de tipo Gerente, obtiene sus
     * atributos y envia los datos de un Nuevo Gerente a la Base de Datos para
     * poder tener un Historial de Creacion del Gerente
     *
     * Uso: Utilizado para llevar un Registro de Creacion Nuevos Gerentes en el
     * Historial del Gerente, desde la Vista del Gerente, asi como desde la
     * Carga del Archivo XML
     *
     * @param gerente
     * @return
     */
    public boolean insertarCreacionHistorialGerente(Gerente gerente) {
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
            enviarDividido1.setString(10, "Creacion");
            enviarDividido1.setString(11, fechaCreacion.toString());
            enviarDividido1.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
    
    
    /**
     * METODO PARA INSERTAR LA CONFIGURACION DE LOS REPORTES PARA CADA NUEVO GERENTE
     * @param codigoGerente
     * @return 
     */
    public boolean insertarConfiguracionReportesPorDefecto(int codigoGerente) {
        String queryDividido1 = "INSERT INTO Configuracion_Reportes(Limite_Reporte2, Limite_Reporte3, Gerente_Usuario_Codigo)"
                + "VALUES(?,?,?)";
        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos de Creacion de un Nuevo Gerente a la Tabla Historial_Gerente
            enviarDividido1.setDouble(1, Constantes.getLIMITE_REPORTE_2_DEFECTO());
            enviarDividido1.setDouble(2, Constantes.getLIMITE_REPORTE_3_DEFECTO());
            enviarDividido1.setInt(3, codigoGerente);
            enviarDividido1.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
/**
 * Verifica la Existencia de Gerentes en la Base de Datos, para el Inicio de las Vistas
 * @return 
 */
    public boolean verificarEstadoDB() {

        Gerente gerente = null;

        ResultSet rs = Conexion.getTabla("SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, u.Password, u.Tipo_Usuario, g.Turno, g.Hora_Entrada, g.Hora_Salida, g.Estado FROM Usuario u INNER JOIN Gerente g ON g.Usuario_Codigo = u.Codigo");
        try {
            while (rs.next()) {
                gerente = new Gerente(rs.getInt("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Direccion"), rs.getString("Sexo"), rs.getString("Password"), rs.getInt("Tipo_Usuario"), rs.getString("Turno"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getBoolean("Estado"));

            }

        } catch (Exception e) {

        }
        if (gerente == null) {
            return false;
        } else {
            return true;
        }

    }
}
