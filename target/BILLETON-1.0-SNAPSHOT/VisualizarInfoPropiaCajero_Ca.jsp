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
       
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Perfil del Cajero <%=request.getSession().getAttribute("nombreUsuario")%></strong></h1>
        
             
            <h2 class="align-content-lg-center">Datos de la Cuenta de Usuario:</h2>
              
              
             
        
            <div class="row">

                <div class="form-group col-md-4">
                    <h3><strong>Codigo de Usuario: </strong></h3>
                    <h4>${Usuario.codigo}</h4>
                </div>
                <div class="form-group col-md-4">
                    <h3><strong>Nombre: </strong></h3>
                    <h4>${Usuario.nombre}</h4>
                </div>

            </div>

            <div class="row">


                <div class="form-group col-md-4">
                    <h3><strong>DPI: </strong></h3>
                    <h4>${Usuario.DPI}</h4>
                </div>

                <div class="form-group col-md-4">
                    <h3><strong>Dirección: </strong></h3>
                    <h4>${Usuario.direccion}</h4>
                </div>
            </div>


            <div class="row">

                <div class="form-group col-md-4">
                    <h3><strong>Sexo: </strong></h3>
                    <h4>${Usuario.sexo}</h4>
                </div>
                <div class="form-group col-md-4">
                    <h3><strong>Turno: </strong></h3>
                    <h4>${Usuario.turno}</h4>
                </div>
            </div>

            <div class="row">

                <div class="form-group col-md-4">
                    <h3><strong>Horario de Inicio de Labores: </strong></h3>
                    <h4>${Usuario.horaEntrada} Horas</h4>
                </div>
                <div class="form-group col-md-6">
                    <h3><strong>Horario de Finalización de Labores: </strong></h3>
                    <h4>${Usuario.horaSalida} Horas</h4>
                </div>

            </div>


        </div>
 <CENTER>

                             <a href="InicioCajero.jsp" class="btn btn-primary">Volver al Inicio</a>

                </CENTER>
 


       
       <% } else if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 1) { %>
        <% response.sendRedirect(request.getContextPath()+"/InicioGerente.jsp"); %>
        <% } else if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 3) { %>
        <% response.sendRedirect(request.getContextPath()+"/InicioCliente.jsp"); %>
        <% } else { %>
    <% response.sendRedirect(request.getContextPath()+"/Login.jsp");
         %>
        <% }%>
        <%@ include file = "FooterGeneral.html" %>

    </body>
</html>
