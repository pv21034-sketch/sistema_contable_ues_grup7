package vistas;

import modelo.CuentaBancaria;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.CuentaBancariaDAO;

public class FormularioBancario extends javax.swing.JFrame {

    private int idEmpresa;

    // Base de datos temporal
    public static ArrayList<CuentaBancaria> listaCuentas =
            new ArrayList<>();

    public FormularioBancario(int idEmpresa) {

        this.idEmpresa = idEmpresa;

        initComponents();

        txtIdEmpresa.setText(String.valueOf(idEmpresa));

        txtIdEmpresa.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdEmpresa = new javax.swing.JTextField();
        txtBanco = new javax.swing.JTextField();
        txtNumeroCuenta = new javax.swing.JTextField();
        cbTipoCuenta = new javax.swing.JComboBox<>();
        cbEstado = new javax.swing.JComboBox<>();
        txtSaldoInicial = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtIdEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEmpresaActionPerformed(evt);
            }
        });

        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBancoKeyReleased(evt);
            }
        });

        cbTipoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Seleccione)", "Ahorros", "Corrientes " }));

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Seleccione)", "ACTIVO", "INACTIVO" }));

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel1.setText("Id Empresa :");

        jLabel2.setText("Banco :");

        jLabel3.setText("Numero de cuenta : ");

        jLabel4.setText("Tipo de cuenta : ");

        jLabel5.setText("Saldo Inicial :");

        jLabel6.setText("Estado :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setText("CUENTAS BANCARIAS ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTipoCuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBanco)
                            .addComponent(txtNumeroCuenta)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel7)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel7)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEmpresaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
          try {

        String ban =
                txtBanco.getText()
                        .trim()
                        .toUpperCase();

        String num =
                txtNumeroCuenta.getText()
                        .trim();

        String tipo =
                cbTipoCuenta
                        .getSelectedItem()
                        .toString();

        double saldo =
                Double.parseDouble(
                        txtSaldoInicial
                                .getText()
                                .trim()
                );

        String est =
                cbEstado
                        .getSelectedItem()
                        .toString();

        if (ban.isEmpty() || num.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        for (CuentaBancaria c : listaCuentas) {

            if (c.getIdEmpresa() != idEmpresa) {
                continue;
            }

            if (c.getNumeroCuenta()
                    .equalsIgnoreCase(num)) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cuenta ya existe.",
                        "Duplicado",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }

        CuentaBancaria nueva =
                new CuentaBancaria(
                        idEmpresa,
                        ban,
                        num,
                        tipo,
                        saldo,
                        est
                );

        listaCuentas.add(nueva);

        CuentaBancariaDAO dao =
                new CuentaBancariaDAO();

        boolean guardado =
                dao.guardar(nueva);

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cuenta guardada en MySQL."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error guardando en MySQL.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        limpiarCampos();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "El saldo debe ser numérico.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error inesperado: "
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
                                        
    DialogBuscar buscador =
        new DialogBuscar(this, true);

buscador.setLocationRelativeTo(this);

buscador.setVisible(true);

String numeroABuscar =
        buscador.getNumeroEncontrado().trim();

if (numeroABuscar.isEmpty()) {

    return;
}

boolean encontrado = false;

for (CuentaBancaria c : listaCuentas) {

    if (c.getIdEmpresa() != idEmpresa) {
        continue;
    }

    if (c.getNumeroCuenta()
            .equalsIgnoreCase(numeroABuscar)) {

        txtIdEmpresa.setText(
                String.valueOf(c.getIdEmpresa())
        );

        txtBanco.setText(
                c.getBanco()
        );

        txtNumeroCuenta.setText(
                c.getNumeroCuenta()
        );

        cbTipoCuenta.setSelectedItem(
                c.getTipoCuenta()
        );

        txtSaldoInicial.setText(
                String.valueOf(c.getSaldoInicial())
        );

        cbEstado.setSelectedItem(
                c.getEstado()
        );

        encontrado = true;

        break;
    }
}

if (!encontrado) {

    JOptionPane.showMessageDialog(
            this,
            "Cuenta no encontrada.",
            "Búsqueda",
            JOptionPane.WARNING_MESSAGE
    );
}
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       DialogEditar ventanaEdit =
            new DialogEditar(
                    this,
                    true,
                    idEmpresa
            );

    ventanaEdit.setLocationRelativeTo(this);

    ventanaEdit.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
String numeroBuscar =
        txtNumeroCuenta.getText().trim();

if (numeroBuscar.isEmpty()) {

    JOptionPane.showMessageDialog(
            this,
            "Ingrese un número de cuenta.",
            "Validación",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

boolean eliminado = listaCuentas.removeIf(
        c -> c.getIdEmpresa() == idEmpresa
                && c.getNumeroCuenta()
                        .equalsIgnoreCase(numeroBuscar)
);

if (eliminado) {

    limpiarCampos();

    JOptionPane.showMessageDialog(
            this,
            "Registro eliminado correctamente."
    );

} else {

    JOptionPane.showMessageDialog(
            this,
            "No se encontró el registro.",
            "Búsqueda",
            JOptionPane.WARNING_MESSAGE
    );
}

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBancoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyReleased
 String texto =
        txtBanco.getText()
                .toUpperCase()
                .trim();

txtBanco.setText(texto);
    }//GEN-LAST:event_txtBancoKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioBancario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioBancario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioBancario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioBancario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioBancario(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbTipoCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtIdEmpresa;
    private javax.swing.JTextField txtNumeroCuenta;
    private javax.swing.JTextField txtSaldoInicial;
    // End of variables declaration//GEN-END:variables
private void limpiarCampos() {

    txtIdEmpresa.setText("");

    txtBanco.setText("");

    txtNumeroCuenta.setText("");

    txtSaldoInicial.setText("");

    cbTipoCuenta.setSelectedIndex(0);

    cbEstado.setSelectedIndex(0);

    txtBanco.requestFocus();
}
}
