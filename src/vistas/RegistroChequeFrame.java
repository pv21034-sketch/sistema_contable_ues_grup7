package vistas;

import dao.ChequeDAO;
import modelo.Cheque;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistroChequeFrame extends javax.swing.JFrame {

    private int idEmpresa;

    int idSeleccionado = 0;

    DefaultTableModel modelo;

    public RegistroChequeFrame(int idEmpresa) {

        this.idEmpresa = idEmpresa;

        initComponents();

        ChequeDAO dao = new ChequeDAO();

        dao.listarCuentas(cmbCuentaBancaria, idEmpresa);

        this.setLocationRelativeTo(null);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("N° Cheque");
        modelo.addColumn("Beneficiario");
        modelo.addColumn("Monto");
        modelo.addColumn("Fecha");
        modelo.addColumn("Concepto");

        tblMostrar.setModel(modelo);

        listar();
    }

    private void limpiarCampos() {

        txtNumeroCheque.setText("");
        txtBeneficiario.setText("");
        txtMonto.setText("");
        ftxtFecha.setText("");
        txtConcepto.setText("");
        cmbCuentaBancaria.setSelectedIndex(0);
    }

    public void filtrarTabla(String texto) {

        DefaultTableModel modeloTab =
                (DefaultTableModel) tblMostrar.getModel();

        modeloTab.setRowCount(0);

        ChequeDAO dao = new ChequeDAO();

        List<Cheque> lista =
                dao.buscarCheques(texto, idEmpresa);

        Object[] fila = new Object[6];

        for (int i = 0; i < lista.size(); i++) {

            fila[0] = lista.get(i).getIdCheque();
            fila[1] = lista.get(i).getNumeroCheque();
            fila[2] = lista.get(i).getBeneficiario();
            fila[3] = lista.get(i).getMonto();
            fila[4] = lista.get(i).getFecha();
            fila[5] = lista.get(i).getConcepto();

            modeloTab.addRow(fila);
        }
    }

    public void listar() {

        DefaultTableModel modeloTab =
                (DefaultTableModel) tblMostrar.getModel();

        modeloTab.setRowCount(0);

        ChequeDAO dao = new ChequeDAO();

        List<Cheque> lista =
                dao.listarCheques(idEmpresa);

        Object[] objeto = new Object[6];

        for (int i = 0; i < lista.size(); i++) {

            objeto[0] = lista.get(i).getIdCheque();
            objeto[1] = lista.get(i).getNumeroCheque();
            objeto[2] = lista.get(i).getBeneficiario();
            objeto[3] = lista.get(i).getMonto();
            objeto[4] = lista.get(i).getFecha();
            objeto[5] = lista.get(i).getConcepto();

            modeloTab.addRow(objeto);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCuentaBancaria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroCheque = new javax.swing.JTextField();
        txtBeneficiario = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        txtConcepto = new javax.swing.JTextField();
        ftxtFecha = new javax.swing.JFormattedTextField();
        cmbCuentaBancaria = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMostrar = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBuscarPorNombre = new javax.swing.JTextField();

        txtCuentaBancaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaBancariaActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero de Cheque:");

        jLabel2.setText("Beneficiario:");

        jLabel3.setText("Monto:");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Concepto:");

        jLabel6.setText("Cuenta bancaria:");

        txtBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBeneficiarioActionPerformed(evt);
            }
        });

        try {
            ftxtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tblMostrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMostrarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMostrar);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel7.setText("Buscar por nombre:");

        txtBuscarPorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPorNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBeneficiario)
                            .addComponent(txtConcepto)
                            .addComponent(cmbCuentaBancaria, 0, 346, Short.MAX_VALUE)
                            .addComponent(txtBuscarPorNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnEliminar)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ftxtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnActualizar)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbCuentaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBuscarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBeneficiarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBeneficiarioActionPerformed

    private void txtCuentaBancariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaBancariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaBancariaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
          if (cmbCuentaBancaria.getSelectedIndex() <= 0) {
    JOptionPane.showMessageDialog(
            this,
            "Seleccione una cuenta bancaria.",
            "Validación",
            JOptionPane.WARNING_MESSAGE
    );
    return;
}

