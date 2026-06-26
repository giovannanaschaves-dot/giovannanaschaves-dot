package financeiro;

import financeiro.gui.TelaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal();
        });
    }
}
