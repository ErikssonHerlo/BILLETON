<%-- 
    Document   : Reporte
    Created on : 5/11/2020, 04:43:24
    Author     : erikssonherlo
--%>

<%@page import="configuracion.Constantes"%>
<%@page import="java.time.LocalTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file = "HeaderGeneral.html" %>
    <body>

        <% if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 1) {
    %>
        <%@ include file = "NavBarGerente.jsp"%>
        <% if (request.getAttribute("Reporte") == null){
        %>
        <% RequestDispatcher despachar = request.getRequestDispatcher("VerReporte4Gerente");
           despachar.forward(request, response);%>
        <% } else {
        %>
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Los 10 Clientes con Más Dinero en sus Cuentas Bancarias</strong></h1>
            <br>
         
        <style>
            .my-custom-scrollbar {
                position: relative;
                height: 380px;
                overflow: auto;
            }
            .table-wrapper-scroll-y {
                display: block;
            }
        </style>

            <c:choose>
                <c:when test="${Reporte.isEmpty()}">
                    <div class="alert alert-danger">
                        No se encontro el Listado de Clientes
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th>Codigo del Cliente</th>
                                <th>Nombre</th>
                                <th>Número de Cuenta</th>
                                <th>Saldo de Cuenta</th>
                                
                                <th>Fecha de Creación</th>
                                
                            </tr>
                        </thead>

                        <c:forEach items="${Reporte}" var="resultado"> 
                            <tbody>
                                <tr>
                                    <td>${resultado.getCodigoCliente()}</td>
                                    <td>${resultado.getNombreCliente()}</td>
                                    <td>${resultado.getNumeroCuenta()}</td>
                                    <td>${resultado.getSaldoCuenta()}</td>
                                     <td>${resultado.getFechaCreacion()}</td>
                                    
                                      </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    </div>  
        <br>
        
         <CENTER>

                    <a href="ExportarReporte4Gerente" class="btn btn-success">Exportar Reporte</a>

                </CENTER>
                </c:otherwise>


            </c:choose>
      
        
        
        </div>
        
       

            
        </div>
      
        <% }%>
       
        <% } else if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 2) { %>
        <% response.sendRedirect(request.getContextPath()+"/InicioCajero.jsp"); %>
        <% } else if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 3) { %>
        <% response.sendRedirect(request.getContextPath()+"/InicioCliente.jsp"); %>
        <% } else { %>
        <% response.sendRedirect(request.getContextPath() + "/Login.jsp");
        %>
        <% }%>
        <%@ include file = "FooterGeneral.html" %>

    </body>
</html>
