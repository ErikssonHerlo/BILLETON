/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoACodigoUsuario;
import accesoAObjetos.AccesoAInformeRegistroNuevaInfo;
import accesoAObjetos.AccesoANuevaCuentaBancaria;
import accesoAObjetos.AccesoANuevoGerente;
import configuracion.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.Cuenta;
import objetos.Gerente;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "NuevaCuentaBancaria", urlPatterns = {"/NuevaCuentaBancaria"})
public class NuevaCuentaBancaria extends HttpServlet {

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
            out.println("<title>Servlet NuevaCuentaBancaria</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevaCuentaBancaria at " + request.getContextPath() + "</h1>");
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
        int codigoUsuario = Integer.parseInt(request.getParameter("codigoUsuario"));
        double monto = Double.parseDouble(request.getParameter("montoInicialCuenta"));
        Constantes.setFechaActual(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
    
         //INSTANCIA DE LA CLASE ENCARGADA DE REGISTRAR NUEVAS CUENTAS BANCARIAS
        AccesoANuevaCuentaBancaria registrar = new AccesoANuevaCuentaBancaria();
         
        //OBTIENE EL ULTIMO REGISTRO DE LA TABLA CUENTA, PARA OBTENER EL NUEVO NUMERO DE CUENTA ASIGNADO AL USUARIO
            int numeroCuenta = registrar.obtenerCodigoCuentaBancariaNuevoRegistro();
        
//GUARDAR ATRIBUTOS EN EL OBJETO
            Cuenta nuevaCuenta = new Cuenta(numeroCuenta, Constantes.getFechaActual().toString(), monto, codigoUsuario);
            
            //INGRESO A LA BASE DE DATOS Y VALIDACION
            if (registrar.insertarNuevaCuenta(nuevaCuenta)) {
                
                System.out.println("SI SE INGRESO TODO CORRECTAMENTE");
                //INSTANCIA DE LA CLASE ENCARGADA DE PRESENTAR EL INFORME DEL REGISTRO DEL CLIENTE
                  AccesoAInformeRegistroNuevaInfo informe = new AccesoAInformeRegistroNuevaInfo();
                request.setAttribute("Exitoso", true);
                request.setAttribute("informeRegistro", informe.informeCreacionCliente(numeroCuenta));
            } else {
                request.setAttribute("Exitoso", false);
            }

        
        RequestDispatcher despachar = request.getRequestDispatcher("NuevaCuentaBancaria_G.jsp");
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
