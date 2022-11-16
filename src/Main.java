import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Connection con;
        String insert = "INSERT INTO libros VALUES (null,?,?,?,?)";
        String select = "SELECT * FROM libros";
        String delete = "DELETE FROM libros WHERE id = ?";
        String update = "UPDATE libros SET nombre = ?, autor = ?, fecha_publicacion = ?, editorial = ? WHERE id = ?";
        PreparedStatement psi, pss, psd, psu;
        ResultSet rs;
        int campo;
        boolean flag, flag2 = false, flag3, flag4, validar;
        int count;
        Scanner sc = new Scanner(System.in);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            System.out.println("+------------------------------------+");
            System.out.println("| CONEXIÓN ESTABLECIDA CORRECTAMENTE |");
            System.out.println("+------------------------------------+");
            System.out.println();
            //MENÚ
            do {
                do {
                    try {
                        System.out.println("+------------------------------------+");
                        System.out.println("|          ELIGE UNA OPCIÓN          |");
                        System.out.println("| 1. Insertar nuevo libro            |");
                        System.out.println("| 2. Consultar lista de libros       |");
                        System.out.println("| 3. Modificar un libro              |");
                        System.out.println("| 4. Eliminar un libro               |");
                        System.out.println("+------------------------------------+");
                        String z = sc.nextLine();
                        int opcion = Integer.parseInt(z);
                        flag4 = true;
                        if (opcion == 1) {
                            //INSERT
                            psi = con.prepareStatement(insert);
                            System.out.println("Introduzca el nombre del libro");
                            String nombre = sc.nextLine();
                            psi.setString(1, nombre);
                            System.out.println("Introduzca el nombre del autor del libro");
                            String autor = sc.nextLine();
                            psi.setString(2, autor);
                            System.out.println("Introduzca la fecha de publicación del libro");
                            String fechapublicacion = sc.nextLine();
                            psi.setString(3, fechapublicacion);
                            System.out.println("Introduzca la editorial del libro");
                            String editorial = sc.nextLine();
                            psi.setString(4, editorial);
                            psi.execute();
                        } else if (opcion == 2) {
                            //SELECT
                            pss = con.prepareStatement(select);
                            rs = pss.executeQuery();
                            while (rs.next()) {
                                int id = rs.getInt("id");
                                String nombre2 = rs.getString("nombre");
                                String autor2 = rs.getString("autor");
                                String fechapublicacion2 = rs.getString("fecha_publicacion");
                                String editorial2 = rs.getString("editorial");
                                System.out.print("| " + id);
                                System.out.print(" | " + nombre2);
                                System.out.print(" | " + autor2);
                                System.out.print(" | " + fechapublicacion2);
                                System.out.print(" | " + editorial2 + " | ");
                                System.out.println();
                            }
                        } else if (opcion == 3) {
                            //UPDATE
                            pss = con.prepareStatement(select);
                            rs = pss.executeQuery();
                            psu = con.prepareStatement(update);
                            do {
                                try {
                                    while (rs.next()) {
                                        int id = rs.getInt("id");
                                        String nombre2 = rs.getString("nombre");
                                        String autor2 = rs.getString("autor");
                                        String fechapublicacion2 = rs.getString("fecha_publicacion");
                                        String editorial2 = rs.getString("editorial");
                                        System.out.print("| " + id);
                                        System.out.print(" | " + nombre2);
                                        System.out.print(" | " + autor2);
                                        System.out.print(" | " + fechapublicacion2);
                                        System.out.print(" | " + editorial2 + " | ");
                                        System.out.println();
                                    }
                                    System.out.println("Introduce el nuevo nombre");
                                    String nombre3 = sc.nextLine();
                                    psu.setString(1, nombre3);
                                    System.out.println("Introduce el nuevo autor");
                                    String autor3 = sc.nextLine();
                                    psu.setString(2, autor3);
                                    System.out.println("Introduce la nueva fecha de publicación");
                                    String fechapublicacion3 = sc.nextLine();
                                    psu.setString(3, fechapublicacion3);
                                    System.out.println("Introduce la nueva editorial");
                                    String editorial3 = sc.nextLine();
                                    psu.setString(4, editorial3);
                                    do {
                                        System.out.println("Introduce el ID del libro a modificar");
                                        String y = sc.nextLine();
                                        int id2 = Integer.parseInt(y);
                                        psu.setInt(5, id2);
                                        count = psu.executeUpdate();
                                        if (count > 0) {
                                            psu.execute();
                                            System.out.println("Libro modificado correctamente.");
                                            validar = false;
                                        } else {
                                            System.out.println("Esa ID no corresponde a ningún libro.");
                                            System.out.println();
                                            validar = true;
                                        }
                                    } while (validar);
                                    validar = true;
                                } catch (Exception e) {
                                    System.out.println("ERROR, inténtalo de nuevo");
                                    rs = pss.executeQuery();
                                    validar = false;
                                }
                            } while (!validar);
                        } else if (opcion == 4) {
                            //DELETE
                            pss = con.prepareStatement(select);
                            rs = pss.executeQuery();
                            psd = con.prepareStatement(delete);
                            do {
                                try {
                                    while (rs.next()) {
                                        int id = rs.getInt("id");
                                        String nombre2 = rs.getString("nombre");
                                        String autor2 = rs.getString("autor");
                                        String fechapublicacion2 = rs.getString("fecha_publicacion");
                                        String editorial2 = rs.getString("editorial");
                                        System.out.print("| " + id);
                                        System.out.print(" | " + nombre2);
                                        System.out.print(" | " + autor2);
                                        System.out.print(" | " + fechapublicacion2);
                                        System.out.print(" | " + editorial2 + " | ");
                                        System.out.println();
                                    }
                                    System.out.println();
                                    System.out.println("Introduce el ID del libro que desea eliminar");
                                    String x = sc.nextLine();
                                    campo = Integer.parseInt(x);
                                    psd.setInt(1, campo);
                                    count = psd.executeUpdate();
                                    if (count > 0) {
                                        psd.execute();
                                        System.out.println("Libro eliminado correctamente");
                                        flag = true;
                                    } else {
                                        System.out.println("Esa ID no corresponde a ningún libro.");
                                        System.out.println();
                                        flag = false;
                                    }
                                } catch (Exception e) {
                                    System.out.println("El valor introducido es incorrecto");
                                    System.out.println();
                                    rs = pss.executeQuery();
                                    flag = false;
                                }
                            } while (!flag);
                        } else {
                            System.out.println("El valor introducido no corresponde a ninguna de las opciones dadas");
                            System.out.println();
                            flag4 = false;
                        }
                    } catch (Exception e) {
                        System.out.println("El valor introducido es incorrecto.");
                        System.out.println();
                        flag4 = false;
                    }
                } while (!flag4);
                do {
                    System.out.println("+------------------------------------+");
                    System.out.println("|      ¿Deseas hacer algo más?       |");
                    System.out.println("|               Si / No              |");
                    System.out.println("+------------------------------------+");
                    String continuar = sc.nextLine();
                    continuar = continuar.toLowerCase();
                    if (continuar.equals("si")) {
                        flag3 = true;
                    } else if (continuar.equals("no")) {
                        flag2 = true;
                        flag3 = true;
                    } else {
                        System.out.println("No has escrito una opción válida, por favor, escribe 'si' o  'no'");
                        flag3 = false;
                    }
                } while (!flag3);
            } while (!flag2);
        } catch (Exception e) {
            System.out.println("+-------------------+");
            System.out.println("| CONEXION INVALIDA |");
            System.out.println("+-------------------+");
        }
    }
}