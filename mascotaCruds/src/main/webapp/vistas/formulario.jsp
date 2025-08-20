<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="modelo.Mascota"%>

<%
Mascota mascota = (Mascota) request.getAttribute("mascota");
boolean esEdicion = mascota != null;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=esEdicion ? "Editar Mascota" : "Nueva Mascota"%></title>
</head>
<body>
	<h2><%=esEdicion ? "Editar Mascota" : "Nueva Mascota"%></h2>
	<form action="MascotaControlador" method="post">
		<input type="hidden" name="id"
			value="<%=esEdicion ? mascota.getId() : ""%>"> Nombre: <input
			type="text" name="nombre"
			value="<%=esEdicion ? mascota.getNombre() : ""%>"><br>
		Especie: <input type="text" name="especie"
			value="<%=esEdicion ? mascota.getEspecie() : ""%>"><br>
		Edad: <input type="number" name="edad"
			value="<%=esEdicion ? mascota.getEdad() : ""%>"><br> <input
			type="submit" value="Guardar">
	</form>
</body>
</html>
