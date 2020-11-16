<%-- 
    Document   : index
    Created on : 1/11/2020, 02:54:58
    Author     : erikssonherlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    conexionMySQL.Conexion.crearConexion();
%>
<html>
    <%@ include file = "HeaderGeneral.html" %>
    <body>
          <% if (request.getAttribute("Estado") == null) {%>
        <% RequestDispatcher despachar = request.getRequestDispatcher("InicioVistas");
           despachar.forward(request, response);%>
           <% } else {%>
             <% if ((boolean)request.getAttribute("Estado")) {%>
           
            <% response.sendRedirect("Login.jsp"); %>
        <% } else {%>
            <% response.sendRedirect("CargaArchivo.jsp"); %>
        <% }%>
        <% }%>
        <%@ include file = "FooterGeneral.html" %>

    </body>
</html>