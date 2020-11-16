/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoAVisualizacionClientes;
import com.sun.tools.doclint.Entity;
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
@WebServlet(name = "VisualizarClientes", urlPatterns = {"/VisualizarClientes"})
public class VisualizarClientes extends HttpServlet {

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
            out.println("<title>Servlet VisualizarClientes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VisualizarClientes at " + request.getContextPath() + "</h1>");
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
        String nombre = new String(request.getParameter("nombreUsuario").getBytes("ISO-8859-1"), "UTF-8");
        int codigoUsuario = 0;
        //codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
        String codigoUsuarioBusqueda = request.getParameter("codigoUsuario");
        
       //SI INGRESO TODOS LOS DATOS ADECUADAMENTE, PROCEDEMOS A REGISTRARLOS 
        
            AccesoAVisualizacionClientes acceso = new AccesoAVisualizacionClientes();
             
             
             if(!codigoUsuarioBusqueda.equals("") && nombre.equals(""))
             {
                 System.out.println("Busqueda solo por Codigo");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCodigoClientes(codigoUsuario));
                 
             } else if(codigoUsuarioBusqueda.equals("") && !nombre.equals(""))
             {
                 System.out.println("Busqueda solo por Nombre");
                 request.setAttribute("Reporte", acceso.busquedaNombreClientes(nombre));
             } else if(!codigoUsuarioBusqueda.equals("") && !nombre.equals(""))
             {
                 System.out.println("Busqueda por Nombre y Codigo");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCodigoNombreClientes(codigoUsuario, nombre));
                 
             } else {
             
                 System.out.println("Busqueda General");
                 request.setAttribute("Reporte", acceso.busquedaGeneralClientes());
                 
             }
             /**
             
        if(!nombre.equals("") && titulo.equals("") && horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaN(nombre));
            System.out.println("Busqueda por Nombre");
        } 
        else if(nombre.equals("") && !titulo.equals("") && horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaE(titulo));
            System.out.println("Busqueda por Especialidad");
        } 
        else if(nombre.equals("") && titulo.equals("") && !horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaH(horario));
        System.out.println("Busqueda por Horario");
        } 
        else if(!nombre.equals("") && !titulo.equals("") && horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaNE(nombre, titulo));
        System.out.println("Busqueda por Nombre y Especialidad");
        } 
        else if(!nombre.equals("") && titulo.equals("") && !horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaNH(nombre, horario));
        System.out.println("Busqueda por Nombre y Horario");
        } 
        else if(nombre.equals("") && !titulo.equals("") && !horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaEH(titulo, horario));
        System.out.println("Busqueda por Especialidad y Horario");
        } 
        else if(!nombre.equals("") && !titulo.equals("") && !horario.equals("")){
        request.setAttribute("Reporte", acceso.busquedaNEH(nombre, titulo, horario));
        System.out.println("Busqueda por Nombre, Especialidad y Horario");
        } else {
        request.setAttribute("Reporte", acceso.busquedaGeneral());
        System.out.println("Busqueda General");
        }
        
            */
                  
        RequestDispatcher despachar = request.getRequestDispatcher("VisualizarClientes_G.jsp");
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
