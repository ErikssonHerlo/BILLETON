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

            <h1 class="align-content-lg-center"><strong>Editar Gerente</strong></h1>
<p style="font-size: 15px;">El Gerente puede conservar los Datos que habia registrado Inicialmente y Cambiar unicamente el Dato que desea Editar</p>
            
            <FORM class="col-12 caja2" METHOD="POST" ACTION="EditarGerente">  
                <div class="row">
                <div class="form-group col-md-4">
                        <h3>Codigo del Gerente: </h3>
                        
                    </div>
                <div class="form-group col-md-4">
                    <br>
                    <input class="form-control" type="text"  name="codigoUsuarioEditado" id="codigoUsuarioEditado" value="${gerente.codigo}" readonly>
                    
                    
                </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nombreUsuario">Nombre:</label>
                        <input class="form-control" type="text"  name="nombreUsuario" id="nombreUsuario" value="${gerente.nombre}" placeholder="Ingrese el Nombre del Gerente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="DPIUsuario">DPI: </label>
                        <input class="form-control" type="number"  name="DPIUsuario" id="DPIUsuario" value="${gerente.DPI}"placeholder="Ingrese el DPI del Gerente" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="direccionUsuario">Dirección: </label>
                        <input class="form-control" type="text"  name="direccionUsuario" id="direccionUsuario" value="${gerente.direccion}" placeholder="Ingrese la Dirección del Gerente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="sexoUsuario">Sexo: </label>

                     <select class="form-control" name="sexoUsuario" id="sexoUsuario" value="${gerente.sexo}" required>
                            
                                <c:if test="${gerente.sexo.equalsIgnoreCase('Masculino')}">
                                <option value="Masculino">Masculino</option>
                                <option value="Femenino">Femenino</option>
                                </c:if>>
                            <c:if test="${gerente.sexo.equalsIgnoreCase('Femenino')}">
                               
                                <option value="Femenino">Femenino</option>
                                 <option value="Masculino">Masculino</option>
                                </c:if>>
                            
                        </select>                  
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="passwordUsuario">Contraseña: </label>
                        <input class="form-control" type="password"  name="passwordUsuario" id="passwordUsuario" value="${gerente.password}" placeholder="Ingrese su Contraseña Gerente" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="horarioUsuario">Horario de Trabajo: </label>

                        <select class="form-control" name="horarioUsuario" id="horarioUsuario" value="${gerente.turno}" required>
                            <c:if test="${gerente.turno.equalsIgnoreCase('Matutino')}">
                            <option value="Matutino">Matutino</option>
                            <option value="Vespertino">Vespertino</option>
                            </c:if>
                            <c:if test="${gerente.turno.equalsIgnoreCase('Vespertino')}">
                            
                            <option value="Vespertino">Vespertino</option>
                             <option value="Matutino">Matutino</option>
                            </c:if>    
                        </select>                      
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-4">
                        <strong><small style=" font-size: 10px">* TODOS LOS CAMPOS SON OBLIGATORIOS</small></strong>    
                    </div>

                </div>

                <CENTER>

                    <input class="btn btn-primary" type="submit" value="Guardar Cambios">
                    <a class="btn btn-danger" href="VisualizarInfoPropiaGerente_G.jsp">Regresar</a>
                    
                </CENTER>

            </FORM>

        </div>
        <% } else {%>
        <div class="container" style="padding-top: 70px" >
            <h1 class="align-content-lg-center"><strong>Editar Gerente</strong></h1>

            <% if ((boolean) request.getAttribute("Exitoso")) {%>
            
            <div class="alert alert-success">
                Edición de la Información Propia Realizada con Exito
            </div>
         


            <% } else {%>
            <div class="alert alert-danger">
                Error al Editar la Información Propia, Complete los Campos Obligatorios
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="EditarGerente" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>
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
