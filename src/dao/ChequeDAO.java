package dao;

import conexion.Conexion;
import modelo.Cheque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ChequeDAO {

    public boolean registrarCheque(Cheque chq) {

        String sqlCheque =
                "INSERT INTO cheques " +
                "(id_empresa, id_cuenta, numero_cheque, beneficiario, monto, fecha, concepto, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlMovimiento =
                "INSERT INTO movimientos_bancarios " +
                "(id_empresa, id_cuenta, fecha, tipo, monto, concepto, origen, id_origen, estado_conciliacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlActualizarSaldo =
                "UPDATE cuentas_bancarias " +
                "SET saldo_actual = saldo_actual - ? " +
                "WHERE id_cuenta = ?";

        try (Connection cn = Conexion.getConexion()) {

            cn.setAutoCommit(false);

            int idChequeGenerado;

            try (PreparedStatement psCheque =
                         cn.prepareStatement(sqlCheque, Statement.RETURN_GENERATED_KEYS)) {

                psCheque.setInt(1, 1);
                psCheque.setInt(2, chq.getIdCuenta());
                psCheque.setString(3, chq.getNumeroCheque());
                psCheque.setString(4, chq.getBeneficiario());
                psCheque.setDouble(5, chq.getMonto());
                psCheque.setDate(6, chq.getFecha());
                psCheque.setString(7, chq.getConcepto());
                psCheque.setString(8, "EMITIDO");

                psCheque.executeUpdate();

                try (ResultSet rs = psCheque.getGeneratedKeys()) {
                    if (rs.next()) {
                        idChequeGenerado = rs.getInt(1);
                    } else {
                        cn.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement psMov = cn.prepareStatement(sqlMovimiento)) {

                psMov.setInt(1, 1);
                psMov.setInt(2, chq.getIdCuenta());
                psMov.setDate(3, chq.getFecha());
                psMov.setString(4, "EGRESO");
                psMov.setDouble(5, chq.getMonto());
                psMov.setString(6, "Emisión de cheque N° " + chq.getNumeroCheque());
                psMov.setString(7, "CHEQUE");
                psMov.setInt(8, idChequeGenerado);
                psMov.setString(9, "PENDIENTE");

                psMov.executeUpdate();
            }

            try (PreparedStatement psSaldo = cn.prepareStatement(sqlActualizarSaldo)) {

                psSaldo.setDouble(1, chq.getMonto());
                psSaldo.setInt(2, chq.getIdCuenta());

                psSaldo.executeUpdate();
            }

            cn.commit();
            return true;

        } catch (SQLException e) {

            System.err.println("Error al registrar cheque: " + e.getMessage());
            return false;
        }
    }

    public void listarCuentas(JComboBox<String> combo) {

        String sql =
                "SELECT id_cuenta, banco, numero_cuenta " +
                "FROM cuentas_bancarias " +
                "WHERE estado = 'ACTIVO'";

        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            combo.removeAllItems();
            combo.addItem("Seleccione una cuenta...");

            while (rs.next()) {

                combo.addItem(
                        rs.getInt("id_cuenta") + " - " +
                        rs.getString("banco") + " - " +
                        rs.getString("numero_cuenta")
                );
            }

        } catch (SQLException e) {

            System.err.println("Error al llenar combo: " + e.getMessage());
        }
    }

    public List<Cheque> listarCheques() {

        List<Cheque> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM cheques ORDER BY id_cheque DESC";

        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Cheque c = new Cheque();

                c.setIdCheque(rs.getInt("id_cheque"));
                c.setIdCuenta(rs.getInt("id_cuenta"));
                c.setNumeroCheque(rs.getString("numero_cheque"));
                c.setBeneficiario(rs.getString("beneficiario"));
                c.setMonto(rs.getDouble("monto"));
                c.setFecha(rs.getDate("fecha"));
                c.setConcepto(rs.getString("concepto"));

                lista.add(c);
            }

        } catch (SQLException e) {

            System.err.println("Error listando cheques: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminar(int idCheque) {

        String sql =
                "UPDATE cheques SET estado = 'ANULADO' WHERE id_cheque = ?";

        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
        ) {

            ps.setInt(1, idCheque);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error anulando cheque: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Cheque chq) {

        String sql =
                "UPDATE cheques SET " +
                "numero_cheque = ?, " +
                "beneficiario = ?, " +
                "monto = ?, " +
                "fecha = ?, " +
                "concepto = ? " +
                "WHERE id_cheque = ?";

        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
        ) {

            ps.setString(1, chq.getNumeroCheque());
            ps.setString(2, chq.getBeneficiario());
            ps.setDouble(3, chq.getMonto());
            ps.setDate(4, chq.getFecha());
            ps.setString(5, chq.getConcepto());
            ps.setInt(6, chq.getIdCheque());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Error al actualizar cheque: " + e.getMessage());
            return false;
        }
    }

    public List<Cheque> buscarCheques(String texto) {

        List<Cheque> lista = new ArrayList<>();

        String sql =
                "SELECT * FROM cheques " +
                "WHERE beneficiario LIKE ? " +
                "OR numero_cheque LIKE ? " +
                "ORDER BY id_cheque DESC";

        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Cheque c = new Cheque();

                    c.setIdCheque(rs.getInt("id_cheque"));
                    c.setIdCuenta(rs.getInt("id_cuenta"));
                    c.setNumeroCheque(rs.getString("numero_cheque"));
                    c.setBeneficiario(rs.getString("beneficiario"));
                    c.setMonto(rs.getDouble("monto"));
                    c.setFecha(rs.getDate("fecha"));
                    c.setConcepto(rs.getString("concepto"));

                    lista.add(c);
                }
            }

        } catch (SQLException e) {

            System.err.println("Error buscando cheques: " + e.getMessage());
        }

        return lista;
    }
}