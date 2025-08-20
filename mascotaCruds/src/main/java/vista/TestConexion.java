package vista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Conexion;
import java.io.IOException;
@WebServlet("/TestConexion")



public class TestConexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Conexion conectarBD = new Conexion();
		response.setContentType("text/html;charset=UTF-8");
		if (conectarBD.conectarBD() != null) {
			response.getWriter().println("<h2 style='color:green;'>Conectado a la base de datos</h2>");
		} else {
			response.getWriter().println("<h2 style='color:red;'>No conectado a la base de datos</h2>");
		}
	}
}
