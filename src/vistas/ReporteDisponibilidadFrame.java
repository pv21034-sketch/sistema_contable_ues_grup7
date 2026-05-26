package vistas;

import conexion.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ReporteDisponibilidadFrame extends JFrame {

    private int idEmpresa;

    private JTable tablaReporte;

    private DefaultTableModel modelo;

    public ReporteDisponibilidadFrame(int idEmpresa) {

        this.idEmpresa = idEmpresa;

        setTitle("Reporte de Disponibilidad Diaria");
        setSize(950, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();

        modelo.addColumn("ID Cuenta");
        modelo.addColumn("Banco");
        modelo.addColumn("Número Cuenta");
        modelo.addColumn("Fecha");
        modelo.addColumn("Saldo Inicial");
        modelo.addColumn("Ingresos");
        modelo.addColumn("Egresos");
        modelo.addColumn("Saldo Disponible");

        tablaReporte = new JTable(modelo);

        add(new JScrollPane(tablaReporte), BorderLayout.CENTER);

        JButton btnActualizar =
                new JButton("Actualizar Reporte");

        btnActualizar.addActionListener(
                e -> cargarReporte()
        );

        add(btnActualizar, BorderLayout.SOUTH);

        cargarReporte();
    }

    private void cargarReporte() {

        modelo.setRowCount(0);

        String sql =
                "SELECT * " +
                "FROM vista_disponibilidad_diaria " +
                "WHERE id_empresa = ? " +
                "ORDER BY fecha DESC";

        try (
                Connection con = Conexion.getConexion();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    modelo.addRow(new Object[]{

                        rs.getInt("id_cuenta"),

                        rs.getString("banco"),

                        rs.getString("numero_cuenta"),

                        rs.getDate("fecha"),

                        rs.getDouble("saldo_inicial"),

                        rs.getDouble("ingresos"),

                        rs.getDouble("egresos"),

                        rs.getDouble("saldo_disponible")
                    });
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar reporte: "
                    + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new ReporteDisponibilidadFrame(1)
                    .setVisible(true);
        });
    }
}