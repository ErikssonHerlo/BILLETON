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

        <% if ((int) request.getSession().getAttribute("codigoUsuario") != 0 && (int) request.getSession().getAttribute("tipoUsuario") == 1) {
        %>
        <%@ include file = "NavBarGerente.jsp"%>
     
        <% if (request.getAttribute("Exitoso") == null) {%>
     
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Editar Limite de Reportes</strong></h1>
<p style="font-size: 15px;">El Gerente puede conservar los Datos que habia registrado Inicialmente y Cambiar unicamente el Dato que desea Editar</p>
            
            <FORM class="col-12 caja2" METHOD="POST" ACTION="EditarLimiteReportes">  
                <div class="row">
                <div class="form-group col-md-4">
                        <h3>Codigo del Gerente: </h3>
                        
                    </div>
                <div class="form-group col-md-4">
                    <br>
                    <input class="form-control" type="text"  name="codigoUsuarioEditado" id="codigoUsuarioEditado" value="${limite.codigoGerente}" readonly>
                    
                    
                </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nombreUsuario">Descripción del Reporte No. 2:</label>
                            <p style="font-size: 15px;">Reporte de Clientes con Transacciones Monetarias Mayores a un Límite Establecido</p>

                    </div>
                    <div class="form-group col-md-4">
                        <label for="DPIUsuario">Limite del Reporte  No. 2: </label>
                        <input class="form-control"  type="number" step="0.01" min="1" name="limiteReporte2" id="limiteReporte2" value="${limite.limiteReporte2}"placeholder="Ingrese el Limite para el Reporte 2 del Gerente" required>
                    </div>
                </div>
                
            <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nombreUsuario">Descripción del Reporte No. 3:</label>
                            <p style="font-size: 15px;">Reporte de Clientes con Transacciones Monetarias Sumadas Mayores a un Límite Establecido</p>

                    </div>
                    <div class="form-group col-md-4">
                        <label for="DPIUsuario">Limite del Reporte No. 3: </label>
                        <input class="form-control"  type="number" step="0.01" min="1" name="limiteReporte3" id="limiteReporte3" value="${limite.limiteReporte3}"placeholder="Ingrese el Limite para el Reporte 3 del Gerente" required>
                    </div>
                </div>



                <div class="row">
                    <div class="form-group col-md-4">
                        <strong><small style=" font-size: 10px">* TODOS LOS CAMPOS SON OBLIGATORIOS</small></strong>    
                    </div>

                </div>

                <CENTER>

                    <input class="btn btn-primary" type="submit" value="Guardar Cambios">
                    <a class="btn btn-danger" href="InicioGerente.jsp">Regresar</a>
                    
                </CENTER>

            </FORM>

        </div>
        <% } else {%>
        <div class="container" style="padding-top: 70px" >
            <h1 class="align-content-lg-center"><strong>Editar Limite de Reportes</strong></h1>

            <% if ((boolean) request.getAttribute("Exitoso")) {%>
            
            <div class="alert alert-success">
                Edición de los Limites para los Reportes Realizada con Exito
            </div>
         


            <% } else {%>
            <div class="alert alert-danger">
                Error al Editar los Limites de los Reportes, Complete los Campos Obligatorios
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="EditarLimiteReportes" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>
            </div>
            <% }%>
        </div>
        <% }%>


        <% } else if ((int) request.getSession().getAttribute("codigoUsuario") != 0 && (int) request.getSession().getAttribute("tipoUsuario") == 2) { %>
        <% response.sendRedirect(request.getContextPath() + "/InicioCajero.jsp"); %>
        <% } else if ((int) request.getSession().getAttribute("codigoUsuario") != 0 && (int) request.getSession().getAttribute("tipoUsuario") == 3) { %>
        <% response.sendRedirect(request.getContextPath() + "/InicioCliente.jsp"); %>
        <% } else { %>
        <% response.sendRedirect(request.getContextPath() + "/Login.jsp");
        %>
        <% }%>
        <%@ include file = "FooterGeneral.html" %>

    </body>
</html>
