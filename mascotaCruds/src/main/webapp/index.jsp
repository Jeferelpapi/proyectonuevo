<%@page import="modelo.Conexion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sistema de Gestión de Personas</title>

<!-- Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background: linear-gradient(135deg, #1f4e79, #007bff);
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.card {
	background: rgba(255, 255, 255, 0.15);
	backdrop-filter: blur(10px);
	border: none;
	border-radius: 20px;
	padding: 2rem;
	width: 100%;
	max-width: 600px;
	box-shadow: 0px 8px 25px rgba(0, 0, 0, 0.2);
	color: white;
}

.card-header {
	font-size: 1.8rem;
	font-weight: bold;
	border-bottom: none;
	margin-bottom: 1rem;
}

.btn-custom {
	border-radius: 10px;
	font-weight: 600;
	padding: 12px 20px;
	font-size: 1.05rem;
	transition: transform 0.2s ease, box-shadow 0.2s ease;
	border: none;
}

.btn-custom:hover {
	transform: translateY(-2px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}

.btn-green {
	background-color: #28a745;
	color: white;
}

.btn-blue {
	background-color: #0dcaf0;
	color: white;
}
</style>
</head>
<body>

<div class="card text-center">
	<div class="card-header">📋 Sistema de Gestión de Personas</div>
	<div class="card-body">

	
		<%
		Conexion test = new Conexion();
		try {
		    if (!test.conectarBD().isClosed()) {
		%>
			<div class="alert alert-success" role="alert">
				✅ Conectado a la base de datos
			</div>
		<%
		    } else {
		%>
			<div class="alert alert-danger" role="alert">
				❌ No se pudo conectar a la base de datos
			</div>
		<%
		    }
		} catch (Exception e) {
		%>
			<div class="alert alert-warning" role="alert">
				⚠️ Error: <%= e.getMessage() %>
			</div>
		<%
		}
		%>

		<p class="mt-3">Bienvenido al sistema CRUD de personas. Selecciona una opción:</p>
		<div class="d-grid gap-3 mt-4">
			<a href="MascotaControlador?accion=listar"
				class="btn btn-custom btn-green">📑 Lista de mascotas</a>
			<a href="MascotaControlador?accion=nuevo"
				class="btn btn-custom btn-blue">➕ Agregar nueva mascota</a>
		</div>

		<hr class="my-4">

		<!-- Enlaces adicionales -->
		<div class="d-grid gap-2">
			<a href="TestConexion" class="btn btn-outline-light">🔗 Probar conexión a la BD</a>
			<a href="vistas/formulario.jsp" class="btn btn-outline-light">📄 Ver formulario</a>
		</div>
	</div>
	<div class="card-footer">Desarrollado con pasión por la tecnología</div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

