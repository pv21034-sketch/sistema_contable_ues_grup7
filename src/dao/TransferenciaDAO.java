package dao;

import conexion.Conexion;
import modelo.Transferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class TransferenciaDAO {

    public boolean ejecutarTransferencia(Transferencia t) {

        String sqlSalida =
                "UPDATE cuentas_bancarias " +
                "SET saldo_actual = saldo_actual - ? " +
                "WHERE id_cuenta = ? AND id_empresa = ?";

        String sqlEntrada =
                "UPDATE cuentas_bancarias " +
                "SET saldo_actual = saldo_actual + ? " +
                "WHERE id_cuenta = ? AND id_empresa = ?";

        String sqlInsertTransferencia =
                "INSERT INTO transferencias " +
                "(id_empresa, id_cuenta_origen, id_cuenta_destino, monto, fecha, concepto, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlMovimiento =
                "INSERT INTO movimientos_bancarios " +
                "(id_empresa, id_cuenta, fecha, tipo, monto, concepto, origen, id_origen, estado_conciliacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {

            con.setAutoCommit(false);

            int idTransferenciaGenerada;

            try (PreparedStatement ps1 = con.prepareStatement(sqlSalida)) {

                ps1.setDouble(1, t.getMonto());
                ps1.setInt(2, t.getIdCuentaOrigen());
                ps1.setInt(3, t.getIdEmpresa());

                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(sqlEntrada)) {

                ps2.setDouble(1, t.getMonto());
                ps2.setInt(2, t.getIdCuentaDestino());
                ps2.setInt(3, t.getIdEmpresa());

                ps2.executeUpdate();
            }

            try (PreparedStatement ps3 = con.prepareStatement(
                    sqlInsertTransferencia,
                    Statement.RETURN_GENERATED_KEYS
            )) {

                ps3.setInt(1, t.getIdEmpresa());
                ps3.setInt(2, t.getIdCuentaOrigen());
                ps3.setInt(3, t.getIdCuentaDestino());
                ps3.setDouble(4, t.getMonto());
                ps3.setString(5, t.getFecha());
                ps3.setString(6, t.getConcepto());
                ps3.setString(7, "COMPLETADA");

                ps3.executeUpdate();

                try (ResultSet rs = ps3.getGeneratedKeys()) {

                    if (rs.next()) {

                        idTransferenciaGenerada = rs.getInt(1);

                    } else {

                        con.rollback();

                        return false;
                    }
                }
            }

            try (PreparedStatement psMovSalida =
                         con.prepareStatement(sqlMovimiento)) {

                psMovSalida.setInt(1, t.getIdEmpresa());
                psMovSalida.setInt(2, t.getIdCuentaOrigen());
                psMovSalida.setString(3, t.getFecha());
                psMovSalida.setString(4, "EGRESO");
                psMovSalida.setDouble(5, t.getMonto());
                psMovSalida.setString(6, t.getConcepto());
                psMovSalida.setString(7, "TRANSFERENCIA");
                psMovSalida.setInt(8, idTransferenciaGenerada);
                psMovSalida.setString(9, "PENDIENTE");

                psMovSalida.executeUpdate();
            }

            try (PreparedStatement psMovEntrada =
                         con.prepareStatement(sqlMovimiento)) {

                psMovEntrada.setInt(1, t.getIdEmpresa());
                psMovEntrada.setInt(2, t.getIdCuentaDestino());
                psMovEntrada.setString(3, t.getFecha());
                psMovEntrada.setString(4, "INGRESO");
                psMovEntrada.setDouble(5, t.getMonto());
                psMovEntrada.setString(6, t.getConcepto());
                psMovEntrada.setString(7, "TRANSFERENCIA");
                psMovEntrada.setInt(8, idTransferenciaGenerada);
                psMovEntrada.setString(9, "PENDIENTE");

                psMovEntrada.executeUpdate();
            }

            con.commit();

            return true;

        } catch (SQLException e) {

            System.err.println(
                    "Error al ejecutar transferencia: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public double obtenerSaldoActual(
            int idCuenta,
            int idEmpresa
    ) {

        double saldo = 0;

        String sql =
                "SELECT saldo_actual " +
                "FROM cuentas_bancarias " +
                "WHERE id_cuenta = ? AND id_empresa = ?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idCuenta);
            ps.setInt(2, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    saldo = rs.getDouble("saldo_actual");
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error al obtener saldo: "
                    + e.getMessage()
            );
        }

        return saldo;
    }

    public int obtenerIdPorNumero(
            String numeroCuenta,
            int idEmpresa
    ) {

        int id = -1;

        String sql =
                "SELECT id_cuenta " +
                "FROM cuentas_bancarias " +
                "WHERE numero_cuenta = ? " +
                "AND id_empresa = ?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, numeroCuenta);
            ps.setInt(2, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    id = rs.getInt("id_cuenta");
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error al buscar ID: "
                    + e.getMessage()
            );
        }

        return id;
    }

    public List<String> obtenerNumerosCuentas(
            int idEmpresa
    ) {

        List<String> cuentas = new ArrayList<>();

        String sql =
                "SELECT numero_cuenta " +
                "FROM cuentas_bancarias " +
                "WHERE id_empresa = ? " +
                "AND estado = 'Activo'";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    cuentas.add(
                            rs.getString("numero_cuenta")
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error al obtener lista de cuentas: "
                    + e.getMessage()
            );
        }

        return cuentas;
    }

    public DefaultTableModel cargarModeloTabla(
            int idEmpresa
    ) {

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Número de Cuenta");
        modelo.addColumn("Banco");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Saldo Actual");
        modelo.addColumn("Último Concepto");
        modelo.addColumn("Última Fecha");

        String sql =
                "SELECT c.id_cuenta, c.numero_cuenta, c.banco, " +
                "c.nombre, c.apellido, c.saldo_actual, " +

                "(SELECT t.concepto " +
                "FROM transferencias t " +
                "WHERE t.id_cuenta_origen = c.id_cuenta " +
                "ORDER BY t.id_transferencia DESC LIMIT 1) " +
                "AS ultimo_concepto, " +

                "(SELECT t.fecha " +
                "FROM transferencias t " +
                "WHERE t.id_cuenta_origen = c.id_cuenta " +
                "ORDER BY t.id_transferencia DESC LIMIT 1) " +
                "AS ultima_fecha " +

                "FROM cuentas_bancarias c " +
                "WHERE c.id_empresa = ?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] fila = {

                        rs.getInt("id_cuenta"),

                        rs.getString("numero_cuenta"),

                        rs.getString("banco"),

                        rs.getString("nombre"),

                        rs.getString("apellido"),

                        rs.getDouble("saldo_actual"),

                        rs.getString("ultimo_concepto") == null
                                ? "N/A"
                                : rs.getString("ultimo_concepto"),

                        rs.getString("ultima_fecha") == null
                                ? "N/A"
                                : rs.getString("ultima_fecha")
                    };

                    modelo.addRow(fila);
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error SQL al cargar tabla: "
                    + e.getMessage()
            );
        }

        return modelo;
    }

    public String[] buscarPorNumeroCuenta(
            String numeroCuenta,
            int idEmpresa
    ) {

        String sql =
                "SELECT id_cuenta, nombre, apellido, saldo_actual " +
                "FROM cuentas_bancarias " +
                "WHERE numero_cuenta = ? " +
                "AND id_empresa = ?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, numeroCuenta);
            ps.setInt(2, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new String[]{

                        rs.getString("id_cuenta"),

                        rs.getString("nombre"),

                        rs.getString("apellido"),

                        String.valueOf(
                                rs.getDouble("saldo_actual")
                        )
                    };
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error buscando cuenta: "
                    + e.getMessage()
            );
        }

        return null;
    }

    public String[] buscarPorId(
            int idCuenta,
            int idEmpresa
    ) {

        String sql =
                "SELECT numero_cuenta, nombre, apellido, saldo_actual " +
                "FROM cuentas_bancarias " +
                "WHERE id_cuenta = ? AND id_empresa = ?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idCuenta);
            ps.setInt(2, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new String[]{

                        rs.getString("numero_cuenta"),

                        rs.getString("nombre"),

                        rs.getString("apellido"),

                        String.valueOf(
                                rs.getDouble("saldo_actual")
                        )
                    };
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error buscando por ID: "
                    + e.getMessage()
            );
        }

        return null;
    }

    public String generarNumeroCuentaUnico() {

        long millis = System.currentTimeMillis();

        String texto = String.valueOf(millis);

        String ultimosDigitos =
                texto.substring(texto.length() - 7);

        return "700" + ultimosDigitos;
    }
}