try {
    Cheque cheque = new Cheque();

    String itemSeleccionado = cmbCuentaBancaria
            .getSelectedItem()
            .toString();

    int idCuenta = Integer.parseInt(
            itemSeleccionado.split("-")[0].trim()
    );

    cheque.setIdEmpresa(idEmpresa);
    cheque.setIdCuenta(idCuenta);
    cheque.setNumeroCheque(txtNumeroCheque.getText().trim());
    cheque.setBeneficiario(txtBeneficiario.getText().trim().toUpperCase());
    cheque.setMonto(Double.parseDouble(txtMonto.getText().trim()));
    cheque.setConcepto(txtConcepto.getText().trim());

    try {
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("dd/MM/yyyy");

        java.util.Date fechaUtil =
                sdf.parse(ftxtFecha.getText().trim());

        cheque.setFecha(
                new java.sql.Date(fechaUtil.getTime())
        );

    } catch (Exception e) {
        cheque.setFecha(
                new java.sql.Date(System.currentTimeMillis())
        );
    }

    ChequeDAO dao = new ChequeDAO();

    if (dao.registrarCheque(cheque, idEmpresa)) {
        JOptionPane.showMessageDialog(
                this,
                "Cheque registrado y movimiento de salida generado."
        );

        limpiarCampos();
        listar();

    } else {
        JOptionPane.showMessageDialog(
                this,
                "Error al guardar en la base de datos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(
            this,
            "El monto debe ser un número válido.",
            "Error",
            JOptionPane.ERROR_MESSAGE
    );

} catch (Exception e) {
    JOptionPane.showMessageDialog(
            this,
            "Error crítico: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
    );
}
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMostrarMouseClicked
   int fila = tblMostrar.getSelectedRow();

    if (fila == -1) {
        return;
    }

    idSeleccionado = Integer.parseInt(
            tblMostrar.getValueAt(fila, 0).toString()
    );

    String numCheque =
            tblMostrar.getValueAt(fila, 1).toString();

    String beneficiario =
            tblMostrar.getValueAt(fila, 2).toString();

    String monto =
            tblMostrar.getValueAt(fila, 3).toString();

    String fechaSQL =
            tblMostrar.getValueAt(fila, 4).toString();

    String concepto =
            tblMostrar.getValueAt(fila, 5).toString();

    txtNumeroCheque.setText(numCheque);
    txtBeneficiario.setText(beneficiario);
    txtMonto.setText(monto);
    txtConcepto.setText(concepto);

    try {
        java.text.SimpleDateFormat formatoEntrada =
                new java.text.SimpleDateFormat("yyyy-MM-dd");

        java.text.SimpleDateFormat formatoSalida =
                new java.text.SimpleDateFormat("dd/MM/yyyy");

        java.util.Date date =
                formatoEntrada.parse(fechaSQL);

        ftxtFecha.setText(formatoSalida.format(date));

    } catch (Exception e) {
        ftxtFecha.setText("");
    }
    }//GEN-LAST:event_tblMostrarMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
if (idSeleccionado == 0) {

    JOptionPane.showMessageDialog(
            this,
            "Seleccione un cheque de la tabla.",
            "Validación",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

int confirmacion = JOptionPane.showConfirmDialog(
        this,
        "¿Seguro que desea eliminar este cheque?",
        "Confirmación",
        JOptionPane.YES_NO_OPTION
);

if (confirmacion == JOptionPane.YES_OPTION) {

    ChequeDAO dao = new ChequeDAO();

    if (dao.eliminar(idSeleccionado, idEmpresa)) {

        JOptionPane.showMessageDialog(
                this,
                "Cheque eliminado correctamente."
        );

        listar();

        limpiarCampos();

        idSeleccionado = 0;

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se pudo eliminar el cheque.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
if (idSeleccionado == 0) {

    JOptionPane.showMessageDialog(
            this,
            "Seleccione un registro de la tabla primero.",
            "Validación",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

try {

    Cheque cheque = new Cheque();

    cheque.setIdCheque(idSeleccionado);

    cheque.setIdEmpresa(idEmpresa);

    cheque.setNumeroCheque(
            txtNumeroCheque.getText().trim()
    );

    cheque.setBeneficiario(
            txtBeneficiario.getText()
                    .trim()
                    .toUpperCase()
    );

    cheque.setMonto(
            Double.parseDouble(
                    txtMonto.getText().trim()
            )
    );

    cheque.setConcepto(
            txtConcepto.getText().trim()
    );

    try {

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("dd/MM/yyyy");

        java.util.Date fecha =
                sdf.parse(ftxtFecha.getText().trim());

        cheque.setFecha(
                new java.sql.Date(fecha.getTime())
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Formato de fecha incorrecto. Use dd/MM/yyyy.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    ChequeDAO dao = new ChequeDAO();

    if (dao.actualizar(cheque, idEmpresa)) {

        JOptionPane.showMessageDialog(
                this,
                "Registro actualizado correctamente."
        );

        listar();

        limpiarCampos();

        idSeleccionado = 0;

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se pudo actualizar el registro.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
            this,
            "El monto debe ser un número válido.",
            "Error",
            JOptionPane.ERROR_MESSAGE
    );
}
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBuscarPorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPorNombreKeyReleased

    String textoBusqueda =
            txtBuscarPorNombre.getText().trim();

    filtrarTabla(textoBusqueda);
    }//GEN-LAST:event_txtBuscarPorNombreKeyReleased

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
            java.util.logging.Logger.getLogger(RegistroChequeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroChequeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroChequeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroChequeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroChequeFrame(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCuentaBancaria;
    private javax.swing.JFormattedTextField ftxtFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMostrar;
    private javax.swing.JTextField txtBeneficiario;
    private javax.swing.JTextField txtBuscarPorNombre;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtCuentaBancaria;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroCheque;
    // End of variables declaration//GEN-END:variables
}
