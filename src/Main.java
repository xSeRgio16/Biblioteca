import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con;
        String consulta = "INSERT INTO libros VALUES (null,?,?,?)";
        String select = "SELECT * FROM libros";
        String delete = "DELETE FROM libros WHERE nombre=?";
        PreparedStatement psc, pss, psd;
        ResultSet rs = null;
        String campo;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            JOptionPane.showMessageDialog(null, "La conexión si funciona :)");
            psc = con.prepareStatement(consulta);
            String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del libro");
            psc.setString(1, nombre);
            String autor = JOptionPane.showInputDialog(null, "Introduzca el nombre del autor");
            psc.setString(2, autor);
            String fechapublicacion = JOptionPane.showInputDialog(null, "Introduzca la fecha de publicación");
            psc.setString(3, fechapublicacion);
            psc.execute();
            pss = con.prepareStatement(select);
            rs = pss.executeQuery();
            while (rs.next()) {
                String nombre2 = rs.getString("Nombre");
                String autor2 = rs.getString("Autor");
                String fechapublicacion2 = rs.getString("Fecha_publicacion");
                System.out.print(" | " + nombre2);
                System.out.print(" | " + autor2);
                System.out.print(" | " + fechapublicacion2);
                System.out.println();
            }
            psd = con.prepareStatement(delete);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La conexión no funciona :(");
        }
    }
}