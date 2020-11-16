<%-- 
    Document   : NavBarCajero
    Created on : 3/11/2020, 08:19:33
    Author     : erikssonherlo
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   <div class="container">
            <!-- Fixed navbar -->
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="InicioCajero.jsp">Banco El Billeton</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown"><a href="RealizarDeposito_Ca.jsp">Realizar Déposito</a></li>
                        <li class="dropdown"><a href="">Realizar Retiro</a></li>
                            
                           
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reportes <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                   
                                    <li class="dropdown-header">Reportes del Día</li>
                                    <li><a href="">Listado de Dépositos y Retiros realizados durante su Turno</a></li>
                                   
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Reportes en un Intervalo de Tiempo</li>
                                    <li><a href="">Listado de las Transacciones Realizadas por día en un Intervalo de Tiempo</a></li>
                                   
                                </ul>
                            </li>
                            
                            <li class="dropdown"><a href=""></a>___________________</li>
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=(String)request.getSession().getAttribute("nombreUsuario")%> <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Perfil</li>
                                    <li><a href="VisualizarInfoPropiaCajero">Ver mi Información</a></li>
                                    
                                    <li class="dropdown-header">Sesión</li>
                                    <li><a href="CerrarSesion">Cerrar Sesión</a></li>
                          
                                </ul>
                            </li>
                            <li class="dropdown"><a href="CerrarSesion">Cerrar Sesión</a></li>

                        </ul>
                    </div>
                </div>
            </nav>
        </div>>
