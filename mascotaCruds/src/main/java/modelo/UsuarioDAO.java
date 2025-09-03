
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class UsuarioDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mascotas", "root", "2556229");
    }
    
    
    //Se conecta a la base de datos.

//Busca en la tabla usuarios al que tenga ese username.

//Si lo encuentra:

//Crea un objeto Usuario.

//Le asigna sus datos: usuario, contraseña, rol, intentos, si está bloqueado y el correo.

//Devuelve ese objeto.

//Si no lo encuentra, devuelve null.

    public Usuario buscarUsuario(String username) throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM usuarios WHERE username=?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setRol(rs.getString("rol"));
            u.setIntentos(rs.getInt("intentos"));
            u.setBloqueado(rs.getBoolean("bloqueado"));
            u.setCorreo(rs.getString("correo"));
            return u;
        }
        return null;
    }

    
    
    //Conecta con la base de datos.

//Verifica si existe un usuario con username, password y rol correctos y que NO esté bloqueado (bloqueado=FALSE).

//Si lo encuentra, crea un objeto Usuario con los datos básicos y lo devuelve.

//Si no existe, retorna
    public Usuario validarLogin(String usuario, String contrasena, String rol) throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM usuarios WHERE username=? AND password=? AND rol=? AND bloqueado=FALSE");
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ps.setString(3, rol);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setUsername(rs.getString("username"));
            u.setRol(rs.getString("rol"));
            return u;
        }
        return null;
    }

    
    //Suma +1 al contador de intentos fallidos de ese usuario.

//Luego consulta cuántos intentos lleva.

//Devuelve el número de intentos actuales.
    
    
    
    public int incrementarIntentos(String usuario) throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE usuarios SET intentos=intentos+1 WHERE username=?");
        ps.setString(1, usuario);
        ps.executeUpdate();

        ps = con.prepareStatement("SELECT intentos FROM usuarioss WHERE username=?");
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("intentos");
        }
        return 0;
    }

    
    //Cuando el usuario inicia sesión correctamente, resetea el contador de intentos fallidos a 0.
    
    public void resetearIntentos(String usuario) throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE usuarios SET intentos=0 WHERE username=?");
        ps.setString(1, usuario);
        ps.executeUpdate();
    }
    
    
    //Cambia el campo bloqueado a TRUE, es decir, inhabilita la cuenta del usuario.

       //A partir de aquí, aunque ponga usuario y contraseña correctos, ya no podrá iniciar sesión porque validarLogin() verifica que bloqueado=FALSE.

    public void bloquearUsuario(String usuario) throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE usuarios SET bloqueado=TRUE WHERE username=?");
        ps.setString(1, usuario);
        ps.executeUpdate();
    }
}


