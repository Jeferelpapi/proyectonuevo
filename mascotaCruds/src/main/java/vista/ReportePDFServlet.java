package vista;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReportePDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();

		Connection conn = null;

		try {
			// 🔹 Conexión a MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "2556229");
	

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nombre, email FROM usuarios");

			// 🔹 Crear documento PDF
			Document documento = new Document();
			PdfWriter.getInstance(documento, out);
			documento.open();

			
			documento.add(new Paragraph("📄 Reporte de Usuarios"));
			documento.add(new Paragraph(" "));

			PdfPTable tabla = new PdfPTable(3); // 3 columnas
			tabla.addCell("ID");
			tabla.addCell("Nombre");
			tabla.addCell("Email");

			while (rs.next()) {
				tabla.addCell(String.valueOf(rs.getInt("id")));
				tabla.addCell(rs.getString("nombre"));
				tabla.addCell(rs.getString("email"));
			}

			documento.add(tabla);
			documento.close();

		} catch (Exception e) {
			e.printStackTrace();
			out.write(("Error generando PDF: " + e.getMessage()).getBytes());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
	}
}