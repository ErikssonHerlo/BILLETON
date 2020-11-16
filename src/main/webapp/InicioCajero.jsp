<%-- 
    Document   : InicioCajero
    Created on : 5/11/2020, 04:50:24
    Author     : erikssonherlo
--%>

<%@page import="java.time.LocalTime"%>
<%@page import="configuracion.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <%@ include file = "HeaderGeneral.html" %>
    <body>
      
    <% if((int)request.getSession().getAttribute("codigoUsuario") != 0 && (int)request.getSession().getAttribute("tipoUsuario") == 2) {
    %>
        <%@ include file = "NavBarCajero.jsp"%>
         <% Constantes.setEntradaUsuario(LocalTime.parse((String) request.getSession().getAttribute("horarioEntrada")));
           Constantes.setSalidaUsuario(LocalTime.parse((String) request.getSession().getAttribute("horarioSalida")));
           %>
        <%@ include file = "CarrouselGeneral.html"%>
        
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
