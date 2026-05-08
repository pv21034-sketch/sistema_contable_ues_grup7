/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import conexion.Conexion;
import dao.TransferenciaDAO;
import modelo.Transferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ing-jos-flores
 */
public class frmTrasferencias extends javax.swing.JFrame {

    private TransferenciaDAO transferenciaDAO =
            new TransferenciaDAO();

    private int idEmpresaActual = 1;

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(
                    frmTrasferencias.class.getName()
            );

    /**
     * Creates new form frmTrasferencias
     */
    public frmTrasferencias() {

        initComponents();

        setLocationRelativeTo(null);

        mostrarSaldos();

        // Bloqueando campos del Emisor
        txtNuCuentaEmisor.setEditable(false);
        txtNombresEmisor.setEditable(false);
        txtApellidosEmisor.setEditable(false);
        txtSaldoEmisor.setEditable(false);

        // Fecha automática
        ftxtFechaEnviar.setEditable(false);
        ftxtFechaEnviar.setEnabled(false);

        establecerFechaActual();

        // Bloqueando campos del Receptor
        txtIdRecibe.setEditable(false);
        txtNombresReceptor.setEditable(false);
        txtApellidosReceptor.setEditable(false);
        txtSaldoReceptor.setEditable(false);
    }

    public void limpiarFormulario() {

        // Campos del Emisor
        txtIdEnviar.setText("");
        txtNuCuentaEmisor.setText("");
        txtNombresEmisor.setText("");
        txtApellidosEmisor.setText("");
        txtSaldoEmisor.setText("");

        // Campos del Receptor
        txtIdRecibe.setText("");
        txtNuCuentaReceptor.setText("");
        txtNombresReceptor.setText("");
        txtApellidosReceptor.setText("");
        txtSaldoReceptor.setText("");

        // Campos de transferencia
        txtMontoEnviar.setText("");
        txtConceptoEnviar.setText("");

        // Fecha actual nuevamente
        establecerFechaActual();

        // Cursor al inicio
        txtIdEnviar.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMontoEnviar = new javax.swing.JTextField();
        lblMonto = new javax.swing.JLabel();
        txtConceptoEnviar = new javax.swing.JTextField();
        lblConcepto = new javax.swing.JLabel();
        btnTransferir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblCuentas = new javax.swing.JTable();
        lblBuscarIdEnviar = new javax.swing.JLabel();
        txtIdEnviar = new javax.swing.JTextField();
        btnBuscarId = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNuCuentaEmisor = new javax.swing.JLabel();
        txtNuCuentaEmisor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblNuCuentaReceptor = new javax.swing.JLabel();
        txtNuCuentaReceptor = new javax.swing.JTextField();
        btnBuscarCuenta = new javax.swing.JButton();
        lblIdReceptor = new javax.swing.JLabel();
        txtIdRecibe = new javax.swing.JTextField();
        lblNombresReceptor = new javax.swing.JLabel();
        txtNombresReceptor = new javax.swing.JTextField();
        lblApellidosReceptor = new javax.swing.JLabel();
        txtApellidosReceptor = new javax.swing.JTextField();
        lblSaldoReceptor = new javax.swing.JLabel();
        txtSaldoReceptor = new javax.swing.JTextField();
        lblSaldoEmisor = new javax.swing.JLabel();
        txtSaldoEmisor = new javax.swing.JTextField();
        lblNombresEmisor = new javax.swing.JLabel();
        txtNombresEmisor = new javax.swing.JTextField();
        lblApellidosEmisor = new javax.swing.JLabel();
        txtApellidosEmisor = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        lblFechaEnviar = new javax.swing.JLabel();
        ftxtFechaEnviar = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMontoEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoEnviarKeyTyped(evt);
            }
        });

        lblMonto.setText("Monto:");

        lblConcepto.setText("Concepto:");

        btnTransferir.setText("Transferir");
        btnTransferir.addActionListener(this::btnTransferirActionPerformed);

        jtblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblCuentas);

        jScrollPane2.setViewportView(jScrollPane1);

        lblBuscarIdEnviar.setText("Id del Emisor:");

        btnBuscarId.setText("Buscar ID");
        btnBuscarId.addActionListener(this::btnBuscarIdActionPerformed);

        jLabel1.setText("DATOS DEL EMISOR DE LA TRANSFERENCIA");

        lblNuCuentaEmisor.setText("N° de cuenta:");

        jLabel2.setText("DATOS DEL RECEPTOR DE LA TRANSFERENCIA");

        lblNuCuentaReceptor.setText("N° de cuenta:");

        btnBuscarCuenta.setText("Buscar N° de cuenta");
        btnBuscarCuenta.addActionListener(this::btnBuscarCuentaActionPerformed);

        lblIdReceptor.setText("Id del Receptor:");

        lblNombresReceptor.setText("Nombres:");

        lblApellidosReceptor.setText("Apellidos:");

        lblSaldoReceptor.setText("Saldo:");

        lblSaldoEmisor.setText("saldo:");

        lblNombresEmisor.setText("Nombres:");

        lblApellidosEmisor.setText("Apellidos:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        lblFechaEnviar.setText("Fecha:");

        try {
            ftxtFechaEnviar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblBuscarIdEnviar)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscarId))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNuCuentaEmisor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNuCuentaEmisor))
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblApellidosEmisor)
                                            .addComponent(lblFechaEnviar))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtApellidosEmisor)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ftxtFechaEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 244, Short.MAX_VALUE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombresEmisor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombresEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblSaldoReceptor)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSaldoReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNuCuentaReceptor)
                                            .addComponent(lblIdReceptor)
                                            .addComponent(lblNombresReceptor))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombresReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtIdRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtNuCuentaReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(23, 23, 23)
                                                        .addComponent(btnBuscarCuenta))))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblApellidosReceptor)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtApellidosReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblConcepto)
                                            .addComponent(lblMonto))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMontoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtConceptoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSaldoEmisor)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSaldoEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(197, 197, 197)
                                .addComponent(btnTransferir)
                                .addGap(35, 35, 35)
                                .addComponent(btnCancelar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNuCuentaReceptor)
                            .addComponent(txtNuCuentaReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCuenta))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdReceptor)
                            .addComponent(txtIdRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombresReceptor)
                            .addComponent(txtNombresReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellidosReceptor)
                            .addComponent(txtApellidosReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSaldoReceptor)
                            .addComponent(txtSaldoReceptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTransferir)
                            .addComponent(btnCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscarIdEnviar)
                            .addComponent(txtIdEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarId))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNuCuentaEmisor)
                            .addComponent(txtNuCuentaEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombresEmisor)
                            .addComponent(txtNombresEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApellidosEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellidosEmisor))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaEnviar)
                            .addComponent(ftxtFechaEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMonto)
                            .addComponent(txtMontoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtConceptoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblConcepto))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSaldoEmisor)
                            .addComponent(txtSaldoEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   public void mostrarSaldos() {

    DefaultTableModel modeloTabla =
            transferenciaDAO.cargarModeloTabla(
                    idEmpresaActual
            );

    jtblCuentas.setModel(modeloTabla);
}

public void actualizarTabla() {

    DefaultTableModel modeloTabla =
            transferenciaDAO.cargarModeloTabla(
                    idEmpresaActual
            );

    jtblCuentas.setModel(modeloTabla);
}
    private void btnTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferirActionPerformed
    // Validar campos vacíos
    if (txtIdEnviar.getText().trim().isEmpty()
            || txtIdRecibe.getText().trim().isEmpty()
            || txtMontoEnviar.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Debe buscar y seleccionar ambas cuentas antes de transferir.",
                "Validación",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    try {

        int idOrigen =
                Integer.parseInt(
                        txtIdEnviar.getText().trim()
                );

        int idDestino =
                Integer.parseInt(
                        txtIdRecibe.getText().trim()
                );

        double montoATransferir =
                Double.parseDouble(
                        txtMontoEnviar.getText().trim()
                );

        // Validar transferencia a la misma cuenta
        if (idOrigen == idDestino) {

            JOptionPane.showMessageDialog(
                    this,
                    "No puede transferirse a la misma cuenta.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Validar monto positivo
        if (montoATransferir <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "El monto debe ser mayor a cero.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Obtener saldo disponible
        double saldoDisponible =
                Double.parseDouble(
                        txtSaldoEmisor.getText().trim()
                );

        // Validar fondos
        if (montoATransferir > saldoDisponible) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fondos insuficientes.\n\n" +
                    "Saldo disponible: $" + saldoDisponible +
                    "\nMonto solicitado: $" + montoATransferir,
                    "Fondos insuficientes",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Confirmación
        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Confirmar transferencia de $" +
                        montoATransferir + "?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );

        if (respuesta == JOptionPane.YES_OPTION) {

            Transferencia t =
                    new Transferencia();

            t.setIdEmpresa(1);

            t.setIdCuentaOrigen(idOrigen);

            t.setIdCuentaDestino(idDestino);

            t.setMonto(montoATransferir);

            t.setConcepto(
                    txtConceptoEnviar.getText()
                            .trim()
                            .toUpperCase()
            );

            t.setFecha(
                    new java.text.SimpleDateFormat(
                            "yyyy-MM-dd"
                    ).format(
                            new java.util.Date()
                    )
            );

            boolean exito =
                    transferenciaDAO
                            .ejecutarTransferencia(t);

            if (exito) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transferencia realizada correctamente."
                );

                limpiarFormulario();

                actualizarTabla();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo realizar la transferencia.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Ingrese valores numéricos válidos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnTransferirActionPerformed

    private void btnBuscarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIdActionPerformed
if (txtIdEnviar.getText().trim().isEmpty()) {

    JOptionPane.showMessageDialog(
            this,
            "Ingrese el ID del emisor.",
            "Validación",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

try {

    int id =
            Integer.parseInt(
                    txtIdEnviar.getText().trim()
            );

    String[] datos =
            transferenciaDAO.buscarPorId(id);

    if (datos != null) {

        txtNuCuentaEmisor.setText(datos[0]);

        txtNombresEmisor.setText(datos[1]);

        txtApellidosEmisor.setText(datos[2]);

        txtSaldoEmisor.setText(datos[3]);

    } else {

        JOptionPane.showMessageDialog(
                this,
                "ID de emisor no encontrado.",
                "Búsqueda",
                JOptionPane.WARNING_MESSAGE
        );
    }

    establecerFechaActual();

    ftxtFechaEnviar.setEditable(false);

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
            this,
            "El ID del emisor debe ser numérico.",
            "Error",
            JOptionPane.ERROR_MESSAGE
    );
}
    }//GEN-LAST:event_btnBuscarIdActionPerformed

    private void btnBuscarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCuentaActionPerformed
String cuenta =
        txtNuCuentaReceptor
                .getText()
                .trim();

if (cuenta.isEmpty()) {

    JOptionPane.showMessageDialog(
            this,
            "Ingrese el número de cuenta del receptor.",
            "Validación",
            JOptionPane.WARNING_MESSAGE
    );

    return;
}

String[] datos =
        transferenciaDAO
                .buscarPorNumeroCuenta(cuenta);

if (datos != null) {

    txtIdRecibe.setText(datos[0]);

    txtNombresReceptor.setText(datos[1]);

    txtApellidosReceptor.setText(datos[2]);

    txtSaldoReceptor.setText(datos[3]);

} else {

    JOptionPane.showMessageDialog(
            this,
            "El número de cuenta no existe.",
            "Búsqueda",
            JOptionPane.WARNING_MESSAGE
    );

    txtIdRecibe.setText("");
    txtNombresReceptor.setText("");
    txtApellidosReceptor.setText("");
    txtSaldoReceptor.setText("");
}
    }//GEN-LAST:event_btnBuscarCuentaActionPerformed

    private void txtMontoEnviarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoEnviarKeyTyped
char c = evt.getKeyChar();

// Permitir números
boolean esNumero =
        Character.isDigit(c);

// Permitir punto decimal
boolean esPunto =
        c == '.';

// Permitir borrar
boolean esBorrar =
        c == java.awt.event.KeyEvent.VK_BACK_SPACE
        || c == java.awt.event.KeyEvent.VK_DELETE;

// Bloquear cualquier otro carácter
if (!esNumero && !esPunto && !esBorrar) {

    evt.consume();
}

// Evitar más de un punto decimal
if (esPunto
        && txtMontoEnviar
                .getText()
                .contains(".")) {

    evt.consume();
}
    }//GEN-LAST:event_txtMontoEnviarKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

public void establecerFechaActual() {

    SimpleDateFormat formatoFecha =
            new SimpleDateFormat("yyyy-MM-dd");

    String fechaActual =
            formatoFecha.format(new Date());

    ftxtFechaEnviar.setText(fechaActual);

    ftxtFechaEnviar.setValue(fechaActual);
}
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmTrasferencias().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCuenta;
    private javax.swing.JButton btnBuscarId;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnTransferir;
    private javax.swing.JFormattedTextField ftxtFechaEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtblCuentas;
    private javax.swing.JLabel lblApellidosEmisor;
    private javax.swing.JLabel lblApellidosReceptor;
    private javax.swing.JLabel lblBuscarIdEnviar;
    private javax.swing.JLabel lblConcepto;
    private javax.swing.JLabel lblFechaEnviar;
    private javax.swing.JLabel lblIdReceptor;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNombresEmisor;
    private javax.swing.JLabel lblNombresReceptor;
    private javax.swing.JLabel lblNuCuentaEmisor;
    private javax.swing.JLabel lblNuCuentaReceptor;
    private javax.swing.JLabel lblSaldoEmisor;
    private javax.swing.JLabel lblSaldoReceptor;
    private javax.swing.JTextField txtApellidosEmisor;
    private javax.swing.JTextField txtApellidosReceptor;
    private javax.swing.JTextField txtConceptoEnviar;
    private javax.swing.JTextField txtIdEnviar;
    private javax.swing.JTextField txtIdRecibe;
    private javax.swing.JTextField txtMontoEnviar;
    private javax.swing.JTextField txtNombresEmisor;
    private javax.swing.JTextField txtNombresReceptor;
    private javax.swing.JTextField txtNuCuentaEmisor;
    private javax.swing.JTextField txtNuCuentaReceptor;
    private javax.swing.JTextField txtSaldoEmisor;
    private javax.swing.JTextField txtSaldoReceptor;
    // End of variables declaration//GEN-END:variables
}
