<%-- 
    Document   : Login
    Created on : 3/11/2020, 00:14:42
    Author     : erikssonherlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <%@ include file = "HeaderGeneral.html" %>
    <body>
          <% request.getSession().setAttribute("codigoUsuario", 0);
            request.getSession().setAttribute("nombreUsuario", null);
            request.getSession().setAttribute("horarioEntrada", null);
            request.getSession().setAttribute("horarioSalida", null);
            request.getSession().setAttribute("tipoUsuario", null);
            request.getSession().setAttribute("objetoUsuario", null);
            request.getSession().setAttribute("DPIUsuario", 0);
              
    %>
        <style>

.abs-center {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}

        </style>
        <% if (request.getAttribute("Exitoso") == null) {%>
        <div class="container" style="padding-top: 10px" >
          
                
            <center>
                <font size="7" class="align-content-lg-center"><strong>Banco El Billetón</strong></font>
                <h2 class="align-content-lg-center">Inicio de Sesión</h2>
                <img src="resources/user6.jpg" class="card-img-top" alt="9" width="230" height="230">
                </center>
            
            <div class="modal-dialog text-centered">
            <FORM class="px-5 py-4" METHOD="POST" ACTION="InicioSesion">  
                
                    <div class="form-group">
                        <label for="usuarioCodigo">Codigo de Usuario: </label>
                        <input class="form-control" type="text"  name="usuarioCodigo" id="usuarioCodigo" placeholder="Ingrese su Codigo de Usuario" required>
                    </div>

                
                
                    <div class="form-group">
                        <label for="usuarioPassword">Contraseña: </label>
                        <input class="form-control" type="text"  name="usuarioPassword" id="usuarioPassword" placeholder="Ingrese su Contraseña" required>
                    </div>



                <br>
                <CENTER>

                    <input align="center" class="btn btn-primary" type="submit" value="Iniciar Sesión" >
                    
                </CENTER>

            </FORM>
            </div>
       
            <center>
           <a class="dropdown-item" href="NuevoPacienteLogin.jsp">¿No tienes una Cuenta? Conoce como puedes Registrarte</a>
            </center>
            

        
        <% } else {%>
  <div class="container" style="padding-top: 10px" >
          
                
            <center>
                <font size="7" class="align-content-lg-center"><strong>Banco El Billetón</strong></font>
                <h2 class="align-content-lg-center">Inicio de Sesión</h2>
                <img src="resources/user6.jpg" class="card-img-top" alt="9" width="230" height="230">
                </center>
            
            <div class="modal-dialog text-centered">
            <% if ((boolean)request.getAttribute("Exitoso")) {%>
            <div class="alert alert-success">
                Inicio de Sesion Completado con Exito
            </div>
            <% } else {%>
            <div class="alert alert-danger">
                Contraseña o Codigo Incorrecto, Ingrese de Nuevo
            </div>
            </div>
            <div class="col-12 caja2">
                <CENTER>

                    <a href="Login.jsp" class="btn btn-primary">Ingresar Datos de Nuevo</a>

                </CENTER>
            </div>
            <% }%>
        </div>
        <% }%>
        <%@ include file = "FooterGeneral.html" %>
    </body>
</html>
