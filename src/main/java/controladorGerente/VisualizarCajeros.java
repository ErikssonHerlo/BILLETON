/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoAVisualizacionCajeros;
import accesoAObjetos.AccesoAVisualizacionClientes;
import configuracion.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "VisualizarCajeros", urlPatterns = {"/VisualizarCajeros"})
public class VisualizarCajeros extends HttpServlet {

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
            out.println("<title>Servlet VisualizarCajeros</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VisualizarCajeros at " + request.getContextPath() + "</h1>");
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
       int codigoUsuario = 0;
       String nombre = Constantes.getStringUTF(request.getParameter("nombreUsuario"));
       
        String turno = Constantes.getStringUTF(request.getParameter("turnoUsuario"));
        String codigoUsuarioBusqueda = request.getParameter("codigoUsuario");
        
       //SI INGRESO TODOS LOS DATOS ADECUADAMENTE, PROCEDEMOS A REGISTRARLOS 
        
            AccesoAVisualizacionCajeros acceso = new AccesoAVisualizacionCajeros();
             
             if(!codigoUsuarioBusqueda.equals("") && nombre.equals("") && turno.equals(""))
             {
                 System.out.println("Busqueda solo por Codigo");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCajerosCodigo(codigoUsuario));
                 
             } else if(codigoUsuarioBusqueda.equals("") && !nombre.equals("") && turno.equals(""))
             {
                 System.out.println("Busqueda solo por Nombre");
                 request.setAttribute("Reporte", acceso.busquedaCajerosNombre(nombre));
             } else if(codigoUsuarioBusqueda.equals("") && nombre.equals("") && !turno.equals(""))
             {
                 System.out.println("Busqueda solo por Turno");
                 request.setAttribute("Reporte", acceso.busquedaCajerosTurno(turno));
             }  else if(!codigoUsuarioBusqueda.equals("") && !nombre.equals("") && turno.equals(""))
             {
                 System.out.println("Busqueda por Codigo y Nombre");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCajerosC_N(codigoUsuario, nombre));
                 
             }else if(!codigoUsuarioBusqueda.equals("") && nombre.equals("") && !turno.equals(""))
             {
                 System.out.println("Busqueda por Codigo y Turno");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCajerosC_T(codigoUsuario, turno));
                 
             } else if(codigoUsuarioBusqueda.equals("") && !nombre.equals("") && !turno.equals(""))
             {
                 System.out.println("Busqueda por Nombre y Turno");
                 
                 request.setAttribute("Reporte", acceso.busquedaCajerosN_T(nombre, turno));
                 
             }else if(!codigoUsuarioBusqueda.equals("") && !nombre.equals("") && !turno.equals(""))
             {
                 System.out.println("Busqueda por Codigo, Nombre y Turno");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCajerosC_N_T(codigoUsuario, nombre, turno));
                 
             }  else {
             
                 System.out.println("Busqueda General");
                 request.setAttribute("Reporte", acceso.busquedaGeneralCajeros());
                 
             }
           
                  
        RequestDispatcher despachar = request.getRequestDispatcher("VisualizarCajeros_G.jsp");
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
        processRequest(request, response);
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
