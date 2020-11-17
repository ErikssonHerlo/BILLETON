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
                        <a class="navbar-brand" href="InicioGerente.jsp">Banco El Billeton</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Ver Información <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                      
                                    <li class="dropdown-header">Usuarios</li>
                                    <li><a href="VisualizarClientes_G.jsp">Clientes</a></li>
                                    <li><a href="VisualizarCajeros_G.jsp">Cajeros</a></li>
                                    <li><a href="VisualizarGerentes_G.jsp">Gerentes</a></li>
               
                                    
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Cuentas Bancarias</li>
                                    <li><a href="VisualizarCuentasBancarias_G.jsp">Cuentas de los Clientes</a></li>
                                    
                                    
                                    
                                
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Agregar Nueva Información <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Usuarios</li>
                                    <li><a href="NuevoGerente_G.jsp">Nuevo Gerente</a></li>
                                    <li><a href="NuevoCajero_G.jsp">Nuevo Cajero</a></li>
                                    <li><a href="NuevoCliente_G.jsp">Nuevo Cliente</a></li>
                                    <li role="separator" class="divider"></li>
                                    
                                    <li class="dropdown-header">Cuentas Bancarias</li>
                                    <li><a href="NuevaCuentaBancaria_G.jsp">Nueva Cuenta</a></li>
                               
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Configuración <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Configuracion de Reportes</li>
                                    <li><a href="EditarLimiteReportes">Ver y Editar Limite de Comparación para Reportes</a></li>
                                    

                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reportes <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                   
                                    <li class="dropdown-header">Historial de Cambios</li>
                                    <li><a href="">Historial de Cambios de un Usuario</a></li>
                                    <li><a href="">Historial de Cambios de un Cajero</a></li>
                                    <li><a href="">Historial de Cambios de un Gerente</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Transacciones de Clientes</li>
                                    <li><a href="Reporte2_Gerente.jsp">Transacciones Monetarias Mayores a un Límite Establecido</a></li>
                                    <li><a href="Reporte3_Gerente.jsp">Transacciones Sumadas Mayores a un Límite Establecido</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li class="dropdown-header">Clientes</li>
                                    <li><a href="Reporte4_Gerente.jsp">Los 10 Clientes con más Dinero en sus Cuentas</a></li>
                                    <li><a href="">Clientes sin Transacciones en un Intervalo de Tiempo</a></li>
                                    
                                    <li role="separator" class="divider"></li>
                                    
                                    <li class="dropdown-header">Historial de Transacciones</li>
                                    <li><a href="Reporte6_Gerente.jsp">Historial de Transacciones por Cliente</a></li>

                                     <li role="separator" class="divider"></li>
                                    
                                    <li class="dropdown-header">Cajeros</li>
                                    <li><a href="Reporte7_Gerente.jsp">Cajeros con más Transacciones en un Intervalo de Tiempo</a></li>

                                </ul>
                            </li>
                            
                            <li class="dropdown"><a href=""></a>__________</li>
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=(String)request.getSession().getAttribute("nombreUsuario")%> <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="dropdown-header">Perfil</li>
                                    <li><a href="VisualizarInfoPropiaGerente">Ver mi Información</a></li>
                                   
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
