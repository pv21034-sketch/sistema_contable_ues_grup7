package vistas;

import conexion.Conexion;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegistroEmpresaUsuarioFrame extends JFrame {

    private JTextField txtNombreComercial;
    private JTextField txtRazonSocial;
    private JTextField txtNRC;
    private JTextField txtNIT;
    private JTextField txtGiro;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JTextField txtNombreUsuario;
    private JTextField txtUsuario;
    private JPasswordField txtClave;

    public RegistroEmpresaUsuarioFrame() {

        setTitle("Crear Cuenta");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(12, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        txtNombreComercial = new JTextField();
        txtRazonSocial = new JTextField();
        txtNRC = new JTextField();
        txtNIT = new JTextField();
        txtGiro = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        txtNombreUsuario = new JTextField();
        txtUsuario = new JTextField();
        txtClave = new JPasswordField();

        panel.add(new JLabel("Nombre Comercial:"));
        panel.add(txtNombreComercial);

        panel.add(new JLabel("Razón Social:"));
        panel.add(txtRazonSocial);

        panel.add(new JLabel("NRC:"));
        panel.add(txtNRC);

        panel.add(new JLabel("NIT:"));
        panel.add(txtNIT);

        panel.add(new JLabel("Giro:"));
        panel.add(txtGiro);

        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);

        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);

        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);

        panel.add(new JLabel("Nombre del Usuario:"));
        panel.add(txtNombreUsuario);

        panel.add(new JLabel("Usuario Login:"));
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        panel.add(txtClave);

        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(e -> registrar());

        panel.add(new JLabel(""));
        panel.add(btnRegistrar);

        add(panel);
        setVisible(true);
    }

    private void registrar() {

        if (txtNombreComercial.getText().trim().isEmpty()
                || txtRazonSocial.getText().trim().isEmpty()
                || txtNRC.getText().trim().isEmpty()
                || txtNIT.getText().trim().isEmpty()
                || txtGiro.getText().trim().isEmpty()
                || txtDireccion.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty()
                || txtNombreUsuario.getText().trim().isEmpty()
                || txtUsuario.getText().trim().isEmpty()
                || new String(txtClave.getPassword()).trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        String sqlEmpresa =
                "INSERT INTO empresa "
                + "(nombre_comercial, razon_social, nrc, nit, giro, direccion, telefono, correo, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'ACTIVA')";

        String sqlUsuario =
                "INSERT INTO usuarios "
                + "(id_empresa, nombre, usuario, clave, rol, estado) "
                + "VALUES (?, ?, ?, ?, 'ADMIN', 'ACTIVO')";

        try (Connection con = Conexion.getConexion()) {

            con.setAutoCommit(false);

            PreparedStatement psEmpresa =
                    con.prepareStatement(sqlEmpresa, Statement.RETURN_GENERATED_KEYS);

            psEmpresa.setString(1, txtNombreComercial.getText().trim());
            psEmpresa.setString(2, txtRazonSocial.getText().trim());
            psEmpresa.setString(3, txtNRC.getText().trim());
            psEmpresa.setString(4, txtNIT.getText().trim());
            psEmpresa.setString(5, txtGiro.getText().trim());
            psEmpresa.setString(6, txtDireccion.getText().trim());
            psEmpresa.setString(7, txtTelefono.getText().trim());
            psEmpresa.setString(8, txtCorreo.getText().trim());

            psEmpresa.executeUpdate();

            ResultSet rs = psEmpresa.getGeneratedKeys();

            int idEmpresa = 0;

            if (rs.next()) {
                idEmpresa = rs.getInt(1);
            }

            PreparedStatement psUsuario = con.prepareStatement(sqlUsuario);

            psUsuario.setInt(1, idEmpresa);
            psUsuario.setString(2, txtNombreUsuario.getText().trim());
            psUsuario.setString(3, txtUsuario.getText().trim());
            psUsuario.setString(4, new String(txtClave.getPassword()).trim());

            psUsuario.executeUpdate();

            con.commit();

            JOptionPane.showMessageDialog(
                    this,
                    "Cuenta creada correctamente. Ya puede iniciar sesión."
            );

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage());
        }
    }
}