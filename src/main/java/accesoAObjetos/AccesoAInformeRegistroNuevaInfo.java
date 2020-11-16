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
import objetos.Cajero;
import informesDeVisualizacion.ReporteCreacionNuevoCliente;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAInformeRegistroNuevaInfo {
     /**
      * METODO PARA VISUALIZAR LA INFORMACION DEL CLIENTE, LUEGO DE SU CREACION DESDE LA VISTA
      * @param numeroCuenta
      * @return 
      */
       public ReporteCreacionNuevoCliente informeCreacionCliente(int numeroCuenta){
       
           ReporteCreacionNuevoCliente informe = null ;
             try {
            String query = "SELECT cl.Usuario_Codigo, cl.Nombre, cb.No_Cuenta, cb.Saldo_Cuenta FROM Cliente cl INNER JOIN Cuenta cb ON cl.Usuario_Codigo = cb.Cliente_Usuario_Codigo WHERE cb.No_Cuenta = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;
            enviar.setInt(1, numeroCuenta);
            rs = enviar.executeQuery();

            while (rs.next()) {
                
            informe = new ReporteCreacionNuevoCliente(rs.getInt("Usuario_Codigo"), rs.getString("Nombre"), rs.getInt("No_Cuenta"), rs.getDouble("Saldo_Cuenta"));

            }
            return informe;
        } catch (Exception e) {
            return null;
        }

       }
       
       /**
        * METODO PARA VISUALIZAR LA INFORMACION DEL CAJERO, LUEGO DE SU CREACION DESDE LA VISTA
        * @param codigoUsuario
        * @return 
        *
         public Cajero informeCreacionCajero(int codigoUsuario){
       
           Cajero informe = null;
             try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, u.Tipo_Usuario, c.Turno, c.Hora_Entrada, c.Hora_Salida, c.Estado FROM Usuario u INNER JOIN Cajero c ON u.Codigo = c.Usuario_Codigo WHERE c.Usuario_Codigo = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;
            enviar.setInt(1, codigoUsuario);
            rs = enviar.executeQuery();

            while (rs.next()) {
                
            //informe = new ReporteCreacionNuevoCliente(rs.getInt("Usuario_Codigo"), rs.getString("Nombre"), rs.getInt("No_Cuenta"), rs.getDouble("Saldo_Cuenta"));
            informe = new Cajero(rs.getInt(query), query, query, query, query, query, codigoUsuario, query, query, query, true)
            }
            return informe;
        } catch (Exception e) {
            return null;
        }

       }
       * */
}
