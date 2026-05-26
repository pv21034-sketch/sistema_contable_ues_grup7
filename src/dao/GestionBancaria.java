package dao;

import vistas.FormularioBancario;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GestionBancaria {

    public static void abrirFormularioBancario(int idEmpresa) {

        aplicarEstiloVisual();

        java.awt.EventQueue.invokeLater(() -> {

            FormularioBancario formulario =
                    new FormularioBancario(idEmpresa);

            formulario.setVisible(true);
        });
    }

    private static void aplicarEstiloVisual() {

        try {

            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo aplicar el estilo visual: "
                    + e.getMessage(),
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}