public class PagamentoPix extends Pagamento {

    // Atributo extra, só do Pix
    private String chavePix;

    // Construtor: recebe o valor + o que é específico do Pix
    public PagamentoPix(double valor, String chavePix) {
        super(valor);            // repassa o valor para a mãe (Pagamento)
        this.chavePix = chavePix;
    }

    // Sobrescrita: o jeito que o Pix processa
    @Override
    public boolean processar() {
        System.out.println("Gerando QR Code para o PIX...");
        return true;
    }
}
