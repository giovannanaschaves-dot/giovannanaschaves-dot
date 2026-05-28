package financeiro.util;

public class Validador {

    public static boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean valorValido(double valor) {
        return valor > 0;
    }

    public static double lerDouble(String texto) {
        try {
            return Double.parseDouble(texto.replace(",", ".").trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int lerInt(String texto) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}