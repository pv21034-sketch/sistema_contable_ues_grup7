package vista;

import modelo.Conexion;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JComboBox<String> cmbEmpresa;
    private int[] idsEmpresas;

    public LoginFrame() {
        
        setTitle("Sistema Contable - Iniciar Sesión");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(new JLabel("Empresa:"));
        cmbEmpresa = new JComboBox<>();
        cargarEmpresas();
        panel.add(cmbEmpresa);

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        txtClave = new JPasswordField();
        panel.add(txtClave);

        panel.add(new JLabel(""));
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(e -> iniciarSesion());
        panel.add(btnEntrar);

        add(panel);
        setVisible(true);
    }

    private void cargarEmpresas() {
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                "SELECT id_empresa, nombre_comercial FROM empresa")) {

            List<String> nombres = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();

            while (rs.next()) {
                ids.add(rs.getInt("id_empresa"));
                nombres.add(rs.getString("nombre_comercial"));
            }

            idsEmpresas = ids.stream().mapToInt(i -> i).toArray();
            for (String n : nombres) cmbEmpresa.addItem(n);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando empresas: " + e.getMessage());
        }
    }

    private void iniciarSesion() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();
        int idEmpresa = idsEmpresas[cmbEmpresa.getSelectedIndex()];

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ? " +
                     "AND id_empresa = ? AND estado = 'ACTIVO'";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, idEmpresa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Bienvenido, " + rs.getString("nombre"));
                new MainFrame(idEmpresa, cmbEmpresa.getSelectedItem().toString());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}