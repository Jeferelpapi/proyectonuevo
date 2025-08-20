package vista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Mascota;
import modelo.MascotaDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/MascotaControlador")
public class MascotaControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;
    MascotaDAO dao = new MascotaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("listar")) {
            List<Mascota> lista = dao.listar();
            request.setAttribute("mascotas", lista);
            request.getRequestDispatcher("vistas/listar.jsp").forward(request, response);

        } else if (accion.equals("nuevo")) {
            request.getRequestDispatcher("vistas/formulario.jsp").forward(request, response);

        } else if (accion.equals("editar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Mascota m = dao.obtenerPorId(id);
                request.setAttribute("mascota", m);
                request.getRequestDispatcher("vistas/formulario.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("MascotaControlador?accion=listar");
            }

        } else if (accion.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
                mostrarAlerta(response, "Mascota eliminada correctamente", "MascotaControlador");
            } catch (NumberFormatException e) {
                mostrarAlerta(response, "Error al eliminar la mascota", "MascotaControlador");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        String edadStr = request.getParameter("edad");
        String idStr = request.getParameter("id");

        Mascota m = new Mascota();
        m.setNombre(nombre != null ? nombre.trim() : "");
        m.setEspecie(especie != null ? especie.trim() : "");

        try {
            m.setEdad(Integer.parseInt(edadStr));
        } catch (NumberFormatException e) {
            m.setEdad(0);
        }

        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                m.setId(Integer.parseInt(idStr.trim()));
                dao.actualizar(m);
                mostrarAlerta(response, "Mascota actualizada correctamente", "MascotaControlador");
            } catch (NumberFormatException e) {
                mostrarAlerta(response, "Error al actualizar la mascota", "MascotaControlador");
            }
        } else {
            dao.insertar(m);
            mostrarAlerta(response, "Mascota registrada correctamente", "MascotaControlador");
        }
    }

    // Método para mostrar alertas en el navegador y redirigir
    private void mostrarAlerta(HttpServletResponse response, String mensaje, String urlRedirect)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<script>alert('" + mensaje + "'); window.location='" + urlRedirect + "';</script>");
    }
}
