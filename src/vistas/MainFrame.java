package vistas;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(int idEmpresa, String nombreEmpresa) {

        setTitle("Módulo Bancos | Empresa: " + nombreEmpresa);
        setSize(700, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JButton btnCuentas = new JButton("Cuentas Bancarias");
        JButton btnCheques = new JButton("Emisión de Cheques");
        JButton btnTransferencias = new JButton("Transferencias");
        JButton btnConciliacion = new JButton("Conciliación Bancaria");
        JButton btnReporte = new JButton("Disponibilidad Diaria");
        JButton btnSalir = new JButton("Cerrar Sesión");

        btnCuentas.addActionListener(e -> {
            FormularioBancario ventana = new FormularioBancario();
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnCheques.addActionListener(e -> {
            RegistroChequeFrame ventana = new RegistroChequeFrame();
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnTransferencias.addActionListener(e -> {
            frmTrasferencias ventana = new frmTrasferencias();
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnConciliacion.addActionListener(e -> {
            ConciliacionFrame ventana = new ConciliacionFrame(idEmpresa);
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnReporte.addActionListener(e -> {
            ReporteDisponibilidadFrame ventana = new ReporteDisponibilidadFrame();
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        panel.add(btnCuentas);
        panel.add(btnCheques);
        panel.add(btnTransferencias);
        panel.add(btnConciliacion);
        panel.add(btnReporte);
        panel.add(btnSalir);

        JLabel titulo = new JLabel(
                "  Módulo: Bancos  |  Empresa: " + nombreEmpresa,
                SwingConstants.LEFT
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        add(titulo, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame(1, "EMPRESA DEMO").setVisible(true);
        });
    }
}