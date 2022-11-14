import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con;
        String consulta = "INSERT INTO libros VALUES (null,?,?,?)";
        PreparedStatement ps;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            JOptionPane.showMessageDialog(null, "La conexión si funciona :)");
            ps = con.prepareStatement(consulta);
            String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del libro");
            ps.setString(1, nombre);
            String autor = JOptionPane.showInputDialog(null, "Introduzca el nombre del autor");
            ps.setString(2, autor);
            String fechapublicacion = JOptionPane.showInputDialog(null, "Introduzca la fecha de publicación");
            ps.setString(3, fechapublicacion);
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La conexión no funciona :(");
        }
    }
}