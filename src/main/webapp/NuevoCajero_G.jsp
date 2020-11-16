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
        <%-- 
        OJOOOOOOOOOOOOOOOOOOOOOOOOOOOO
            RECORDAR CAMBIAR EL HORARIO A UN HORARIO ACTUAL, CUANDO VAYAS A ENTREGAR EL PROYECTO
Constantes.setHorarioActual(LocalTime.now());
        --%>
        <%
            Constantes.setHorarioActual(Constantes.getHORARIO_MATUTINO_CALIFICACION());

        %>
        <% if (Constantes.getHorarioActual().isAfter(Constantes.getEntradaUsuario()) && Constantes.getHorarioActual().isBefore(Constantes.getSalidaUsuario())) 
        {
        %>

        
        <% if (request.getAttribute("Exitoso") == null) {%>
        <%
            System.out.println("ESTA EN HORARIO DE TRABAJO PONGASE A TRABAJAR PERRO");%>
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Registrar Nuevo Cajero</strong></h1>

            <FORM class="col-12 caja2" METHOD="POST" ACTION="NuevoCajero">  
                <h3 class="align-content-lg-center">Datos del Cajero</h3>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nombreUsuario">Nombre:</label>
                        <input class="form-control" type="text"  name="nombreUsuario" id="nombreUsuario" placeholder="Ingrese el Nombre del Cajero" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="DPIUsuario">DPI: </label>
                        <input class="form-control" type="number"  name="DPIUsuario" id="DPIUsuario" placeholder="Ingrese el DPI del Cajero" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="direccionUsuario">Dirección: </label>
                        <input class="form-control" type="text"  name="direccionUsuario" id="direccionUsuario" placeholder="Ingrese la Dirección del Cajero" required>
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
                        <label for="passwordUsuario">Contraseña: </label>
                        <input class="form-control" type="password"  name="passwordUsuario" id="passwordUsuario" placeholder="Ingrese la Contraseña Asignada al Cajero" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="horarioUsuario">Horario de Trabajo: </label>

                        <select class="form-control" name="horarioUsuario" id="horarioUsuario" required>
                            <option value="Matutino">Matutino</option>
                            <option value="Vespertino">Vespertino</option>

                        </select>                      
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
            <h1 class="align-content-lg-center"><strong>Registrar Nuevo Cajero</strong></h1>

            <% if ((boolean) request.getAttribute("Exitoso")) {%>
            <div class="alert alert-success">
                Cliente Agregado con Exito
            </div>
            <h2 class="align-content-lg-center">Informe de la Cuenta de Usuario Creada</h2>
            <div class="row">

                <div class="form-group col-md-4">
                    <h3><strong>Codigo del Cliente: </strong></h3>
                    <h4>${informeRegistro.codigo}</h4>
                </div>
                <div class="form-group col-md-4">
                    <h3><strong>Nombre del Cliente: </strong></h3>
                    <h4>${informeRegistro.nombre}</h4>
                </div>

            </div>
            <div class="row">

                <div class="form-group col-md-4">
                    <h3><strong>Horario de Inicio de Labores: </strong></h3>
                    <h4>${informeRegistro.horaEntrada} Horas</h4>
                </div>
                <div class="form-group col-md-6">
                    <h3><strong>Horario de Finalización de Labores: </strong></h3>
                    <h4>${informeRegistro.horaSalida} Horas</h4>
                </div>

            </div>


            <% } else {%>
            <div class="alert alert-danger">
                Error al Agregar al Cajero, Complete los Campos Obligatorios
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="NuevoCajero_G.jsp" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>SELECT
            </div>
            <% }%>
        </div>
        <% }%>


        <%} else {
            System.out.println("VAYA A ECHARSE Y DEJE DE ESTAR VIENDO COSAS");
        %>
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
