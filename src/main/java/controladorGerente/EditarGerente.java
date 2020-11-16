/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoACodigoUsuario;
import accesoAObjetos.AccesoAEdicionCajero;
import accesoAObjetos.AccesoAEdicionCliente;
import accesoAObjetos.AccesoAEdicionGerente;
import accesoAObjetos.AccesoAInformeRegistroNuevaInfo;
import accesoAObjetos.AccesoANuevoCajero;
import configuracion.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Cajero;
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "EditarGerente", urlPatterns = {"/EditarGerente"})
public class EditarGerente extends HttpServlet {

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
            out.println("<title>Servlet EditarGerente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarGerente at " + request.getContextPath() + "</h1>");
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
      AccesoAEdicionGerente acceso = new AccesoAEdicionGerente();
        int codigoUsuario = (int)request.getSession().getAttribute("codigoUsuario");
            request.setAttribute("gerente", acceso.obtenerGerenteAEditar(codigoUsuario));


        RequestDispatcher despachar = request.getRequestDispatcher("EditarGerente_G.jsp");
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
        int codigo = Integer.parseInt(request.getParameter("codigoUsuarioEditado"));
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

            //GUARDAR ATRIBUTOS EN EL OBJETO
            Gerente nuevoGerente = new Gerente(codigo, nombre, DPI, direccion, sexo, password, 1, turno, horaEntrada, horaSalida, true);

            //INSTANCIA DE LA CLASE ENCARGADA DE EDITAR CAJEROS
            AccesoAEdicionGerente registrar = new AccesoAEdicionGerente();

            //INGRESO A LA BASE DE DATOS Y VALIDACION
            if (registrar.editarGerente(nuevoGerente)) {
                registrar.insertarEdicionHistorialGerente(nuevoGerente);
                System.out.println("SI SE INGRESO TODO CORRECTAMENTE");
                request.setAttribute("Exitoso", true);
                 request.getSession().setAttribute("nombreUsuario", nombre);
            } else {
                request.setAttribute("Exitoso", false);
            }

        }
        RequestDispatcher despachar = request.getRequestDispatcher("EditarCajero_G.jsp");
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
