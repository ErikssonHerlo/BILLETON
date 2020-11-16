/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorCargaArchivo;


import accesoAObjetos.AccesoANuevoGerente;
import accesoAObjetos.AccesoAInformeDatos;
import accesoAObjetos.AccesoANuevoCajero;
import cargaArchivos.LecturaArchivo;
import configuracion.Constantes;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import objetos.Cajero;


/**
 *
 * @author erikssonherlo
 */
@MultipartConfig(maxFileSize = 16177215) //Maximo = 16mb
@WebServlet("/CargaArchivo")
public class CargaArchivo extends HttpServlet {

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
            out.println("<title>Servlet ControladorCargaArchivo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCargaArchivo at " + request.getContextPath() + "</h1>");
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
            //INSERTAR USUARIO DE TIPO CAJERO PARA LA BANCA VIRTUAL
            Cajero bancaVirtual = new Cajero(101, "BancaVirtual", "", "", "", "8cX7%%tedj4!yJm4", 2, "Toda Hora", "0:00", "24:00", true);
        
            AccesoANuevoCajero nuevoCajero = new AccesoANuevoCajero();
            nuevoCajero.insertarNuevoCajero(bancaVirtual);
            //Guardar el Archivo XML en la carpeta DPIClientes
             Part archivo = request.getPart("archivoDB");
             String nombreArchivo = Paths.get(archivo.getSubmittedFileName()).getFileName().toString();
             System.out.println("Path del Archivo: "+nombreArchivo);
             
             String pathAbsolutoArchivo = Constantes.getPATH_ABSOLUTO_ARCHIVOS()+nombreArchivo;
             //nombreArchivo = data.xml
             guardarArchivos(archivo, nombreArchivo);
             
             //Guardar todos los Archivos que estan incluidos dentro de la carga del Archivo en la carpeta ArchivosDB
                 
             ArrayList<Part> filePartArchivosDB = (ArrayList<Part>) request.getParts();       
             for (Part part : filePartArchivosDB) {
             String rutaArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            guardarArchivos(part, rutaArchivo);
            }
             //
             LecturaArchivo lectura = new LecturaArchivo();
             lectura.dividirEtiquetas(pathAbsolutoArchivo);
             AccesoAInformeDatos informe = new AccesoAInformeDatos();
             if(informe.informeCargaCajeros()>0 && informe.informeCargaGerentes()>0 && informe.informeCargaClientes()>0 && informe.informeCargaCuentas()>0 && informe.informeCargaTransacciones()>0)
             {
                    request.setAttribute("Exitoso", true);
                    request.setAttribute("InformeGerentes", informe.informeCargaGerentes());
                    request.setAttribute("InformeCajeros", informe.informeCargaCajeros());
                    request.setAttribute("InformeClientes", informe.informeCargaClientes());
                    request.setAttribute("InformeCuentas", informe.informeCargaCuentas());
                    request.setAttribute("InformeTransacciones", informe.informeCargaTransacciones());
             }else
             {
                request.setAttribute("Exitoso", false);
                request.setAttribute("InformeCarga", informe.informeCargaArchivo());
             }

        RequestDispatcher despachar = request.getRequestDispatcher("CargaArchivo.jsp");
        despachar.forward(request, response);
        
    }

    private void guardarArchivos(Part filePart,String rutaArchivo){
        
        File rutaDestino = new File(Constantes.getPATH_ABSOLUTO_ARCHIVOS());
        File file = new File(rutaDestino,rutaArchivo);

        try(InputStream inputS = filePart.getInputStream()) {
            
            Files.copy(inputS, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace(System.out);
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
