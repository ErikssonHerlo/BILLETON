/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoAObjetos;

import conexionMySQL.Conexion;
import java.io.InputStream;
import static java.nio.file.Paths.get;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import objetos.Cliente;
import objetos.Cuenta;

/**
 *
 * @author erikssonherlo
 */
public class AccesoAEdicionCliente {

    /**
     * METODO QUE BUSCA EL CLIENTE QUE VAMOS A EDITAR DESDE LA VISTA DEL GERENTE
     *
     * @return
     */
    public Cliente obtenerClienteAEditar(int codigoCliente) {

        Cliente cliente = null;

        try {
            String query = "SELECT u.Codigo, u.Nombre, u.DPI, u.Direccion, u.Sexo, CAST(aes_decrypt(u.Password, 'AES') AS CHAR(100)) Password_Descript, u.Tipo_Usuario, cl.Nacimiento, cl.DPI_Escaneado, cl.Estado FROM Usuario u INNER JOIN Cliente cl ON cl.Usuario_Codigo = u.Codigo WHERE cl.Usuario_Codigo = ?";
            PreparedStatement enviar = Conexion.conexion.prepareStatement(query);
            ResultSet rs = null;

            enviar.setInt(1, codigoCliente);

            rs = enviar.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(rs.getInt("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Direccion"), rs.getString("Sexo"), rs.getString("Password_Descript"), rs.getInt("Tipo_Usuario"), rs.getString("Nacimiento"), rs.getBinaryStream("DPI_Escaneado"), rs.getBoolean("Estado"));

            }

        } catch (Exception e) {

        }

        return cliente;
    }

/**
 * METODO UTILIZADO PARA EDITAR AL CLIENTE SIN CAMBIAR EL DPI
 * @param cliente
 * @return 
 */
    public boolean editarClienteSinDPI(Cliente cliente) {

        String queryDividido1 = "UPDATE Usuario SET Nombre= ?, DPI= ?, Direccion= ?, Sexo= ?, Password=aes_encrypt(?,'AES') WHERE Codigo = ?";

        String queryDividido2 = "UPDATE Cliente SET Nombre=?, Nacimiento=? WHERE Usuario_Codigo =?";

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //ENVIAR LA EDICION DE LOS DATOS A LA TABLA USUARIO
            enviarDividido1.setString(1, cliente.getNombre());
            enviarDividido1.setString(2, cliente.getDPI());
            enviarDividido1.setString(3, cliente.getDireccion());
            enviarDividido1.setString(4, cliente.getSexo());
            enviarDividido1.setString(5, cliente.getPassword());
            enviarDividido1.setInt(6, cliente.getCodigo());
            enviarDividido1.executeUpdate();

            //ENVIA LA EDICION DE LOS DATOS A LA TABLA CLIENTE
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);

            enviarDividido2.setString(1, cliente.getNombre());
            enviarDividido2.setString(2, cliente.getNacimiento());
            enviarDividido2.setInt(3, cliente.getCodigo());
            enviarDividido2.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
    
    /**
     * METODO UTILIZADO PARA LA EDICION DE CLIENTES CON DPI NUEVO
     * @param cliente
     * @return 
     */
     public boolean editarClienteConDPI(Cliente cliente) {

        String queryDividido1 = "UPDATE Usuario SET Nombre= ?, DPI= ?, Direccion= ?, Sexo= ?, Password=aes_encrypt(?,'AES') WHERE Codigo = ?";

        String queryDividido2 = "UPDATE Cliente SET Nombre=?, Nacimiento=?, DPI_Escaneado= ? WHERE Usuario_Codigo =?";

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //ENVIAR LA EDICION DE LOS DATOS A LA TABLA USUARIO
            enviarDividido1.setString(1, cliente.getNombre());
            enviarDividido1.setString(2, cliente.getDPI());
            enviarDividido1.setString(3, cliente.getDireccion());
            enviarDividido1.setString(4, cliente.getSexo());
            enviarDividido1.setString(5, cliente.getPassword());
            enviarDividido1.setInt(6, cliente.getCodigo());
            enviarDividido1.executeUpdate();

            //ENVIA LA EDICION DE LOS DATOS A LA TABLA CLIENTE
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);

            enviarDividido2.setString(1, cliente.getNombre());
            enviarDividido2.setString(2, cliente.getNacimiento());
            enviarDividido2.setBlob(3, cliente.getDPIEscaneado());
            enviarDividido2.setInt(4, cliente.getCodigo());
            enviarDividido2.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
    
    
    /**
     * METODO UTILIZADO PARA GUARDAR EL HISTORIAL DE EDICION DEL CLIENTE
     * @param cliente
     * @return 
     */
     public boolean insertarEdicionHistorialClienteSinDPI(Cliente cliente) {
        String queryDividido1 = "INSERT INTO Historial_Cliente(Usuario_Codigo, Nombre, DPI, Direccion, Sexo, Password, Nacimiento, DPI_Escaneado, Tipo, Fecha_Cambio) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?,"
                + "(SELECT DPI_Escaneado FROM Cliente WHERE Usuario_Codigo = ?),?,?)";
        java.sql.Date fechaCreacion = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos de Creacion de un Nuevo Cliente a la Tabla Historial_Cliente
            enviarDividido1.setInt(1, cliente.getCodigo());
            enviarDividido1.setString(2, cliente.getNombre());
            enviarDividido1.setString(3, cliente.getDPI());
            enviarDividido1.setString(4, cliente.getDireccion());
            enviarDividido1.setString(5, cliente.getSexo());
            enviarDividido1.setString(6, cliente.getPassword());
            enviarDividido1.setString(7, cliente.getNacimiento());
            enviarDividido1.setInt(8, cliente.getCodigo());
            enviarDividido1.setString(9, "Edicion");
            enviarDividido1.setString(10, fechaCreacion.toString());
            enviarDividido1.executeUpdate();

          
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
     /**
      * INGRESAR UN HISTORIAL DE CAMBIOS CON DPI
      * @param cliente
      * @return 
      */
      public boolean insertarEdicionHistorialClienteConDPI(Cliente cliente) {
        String queryDividido1 = "INSERT INTO Historial_Cliente(Usuario_Codigo, Nombre, DPI, Direccion, Sexo, Password, Nacimiento, DPI_Escaneado, Tipo, Fecha_Cambio) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?,?,?,?)";
        java.sql.Date fechaCreacion = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos de Creacion de un Nuevo Cliente a la Tabla Historial_Cliente
            enviarDividido1.setInt(1, cliente.getCodigo());
            enviarDividido1.setString(2, cliente.getNombre());
            enviarDividido1.setString(3, cliente.getDPI());
            enviarDividido1.setString(4, cliente.getDireccion());
            enviarDividido1.setString(5, cliente.getSexo());
            enviarDividido1.setString(6, cliente.getPassword());
            enviarDividido1.setString(7, cliente.getNacimiento());
            enviarDividido1.setBlob(8, cliente.getDPIEscaneado());
            enviarDividido1.setString(9, "Edicion");
            enviarDividido1.setString(10, fechaCreacion.toString());
            enviarDividido1.executeUpdate();

          
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }

}
