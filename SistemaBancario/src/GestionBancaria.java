public class GestionBancaria {

    public static void main(String[] args) {
        // Este código hace que el formulario se vea con el estilo del sistema operativo
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("No se pudo establecer el estilo visual.");
        }

        // Lanza el formulario para que sea visible
        java.awt.EventQueue.invokeLater(() -> {
            // Reemplaza 'FormularioBancario' por el nombre exacto de tu archivo JFrame
            new FormularioBancario().setVisible(true);
        });
    }
}