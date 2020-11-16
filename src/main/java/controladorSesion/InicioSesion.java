/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorSesion;

import accesoAObjetos.AccesoAUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Cajero;
import objetos.Cliente;
import objetos.ClienteELIMINAR;
import objetos.Gerente;

import sun.jvm.hotspot.utilities.soql.SOQLException;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "InicioSesion", urlPatterns = {"/InicioSesion"})
public class InicioSesion extends HttpServlet {

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
            out.println("<title>Servlet InicioSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InicioSesion at " + request.getContextPath() + "</h1>");
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
        try {
        int codigoUsuario = Integer.parseInt(request.getParameter("usuarioCodigo"));
        String passwordUsuario = request.getParameter("usuarioPassword");
       
        AccesoAUsuario acceso = new AccesoAUsuario();
       Gerente gerenteSesion = acceso.loginGerente(codigoUsuario, passwordUsuario);
       Cajero cajeroSesion = acceso.loginCajero(codigoUsuario, passwordUsuario);
       Cliente clienteSesion = acceso.loginCliente(codigoUsuario, passwordUsuario);
       
        
            System.out.println("Recibi los parametros");
        if(gerenteSesion != null)
        {
            System.out.println("Ingresamos al Usuario: "+gerenteSesion.getNombre());
            request.getSession().setAttribute("codigoUsuario", gerenteSesion.getCodigo());
            request.getSession().setAttribute("nombreUsuario", gerenteSesion.getNombre());
            request.getSession().setAttribute("horarioEntrada", gerenteSesion.getHoraEntrada());
            request.getSession().setAttribute("horarioSalida", gerenteSesion.getHoraSalida());
            request.getSession().setAttribute("tipoUsuario", gerenteSesion.getTipoUsuario());
            request.getSession().setAttribute("objetoUsuario", gerenteSesion);
           
            response.sendRedirect(request.getContextPath()+"/InicioGerente.jsp");
                    
        } else if(cajeroSesion != null && cajeroSesion.getCodigo()!=101)
        {
            System.out.println("Ingresamos al Usuario: "+cajeroSesion.getNombre());
           request.getSession().setAttribute("codigoUsuario", cajeroSesion.getCodigo());
            request.getSession().setAttribute("nombreUsuario", cajeroSesion.getNombre());
            request.getSession().setAttribute("horarioEntrada", cajeroSesion.getHoraEntrada());
            request.getSession().setAttribute("horarioSalida", cajeroSesion.getHoraSalida());
            request.getSession().setAttribute("tipoUsuario", cajeroSesion.getTipoUsuario());
            request.getSession().setAttribute("objetoUsuario", cajeroSesion);
           
            response.sendRedirect(request.getContextPath()+"/InicioCajero.jsp");
                    
        }  else if(clienteSesion != null)
        {
             System.out.println("Ingresamos al Usuario: "+clienteSesion.getNombre());
           request.getSession().setAttribute("codigoUsuario", clienteSesion.getCodigo());
            request.getSession().setAttribute("nombreUsuario", clienteSesion.getNombre());
            request.getSession().setAttribute("DPIUsuario", clienteSesion.getDPI());
            request.getSession().setAttribute("tipoUsuario", clienteSesion.getTipoUsuario());
            request.getSession().setAttribute("objetoUsuario", clienteSesion);
            response.sendRedirect(request.getContextPath()+"/InicioCliente.jsp");
                    
        }  
  else {
         request.setAttribute("Exitoso", false);
        
        RequestDispatcher despachar = request.getRequestDispatcher("Login.jsp");
        despachar.forward(request, response);
        
        }
       
        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
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
