public class PagamentoCartao extends Pagamento {

    // Atributos extras, só do cartão
    private String numeroCartao;
    private String nomeTitular;

    public PagamentoCartao(double valor, String numeroCartao, String nomeTitular) {
        super(valor);                    // de novo: valor vai para a mãe, e é a primeira linha
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
    }

    // Sobrescrita: o cartão tem uma regra de negócio própria
    @Override
    public boolean processar() {
        if (valor > 5000) {
            System.out.println("Transação negada: Limite excedido");
            return false;
        }
        System.out.println("Aprovando cartão no banco...");
        return true;
    }
}