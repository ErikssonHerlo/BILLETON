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

         <% if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 2) {
    %>
        <%@ include file = "NavBarCajero.jsp"%>

   
        <div class="container" style="padding-top: 35px" >

            <h1 class="align-content-lg-center"><strong>Transacciones Realizadas Durante el Turno del Cajero: </strong></h1>
            <h2 class="align-content-lg-center"><strong><%=request.getSession().getAttribute("nombreUsuario")%></strong></h2>
           
            <h3 class="align-content-lg-center">Intervalo de Tiempo:</h3>

            <FORM class="col-12 caja1" METHOD="GET" ACTION="VerReporte2Cajero">  
                <div class="row">
                    <div class="form-group col-md-5">
                        <label for="fechaInicio">Fecha de Inicio: </label>
                        <input class="form-control" type="date"  name="fechaInicio" id="fechaInicio" min="2000-01-01" max="2030-01-01" placeholder="Ingrese la Fecha de Inicio" required>
                     
                    </div>
                    <div class="form-group col-md-5">
                        <label for="fechaFinal">Fecha de Finalización: </label>
                        <input class="form-control" type="date"  name="fechaFinal" id="fechaFinal" min="2000-01-01" max="2030-01-01" placeholder="Ingrese la Fecha de Finalización" required>
                    </div>
          
                    
                    <div class="form-group col-md-2">
 
                        <br>
                        <input class="form-control-sm btn btn-primary" type="submit" value="Buscar">
                    </div>
                </div>

            </FORM>
            
            <h3 class="align-content-lg-center">Balance Final: Q. ${saldoCaja}</h3>

         
        <style>
            .my-custom-scrollbar {
                position: relative;
                height: 300px;
                overflow: auto;
            }
            .table-wrapper-scroll-y {
                display: block;
            }
        </style>

            <c:choose>
                <c:when test="${Reporte.isEmpty()}">
                    <div class="alert alert-danger">
                        El Cajero No Realizo Ninguna Transaccion durante su Turno
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Numero de Cuenta</th>
                                <th>Fecha</th>
                                <th>Hora</th>
                                <th>Tipo</th>
                                <th>Monto</th>
                                <th>Codigo del Cajero</th>
                                
                            </tr>
                        </thead>

                        <c:forEach items="${Reporte}" var="resultado"> 
                            <tbody>
                                <tr>
                                    <td>${resultado.getCodigo()}</td>
                                    <td>${resultado.getNoCuenta()}</td>
                                    <td>${resultado.getFecha()}</td>
                                    <td>${resultado.getHora()}</td>
                                    <td>${resultado.getTipo()}</td>
                                    <td>${resultado.getMonto()}</td>
                                    <td>${resultado.getCodigoCajero()}</td>
                                    
                                      </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    </div>
                   <br>
               <FORM class="col-12 caja1" METHOD="GET" ACTION="ExportarReporte2Cajero">  
              
                   <input type="hidden"  name="Inicio" value="${Inicio}">
                    
                   <input type="hidden"  name="Final" value="${Final}">
                    
 
                        <br>
                        <center>
                        <input class="form-control-sm btn btn-success" type="submit" value="Exportar Reporte">
                        </center>
               

            </FORM>

                </c:otherwise>


            </c:choose>
        
   
       
        
        
        </div>
        
       

            
        </div>
    
       
        <% } else if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 1) { %>
        <% response.sendRedirect(request.getContextPath()+"/InicioGerente.jsp"); %>
        <% } else if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 3) { %>
        <% response.sendRedirect(request.getContextPath()+"/InicioCliente.jsp"); %>
        <% } else { %>
        <% response.sendRedirect(request.getContextPath() + "/Login.jsp");
        %>
        <% }%>
        <%@ include file = "FooterGeneral.html" %>

    </body>
</html>
