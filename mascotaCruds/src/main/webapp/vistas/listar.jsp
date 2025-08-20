<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, modelo.Mascota"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de mascotas</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
        margin: 0;
        padding: 20px;
    }
    h2 {
        text-align: center;
        color: #333;
    }
    .btn {
        display: inline-block;
        padding: 8px 14px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        margin-bottom: 15px;
    }
    .btn:hover {
        background-color: #45a049;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }
    th, td {
        padding: 12px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #4CAF50;
        color: white;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .acciones a {
        padding: 6px 10px;
        text-decoration: none;
        border-radius: 4px;
        color: white;
    }
    .editar {
        background-color: #2196F3;
    }
    .editar:hover {
        background-color: #0b7dda;
    }
    .eliminar {
        background-color: #f44336;
    }
    .eliminar:hover {
        background-color: #da190b;
    }
</style>
</head>
<body>

    <h2> personas Registradas</h2>
    <div style="text-align: right; margin-bottom: 10px;">
        <a href="MascotaControlador?accion=nuevo" class="btn">➕ Nueva Mascota</a>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Especie</th>
            <th>Edad</th>
            <th>Acciones</th>
        </tr>
        <%
        List<Mascota> mascotas = (List<Mascota>) request.getAttribute("mascotas");
        if (mascotas != null) {
            for (Mascota m : mascotas) {
        %>
        <tr>
            <td><%=m.getId()%></td>
            <td><%=m.getNombre()%></td>
            <td><%=m.getEspecie()%></td>
            <td><%=m.getEdad()%></td>
            <td class="acciones">
                <a href="MascotaControlador?accion=editar&id=<%=m.getId()%>" class="editar">✏ Editar</a>
                <a href="MascotaControlador?accion=eliminar&id=<%=m.getId()%>" class="eliminar">🗑 Eliminar</a>
            </td>
        </tr>
        <%
            }
        }
        %>
    </table>

</body>
</html>
