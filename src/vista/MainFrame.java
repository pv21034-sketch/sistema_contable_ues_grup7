package vista;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(int idEmpresa, String nombreEmpresa) {
        setTitle("Módulo Bancos | Empresa: " + nombreEmpresa);
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JButton btnCuentas        = new JButton("Cuentas Bancarias");
        JButton btnCheques        = new JButton("Emision de Cheques");
        JButton btnTransferencias = new JButton("Transferencias");
        JButton btnConciliacion   = new JButton("Conciliacion Bancaria");
        JButton btnReporte        = new JButton("Disponibilidad Diaria");
        JButton btnSalir          = new JButton("Cerrar Sesion");

        // Aquí irás conectando los formularios de tus compañeros:
        // btnCuentas.addActionListener(e -> new CuentasBancariasFrame(idEmpresa));
        // btnCheques.addActionListener(e -> new ChequesFrame(idEmpresa));
        // btnTransferencias.addActionListener(e -> new TransferenciasFrame(idEmpresa));
        // btnConciliacion.addActionListener(e -> new ConciliacionFrame(idEmpresa));
        // btnReporte.addActionListener(e -> new ReporteDisponibilidadFrame(idEmpresa));

        btnSalir.addActionListener(e -> { new LoginFrame(); dispose(); });

        panel.add(btnCuentas);
        panel.add(btnCheques);
        panel.add(btnTransferencias);
        panel.add(btnConciliacion);
        panel.add(btnReporte);
        panel.add(btnSalir);

        add(new JLabel("  Modulo: Bancos  |  " + nombreEmpresa, SwingConstants.LEFT), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}