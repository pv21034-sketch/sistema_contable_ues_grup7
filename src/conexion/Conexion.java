package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
        "jdbc:mysql://acela.proxy.rlwy.net:34564/railway?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String USUARIO = "root";

    private static final String CLAVE =
        "hqlYdWxTocchiShASKFsKcqprGNKFlrH";

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