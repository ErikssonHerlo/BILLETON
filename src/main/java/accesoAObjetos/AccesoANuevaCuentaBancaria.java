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
import java.util.List;
import objetos.ClienteELIMINAR;
import objetos.Cuenta;

/**
 *
 * @author erikssonherlo
 */
public class AccesoANuevaCuentaBancaria {
    /**
 * METODO QUE RECIBE UN OBJETO DE TIPO CUENTA, OBTIENE SUS ATRIBUTOS Y ENVIA LOS DATOS DE UNA NUEVA CUENTA, PERTENECIENTE A UN CLIENTE A LA BASE DE DATOS
 * UTILIZADO PARA LA CREACION DE NUEVAS CUENTAS DESDE LA VISTA DEL GERENTE
 * @param Cuenta
 * @return 
 */
    public boolean insertarNuevaCuenta(Cuenta cuenta) {
        String queryDividido1 = "INSERT INTO Cuenta(No_Cuenta, Fecha_Creacion, Saldo_Cuenta, Cliente_Usuario_Codigo) "
                + "VALUES(?,?,?,?)";
        
        try {
            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales de una Cuenta a la Tabla Cuenta
            enviarDividido1.setInt(1, cuenta.getNoCuenta());
            enviarDividido1.setString(2, cuenta.getFechaCreacion());
            enviarDividido1.setDouble(3, cuenta.getSaldo());
            enviarDividido1.setInt(4, cuenta.getCodigoCliente());
            enviarDividido1.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
    
   /**
     * METODO UTILIZADO PARA OBTENER EL ULTIMO NUMERO DE CUENTA DISPONIBLE, PARA REGISTRAR 
     * UNA NUEVA CUENTA BANCARIA
     * @return 
     */
       public int obtenerCodigoCuentaBancariaNuevoRegistro() {

           int numeroCuenta = 0;
        try {
            String query = "SELECT MAX(No_Cuenta)+1 FROM Cuenta";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                
                numeroCuenta = rs.getInt("MAX(No_Cuenta)+1");

            }
            return numeroCuenta;

        } catch (Exception e) {
            return numeroCuenta;
        }

    }
}
