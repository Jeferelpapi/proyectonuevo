package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
	public static Connection conectarBD() {
		Connection conexion = null;
		String url = "jdbc:mysql://localhost:3306/mascotas";
		String usuario = "root";
		String contraseña = "2556229";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
			conexion = DriverManager.getConnection(url, usuario, contraseña);
			System.out.println("Conexión exitosa a la base de datos.");
		} catch (ClassNotFoundException e) {
			System.err.println("No se encontró el driver JDBC: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
			
		
		}
		return conexion;
		
		
		
		
	}
}