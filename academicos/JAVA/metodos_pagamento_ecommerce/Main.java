public class Main {
    public static void main(String[] args) {

        GatewayPagamento gateway = new GatewayPagamento();

        // Variáveis do tipo MÃE guardando objetos das FILHAS
        Pagamento pix = new PagamentoPix(150.00, "giovanna@email.com");
        Pagamento cartao = new PagamentoCartao(6000.00, "1234 5678 9012 3456", "Giovanna Chaves");

        // Um de cada vez, pelo mesmo método
        gateway.realizarCobranca(pix);
        gateway.realizarCobranca(cartao);
    }
}
