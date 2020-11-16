/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoACodigoUsuario;
import accesoAObjetos.AccesoAEdicionCliente;
import accesoAObjetos.AccesoAInformeRegistroNuevaInfo;
import accesoAObjetos.AccesoANuevaCuentaBancaria;
import accesoAObjetos.AccesoANuevoCliente;
import configuracion.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.soap.SOAPFault;
import objetos.Cliente;
import objetos.Cuenta;

/**
 *
 * @author erikssonherlo
 */
@MultipartConfig(maxFileSize = 16177215) //Maximo = 16mb
@WebServlet(name = "EditarCliente", urlPatterns = {"/EditarCliente"})
public class EditarCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditarCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigoCliente = Integer.parseInt(request.getParameter("codigoCliente"));

        AccesoAEdicionCliente editarCliente = new AccesoAEdicionCliente();
        request.setAttribute("cliente", editarCliente.obtenerClienteAEditar(codigoCliente));

        RequestDispatcher despachar = request.getRequestDispatcher("EditarCliente_G.jsp");
        despachar.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean editarEscaner = false;

        int codigo = Integer.parseInt(request.getParameter("codigoUsuarioEditado"));

        String nombre = new String(request.getParameter("nombreUsuario").getBytes("ISO-8859-1"), "UTF-8");

        String DPI = request.getParameter("DPIUsuario");
        String direccion = new String(request.getParameter("direccionUsuario").getBytes("ISO-8859-1"), "UTF-8");
        String sexo = request.getParameter("sexoUsuario");
        String nacimiento = request.getParameter("nacimientoUsuario");
        String password = Constantes.getStringUTF(request.getParameter("passwordUsuario"));

        System.out.println("Nombre" + nombre);
        System.out.println("Direccion " + direccion);
        //DPI ESCANEADO
        Part archivo = request.getPart("DPIEscaneado");
        String nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
        System.out.println("Path del Archivo: " + nombreArchivo);

        if (!nombreArchivo.equals("")) {
            guardarArchivos(archivo, nombreArchivo);
            System.out.println("EL USUARIO ELIJIO GUARDAR UN NUEVO DPI EN FORMATO PDF");
            editarEscaner = true;
        } else {
            System.out.println("EL USUARIO ELIJIO NO EDITAR EL DPI");
            editarEscaner = false;

        }

        //VALIDACION PARA EVITAR QUE INGRESE ESPACIOS EN BLANCO
        if (nombre.trim().equals("")) {
            System.out.println("ERROR, SOLO INGRESO UN ESPACIO EN BLANCO");
            request.setAttribute("Exitoso", false);
        } else if (direccion.trim().equals("")) {
            System.out.println("ERROR, SOLO INGRESO UN ESPACIO EN BLANCO");
            request.setAttribute("Exitoso", false);
        } else if (password.trim().equals("")) {
            System.out.println("ERROR, SOLO INGRESO UN ESPACIO EN BLANCO");
            request.setAttribute("Exitoso", false);
        }//SI INGRESO TODOS LOS DATOS ADECUADAMENTE, PROCEDEMOS A REGISTRARLOS 
        else {
            //INSTANCIA DE LA CLASE ENCARGADA DE EDITAR CLIENTES
            AccesoAEdicionCliente registrar = new AccesoAEdicionCliente();
            Constantes constante = new Constantes();
            //INSTANCIA DE LA CLASE ENCARGADA DE PRESENTAR EL INFORME DEL REGISTRO DEL CLIENTE
            Cliente nuevoCliente;

            if (editarEscaner) {
                System.out.println("ELJIO TRUE, GUARDAR UN NUEVO DPI");
                
                nuevoCliente = new Cliente(codigo, nombre, DPI, direccion, sexo, password, 3, nacimiento, new FileInputStream(constante.getPATH_ABSOLUTO_ARCHIVOS()+nombreArchivo), true);
                if (registrar.editarClienteConDPI(nuevoCliente)){
                    System.out.println("Guardar DPI");
                        registrar.insertarEdicionHistorialClienteConDPI(nuevoCliente);
                    

                    request.setAttribute("Exitoso", true);
                    //            request.setAttribute("informeRegistro", informe.informeCreacionCliente(numeroCuenta));
                } else {
                    request.setAttribute("Exitoso", false);
                }

            } else {
                System.out.println("ELJIO FALSE NO DEBO GUARDAR DPI");
                nombreArchivo = "Carne Universitario Eriksson.pdf";
                //GUARDAR ATRIBUTOS EN EL OBJETO
                nuevoCliente = new Cliente(codigo, nombre, DPI, direccion, sexo, password, 3, nacimiento, InputStream.nullInputStream(), true);
                if (registrar.insertarEdicionHistorialClienteSinDPI(nuevoCliente)) {
                    registrar.editarClienteSinDPI(nuevoCliente);

                    request.setAttribute("Exitoso", true);
                    //            request.setAttribute("informeRegistro", informe.informeCreacionCliente(numeroCuenta));
                } else {
                    request.setAttribute("Exitoso", false);
                }
            }

        }
        RequestDispatcher despachar = request.getRequestDispatcher("EditarCliente_G.jsp");
        despachar.forward(request, response);
    }
private void guardarArchivos(Part filePart,String rutaArchivo){
        
        File rutaDestino = new File(Constantes.getPATH_ABSOLUTO_ARCHIVOS());
        File file = new File(rutaDestino,rutaArchivo);

        try(InputStream inputS = filePart.getInputStream()) {
            
            Files.copy(inputS, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
}
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
