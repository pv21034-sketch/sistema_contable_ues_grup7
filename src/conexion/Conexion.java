package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
        "jdbc:mysql://localhost:3306/sistemas_contables_grup4";

    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    public static Connection getConexion() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USUARIO, CLAVE);

        } catch (Exception e) {

            System.err.println("Error al conectar: " + e.getMessage());

            return null;
        }
    }
}