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
            ps.setString(1, "Harry Potter");
            ps.setString(2, "Sergio");
            ps.setString(3, "12/09/1994");
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La conexión no funciona :(");
        }
    }
}