<%-- 
    Document   : CargaArchivo
    Created on : 3/11/2020, 12:22:22
    Author     : erikssonherlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "HeaderGeneral.html" %>
    <body>
        <% if (request.getAttribute("Exitoso") == null) {%>
        <div class="container" style="padding-top: 70px">
            <center>
                <h1 class="align-content-lg-center">Carga de Archivo XML</h1>

            </center>

            <FORM class="col-12 caja2" METHOD="POST" ACTION="CargaArchivo" enctype="multipart/form-data">  
                <div class="row">
                    <div class="form-group col-md-5">
                        <label>Elija el Archivo que desea Cargar a la Base de Datos: </label>
                    </div>
                    <div class="form-group col-md-5">

                        <input class="form-control" type="file"  name="archivoDB" id="archivoDB" placeholder="Elija el Archivo" accept="application/xml" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-5">
                        <label>Seleccione los Archivos Complementarios del Archivo XML:</label>
                    </div>
                    <div class="form-group col-md-5">
                        <input class="form-control" type="file"  name="archivosComplementariosDB" id="archivosComplementariosDB" placeholder="Elija los archivos" accept=".pdf" multiple required>
                    </div>
                </div>


                <br>
                <CENTER>

                    <input class="btn btn-primary" type="submit" value="Cargar">
                    <input class="btn" type="reset" value="Eliminar Datos">
                </CENTER>

            </FORM>

        </div>
        <% } else {%>
        <div class="container" style="padding-top: 70px" >
            <center>
                <h1 class="align-content-lg-center">Informe de la Carga de Archivo</h1>
            </center>
            <% if ((boolean) request.getAttribute("Exitoso")) {%>

            <div class="alert alert-success">
                Carga de Archivo Realizada con Exito
            </div>
            <% } else {%>
            <div class="alert alert-danger">
                Fallo en la Carga de Archivo

            </div>
         

            <% }%>
            <center>
            <h2 class="align-content-lg-center">Detalle del Informe</h2>
            </center>


            <div class="row">
                <h4 class="col-md-4"><strong>Gerentes Ingresados:</strong></h4>
                <h4 class="col-md-8"><%= (int) request.getAttribute("InformeGerentes")%></h4>
           
                <h4 class="col-md-4"><strong>Cajeros Ingresados:</strong></h4>
                <h4 class="col-md-8"><%= (int) request.getAttribute("InformeCajeros")%></h4>
            
                <h4 class="col-md-4"><strong>Clientes Ingresados:</strong></h4>
                <h4 class="col-md-8"><%= (int) request.getAttribute("InformeClientes")%></h4>
           
                <h4 class="col-md-4"><strong>Cuentas Bancarias Ingresadas:</strong></h4>
                <h4 class="col-md-8"><%= (int) request.getAttribute("InformeCuentas")%></h4>
            
                <h4 class="col-md-4"><strong>Transacciones Ingresadas:</strong></h4>
                <h4 class="col-md-8"><%= (int) request.getAttribute("InformeTransacciones")%></h4>
            </div>
            <br>
            <CENTER>

                <a href="Login.jsp" class="btn btn-primary">Iniciar Sesion</a>


            </CENTER>



            
        </div>
        <% }%>

<%@ include file = "FooterGeneral.html" %>
    </body>
</html>
