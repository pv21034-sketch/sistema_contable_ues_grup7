package vista;

import modelo.Conexion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ConciliacionFrame extends JFrame {

    private JTable tablaMovimientos;
    private DefaultTableModel modelo;
    private int idEmpresa;

    public ConciliacionFrame(int idEmpresa) {
        this.idEmpresa = idEmpresa;
        setTitle("Módulo de Conciliación Bancaria");
        setSize(950, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Panel Superior con botón de actualización
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefrescar = new JButton("Actualizar Movimientos");
        panelSuperior.add(new JLabel("Empresa ID: " + idEmpresa));
        panelSuperior.add(btnRefrescar);
        add(panelSuperior, BorderLayout.NORTH);

        // Configuración de la Tabla
        String[] columnas = {"ID", "Fecha", "Tipo", "Monto", "Concepto", "Origen", "Estado"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Evita que el usuario edite las celdas directamente
            }
        };
        tablaMovimientos = new JTable(modelo);
        add(new JScrollPane(tablaMovimientos), BorderLayout.CENTER);

        // Panel Inferior con botón para conciliar
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnConciliar = new JButton("Marcar como CONCILIADO");
        btnConciliar.setBackground(new Color(46, 204, 113)); // Color verde
        btnConciliar.setForeground(Color.WHITE);
        panelBotones.add(btnConciliar);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnConciliar.addActionListener(e -> cambiarEstado("Conciliado"));

        // Carga inicial de datos
        cargarDatos();
    }

    private void cargarDatos() {
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar

        
        String sql = "SELECT id_movimiento, fecha, tipo, monto, concepto, origen, estado_conciliacion "
                + "FROM movimientos_bancarios WHERE id_empresa = ?";

        try (Connection con = Conexion.getConexion()) {
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Error de conexión con la base de datos.");
                return;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idEmpresa);
                ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    private void cambiarEstado(String nuevoEstado) {
        int fila = tablaMovimientos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
            return;
        }

        int idMov = (int) modelo.getValueAt(fila, 0);

        // Actualización del estado en la base de datos
        String sql = "UPDATE movimientos_bancarios SET estado_conciliacion = ? WHERE id_movimiento = ?";

        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (con != null) {
                ps.setString(1, nuevoEstado);
                ps.setInt(2, idMov);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Estado actualizado con éxito.");
                cargarDatos(); // Refrescar la tabla automáticamente
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }
}