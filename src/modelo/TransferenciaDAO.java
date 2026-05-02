package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Transferencia; // Ajusta según el nombre de tu paquete
import modelo.Conexion;      // El archivo de la Persona 2 [cite: 18, 19]

/**
 *
 * @author ing-jos-flores
 */
public class TransferenciaDAO {

    public boolean ejecutarTransferencia(Transferencia t) {
        String sqlSalida = "UPDATE cuentas_bancarias SET saldo_actual = saldo_actual - ? WHERE id_cuenta = ?";
        String sqlEntrada = "UPDATE cuentas_bancarias SET saldo_actual = saldo_actual + ? WHERE id_cuenta = ?";
        String sqlInsert = "INSERT INTO transferencias (id_empresa, id_cuenta_origen, id_cuenta_destino, monto, fecha, concepto, estado) VALUES (?,?,?,?,?,?,?)";

        Connection con = new Conexion().getConexion(); // Clase de la Persona 2 [cite: 19]

        try {
            con.setAutoCommit(false); // Iniciamos transacción

            // 1. Restar de origen 
            PreparedStatement ps1 = con.prepareStatement(sqlSalida);
            ps1.setDouble(1, t.getMonto());
            ps1.setInt(2, t.getIdCuentaOrigen());
            ps1.executeUpdate();

            // 2. Sumar a destino 
            PreparedStatement ps2 = con.prepareStatement(sqlEntrada);
            ps2.setDouble(1, t.getMonto());
            ps2.setInt(2, t.getIdCuentaDestino());
            ps2.executeUpdate();

            // 3. Registrar la transferencia [cite: 5]
            PreparedStatement ps3 = con.prepareStatement(sqlInsert);
            ps3.setInt(1, t.getIdEmpresa());
            ps3.setInt(2, t.getIdCuentaOrigen());
            ps3.setInt(3, t.getIdCuentaDestino());
            ps3.setDouble(4, t.getMonto());
            ps3.setString(5, t.getFecha());
            ps3.setString(6, t.getConcepto());
            ps3.setString(7, "Activo");
            ps3.executeUpdate();

            con.commit(); // Guardar todo
            return true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (Exception ex) {
            } // Deshacer si hay error
            return false;
        }
    }

    public double obtenerSaldoActual(int idCuenta) {
        double saldo = 0;
        String sql = "SELECT saldo_actual FROM cuentas_bancarias WHERE id_cuenta = ?";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo_actual");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener saldo: " + e.getMessage());
        }
        return saldo;
    }
    // MÉTODO 1: Buscar ID (Lógica de datos)

    public int obtenerIdPorNumero(String numeroCuenta) {
        int id = -1;
        String sql = "SELECT id_cuenta FROM cuentas_bancarias WHERE numero_cuenta = ?";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numeroCuenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_cuenta");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar ID: " + e.getMessage());
        }
        return id;
    }

// Nuevo método en el DAO para obtener los números de cuenta de la empresa
    public List<String> obtenerNumerosCuentas(int idEmpresa) {
        List<String> cuentas = new ArrayList<>();
        String sql = "SELECT numero_cuenta FROM cuentas_bancarias WHERE id_empresa = ?";

        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cuentas.add(rs.getString("numero_cuenta"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista de cuentas: " + e.getMessage());
        }
        return cuentas;
    }

// MÉTODO 2: Obtener datos para la tabla
    public DefaultTableModel cargarModeloTabla(int idEmpresa) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Número de Cuenta");
        modelo.addColumn("Banco");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Saldo Actual");
        modelo.addColumn("Último Concepto");
        modelo.addColumn("Última Fecha"); // Nueva columna para la fecha

        // Subconsultas para traer el último concepto y la última fecha de la tabla transferencias
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.banco, c.nombre, c.apellido, c.saldo_actual, "
                + "(SELECT t.concepto FROM transferencias t WHERE t.id_cuenta_origen = c.id_cuenta ORDER BY t.id_transferencia DESC LIMIT 1) as ultimo_concepto, "
                + "(SELECT t.fecha FROM transferencias t WHERE t.id_cuenta_origen = c.id_cuenta ORDER BY t.id_transferencia DESC LIMIT 1) as ultima_fecha "
                + "FROM cuentas_bancarias c WHERE c.id_empresa = ?";

        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_cuenta"),
                    rs.getString("numero_cuenta"),
                    rs.getString("banco"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getDouble("saldo_actual"),
                    rs.getString("ultimo_concepto") == null ? "N/A" : rs.getString("ultimo_concepto"),
                    rs.getString("ultima_fecha") == null ? "N/A" : rs.getString("ultima_fecha") // Manejo de nulos
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al cargar tabla completa: " + e.getMessage());
        }
        return modelo;
    }

    // Método para buscar datos del RECEPTOR usando el Número de Cuenta único
    public String[] buscarPorNumeroCuenta(String numeroCuenta) {
        String sql = "SELECT id_cuenta, nombre, apellido, saldo_actual FROM cuentas_bancarias WHERE numero_cuenta = ?";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numeroCuenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[]{
                    rs.getString("id_cuenta"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    String.valueOf(rs.getDouble("saldo_actual"))
                };
            }
        } catch (SQLException e) {
            System.out.println("Error en DAO (buscarCuenta): " + e.getMessage());
        }
        return null;
    }

// Método para buscar datos del EMISOR usando su ID
    public String[] buscarPorId(int idCuenta) {
        // Agregamos nombre y apellido a la consulta
        String sql = "SELECT numero_cuenta, nombre, apellido, saldo_actual FROM cuentas_bancarias WHERE id_cuenta = ?";
        try (Connection con = new Conexion().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Ahora devolvemos 4 elementos. ¡Esto evita el error de Index Out of Bounds!
                return new String[]{
                    rs.getString("numero_cuenta"), // datos[0]
                    rs.getString("nombre"), // datos[1]
                    rs.getString("apellido"), // datos[2]
                    String.valueOf(rs.getDouble("saldo_actual")) // datos[3]
                };
            }
        } catch (SQLException e) {
            System.out.println("Error en DAO (buscarId): " + e.getMessage());
        }
        return null;
    }

    public String generarNumeroCuentaUnico() {
        // Ejemplo: Prefijo 700 + últimos 7 dígitos del tiempo actual en milisegundos
        long millis = System.currentTimeMillis();
        String uniquePart = String.valueOf(millis).substring(String.valueOf(millis).length() - 7);
        return "700" + uniquePart;
    }

}
