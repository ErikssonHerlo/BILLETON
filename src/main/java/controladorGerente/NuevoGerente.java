/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoACodigoUsuario;
import accesoAObjetos.AccesoANuevaCuentaBancaria;
import accesoAObjetos.AccesoAInformeRegistroNuevaInfo;
import accesoAObjetos.AccesoANuevoCajero;
import accesoAObjetos.AccesoANuevoCliente;
import accesoAObjetos.AccesoANuevoGerente;
import configuracion.Constantes;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import objetos.Cajero;
import objetos.Cliente;
import objetos.Cuenta;
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "NuevoGerente", urlPatterns = {"/NuevoGerente"})
public class NuevoGerente extends HttpServlet {

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
            out.println("<title>Servlet NuevoCajero</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevoCajero at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String nombre = Constantes.getStringUTF(request.getParameter("nombreUsuario"));
        String DPI = request.getParameter("DPIUsuario");
        String direccion = Constantes.getStringUTF(request.getParameter("direccionUsuario"));
        String sexo = request.getParameter("sexoUsuario");
        String turno = request.getParameter("horarioUsuario");
        String password = Constantes.getStringUTF(request.getParameter("passwordUsuario"));
        String horaEntrada = "";
        String horaSalida = "";
        if (turno.equals("Matutino")) {
            horaEntrada = Constantes.getMATUTINO_HORARIO_ENTRADA();
            horaSalida = Constantes.getMATUTINO_HORARIO_SALIDA();
        } else if (turno.equals("Vespertino")) {
            horaEntrada = Constantes.getVESPERTINO_HORARIO_ENTRADA();
            horaSalida = Constantes.getVESPERTINO_HORARIO_SALIDA();

        }
        System.out.println("NOMBRE " + nombre);

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

            //OBTIENE EL ULTIMO REGISTRO DE LA TABLA USUARIOS, PARA OBTENER EL NUEVO CODIGO DE USUARIO
            AccesoACodigoUsuario nuevoCodigo = new AccesoACodigoUsuario();
            int codigoUsuario = nuevoCodigo.obtenerCodigoUsuarioNuevoRegistro();

            //GUARDAR ATRIBUTOS EN EL OBJETO
            Gerente nuevoGerente = new Gerente(codigoUsuario, nombre, DPI, direccion, sexo, password, 1, turno, horaEntrada, horaSalida, true);
            
            //INSTANCIA DE LA CLASE ENCARGADA DE REGISTRAR NUEVOS GERENTES
            AccesoANuevoGerente registrar = new AccesoANuevoGerente();
            //INGRESO A LA BASE DE DATOS Y VALIDACION
            if (registrar.insertarNuevoGerente(nuevoGerente)) {
                registrar.insertarCreacionHistorialGerente(nuevoGerente);
                registrar.insertarConfiguracionReportesPorDefecto(codigoUsuario);
                System.out.println("SI SE INGRESO TODO CORRECTAMENTE");
                request.setAttribute("Exitoso", true);
                request.setAttribute("informeRegistro", nuevoGerente);
            } else {
                request.setAttribute("Exitoso", false);
            }

        }
        RequestDispatcher despachar = request.getRequestDispatcher("NuevoGerente_G.jsp");
            despachar.forward(request, response);
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
