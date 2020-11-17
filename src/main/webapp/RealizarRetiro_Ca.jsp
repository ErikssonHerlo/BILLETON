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

        <% if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 2) {
    %>
        <%@ include file = "NavBarCajero.jsp"%>
        <%
            Constantes.setHorarioActual(Constantes.getHORARIO_MATUTINO_CALIFICACION());

        %>
        <% if (Constantes.getHorarioActual().isAfter(Constantes.getEntradaUsuario()) && Constantes.getHorarioActual().isBefore(Constantes.getSalidaUsuario())) 
        {
        %>
        
        <% if (request.getAttribute("InformeTransaccion") == null)
        {%>
        
        <% if (request.getAttribute("Exitoso") == null) {%>
        <div class="container" style="padding-top: 50px" >

            <h1 class="align-content-lg-center"><strong>Nuevo Retiro</strong></h1>

            <FORM class="col-12 caja2" METHOD="POST" ACTION="NuevoRetiro">  
                <div class="row">
                    <div class="form-group col-md-4">
                <h3 class="align-content-lg-center">Datos de la Cuenta Bancaria</h3>
                    </div>
                    <div class="form-group col-md-4">
                <h3 class="align-content-lg-center">Datos del Retiro</h3>
                </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="numeroCuenta">Número de Cuenta:</label>
                        <input class="form-control" type="number"  name="numeroCuenta" id="numeroCuenta" placeholder="Ingrese el Número de Cuenta" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="montoTransaccion">Monto de Retiro: </label>
                        <input class="form-control"  type="number" step="0.01" min="25.00" name="montoTransaccion" id="montoTransaccion" placeholder="Ingrese el Monto de Retiro" required>
                    </div>
                    
                </div>
              
                
                <div class="row">
                    <div class="form-group col-md-4">
                        <strong><small style=" font-size: 10px">* TODOS LOS CAMPOS SON OBLIGATORIOS</small></strong>    
                    </div>

                </div>

                <CENTER>
                    
                    <button id="buscarCuenta" class="btn btn-primary" type="submit" name="action" value="buscarCuenta">Buscar</button>
                    
                    <input class="btn" type="reset" value="Eliminar Datos">
                    <br>
                    <br>
                    <a href="InicioCajero.jsp" class="btn btn-primary">Volver al Inicio</a>
                </CENTER>

            </FORM>

        </div>
        <% } else {%>
        <div class="container" style="padding-top: 70px" >
            <h1 class="align-content-lg-center"><strong>Confirmación del Retiro</strong></h1>
            <% if ((boolean) request.getAttribute("Exitoso")) {%>
            
             <FORM class="col-12 caja2" METHOD="POST" ACTION="NuevoRetiro">  
            
                <div class="row">
                    <div class="form-group col-md-3">
                        <label for="numeroCuenta">Número de Cuenta:</label>
                        <input class="form-control" type="number"  name="numeroCuenta" id="numeroCuenta" value="${validacion.noCuenta}"placeholder="Ingrese el Número de Cuenta" required readonly>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="nombre">Dueño de la Cuenta: </label>
                        <input class="form-control"  type="text" name="nombre" id="nombre" value="${validacion.nombre}" placeholder="Ingrese el Monto de Depósito" required readonly>
                    </div>
                    
                    <div class="form-group col-md-3">
                        <label for="montoTransaccion">Monto de Retiro: </label>
                        <input class="form-control"  type="number" step="0.01" min="25.00" name="montoTransaccion" id="montoTransaccion" value="${validacion.monto}" placeholder="Ingrese el Monto de Depósito" required readonly>
                    </div>
                     <div class="form-group col-md-3">
                        <label>DPI del Cliente: </label>
                        <br>
                        <a class="btn btn-primary" href="VisualizarDPICliente?action=verDPIPorCliente&codigoCliente=${validacion.codigoCliente}" target="__blank">Ver DPI Escaneado</a>
                        
                    </div>
                    
                </div>
              
            

                <CENTER>
                    
                    <button id="buscarCuenta" class="btn btn-success" type="submit" name="action" value="confirmarTransaccion">Confirmar Transacción</button>
                    <a class="btn btn-danger" href="RealizarRetiro_Ca.jsp">Cancelar</a>
                    
                </CENTER>

            </FORM>

          
 

            <% } else {%>
            <div class="alert alert-danger">
                No se encontro ninguna Cuenta Bancaria, Por Favor ingrese los Datos Correctamente
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="RealizarRetiro_Ca.jsp" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>
            </div>
            <% }%>
        </div>
        <% }%>
        <% } else {%>
        
        <div class="container" style="padding-top: 60px" >
            <h1 class="align-content-lg-center"><strong>Informe del Retiro </strong></h1>
         <% if ((boolean) request.getAttribute("InformeTransaccion")) {%>
            
         
            <br>
            <div class="alert alert-success">
               Retiro realizado con Exito
            </div>
            <div class="row">
                
                
                <div class="form-group col-md-5">
                        <h3><strong>Codigo de Transaccion: </strong></h3>
                        <h4>${Informe.codigo}</h4>
                    </div>
                    <div class="form-group col-md-5">
                        <h3><strong>Nombre del Cliente Propietario de la Cuenta: </strong></h3>
                        <h4>${Informe.nombre}</h4>
                    </div>

                </div>

                    <div class="row">
                    <div class="form-group col-md-5">
                        <h3><strong>Numero de Cuenta: </strong></h3>
                        <h4>${Informe.noCuenta}</h4>
                    </div>
                    <div class="form-group col-md-5">
                        <h3><strong>Monto: </strong></h3>
                        <h4>Q. ${Informe.monto}</h4>
                    </div>

                    </div>
                <div class="row">
                   
                        <div class="form-group col-md-5">
                            <h3><strong>Fecha: </strong></h3>
                            <h4>${Informe.fecha}</h4>
                        </div>
                        <div class="form-group col-md-5">
                            <h3><strong>Hora: </strong></h3>
                            <h4>${Informe.hora}</h4>
                        </div>
s
                </div>
                         <CENTER>

                             <a href="InicioCajero.jsp" class="btn btn-primary">Volver al Inicio</a>

                </CENTER>
 

            <% } else {%>
            <div class="alert alert-danger">
                Error al Realizar el Retiro, Saldo de Cuenta Insuficiente
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="RealizarRetiro_Ca.jsp" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>
            </div>
            <% }%>
        
        
        </div>
        
        <%}%>
        <%} else {%>
        <div class="container" style="padding-top: 70px" >

            <div class="alert alert-success">
                El Usuario se encuentra fuera del Horario Laboral, por lo que tiene Restringidas ciertas Acciones dentro del Sistema
            </div>
        </div>
        <% }%>
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
