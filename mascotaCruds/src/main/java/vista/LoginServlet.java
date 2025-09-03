package vista;

import java.io.IOException;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import modelo.*;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static final int MAX_INTENTOS = 5;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		String rol = request.getParameter("rol");

		try {
			Usuario user = usuarioDAO.buscarUsuario(usuario);

			if (user == null) {
				response.sendRedirect("error.jsp?msg=Usuario no encontrado");
				return;
			}

			if (user.isBloqueado()) {
				response.sendRedirect("error.jsp?msg=Usuario bloqueado");
				return;
			}

			Usuario validado = usuarioDAO.validarLogin(usuario, contrasena, rol);

			if (validado != null) {
				// Resetear intentos
				usuarioDAO.resetearIntentos(usuario);

				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", validado);

				response.sendRedirect("index.jsp");
			} else {
				int intentos = usuarioDAO.incrementarIntentos(usuario);

				if (intentos >= MAX_INTENTOS) {
					usuarioDAO.bloquearUsuario(usuario);
					enviarCorreoNotificacion(user.getCorreo(), usuario);
					response.sendRedirect("error.jsp?msg=Usuario bloqueado por intentos fallidos");
				} else {
					response.sendRedirect("error.jsp?msg=Intento " + intentos + " de " + MAX_INTENTOS);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp?msg=" + e.getMessage());
		}
		}

	

	// 📧 Método para enviar correo
	private void enviarCorreoNotificacion(String correoDestino, String usuario) {
		String remitente = "jeferpena32|1@gmail.com"; // tu correo
		String clave = "yvvn "; // usa contraseña de aplicación en Gmail
		String asunto = "⚠ Usuario bloqueado";
		String cuerpo = "El usuario " + usuario + " ha sido bloqueado tras 5 intentos fallidos.";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remitente, clave);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
			message.setSubject(asunto);
			message.setText(cuerpo);

			Transport.send(message);
			System.out.println("📧 Correo enviado a " + correoDestino);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
