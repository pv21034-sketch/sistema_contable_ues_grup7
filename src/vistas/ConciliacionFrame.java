package vistas;

import conexion.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConciliacionFrame extends JFrame {

    private JTable tablaMovimientos;
    private DefaultTableModel modelo;
    private final int idEmpresa;

    public ConciliacionFrame(int idEmpresa) {

        this.idEmpresa = idEmpresa;

        setTitle("Módulo de Conciliación Bancaria");
        setSize(950, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        iniciarComponentes();
        cargarDatos();
    }

    private void iniciarComponentes() {

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnRefrescar = new JButton("Actualizar Movimientos");

        panelSuperior.add(new JLabel("Empresa ID: " + idEmpresa));
        panelSuperior.add(btnRefrescar);

        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {
            "ID",
            "Fecha",
            "Tipo",
            "Monto",
            "Concepto",
            "Origen",
            "Estado"
        };

        modelo = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tablaMovimientos = new JTable(modelo);
        tablaMovimientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(tablaMovimientos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnConciliar = new JButton("Marcar como CONCILIADO");

        panelBotones.add(btnConciliar);

        add(panelBotones, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarDatos());
        btnConciliar.addActionListener(e -> cambiarEstado("CONCILIADO"));
    }

    private void cargarDatos() {

        modelo.setRowCount(0);

        String sql =
                "SELECT id_movimiento, fecha, tipo, monto, concepto, origen, estado_conciliacion " +
                "FROM movimientos_bancarios " +
                "WHERE id_empresa = ? " +
                "ORDER BY fecha DESC, id_movimiento DESC";

        try (Connection con = Conexion.getConexion()) {

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error de conexión con la base de datos.",
                        "Conexión",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, idEmpresa);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        modelo.addRow(new Object[]{
                            rs.getInt("id_movimiento"),
                            rs.getDate("fecha"),
                            rs.getString("tipo"),
                            rs.getDouble("monto"),
                            rs.getString("concepto"),
                            rs.getString("origen"),
                            rs.getString("estado_conciliacion")
                        });
                    }
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar movimientos: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cambiarEstado(String nuevoEstado) {

        int fila = tablaMovimientos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un movimiento de la tabla.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea marcar este movimiento como " + nuevoEstado + "?",
                "Confirmar conciliación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        int idMovimiento = Integer.parseInt(
                modelo.getValueAt(fila, 0).toString()
        );

        String sql =
                "UPDATE movimientos_bancarios " +
                "SET estado_conciliacion = ? " +
                "WHERE id_movimiento = ? " +
                "AND id_empresa = ?";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error de conexión con la base de datos.",
                        "Conexión",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idMovimiento);
            ps.setInt(3, idEmpresa);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Movimiento actualizado correctamente."
                );

                cargarDatos();

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar el movimiento.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new ConciliacionFrame(1).setVisible(true);
        });
    }
}