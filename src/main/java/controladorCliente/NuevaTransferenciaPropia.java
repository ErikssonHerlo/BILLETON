/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorCliente;

import controladorCajero.*;
import accesoAObjetos.AccesoACodigoTransaccion;
import accesoAObjetos.AccesoADPIEscaneadoCliente;
import accesoAObjetos.AccesoADeposito;
import accesoAObjetos.AccesoARetiro;
import com.sun.tools.doclint.Entity;
import informesDeVisualizacion.ValidacionRetiros;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Transaccion;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "NuevaTransferenciaPropia", urlPatterns = {"/NuevaTransferenciaPropia"})
public class NuevaTransferenciaPropia extends HttpServlet {

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
            out.println("<title>Servlet NuevoDeposito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevoDeposito at " + request.getContextPath() + "</h1>");
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
        AccesoARetiro acceso = new AccesoARetiro();
        int numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
        double monto = Double.parseDouble(request.getParameter("montoTransaccion"));
        
        int codigoCajero = (int) request.getSession().getAttribute("codigoUsuario");
        String action = request.getParameter("action");
        switch (action) {
            case "buscarCuenta":
                
                if (acceso.obtenerCuentaTransaccion(numeroCuenta, monto) != null) {                    
                    request.setAttribute("Exitoso", true);
                    request.setAttribute("validacion", acceso.obtenerCuentaTransaccion(numeroCuenta, monto));
                } else {
                    request.setAttribute("Exitoso", false);
                    
                }
                
                break;
            case "confirmarTransaccion":
                ValidacionRetiros validacion = acceso.obtenerCuentaTransaccion(numeroCuenta, monto);
                if (validacion.getSaldoCuenta() >= monto) {
                    String nombre = request.getParameter("nombre");
                    AccesoACodigoTransaccion obtener = new AccesoACodigoTransaccion();
                    int codigoTransaccion = obtener.obtenerCodigoNuevaTransaccion();
                    Transaccion nuevoRetiro = new Transaccion(codigoTransaccion, numeroCuenta, "", "", "Debito", monto, codigoCajero);
                    
                    if (acceso.realizarRetiro(nuevoRetiro)) {
                        request.setAttribute("InformeTransaccion", true);
                        request.setAttribute("Informe", acceso.informeRetiro(codigoTransaccion, nombre));
                        
                    } else {
                        
                        request.setAttribute("InformeTransaccion", false);
                    }
                } else {
                    
                    request.setAttribute("InformeTransaccion", false);
                    
                }
                
                break;
            
        }
        RequestDispatcher despachar = request.getRequestDispatcher("RealizarRetiro_Ca.jsp");
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
