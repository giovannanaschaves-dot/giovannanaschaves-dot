public class Main {
    public static void main(String[] args) {

        Produto p = new Produto("Arroz", 10.0);

        // Teste positivo
        p.adicionarEstoque(50);
        p.vender(20);

        // Teste negativo
        p.vender(40); 

        // Casos inválidos
        p.adicionarEstoque(-10);
        p.vender(-5);

        // Casos extremos
        p.adicionarEstoque(0);
        p.vender(0);

        p.exibirResumo();
    }
}
