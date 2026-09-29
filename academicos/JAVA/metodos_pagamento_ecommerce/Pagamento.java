import java.util.UUID;

public abstract class Pagamento {

    // Atributos protegidos: as classes filhas conseguem acessar
    protected String idTransacao;
    protected double valor;
    protected String status;

    // Construtor: recebe só o valor
    public Pagamento(double valor) {
        this.idTransacao = "TX-" + UUID.randomUUID(); // ID gerado automaticamente
        this.valor = valor;
        this.status = "PENDENTE";                     // status inicial
    }

    // Método concreto: todas as filhas reaproveitam
    public void imprimirRecibo() {
        System.out.println("========== RECIBO ==========");
        System.out.println("ID da transação: " + idTransacao);
        System.out.printf("Valor: R$ %.2f%n", valor);
        System.out.println("Status: " + status);
        System.out.println("============================");
    }

    // O contrato: método abstrato, SEM corpo. Cada filha é obrigada a implementar.
    public abstract boolean processar();

    // Setter para o Gateway conseguir mudar o status
    public void setStatus(String status) {
        this.status = status;
    }
}