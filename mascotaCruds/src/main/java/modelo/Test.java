package modelo;

import javax.swing.JOptionPane;

public class Test {

	public static void main(String[] args) {
		Conexion conectarBD = new Conexion();
	
		
		
		
		if (conectarBD.conectarBD() != null) {
			JOptionPane.showMessageDialog(null, "Conectado a la base datos");
		} else {
			JOptionPane.showMessageDialog(null, "No conectado a la base datos");
		
			
		}

	}

}
