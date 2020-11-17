/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargaArchivos;


import accesoAObjetos.AccesoANuevoCajero;

import accesoAObjetos.AccesoANuevoCliente;

import accesoAObjetos.AccesoANuevoGerente;
import accesoAObjetos.AccesoATransaccion;
import com.sun.tools.doclint.Entity;
import configuracion.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import objetos.Cajero;

import objetos.ClienteELIMINAR;
import objetos.Cliente;

import objetos.Cuenta;


import objetos.Gerente;
import objetos.Transaccion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author erikssonherlo
 */
public class LecturaArchivo {

    /**
     * ORDEN DE CARGA DE ARCHIVO 1 Gerente | 1.1 Historial de Creacion Gerente |
     * 2 Cajero | 2.1 Historial de Creacion Cajero | 3 Cliente | 3.1 Historial
     * de Creacion Cliente | 3.2 Cuenta | 3.3 Asociacion entre Cuentas Propias del
     * Cliente | 4 Transaccion
     *
     * @param nombreArchivo
     */
    public void dividirEtiquetas(String pathArchivo) {

        try {

            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(new File(pathArchivo));

            // Obtengo todas las etiquetas PADRE del documento
            NodeList listadoGerentes = documento.getElementsByTagName("GERENTE");
            NodeList listadoCajeros = documento.getElementsByTagName("CAJERO");
            NodeList listadoClientes = documento.getElementsByTagName("CLIENTE");
            NodeList listadoTransacciones = documento.getElementsByTagName("TRANSACCION");

            etiquetaGerenteDB(listadoGerentes);
            etiquetaCajeroDB(listadoCajeros);
            etiquetaClienteDB(listadoClientes);
            etiquetaTransaccionDB(listadoTransacciones);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Recibe todos los atributos con la etiqueta Padre <GERENTE>, recorre las
     * etiquetas hijas, obteniendo sus atributos y envia dichos datos la base de
     * Datos DB
     *
     * @param listadoGerente
     */
    public void etiquetaGerenteDB(NodeList listadoGerente) {
        // Recorro las etiquetas
        System.out.println(" <========>Gerente");

        Gerente gerente;

        for (int i = 0; i < listadoGerente.getLength(); i++) {

            gerente = new Gerente(1, "", "", "", "", "", 1, "", "", "", true);

            // Cojo el nodo actual
            Node nodo = listadoGerente.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearGerente(gerente, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                /**
                 * Envio a la Base de Datos
                 */

                AccesoANuevoGerente nuevoGerente = new AccesoANuevoGerente();
                // Creacion de la Entidad Gerente
                nuevoGerente.insertarNuevoGerente(gerente);
                // Creacion del Historial de Creacion de la Entidad Gerente
               nuevoGerente.insertarCreacionHistorialGerente(gerente);
               nuevoGerente.insertarConfiguracionReportesPorDefecto(gerente.getCodigo());
                System.out.println("");
            }

        }
    }

    /**
     * Recibe todos los atributos con la etiqueta Padre <CAJERO>, recorre las
     * etiquetas hijas, obteniendo sus atributos y envia dichos datos la base de
     * Datos DB
     *
     * @param listadoCajero
     */
    public void etiquetaCajeroDB(NodeList listadoCajero) {
        // Recorro las etiquetas
        System.out.println(" <========>Cajero");

        Cajero cajero;

        for (int i = 0; i < listadoCajero.getLength(); i++) {

            cajero = new Cajero(1, "", "", "", "", "", 2, "", "", "", true);
            // Cojo el nodo actual
            Node nodo = listadoCajero.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearCajero(cajero, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                /**
                 * Envio a la Base de Datos
                 */

                AccesoANuevoCajero nuevoCajero = new AccesoANuevoCajero();
                // Creacion de la Entidad Cajero
                nuevoCajero.insertarNuevoCajero(cajero);

                // Creacion del Historial de Creacion de la Entidad Cajero
                nuevoCajero.insertarCreacionHistorialCajero(cajero);
                System.out.println("");
            }

        }
    }

    /**
     * Recibe todos los atributos con la etiqueta Padre <CLIENTE>, recorre las
     * etiquetas hijas, obteniendo sus atributos y envia dichos datos la base de
     * Datos DB
     * @param listadoPaciente 
     */
     public void etiquetaClienteDB(NodeList listadoPaciente) throws FileNotFoundException {
        // Recorro las etiquetas
        

        Cliente cliente;
        List<Cuenta> cuentas = new ArrayList<>();
        

        for (int i = 0; i < listadoPaciente.getLength(); i++) {
            cliente = new Cliente(1, "", "", "", "", "", 3, "", InputStream.nullInputStream(), true);
            
            cuentas.clear();
            // Cojo el nodo actual
            Node nodo = listadoPaciente.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    NodeList hijoCuentas = hijo.getChildNodes();
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                         if (hijo.getNodeName().equalsIgnoreCase("CUENTAS")) {
                            cuentas = etiquetaCuentasDelCliente(hijo);
                            

                        } else {
                            
                            crearCliente(cliente, hijo.getNodeName(), hijo.getTextContent());
                            
                        }
                    }

                }
                
                AccesoANuevoCliente nuevoCliente = new AccesoANuevoCliente();
                nuevoCliente.verificarDatosConsola(cliente,cuentas);
                //nuevoCliente.insertarNuevoClienteCA(cliente, cuentas);
                nuevoCliente.insertarNuevoClienteCA(cliente, cuentas);
                nuevoCliente.insertarCreacionHistorialCliente(cliente);
                System.out.println("");
            }

        }
    }
    
    
    
    public List<Cuenta> etiquetaCuentasDelCliente(Node cuentas) {
        // Recorro las etiquetas
        List<Cuenta> cuentasVariasCliente = new ArrayList<>();
        cuentasVariasCliente.clear();
        System.out.println("<========>CUENTAS VARIAS DEL CLIENTE<========>");
        // for (int i = 0; i < especialidad.getLength(); i++) {
        // Cojo el nodo actual
        Node nodo = cuentas;
        // Compruebo si el nodo es un elemento
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Lo transformo a Element
            Element e = (Element) nodo;
            // Obtengo sus hijos
            NodeList hijos = e.getChildNodes();
            // Recorro sus hijos
            for (int j = 0; j < hijos.getLength(); j++) {
                // Obtengo al hijo actual
                Node hijo = hijos.item(j);
                
                // Compruebo si es un nodo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Muestro el contenido
                    if (hijo.getNodeName().equalsIgnoreCase("CUENTA")) {
                        System.out.println("AQUI INGRESE A LA ETIQUETA CUENTA SINGULAR Y ENVIE EL HIJO AL SIGUIENTE METODO");
                        //cuentasVariasCliente = etiquetaCuentaCliente(hijo);
                        cuentasVariasCliente = etiquetaCuentaCliente(hijos);
                   // especilidadesMedicas.add(hijo.getTextContent());
                    } else {
                        System.out.println("Error al Ingresar a la Etiqueta CUENTA SINGULAR");
                    }
                    
                }

            }
            System.out.println("");
        }

        return cuentasVariasCliente;
    }

        public List<Cuenta> etiquetaCuentaCliente(NodeList cuentaUnica) {
        // Recorro las etiquetas
        Cuenta cuenta;
        List<Cuenta> cuentasUnicasCliente = new ArrayList<>();
        cuentasUnicasCliente.clear();
        System.out.println("<========>CUENTA UNICA DEL CLIENTE<========>");
        for (int i = 0; i < cuentaUnica.getLength(); i++) {
            cuenta = new Cuenta(1, "", 0.00, 1);
            
        // Cojo el nodo actual
        Node nodo = cuentaUnica.item(i);
        // Compruebo si el nodo es un elemento
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Lo transformo a Element
            Element e = (Element) nodo;
            // Obtengo sus hijos
            NodeList hijos = e.getChildNodes();
            // Recorro sus hijos
            for (int j = 0; j < hijos.getLength(); j++) {
                // Obtengo al hijo actual
                Node hijo = hijos.item(j);
                // Compruebo si es un nodo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Muestro el contenido
                    
                    //cuentasVariasCliente = etiquetaCuentaCliente(hijo);
                    //especilidadesMedicas.add(hijo.getTextContent());
                    
                            System.out.println("Etiqueta dentro de Cuenta: " + hijo.getNodeName()
                                    + ", Valor: " + hijo.getTextContent());
                            switch (hijo.getNodeName().toUpperCase()) {
                                case "CODIGO":
                                    cuenta.setNoCuenta(Integer.parseInt(hijo.getTextContent()));
                                    break;
                                case "CREADA":
                                    cuenta.setFechaCreacion(hijo.getTextContent());
                                    break;
                                case "CREDITO":
                                    cuenta.setSaldo(Double.parseDouble(hijo.getTextContent()));
                                    break;
                                default:
                                    System.out.println("Mostrar error, etiqueta no conocida");
                                //throw new AssertionError();
                            }
                            
                            
                }//AQUI CIERRA EL NODE.ELEMENTNODE

            }
            System.out.println("AGREGO EL OBJETO CUENTA AL ARRAYLIST");
            cuentasUnicasCliente.add(cuenta);
        }

        
        }
        return cuentasUnicasCliente;
    }
    /**
     * Recibe todos los atributos con la etiqueta Padre <TRANSACCION>, recorre las
     * etiquetas hijas, obteniendo sus atributos y envia dichos datos la base de
     * Datos DB
     * @param listadoTransaccion 
     */
  
            public void etiquetaTransaccionDB(NodeList listadoTransaccion) {
        // Recorro las etiquetas
        System.out.println(" <========>TRANSACCION");

        Transaccion transaccion ;

        for (int i = 0; i < listadoTransaccion.getLength(); i++) {

            transaccion = new Transaccion(1, 1, "", "", "", 0.00, 1);
            // Cojo el nodo actual
            Node nodo = listadoTransaccion.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido

                        System.out.println("Etiqueta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());
                        crearTransaccion(transaccion, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                /**
                 * Envio a la Base de Datos
                 */

                AccesoATransaccion nuevaTransaccion = new AccesoATransaccion();
                nuevaTransaccion.insertarNuevaTransaccionCA(transaccion);
                System.out.println("");
            }

        }
    }
    /**
     * ******************************************************************************************************
     * DIVISION ENTRE METODOS CON EL PROYECTO ANTERIOR
     */
     
   
    /**
     * *****************************DIVISION PARA CREACION DE ENTIDADES
     * *****************************BILLETON
     */
    /**
     * Metodo que recibe la etiqueta y el atributo y lo almacena dentro del
     * objeto gerente
     *
     * @param gerente = objeto
     * @param tag = etiqueta del Archivo XML
     * @param atributo = valor que llenara al objeto
     */
    public void crearGerente(Gerente gerente, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                gerente.setCodigo(Integer.parseInt(atributo));
                break;

            case "NOMBRE":
                gerente.setNombre(atributo);
                break;

            case "TURNO":
                if (atributo.equalsIgnoreCase("MATUTINO")) {
                    gerente.setTurno(atributo);
                    gerente.setHoraEntrada("7:00");
                    gerente.setHoraSalida("14:00");
                } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                    gerente.setTurno(atributo);
                    gerente.setHoraEntrada("14:00");
                    gerente.setHoraSalida("19:00");
                } else {
                    // No se reconoce si tiene un Horario Matutino o Vespertino 
                    System.out.println("Lanzar error, no se reconoce token");
                }
                break;

            case "DPI":
                gerente.setDPI(atributo);
                break;

            case "DIRECCION":
                gerente.setDireccion(atributo);
                break;

            case "SEXO":
                gerente.setSexo(atributo);
                break;

            case "PASSWORD":
                gerente.setPassword(atributo);
                break;

            default:
        }
    }

    /**
     * Metodo que recibe la etiqueta y el atributo y lo almacena dentro del
     * objeto cajero
     *
     * @param cajero = objeto
     * @param tag = etiqueta del Archivo XML
     * @param atributo = valor que llenara al objeto
     */
    public void crearCajero(Cajero cajero, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                cajero.setCodigo(Integer.parseInt(atributo));
                break;

            case "NOMBRE":
                cajero.setNombre(atributo);
                break;

            case "TURNO":
                if (atributo.equalsIgnoreCase("MATUTINO")) {
                    cajero.setTurno("Matutino");
                    cajero.setHoraEntrada("7:00");
                    cajero.setHoraSalida("14:00");
                } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                    cajero.setTurno("Vespertino");
                    cajero.setHoraEntrada("14:00");
                    cajero.setHoraSalida("19:00");
                } else {
                    // No se reconoce si tiene un Horario Matutino o Vespertino 
                    System.out.println("Lanzar error, no se reconoce token");
                }
                break;

            case "DPI":
                cajero.setDPI(atributo);
                break;

            case "DIRECCION":
                cajero.setDireccion(atributo);
                break;

            case "SEXO":
                cajero.setSexo(atributo);
                break;

            case "PASSWORD":
                cajero.setPassword(atributo);
                break;

            default:
        }
    }

    
      /**
     * Metodo que recibe la etiqueta y el atributo y lo almacena dentro del
     * objeto cliente
     *
     * @param cliente = objeto
     * @param tag = etiqueta del Archivo XML
     * @param atributo = valor que llenara al objeto
     */
    
    public void crearCliente(Cliente cliente, String tag, String atributo) throws FileNotFoundException {
   
        switch (tag.toUpperCase()) {
            case "CODIGO":
                cliente.setCodigo(Integer.parseInt(atributo));
                break;

            case "NOMBRE":
                cliente.setNombre(atributo);
                break;

            case "DPI":
                cliente.setDPI(atributo);
                break;
                
            case "BIRTH":
                cliente.setNacimiento(atributo);
                break;
                
            case "DIRECCION":
                cliente.setDireccion(atributo); 
                break;

            case "SEXO":
                cliente.setSexo(atributo);
                break;
                
             case "DPI-PDF":
                cliente.setDPIEscaneado(new FileInputStream(Constantes.getPATH_ABSOLUTO_ARCHIVOS()+atributo));
                break;
                
            case "PASSWORD":
                cliente.setPassword(atributo);
                break;

            default:
        }
    }
    
     /**
     * Metodo que recibe la etiqueta y el atributo y lo almacena dentro del
     * objeto transaccion
     *
     * @param transaccion = objeto
     * @param tag = etiqueta del Archivo XML
     * @param atributo = valor que llenara al objeto
     */
    public void crearTransaccion(Transaccion transaccion, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                transaccion.setCodigo(Integer.parseInt(atributo));
                break;

            case "CUENTA-ID":
                transaccion.setNoCuenta(Integer.parseInt(atributo));
                break;

            case "FECHA":
                transaccion.setFecha(atributo);               
                break;

            case "HORA":
                transaccion.setHora(atributo);
                break;

            case "TIPO":
                 if (atributo.equalsIgnoreCase("CREDITO")) {
                    transaccion.setTipo("Credito");
                 } else if (atributo.equalsIgnoreCase("DEBITO")) {
                    transaccion.setTipo("Debito");
                } else {
                    // No se reconoce el tipo de transaccion
                    System.out.println("Lanzar error, no se reconoce token");
                }
                  transaccion.setTipo(atributo);
                break;

            case "MONTO":
                transaccion.setMonto(Double.parseDouble(atributo));
                break;

            case "CAJERO-ID":
                transaccion.setCodigoCajero(Integer.parseInt(atributo));
                break;

            default:
        }
    }
    /**
     * ********************FINALIZAN LOS METODOS DE CREACION DE ENTIDADES
     * BILLETON
     *
     */
   
}
