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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import objetos.Cajero;

import objetos.ClienteELIMINAR;
import objetos.Cliente;
import objetos.Cuenta;

/**
 *
 * @author erikssonherlo
 */
public class AccesoANuevoCliente {
    /**
     * Metodo que recibe un objeto de tipo Cliente, con todos sus atributos, para almacenarlo en la Base de Datos
     * Unicamente utilizado en la CARGA DEL ARCHIVO
     * CA = CARGA DE ARCHIVO
     * @param cliente
     * @return 
     */
    public boolean insertarNuevoClienteCA(Cliente cliente, List<Cuenta> listaCuentas) {
        String queryDividido1 = "INSERT INTO Usuario(Codigo, Nombre, DPI, Direccion, Sexo, Password, Tipo_Usuario) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?)";

        String queryDividido2 = "INSERT INTO Cliente(Usuario_Codigo, Nombre, Nacimiento, DPI_Escaneado, Estado) "
                + "VALUES(?,?,?,?,?)";
        
         String queryDividido3 = "INSERT INTO Cuenta(No_Cuenta, Fecha_Creacion, Saldo_Cuenta, Cliente_Usuario_Codigo) "
                + "VALUES(?,?,?,?)";
         int codigoCliente = cliente.getCodigo();
         try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales del cliente a la Tabla Usuario
            enviarDividido1.setInt(1, cliente.getCodigo());
            enviarDividido1.setString(2, cliente.getNombre());
            enviarDividido1.setString(3, cliente.getDPI());
            enviarDividido1.setString(4, cliente.getDireccion());
            enviarDividido1.setString(5, cliente.getSexo());
            enviarDividido1.setString(6, cliente.getPassword());
            enviarDividido1.setInt(7, 3);
            enviarDividido1.executeUpdate();

            //Envia los Datos Complementarios del Usuario a la tabla cliente
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            enviarDividido2.setInt(1, cliente.getCodigo());
            enviarDividido2.setString(2, cliente.getNombre());
            enviarDividido2.setString(3, cliente.getNacimiento());
            enviarDividido2.setBlob(4, cliente.getDPIEscaneado());
            enviarDividido2.setBoolean(5, cliente.isEstado());
            enviarDividido2.executeUpdate();
            
            //Envia los Datos de la Cuenta Adjudicada al Cliente
            PreparedStatement enviarDividido3 = Conexion.conexion.prepareStatement(queryDividido3);

            //Envio de los Datos Principales de una Cuenta a la Tabla Cuenta, perteneciente a un cliente en Especifico
            //Considerando que el cliente puede tener mas de una cuenta
             for (Cuenta cuenta : listaCuentas) {
                 
            enviarDividido3.setInt(1, cuenta.getNoCuenta());
            enviarDividido3.setString(2, cuenta.getFechaCreacion());
            enviarDividido3.setDouble(3, cuenta.getSaldo());
            enviarDividido3.setInt(4, codigoCliente);
            enviarDividido3.executeUpdate();
            }
            
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
    /**
     * METODO UTILIZADO PARA INSERTAR NUEVOS CLIENTES DESDE LA VISTA
     * @param cliente
     * @param listaCuentas
     * @return 
     */
    public boolean insertarNuevoClienteVista(Cliente cliente, Cuenta cuenta) {
        
        String queryDividido1 = "INSERT INTO Usuario(Codigo, Nombre, DPI, Direccion, Sexo, Password, Tipo_Usuario) "
                + "VALUES(?,?,?,?,?,aes_encrypt(?,'AES'),?)";

        String queryDividido2 = "INSERT INTO Cliente(Usuario_Codigo, Nombre, Nacimiento, DPI_Escaneado, Estado) "
                + "VALUES(?,?,?,?,?)";
        
         String queryDividido3 = "INSERT INTO Cuenta(No_Cuenta, Fecha_Creacion, Saldo_Cuenta, Cliente_Usuario_Codigo) "
                + "VALUES(?,?,?,?)";
       
         
         try {

            PreparedStatement enviarDividido1 = Conexion.conexion.prepareStatement(queryDividido1);

            //Envio de los Datos Principales del cliente a la Tabla Usuario
            enviarDividido1.setInt(1, cliente.getCodigo());
            enviarDividido1.setString(2, cliente.getNombre());
            enviarDividido1.setString(3, cliente.getDPI());
            enviarDividido1.setString(4, cliente.getDireccion());
            enviarDividido1.setString(5, cliente.getSexo());
            enviarDividido1.setString(6, cliente.getPassword());
            enviarDividido1.setInt(7, 3);
            enviarDividido1.executeUpdate();

            //Envia los Datos Complementarios del Usuario a la tabla cliente
            PreparedStatement enviarDividido2 = Conexion.conexion.prepareStatement(queryDividido2);
            enviarDividido2.setInt(1, cliente.getCodigo());
            enviarDividido2.setString(2, cliente.getNombre());
            enviarDividido2.setString(3, cliente.getNacimiento());
            enviarDividido2.setBlob(4, cliente.getDPIEscaneado());
            enviarDividido2.setBoolean(5, cliente.isEstado());
            enviarDividido2.executeUpdate();
            
            //Envia los Datos de la Cuenta Adjudicada al Cliente
            PreparedStatement enviarDividido3 = Conexion.conexion.prepareStatement(queryDividido3);

            //Envio de los Datos Principales de una Cuenta a la Tabla Cuenta, perteneciente a un cliente en Especifico
            enviarDividido3.setInt(1, cuenta.getNoCuenta());
            enviarDividido3.setString(2, cuenta.getFechaCreacion());
            enviarDividido3.setDouble(3, cuenta.getSaldo());
            enviarDividido3.setInt(4, cliente.getCodigo());
            enviarDividido3.executeUpdate();
            
            
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
    
   

     /**
      * METODO PARA VERIFICAR LA LECTURA DEL ARCHIVO
      * @param cliente
      * @param listaCuentas 
      */
     public void verificarDatosConsola(Cliente cliente, List<Cuenta> listaCuentas) {

         int codigoCliente = cliente.getCodigo();

             System.out.println("Codigo: "+cliente.getCodigo());
            System.out.println("Nombre: "+cliente.getNombre());
            System.out.println("DPI: "+cliente.getDPI());
            System.out.println("Direccion: "+cliente.getDireccion());
            System.out.println("Sexo: "+cliente.getSexo());
            System.out.println("Password: "+cliente.getPassword());
            System.out.println("Tipo de Usuario: "+ 3);

            System.out.println("Nacimiento: "+cliente.getNacimiento());
            System.out.println("ArchivoDPI: "+cliente.getDPIEscaneado());
            System.out.println("Estado: "+cliente.isEstado());
 
            
             for (Cuenta cuenta : listaCuentas) {
                 System.out.println("CUENTAS DEL CLIENTE");
                 System.out.println("Numero de Cuenta: "+cuenta.getNoCuenta());
                 System.out.println("Fecha de Creacion: "+cuenta.getFechaCreacion());
                 System.out.println("Saldo: "+cuenta.getSaldo());
                 System.out.println("Codigo del Dueño: "+codigoCliente);
            
            }
            


    }
     
     
     
       /**
     * Descripcion:
     * Metodo que recibe un Objeto de tipo Cliente, obtiene sus atributos y envia los datos de un Nuevo Cliente a la Base de Datos
     * para poder tener un Historial de Creacion del Cliente
     * 
     * Uso:
     * Utilizado para llevar un Registro de Creacion Nuevos Clientes en el Historial del Cliente, desde la Vista del Gerente, asi como desde la Carga del Archivo XML
       * @param cliente
       * @return 
       */
       public boolean insertarCreacionHistorialCliente(Cliente cliente) {
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
            enviarDividido1.setString(9, "Creacion");
            enviarDividido1.setString(10, fechaCreacion.toString());
            enviarDividido1.executeUpdate();

          
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }

    }
     }
