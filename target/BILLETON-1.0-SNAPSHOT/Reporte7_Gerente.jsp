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
           <div class="container" style="padding-top: 70px" >
            <h1 class="align-content-lg-center"><strong>Cajero con Más Transacciones Realizadas</strong></h1>
         
            <h3 class="align-content-lg-center">Intervalo de Tiempo:</h3>

            <FORM class="col-12 caja1" METHOD="GET" ACTION="VerReporte7Gerente">  
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
        

         
        <style>
            .my-custom-scrollbar {
                position: relative;
                height: 100px;
                overflow: auto;
            }
            .table-wrapper-scroll-y {
                display: block;
            }
        </style>

            <c:choose>
                <c:when test="${Reporte.isEmpty()}">
                    <div class="alert alert-danger">
                        El Cajero No Realizo Ninguna Transaccion durante el Intervalo de Tiempo Solicitado
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>Turno</th>
                                <th>Hora de Entrada</th>
                                <th>Hora de Salida</th>
                                <th>Cantidad de Transacciones</th>
                                
                                
                            </tr>
                        </thead>

                        <c:forEach items="${Reporte}" var="resultado"> 
                            <tbody>
                                <tr>
                                    <td>${resultado.getCodigoCajero()}</td>
                                    <td>${resultado.getNombreCajero()}</td>
                                    <td>${resultado.getTurno()}</td>
                                    <td>${resultado.getHoraEntrada()}</td>
                                    <td>${resultado.getHoraSalida()}</td>
                                    <td>${resultado.getCantidad()}</td>
                                    
                                    
                                      </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    </div>
                   <br>
               <FORM class="col-12 caja1" METHOD="GET" ACTION="ExportarReporte7Gerente">  
              
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
