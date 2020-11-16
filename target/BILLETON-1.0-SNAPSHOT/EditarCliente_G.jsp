<%-- 
    Document   : InicioGerente
    Created on : 5/11/2020, 04:43:24
    Author     : erikssonherlo
--%>

<%@page import="configuracion.Constantes"%>
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
        <%
            Constantes.setHorarioActual(Constantes.getHORARIO_MATUTINO_CALIFICACION());

        %>
        <% if (Constantes.getHorarioActual().isAfter(Constantes.getEntradaUsuario()) && Constantes.getHorarioActual().isBefore(Constantes.getSalidaUsuario())) 
        {
        %>

        <% if (request.getAttribute("Exitoso") == null) {%>
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Editar Cliente</strong></h1>
            <p style="font-size: 15px;">El Cliente puede conservar los Datos que habia registrado Inicialmente y Cambiar unicamente el Dato que desea Editar</p>
            <FORM class="col-12 caja2" METHOD="POST" ACTION="EditarCliente" enctype="multipart/form-data">  
                <div class="row">
                <div class="form-group col-md-4">
                        <h3>Codigo del Cliente: </h3>
                        
                    </div>
                <div class="form-group col-md-4">
                    <br>
                    <input class="form-control" type="text"  name="codigoUsuarioEditado" id="codigoUsuarioEditado" value="${cliente.codigo}" readonly>
                    
                    
                </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nombreUsuario">Nombre:</label>
                        <input class="form-control" type="text"  name="nombreUsuario" id="nombreUsuario" value="${cliente.nombre}" placeholder="Ingrese el Nuevo Nombre del Cliente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="DPIUsuario">DPI: </label>
                        <input class="form-control" type="number"  name="DPIUsuario" id="DPIUsuario" value="${cliente.DPI}" placeholder="Ingrese el Nuevo DPI del Cliente" required>
                    </div>
                </div>
            
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="direccionUsuario">Dirección: </label>
                        <input class="form-control" type="text"  name="direccionUsuario" id="direccionUsuario" value="${cliente.direccion}" placeholder="Ingrese la Nueva Dirección del Cliente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="sexoUsuario">Sexo:</label> 
                      

                        <select class="form-control" name="sexoUsuario" id="sexoUsuario" value="${cliente.sexo}" required>
                            
                                <c:if test="${cliente.sexo.equalsIgnoreCase('Masculino')}">
                                <option value="Masculino">Masculino</option>
                                <option value="Femenino">Femenino</option>
                                </c:if>>
                            
                               <c:if test="${cliente.sexo.equalsIgnoreCase('Femenino')}">
                                <option value="Femenino">Femenino</option>
                                   <option value="Masculino">Masculino</option>
                                
                                </c:if>>
                            
                        </select>                      
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nacimientoUsuario">Fecha de Nacimiento: </label>
                        <input class="form-control" type="date"  name="nacimientoUsuario" id="nacimientoUsuario" value="${cliente.nacimiento}" min="1930-01-01" max="2030-01-01" required>
                    </div>

                    <div class="form-group col-md-4">
                        <label for="DPIEscaneado">DPI Escaneado: **</label>
                        <label><a href="VisualizarDPICliente?action=verDPIPorCliente&codigoCliente=${cliente.codigo}" target="_blank">Ver DPI Actual</a></label>
                        
                        <input class="form-control" type="file"  name="DPIEscaneado" id="DPIEscaneado" placeholder="Elija el DPI Escaneado del Cliente" accept=".pdf">
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="passwordUsuario">Contraseña: </label>
                        <input class="form-control" type="password"  name="passwordUsuario" id="passwordUsuario" value="${cliente.password}" placeholder="Ingrese la Nueva Contraseña Asignada al Cliente" required>
                    </div>

                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <strong><small style=" font-size: 10px">* TODOS LOS CAMPOS SON OBLIGATORIOS</small></strong>    
                        <br> 
                        <strong><small style=" font-size: 10px">** SI NO DESEA EDITAR ESTE CAMPO, PUEDE DEJARLO VACIO</small></strong>    
                    
                    </div>

                </div>

                <CENTER>

                    <input class="btn btn-primary" type="submit" value="Guardar Cambios">
                    <a class="btn btn-danger" href="VisualizarClientes_G.jsp">Regresar</a>
                </CENTER>

            </FORM>

        </div>
        <% } else {%>
        <div class="container" style="padding-top: 70px" >
            <h1 class="align-content-lg-center"><strong>Editar Cliente</strong></h1>


            <% if ((boolean) request.getAttribute("Exitoso")) {%>
            <div class="alert alert-success">
                Edición del Cliente Realizada con Exito
            </div>
           

            <% } else {%>
            <div class="alert alert-danger">
                Error al Editar al Cliente, Complete los Campos Obligatorios
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="VisualizarClientes_G.jsp" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>
            </div>
            <% }%>
        </div>
        <% }%>

        <%} else {%>
        <div class="container" style="padding-top: 70px" >

            <div class="alert alert-success">
                El Usuario se encuentra fuera del Horario Laboral, por lo que tiene Restringidas ciertas Acciones dentro del Sistema
            </div>
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
