/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author erikssonherlo
 */
public class AccesoACodigoUsuario {
     /**
     * METODO UTILIZADO PARA OBTENER EL ULTIMO CODIGO DISPONIBLE DE USUARIO DE UN TIPO, PARA REGISTRAR TODOS LOS 
     * USUARIOS CON EL MISMO CODIGO
     * @return 
     */
       public int obtenerCodigoUsuarioNuevoRegistro() {

           int codigoUsuario = 0;
        try {
            String query = "SELECT MAX(Codigo)+1 FROM Usuario";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            rs = enviar.executeQuery();

            while (rs.next()) {
                
                codigoUsuario = rs.getInt("MAX(Codigo)+1");

            }
            return codigoUsuario;

        } catch (Exception e) {
            return codigoUsuario;
        }

    }
}
