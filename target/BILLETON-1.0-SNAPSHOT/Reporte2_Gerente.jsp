<%-- 
    Document   : InicioGerente
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
        <% RequestDispatcher despachar = request.getRequestDispatcher("VerReporte2Gerente");
           despachar.forward(request, response);%>
        <% } else {
        %>
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Clientes con transacciones monetarias mayores a un límite establecido de: Q. ${limiteReporte}</strong></h1>
            <br>
         
        <style>
            .my-custom-scrollbar {
                position: relative;
                height: 340px;
                overflow: auto;
            }
            .table-wrapper-scroll-y {
                display: block;
            }
        </style>

            <c:choose>
                <c:when test="${Reporte.isEmpty()}">
                    <div class="alert alert-danger">
                        No se encontro a Ningun Cliente con Transacciones Mayores al Limite Establecido
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>Número de Cuenta</th>
                                <th>Número de Transacción</th>
                                <th>Tipo de Transacción</th>
                                <th>Monto</th>
                                <th>Fecha</th>
                                
                            </tr>
                        </thead>

                        <c:forEach items="${Reporte}" var="resultado"> 
                            <tbody>
                                <tr>
                                    <td>${resultado.getCodigoCliente()}</td>
                                    <td>${resultado.getNombreCliente()}</td>
                                    <td>${resultado.getNumeroCuenta()}</td>
                                    <td>${resultado.getCodigoTransaccion()}</td>
                                    <td>${resultado.getTipo()}</td>
                                    <td>${resultado.getMonto()}</td>
                                    <td>${resultado.getFecha()}</td>
                                    
                                      </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    </div>  
        <br>
        
         <CENTER>

                    <a href="ExportarReporte2Gerente" class="btn btn-success">Exportar Reporte</a>

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
