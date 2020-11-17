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
            <h1 class="align-content-lg-center"><strong>Historial de Transacciones</strong></h1>
         
            <h3 class="align-content-lg-center">Campos de Búsqueda:</h3>

            <FORM class="col-12 caja1" METHOD="GET" ACTION="VerReporte6Gerente">  
                <div class="row">
                 
                    <div class="form-group col-md-3">
                        <label for="nombreBusqueda">Nombre: </label>
                        <input class="form-control" type="text"  name="nombreBusqueda" id="nombreBusqueda" placeholder="Ingrese el Nombre del Cliente">
                    </div>
                    
                     <div class="form-group col-md-3">
                        <label for="limiteDinero1">Límite Inferior de Dinero: </label>
                     <input class="form-control"  type="number" step="0.01" min="1.00" name="limiteDinero1" id="limiteDinero1" placeholder="Ingrese el Límite Inferior de Busqueda" required>
                    </div>
                    
                      <div class="form-group col-md-3">
                        <label for="limiteDinero2">Límite Superior de Dinero: </label>
                     <input class="form-control"  type="number" step="0.01" min="1.00" name="limiteDinero2" id="limiteDinero2" placeholder="Ingrese el Límite Superior de Busqueda" required>
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
                height: 330px;
                overflow: auto;
            }
            .table-wrapper-scroll-y {
                display: block;
            }
        </style>

            <c:choose>
                <c:when test="${Reporte.isEmpty()}">
                    <div class="alert alert-danger">
                        No Existe un Historial de Transacciones, con los Campos de Búsqueda Solicitados
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Número de Cuenta</th>
                                <th>Fecha</th>
                                <th>Hora</th>
                                <th>Tipo</th>
                                <th>Monto</th>
                                
                                <th>Saldo Anterior</th>
                                <th>Saldo Actual</th>
                                <th>Codigo del Cliente</th>
                                
                                <th>Nombre</th>
                                
                                
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
                                    
                                    <td>${resultado.getSaldoAnterior()}</td>
                                    <td>${resultado.getSaldoActual()}</td>
                                    <td>${resultado.getCodigoCliente()}</td>
                                    
                                    <td>${resultado.getNombreCliente()}</td>
                                    
                                    
                                      </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    </div>
      
               <FORM class="col-12 caja1" METHOD="GET" ACTION="ExportarReporte6Gerente">  
              
                   <input type="hidden"  name="LimiteInferior" value="${LimiteInferior}">
                    
                   <input type="hidden"  name="LimiteSuperior" value="${LimiteSuperior}">
                    
                   <input type="hidden"  name="NombreBusqueda" value="${Nombre}">
                    
 
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
