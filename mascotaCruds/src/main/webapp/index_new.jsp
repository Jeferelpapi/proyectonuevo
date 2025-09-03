
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Controlador.LoginTest" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Iniciar Sesión</title>
<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: linear-gradient(135deg, #1976D2, #D81B60);
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	color: white;
}

.login-box {
	background: rgba(255, 255, 255, 0.1);
	padding: 40px;
	border-radius: 10px;
	backdrop-filter: blur(10px);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	width: 300px;
}

.login-box h2 {
	text-align: center;
	margin-bottom: 20px;
}

.login-box input[type="text"], .login-box input[type="password"] {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: none;
	border-radius: 5px;
}

.login-box label {
	display: block;
	margin-top: 10px;
	font-size: 0.95em;
}

.role-options {
	margin: 10px 0;
}

.role-options label {
	margin-right: 10px;
	font-size: 0.9em;
}

.login-box button {
	width: 100%;
	padding: 12px;
	border: none;
	background: #fff;
	color: #333;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
	margin-top: 20px;
	transition: background 0.3s;
}

.login-box button:hover {
	background: #f0f0f0;
}

.login-box a {
	color: #fff;
	text-decoration: underline;
	font-size: 0.9em;
	display: block;
	text-align: center;
	margin-top: 15px;
}
</style>
</head>

<body>
	<div class="login-box">
		<h2>Iniciar Sesión</h2>
		<form action="LoginServlet" method="get">
			Usuario: <input type="text" name="usuario" required><br>
			<br> Contraseña: <input type="password" name="contrasena"
				required><br>
			<br> Rol: <select name="rol">
				<option value="administrador">Administrador</option>
				<option value="empleado">Empleado</option>
			</select><br>
			<br>
			<button type="submit">Ingresar</button>
		</form>
	</div>
</body>
</html>
