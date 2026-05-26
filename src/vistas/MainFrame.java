package vistas;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final int idEmpresa;
    private final String nombreEmpresa;

    public MainFrame(int idEmpresa, String nombreEmpresa) {

        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;

        setTitle("Sistema Contable - Módulo Bancos");
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(new Color(45, 45, 45));
        encabezado.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Módulo de Bancos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);

        JLabel empresa = new JLabel("Empresa: " + nombreEmpresa);
        empresa.setFont(new Font("Arial", Font.PLAIN, 14));
        empresa.setForeground(new Color(210, 210, 210));

        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(empresa, BorderLayout.SOUTH);

        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 20, 20));
        panelCentral.setBackground(new Color(235, 235, 235));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JButton btnCuentas = crearBoton("Cuentas Bancarias");
        JButton btnCheques = crearBoton("Emisión de Cheques");
        JButton btnTransferencias = crearBoton("Transferencias");
        JButton btnConciliacion = crearBoton("Conciliación Bancaria");
        JButton btnReporte = crearBoton("Disponibilidad Diaria");
        JButton btnSalir = crearBotonSalir("Cerrar Sesión");

        btnCuentas.addActionListener(e -> {
            FormularioBancario ventana = new FormularioBancario(idEmpresa);
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnCheques.addActionListener(e -> {
    RegistroChequeFrame ventana = new RegistroChequeFrame(idEmpresa);
    ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    ventana.setVisible(true);
});

        btnTransferencias.addActionListener(e -> {

    frmTrasferencias ventana =
            new frmTrasferencias(idEmpresa);

    ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    ventana.setVisible(true);
});

        btnConciliacion.addActionListener(e -> {
            ConciliacionFrame ventana = new ConciliacionFrame(idEmpresa);
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnReporte.addActionListener(e -> {
            ReporteDisponibilidadFrame ventana =
        new ReporteDisponibilidadFrame(idEmpresa);
            ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        panelCentral.add(btnCuentas);
        panelCentral.add(btnCheques);
        panelCentral.add(btnTransferencias);
        panelCentral.add(btnConciliacion);
        panelCentral.add(btnReporte);
        panelCentral.add(btnSalir);

        add(encabezado, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Arial", Font.BOLD, 15));
        boton.setForeground(new Color(45, 45, 45));
        boton.setBackground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 190, 190)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        return boton;
    }

    private JButton crearBotonSalir(String texto) {

        JButton boton = crearBoton(texto);

        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(90, 90, 90));

        return boton;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame(1, "EMPRESA DEMO").setVisible(true);
        });
    }
}