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

            <h1 class="align-content-lg-center"><strong>Registrar Nuevo Cliente y Nueva Cuenta Bancaria</strong></h1>

            <FORM class="col-12 caja2" METHOD="POST" ACTION="NuevoCliente" enctype="multipart/form-data">  
                <h3 class="align-content-lg-center">Datos del Cliente</h3>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nombreUsuario">Nombre:</label>
                        <input class="form-control" type="text"  name="nombreUsuario" id="nombreUsuario" placeholder="Ingrese el Nombre del Cliente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="DPIUsuario">DPI: </label>
                        <input class="form-control" type="number"  name="DPIUsuario" id="DPIUsuario" placeholder="Ingrese el DPI del Cliente" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="direccionUsuario">Dirección: </label>
                        <input class="form-control" type="text"  name="direccionUsuario" id="direccionUsuario" placeholder="Ingrese la Dirección del Cliente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="sexoUsuario">Sexo: </label>

                        <select class="form-control" name="sexoUsuario" id="sexoUsuario" required>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>

                        </select>                      
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nacimientoUsuario">Fecha de Nacimiento: </label>
                        <input class="form-control" type="date"  name="nacimientoUsuario" id="nacimientoUsuario" min="1930-01-01" max="2030-01-01" required>
                    </div>

                    <div class="form-group col-md-4">
                        <label for="DPIEscaneado">DPI Escaneado: </label>
                        <input class="form-control" type="file"  name="DPIEscaneado" id="DPIEscaneado" placeholder="Elija el DPI Escaneado del Cliente" accept=".pdf" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="passwordUsuario">Contraseña: </label>
                        <input class="form-control" type="password"  name="passwordUsuario" id="passwordUsuario" placeholder="Ingrese la Contraseña Asignada al Cliente" required>
                    </div>

                </div>



                <h3 class="align-content-lg-center">Datos de la Cuenta Bancaria</h3>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="montoInicialCuenta">Monto de Depósito Inicial: </label>
                        <input class="form-control"  type="number" step="0.01" min="100.00" name="montoInicialCuenta" id="montoInicialCuenta" placeholder="Ingrese el Monto para Aperturar la Cuenta" required>
                    </div>
                    
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <strong><small style=" font-size: 10px">* TODOS LOS CAMPOS SON OBLIGATORIOS</small></strong>    
                    </div>

                </div>

                <CENTER>

                    <input class="btn btn-primary" type="submit" value="Registrar">
                    <input class="btn" type="reset" value="Eliminar Datos">
                </CENTER>

            </FORM>

        </div>
        <% } else {%>
        <div class="container" style="padding-top: 70px" >
            <h1 class="align-content-lg-center"><strong>Registrar Nuevo Cliente y Nueva Cuenta Bancaria</strong></h1>


            <% if ((boolean) request.getAttribute("Exitoso")) {%>
            <div class="alert alert-success">
                Cliente Agregado con Exito
            </div>
            <h2 class="align-content-lg-center">Informe de la Cuenta de Usuario Creada</h2>
            <div class="row">
                
                    <div class="form-group col-md-4">
                        <h3><strong>Codigo del Cliente: </strong></h3>
                        <h4>${informeRegistro.codigoCliente}</h4>
                    </div>
                    <div class="form-group col-md-4">
                        <h3><strong>Nombre del Cliente: </strong></h3>
                        <h4>${informeRegistro.nombreCliente}</h4>
                    </div>

                </div>
                <div class="row">
                   
                        <div class="form-group col-md-4">
                            <h3><strong>Número de Cuenta del Cliente: </strong></h3>
                            <h4>${informeRegistro.numeroCuentaCliente}</h4>
                        </div>
                        <div class="form-group col-md-4">
                            <h3><strong>Saldo de la Cuenta Bancaria: </strong></h3>
                            <h4>Q. ${informeRegistro.saldoCuenta}</h4>
                        </div>

                </div>
 

            <% } else {%>
            <div class="alert alert-danger">
                Error al Agregar al Cliente, Complete los Campos Obligatorios
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="NuevoCliente_G.jsp" class="btn btn-primary">Ingresar Datos de Nuevo</a>

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
