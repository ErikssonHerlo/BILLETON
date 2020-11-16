/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objetos.Cuenta;
import objetos.Transaccion;

/**
 *
 * @author erikssonherlo
 */
public class AccesoATransaccion {
    /**
     * Metodo que recibe un objeto de tipo transaccion, utilizado para el registro de transacciones dentro de la Base de Datos
     * Utilizado UNICAMENTE EN LA CARGA DEL ARCHIVO, ya que solo funciona como registro
     * CA = CARGA DEL ARCHIVO
     * @param transaccion
     * @return 
     */
    public boolean insertarNuevaTransaccionCA(Transaccion transaccion) {
        String queryDividido1 = "";
        if(transaccion.getTipo().equalsIgnoreCase("Credito"))
        {
         queryDividido1 = "INSERT INTO Transaccion(Codigo, Cuenta_No_Cuenta, Fecha, Hora, Tipo, Monto, Saldo_Cuenta_Anterior, Saldo_Cuenta_Actual, Cajero_Usuario_Codigo) "
                + "VALUES(?,?,?,?,?,?,(SELECT Saldo_Cuenta FROM Cuenta WHERE No_Cuenta = ?),(SELECT Saldo_Cuenta + ? FROM Cuenta WHERE No_Cuenta = ?),?)";
        } else if(transaccion.getTipo().equalsIgnoreCase("Debito")){
            queryDividido1 = "INSERT INTO Transaccion(Codigo, Cuenta_No_Cuenta, Fecha, Hora, Tipo, Monto, Saldo_Cuenta_Anterior, Saldo_Cuenta_Actual, Cajero_Usuario_Codigo) "
                + "VALUES(?,?,?,?,?,?,(SELECT Saldo_Cuenta FROM Cuenta WHERE No_Cuenta = ?),(SELECT Saldo_Cuenta - ? FROM Cuenta WHERE No_Cuenta = ?),?)";       
        
        }
        try {
            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales de una Transaccion a la Tabla Transaccion
            enviarDividido1.setInt(1, transaccion.getCodigo());
            enviarDividido1.setInt(2, transaccion.getNoCuenta());
            enviarDividido1.setString(3, transaccion.getFecha());
            enviarDividido1.setString(4, transaccion.getHora());
            enviarDividido1.setString(5, transaccion.getTipo());
            enviarDividido1.setDouble(6, transaccion.getMonto());
            
            enviarDividido1.setInt(7, transaccion.getNoCuenta());
            
            enviarDividido1.setDouble(8, transaccion.getMonto());
            enviarDividido1.setInt(9, transaccion.getNoCuenta());
            
            enviarDividido1.setInt(10, transaccion.getCodigoCajero());
            enviarDividido1.executeUpdate();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
}
