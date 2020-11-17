/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorReportesCajero;

import accesoAReportes.AccesoAReporte1_Cajero;
import accesoAReportes.AccesoAReporte2_Cajero;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.ws.rs.core.HttpHeaders;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reportesCajero.Reporte1Cajero;

/**
 *
 * @author erikssonherlo
 */
@WebServlet(name = "ExportarReporte2Cajero", urlPatterns = {"/ExportarReporte2Cajero"})
public class ExportarReporte2Cajero extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


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
       try {
            
          int codigoUsuario = (int)request.getSession().getAttribute("codigoUsuario");
         String entradaUsuario = (String)request.getSession().getAttribute("horarioEntrada");
         String salidaUsuario = (String)request.getSession().getAttribute("horarioSalida");
         String nombreUsuario = (String)request.getSession().getAttribute("nombreUsuario");
         String fechaInicio = request.getParameter("Inicio");
         String fechaFinal = request.getParameter("Final");
           System.out.println("Inicio "+fechaInicio);
           System.out.println("Final "+fechaFinal);
         AccesoAReporte2_Cajero reportar = new AccesoAReporte2_Cajero();
         
           List<Reporte1Cajero> exportado = reportar.reporte2Cajero(codigoUsuario, entradaUsuario, salidaUsuario, fechaInicio, fechaFinal);
         
            double saldoCaja =  reportar.saldoCaja(codigoUsuario, entradaUsuario, salidaUsuario, fechaInicio, fechaFinal);
      
            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Reporte 2 - Cajero "+nombreUsuario+" Del "+fechaInicio+" al "+fechaFinal+" .pdf");

            
            //List<Cliente> Histo = gestor.reporte5(t, s);
            
            
            File file = new File(request.getServletContext().getRealPath("/resources/ReportesCajero/Reporte2_Cajero.jrxml"));
            JasperReport jasperReports = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(exportado);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombreCajero", nombreUsuario);
            parameters.put("saldoCaja", saldoCaja);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReports, parameters, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
            
        } catch (IOException | NumberFormatException  e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (JRException ex) {
            Logger.getLogger(ExportarReporte2Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

  
}
