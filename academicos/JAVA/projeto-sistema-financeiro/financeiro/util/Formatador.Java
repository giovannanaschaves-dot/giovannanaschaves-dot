package financeiro.util;

public class Formatador {

    public static void linha() {
        System.out.println("-----------------------------");
    }

    public static void titulo(String texto) {
        System.out.println("\n-----------------------------");
        System.out.println("  " + texto);
        System.out.println("-----------------------------");
    }

    public static void sucesso(String mensagem) {
        System.out.println("[OK] " + mensagem);
    }

    public static void erro(String mensagem) {
        System.out.println("[ERRO] " + mensagem);
    }

    public static String dinheiro(double valor) {
        return "R$ " + String.format("%.2f", valor);
    }
}