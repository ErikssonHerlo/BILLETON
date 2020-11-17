/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorReportesCajero;

import accesoAReportes.AccesoAReporte1_Cajero;
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
@WebServlet(name = "VerReporte1Cajero", urlPatterns = {"/VerReporte1Cajero"})
public class VerReporte1Cajero extends HttpServlet {

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
            out.println("<title>Servlet VerReporte1Cajero</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerReporte1Cajero at " + request.getContextPath() + "</h1>");
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
         int codigoUsuario = (int)request.getSession().getAttribute("codigoUsuario");
         String entradaUsuario = (String)request.getSession().getAttribute("horarioEntrada");
         String salidaUsuario = (String)request.getSession().getAttribute("horarioSalida");
         //String entradaUsuario = "2:00";
         //String salidaUsuario = "20:00";
       
         
         AccesoAReporte1_Cajero reportar = new AccesoAReporte1_Cajero();
         
            request.setAttribute("Reporte", reportar.reporte1Cajero(codigoUsuario, entradaUsuario, salidaUsuario));
            request.setAttribute("saldoCaja", reportar.saldoCaja(codigoUsuario, entradaUsuario,salidaUsuario));
        RequestDispatcher despachar = request.getRequestDispatcher("Reporte1_Cajero.jsp");
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
