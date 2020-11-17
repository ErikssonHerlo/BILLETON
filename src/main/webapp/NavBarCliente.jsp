<%-- 
    Document   : NavBarGerente
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
                        <a class="navbar-brand" href="InicioCliente.jsp">Banca Virtual El Billeton</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Realizar Transferencia <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                      
                                    <li class="dropdown-header">Cuentas Propias</li>
                                    <li><a href="ObtenerCuentasPropias">Transferencia a Cuenta Propia</a></li>
                                    
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Cuentas de Terceros</li>
                                    <li><a href="">Transferencia a Cuenta de Terceros</a></li>
                                                          
                                </ul>
                            </li>
                            <li class="dropdown"><a href="">Ver Saldo de Cuenta</a></li>
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Asociación de Cuentas <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Solicitudes</li>
                                    <li><a href="">Realizar Solicitud de Asociación de Cuentas de Terceros</a></li>
                                 
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Notificaciones</li>
                                      <li><a href="">Solicitudes de Asociación de Cuentas de Terceros Recibidas</a></li>
                                   
                               
                                </ul>
                            </li>
            
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reportes <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                   
                                    <li class="dropdown-header">Transacciones</li>
                                    <li><a href="">Las Últimas 15 Transacciones más Grandes Realizadas en el Ultimo Año</a></li>
                                    <li><a href="">Listado de Transacciones Realizadas dentro de un Intervalo de Tiempo</a></li>
                         
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Cuentas Bancarias</li>
                                    <li><a href="">Cuenta con Más Saldo y sus Transacciones  un Intervalo de Tiempo</a></li>
                                 
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Asociación de Cuentas de Terceros</li>
                                    <li><a href="">Historial de Solicitudes de Asociación Recibidas</a></li>
                                    <li><a href="">Historial de Solicitudes de Asociación Enviadas</a></li>
                                    
                                </ul>
                            </li>
                            
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=(String)request.getSession().getAttribute("nombreUsuario")%> <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Perfil</li>
                                    <li><a href="#">Ver mi Información</a></li>
                                    <li><a href="VisualizarDPICliente?action=verDPIPropio" target="_blank">Ver mi DPI</a></li>
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
