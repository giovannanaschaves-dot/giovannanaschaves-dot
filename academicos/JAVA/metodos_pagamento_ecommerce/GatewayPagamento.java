public class GatewayPagamento {

    // Recebe a classe MÃE: aceita qualquer tipo de pagamento
    public void realizarCobranca(Pagamento pagamento) {

        boolean aprovado = pagamento.processar(); // o Java decide sozinho qual processar() rodar

        if (aprovado) {
            pagamento.setStatus("APROVADO");
        } else {
            pagamento.setStatus("RECUSADO");
        }

        pagamento.imprimirRecibo();
        System.out.println();
    }
}
