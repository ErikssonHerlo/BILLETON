/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoAVisualizacionCajeros;
import accesoAObjetos.AccesoAVisualizacionCuentas;
import accesoAObjetos.AccesoAVisualizacionGerentes;
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
@WebServlet(name = "VisualizarCuentas", urlPatterns = {"/VisualizarCuentas"})
public class VisualizarCuentas extends HttpServlet {

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
            out.println("<title>Servlet VisualizarGerentes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VisualizarGerentes at " + request.getContextPath() + "</h1>");
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
        int numeroCuenta = 0;
       String nombre = Constantes.getStringUTF(request.getParameter("nombreUsuario"));
       
        String numeroCuentaBusqueda = request.getParameter("numeroCuenta");
        String codigoUsuarioBusqueda = request.getParameter("codigoUsuario");
        
       //SI INGRESO TODOS LOS DATOS ADECUADAMENTE, PROCEDEMOS A REGISTRARLOS 
        
            AccesoAVisualizacionCuentas acceso = new AccesoAVisualizacionCuentas();
             
             if(!codigoUsuarioBusqueda.equals("") && nombre.equals("") && numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda solo por Codigo");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 request.setAttribute("Reporte", acceso.busquedaCuentasPorCodigoUsuario(codigoUsuario));
                 
             } else if(codigoUsuarioBusqueda.equals("") && !nombre.equals("") && numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda solo por Nombre");
                 request.setAttribute("Reporte", acceso.busquedaCuentasPorNombre(nombre));
             } else if(codigoUsuarioBusqueda.equals("") && nombre.equals("") && !numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda solo por Numero de Cuenta");
                 numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
                 request.setAttribute("Reporte", acceso.busquedaCuentasPorNCuenta(numeroCuenta));
             }  else if(!codigoUsuarioBusqueda.equals("") && !nombre.equals("") && numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda por Codigo y Nombre");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 
                 
             }else if(!codigoUsuarioBusqueda.equals("") && nombre.equals("") && !numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda por Codigo y Cuenta");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
                 
             } else if(codigoUsuarioBusqueda.equals("") && !nombre.equals("") && !numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda por Nombre y Cuenta");
                 
             }else if(!codigoUsuarioBusqueda.equals("") && !nombre.equals("") && !numeroCuentaBusqueda.equals(""))
             {
                 System.out.println("Busqueda por Codigo, Nombre y Cuenta");
                 codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
 
             }  else {
             
                 System.out.println("Busqueda General");
                 request.setAttribute("Reporte", acceso.busquedaGeneralCuentas());
                 
             }
           
                  
        RequestDispatcher despachar = request.getRequestDispatcher("VisualizarCuentasBancarias_G.jsp");
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
