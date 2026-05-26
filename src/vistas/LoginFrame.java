package vistas;

import conexion.Conexion;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JComboBox<String> cmbEmpresa;
    private int[] idsEmpresas;

    public LoginFrame() {

        setTitle("Sistema Contable - Iniciar Sesión");
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel fondo = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(70, 70, 70),
                        getWidth(), getHeight(),
                        new Color(160, 160, 160)
                );

                g2.setPaint(gp);

                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        fondo.setLayout(new GridBagLayout());

        JPanel tarjeta = new JPanel();

        tarjeta.setPreferredSize(new Dimension(340, 420));

        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        tarjeta.setLayout(new GridLayout(10, 1, 8, 8));

        JLabel titulo = new JLabel(
                "Iniciar Sesión",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        titulo.setForeground(new Color(60, 63, 65));

        JLabel subtitulo = new JLabel(
                "Accede a tu cuenta",
                SwingConstants.CENTER
        );

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));

        subtitulo.setForeground(Color.GRAY);

        cmbEmpresa = new JComboBox<>();

        cargarEmpresas();

        txtUsuario = new JTextField();

        txtClave = new JPasswordField();

        JButton btnEntrar = new JButton("Ingresar");

        btnEntrar.setFont(new Font("Arial", Font.BOLD, 15));

        btnEntrar.setForeground(Color.WHITE);

        btnEntrar.setBackground(new Color(60, 63, 65));

        btnEntrar.setFocusPainted(false);

        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnEntrar.addActionListener(e -> iniciarSesion());

        JButton btnCrearCuenta = new JButton("Crear Cuenta");

        btnCrearCuenta.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        btnCrearCuenta.setForeground(
                new Color(60, 63, 65)
        );

        btnCrearCuenta.setBackground(Color.WHITE);

        btnCrearCuenta.setFocusPainted(false);

        btnCrearCuenta.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        btnCrearCuenta.addActionListener(
                e -> new RegistroEmpresaUsuarioFrame()
        );

        tarjeta.add(titulo);

        tarjeta.add(subtitulo);

        tarjeta.add(new JLabel("Empresa:"));

        tarjeta.add(cmbEmpresa);

        tarjeta.add(new JLabel("Usuario:"));

        tarjeta.add(txtUsuario);

        tarjeta.add(new JLabel("Contraseña:"));

        tarjeta.add(txtClave);

        tarjeta.add(btnEntrar);

        tarjeta.add(btnCrearCuenta);

        fondo.add(tarjeta);

        add(fondo);

        setVisible(true);
    }

    private void cargarEmpresas() {

        try (

            Connection con = Conexion.getConexion();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT id_empresa, nombre_comercial FROM empresa"
            )

        ) {

            List<String> nombres = new ArrayList<>();

            List<Integer> ids = new ArrayList<>();

            while (rs.next()) {

                ids.add(rs.getInt("id_empresa"));

                nombres.add(
                        rs.getString("nombre_comercial")
                );
            }

            idsEmpresas =
                    ids.stream().mapToInt(i -> i).toArray();

            for (String n : nombres) {

                cmbEmpresa.addItem(n);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error cargando empresas: "
                    + e.getMessage()
            );
        }
    }

    private void iniciarSesion() {

        String user =
                txtUsuario.getText().trim();

        String pass =
                new String(
                        txtClave.getPassword()
                ).trim();

        if (cmbEmpresa.getSelectedIndex() == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una empresa."
            );

            return;
        }

        int idEmpresa =
                idsEmpresas[
                        cmbEmpresa.getSelectedIndex()
                ];

        String sql =
                "SELECT * FROM usuarios "
                + "WHERE usuario = ? "
                + "AND clave = ? "
                + "AND id_empresa = ? "
                + "AND estado = 'ACTIVO'";

        try (

            Connection con = Conexion.getConexion();

            PreparedStatement ps =
                    con.prepareStatement(sql)

        ) {

            ps.setString(1, user);

            ps.setString(2, pass);

            ps.setInt(3, idEmpresa);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bienvenido, "
                        + rs.getString("nombre")
                );

                new MainFrame(
                        idEmpresa,
                        cmbEmpresa
                                .getSelectedItem()
                                .toString()
                );

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario o contraseña incorrectos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }
}