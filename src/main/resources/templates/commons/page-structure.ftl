[#include '*/commons/debug.ftl' /]
[#macro html_top]
<!DOCTYPE html>
<html lang="es">
	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">		
		<title>[@spring.message "app.nombre"/] - [@spring.message "app.titulo"/]</title>
		<meta content="Mineduc - Departamento de Tecnologia" name="Author" />
		<link href="https://staticv2.mineduc.cl/img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
		<!-- Bootstrap -->
		<link href="https://staticv2.mineduc.cl/css/bootstrap.min.css" rel="stylesheet" media="screen">	
		<link href="https://staticv2.mineduc.cl/css/mineduc-custom.css" rel="stylesheet" media="screen">	
		<link href="${context}/locals/css/font-fix.css" rel="stylesheet" media="screen">	
		<!-- Aquí otras hojas de estilo -->
		<link href="https://staticv2.mineduc.cl/css/jquery-ui.theme.min.css" rel="stylesheet" media="screen">  
	    <link href="https://staticv2.mineduc.cl/css/dataTables.bootstrap.min.css" rel="stylesheet" media="screen">    
	    <link href="https://staticv2.mineduc.cl/css/buttons.dataTables.min.css" rel="stylesheet" media="screen">    
	    <link href="https://staticv2.mineduc.cl/css/buttons.bootstrap.min.css" rel="stylesheet" media="screen">    

		[@scripts /]
		[@google_analytics /]
	</head>
	<body>
[/#macro]

[#macro scripts]
		<!-- faster loads -->
		<script src="https://staticv2.mineduc.cl/js/jquery-2.1.4.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/jquery-ui.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/bootstrap.min.js"></script>		
		
		<!-- scripts de usuario por template -->
		<script src="https://staticv2.mineduc.cl/js/jquery.dataTables.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/dataTables.bootstrap.min.js"></script>
   
		<script src="https://staticv2.mineduc.cl/js/dataTables.buttons.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/buttons.html5.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/buttons.print.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/jszip.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/pdfmake.min.js"></script>
		<script src="https://staticv2.mineduc.cl/js/vfs_fonts.js"></script>    		
		<!-- este es un script que solo se cargara en el template login -->
		<script type="text/javascript" src="${context}/locals/js/login/login.js"></script>
[/#macro]

[#macro html_bottom]
	
		</div>
	</body>
</html>
[/#macro]

[#macro footer]
		<br>
		<footer>
			<hr class="line-separator red"/>
			<address>
			<p>
				<strong>Ministerio de Educación de Chile</strong><br>
				<a href="https://www.gob.cl" title="Ir al portal del Gobierno de Chile">Gobierno de Chile</a><br>
				Dirección: Av. Libertador Bernardo O'Higgins 1371. Teléfono +56 2 24066000<br>
				<span class="text-blue-light">Versión ${version}</span><br>
			</p>
			</address>			
		</footer>	
[/#macro]

[#macro header]
	<div class="container with-outline page-mineduc rounded-bottom cf">
		<img class="logo-mineduc" src="https://staticv2.mineduc.cl/img/logo-med.png" alt="logo mineduc">
		<h1 class="title-application"><a href="#">[@spring.message "app.nombre"/]</a></h1>
		
[/#macro]

[#macro user_info]
		<div class="row user-info">
			<div class="col-sm-12 text-align-right">
				Bienvenido Sr(a). <strong>${(currentUser.nombre)!}&nbsp;${(currentUser.paterno)!}</strong>&nbsp;-&nbsp;<a class="red-text" href="${context}/logout">Cerrar Sesión</a>
			</div>
		</div>
		<div class="row">&nbsp;</div>
[/#macro]

[#macro menu]
		<div id="otro-menu-mineduc" class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Cambia navegación</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${context}/mvc/index.html">Bienvenida</a>
			</div>		
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
				
					[@menu_item_establecimientos /]
					[@menu_item_reportes /]
					[@menu_item_uns /]
					
				</ul>
			</div>			
			
			
		</div>
[/#macro]

[#macro menu_item_uns]
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Apoyo UNS&nbsp;<b class="caret"></b></a>
		<ul class="dropdown-menu">
			[@item_uns_excedentes /]
		</ul>
	</li>
[/#macro]

[#macro item_uns_excedentes]
			<li><a href="${context}/mvc/alumnos-excedentes/carga">Carga Masiva Alumnos Excedentes</a></li>
[/#macro]

[#macro menu_item_establecimientos]
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Ficha&nbsp;<b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="">Establecimiento</a></li>
		</ul>
	</li>
[/#macro]

[#macro menu_item_reportes]
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">Reportes&nbsp;<b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="">Asis.- Estado Avance Mes (Cursos)</a></li>
			<li><a href="">Asis.- Avance Mes (Alumnos)</a></li>
			<li><a href="">Asis.- % Declarado Mes</a></li>
			<li><a href="">Asist.Buscador % Declarado</a></li>
			<li><a href="">Reporte Actas Nacional</a></li>
			<li><a href="">Des Declaraciones</a></li>
		</ul>
	</li>
[/#macro]

[#macro no_javascript]
		<noscript>
			<div class="alert alert-warning alert-dismissible" role="alert">
				<span class="glyphicon glyphicon-alert"></span>
				<p>Para utilizar las funcionalidades completas de este sitio es necesario tener JavaScript habilitado, por favor habilite la funcionalidad para continuar.</p>
			</div>	 
		</noscript>
[/#macro]

[#macro main_page]
	[@html_top /]
	[#escape x as x?html]
		[@header /]
		[@user_info /]
		
		[@menu /]
		[@no_javascript /]
		<div class="container-fluid content with-outline rounded-top rounded-bottom">	
			[#nested /]
			[@footer /]
			[@_debugContent /]
		</div>
	[/#escape]
	[@html_bottom /]
[/#macro]

[#macro main_errors]
	[@html_top /]
	[#escape x as x?html]
		[@header /]
		[@no_javascript /]
		<div class="container-fluid content with-outline rounded-top rounded-bottom">	
			[#nested /]
			[@footer /]
			[@_debugContent /]
		</div>
	[/#escape]
	[@html_bottom /]
[/#macro]

[#macro main_page_login]
	[@html_top /]
	[#escape x as x?html]
		[@header/]
		[@no_javascript /]
		[#nested /]
			
		<div class="container-fluid content with-outline rounded-top rounded-bottom">	
				[@footer /]
		</div>
	[/#escape]
	[@html_bottom /]
[/#macro]

[#macro google_analytics]
	<!-- TODO: pegar aquí el codigo analytics de la aplicacion -->
[/#macro]

[#import "/spring.ftl" as spring /]
