import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root", "");
            JOptionPane.showMessageDialog(null,"la conexion si funciona :)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"la conexion no funciona :(");
        }
    }
}