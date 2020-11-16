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
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Clientes Registrados en el Sistema</strong></h1>
            <h3 class="align-content-lg-center">Campos de Busqueda:</h3>

            <FORM class="col-12 caja2" METHOD="GET" ACTION="VisualizarClientes">  
                <div class="row">
                    <div class="form-group col-md-5">
                        <label for="codigo">Buscar por Codigo de Usuario: </label>
                        <input class="form-control" type="number"  name="codigoUsuario" id="codigoUsuario" placeholder="Ingrese el Codigo del Cliente">
                    </div>
                    <div class="form-group col-md-5">
                        <label for="nombre">Buscar por Nombre: </label>
                        <input class="form-control" type="text"  name="nombreUsuario" id="nombreUsuario" placeholder="Ingrese el Nombre del Cliente">
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
                        No Existe Ningun Cliente Registrado con los Campos de Busqueda que Usted Solicita
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-hover" >
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>DPI</th>
                                <th>Dirección</th>
                                <th>Sexo</th>
                                <th>Fecha de Nacimiento</th>
                                <th>DPI Escaneado</th>
                                <th>Editar Datos</th>

                            </tr>
                        </thead>

                        <c:forEach items="${Reporte}" var="resultado"> 
                            <tbody>
                                <tr>
                                    <td>${resultado.getCodigo()}</td>
                                    <td>${resultado.getNombre()}</td>
                                    <td>${resultado.getDPI()}</td>
                                    <td>${resultado.getDireccion()}</td>
                                    <td>${resultado.getSexo()}</td>
                                    <td>${resultado.getNacimiento()}</td>
                                    <td><a class="btn btn-outline-success" href="VisualizarDPICliente?action=verDPIPorCliente&codigoCliente=${resultado.getCodigo()}" target="__blank">Ver DPI Escaneado</a></td>
                                    <td><a class="btn btn-success" href="EditarCliente?codigoCliente=${resultado.getCodigo()}">Editar Cliente</a></td>
                                    <%-- <td><a href="NuevaCitaMedico?codigoMedico=${resultado.getCodigoMedico()}&tituloEspecialidad=${resultado.getTitulo()}&horaEntrada=${resultado.getHoraEntrada()}&horaSalida=${resultado.getHoraSalida()}" target="__blank">Agendar Cita</a></td>
                              --%>
                                      </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    </div>
                </c:otherwise>


            </c:choose>

        </div>
        
       

            
        </div>
        


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
