package modelo;

import java.sql.*;
import java.util.*;

public class MascotaDAO {

	public List<Mascota> listar() {
		List<Mascota> lista = new ArrayList<>();
		String sql = "SELECT * FROM mascotas";

		
		
		//Llama al método Conexion.conectarBD() para obtener una conexión a la base de datos.

        //Si la conexión es null, imprime un error y devuelve la lista vacía (no se puede consultar sin conexión).
		try (Connection con = Conexion.conectarBD()) {
			if (con == null) {
				System.err.println("Conexión nula al listar mascotas.");
				return lista;
			}
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					Mascota m = new Mascota();
					m.setId(rs.getInt("id"));
					m.setNombre(rs.getString("nombre"));
					m.setEspecie(rs.getString("especie"));
					m.setEdad(rs.getInt("edad"));
					lista.add(m);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al listar mascotas: " + e.getMessage());
		}
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	

	public void insertar(Mascota m) {
		String sql = "INSERT INTO mascotas (nombre, especie, edad) VALUES (?, ?, ?)";

		try (Connection con = Conexion.conectarBD()) {
			if (con == null) {
				System.err.println("Conexión nula al insertar mascota.");
				return;
			}
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, m.getNombre());
				ps.setString(2, m.getEspecie());
				ps.setInt(3, m.getEdad());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("Error al insertar mascota: " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	

	public Mascota obtenerPorId(int id) {
		Mascota m = null;
		String sql = "SELECT * FROM mascotas WHERE id = ?";

		try (Connection con = Conexion.conectarBD()) {
			if (con == null) {
				System.err.println("Conexión nula al obtener mascota por ID.");
				return null;
			}
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						m = new Mascota();
						m.setId(rs.getInt("id"));
						m.setNombre(rs.getString("nombre"));
						m.setEspecie(rs.getString("especie"));
						m.setEdad(rs.getInt("edad"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener mascota por ID: " + e.getMessage());
		}
		return m;
	}
	
	
	
	
	
	
	
	
	

	public void actualizar(Mascota m) {
		String sql = "UPDATE mascotas SET nombre=?, especie=?, edad=? WHERE id=?";

		try (Connection con = Conexion.conectarBD()) {
			if (con == null) {
				System.err.println("Conexión nula al actualizar mascota.");
				return;
			}
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, m.getNombre());
				ps.setString(2, m.getEspecie());
				ps.setInt(3, m.getEdad());
				ps.setInt(4, m.getId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("Error al actualizar mascota: " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	

	public void eliminar(int id) {
		String sql = "DELETE FROM mascotas WHERE id=?";

		try (Connection con = Conexion.conectarBD()) {
			if (con == null) {
				System.err.println("Conexión nula al eliminar mascota.");
				return;
			}
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("Error al eliminar mascota: " + e.getMessage());
		}
	}
}
