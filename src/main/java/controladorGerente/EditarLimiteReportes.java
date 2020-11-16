/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorGerente;

import accesoAObjetos.AccesoAEdicionGerente;
import accesoAObjetos.AccesoAEdicionLimiteReportes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.LimiteReportes;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "EditarLimiteReportes", urlPatterns = {"/EditarLimiteReportes"})
public class EditarLimiteReportes extends HttpServlet {

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
            out.println("<title>Servlet EditarLimiteReportes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarLimiteReportes at " + request.getContextPath() + "</h1>");
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
        AccesoAEdicionLimiteReportes acceso = new AccesoAEdicionLimiteReportes();
        int codigoUsuario = (int)request.getSession().getAttribute("codigoUsuario");
            request.setAttribute("limite", acceso.obtenerLimitesReporteAEditar(codigoUsuario));


        RequestDispatcher despachar = request.getRequestDispatcher("EditarLimiteReportes_G.jsp");
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
         double limiteReporte2 = Double.parseDouble(request.getParameter("limiteReporte2"));
         double limiteReporte3 = Double.parseDouble(request.getParameter("limiteReporte3"));
        
        
            //GUARDAR ATRIBUTOS EN EL OBJETO
            LimiteReportes nuevoLimite = new LimiteReportes(limiteReporte2, limiteReporte3, codigo);
           
            //INSTANCIA DE LA CLASE ENCARGADA DE EDITAR CAJEROS
            AccesoAEdicionLimiteReportes registrar = new AccesoAEdicionLimiteReportes();

            //INGRESO A LA BASE DE DATOS Y VALIDACION
            if (registrar.editarLimiteReportes(nuevoLimite)) {

                System.out.println("SI SE INGRESO TODO CORRECTAMENTE");
                request.setAttribute("Exitoso", true);
               } else {
                request.setAttribute("Exitoso", false);
            }

        
        RequestDispatcher despachar = request.getRequestDispatcher("EditarLimiteReportes_G.jsp");
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
