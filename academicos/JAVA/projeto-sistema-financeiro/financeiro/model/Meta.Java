package financeiro.model;

public class Meta {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private double valorAlvo;
    private double valorAcumulado;
    private String descricao;

    public Meta(String nome, double valorAlvo, String descricao) {
        this.id = contadorId++;
        this.nome = nome;
        this.valorAlvo = valorAlvo;
        this.valorAcumulado = 0.0;
        this.descricao = descricao;
    }

    public int getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValorAlvo() { return valorAlvo; }
    public void setValorAlvo(double valorAlvo) { this.valorAlvo = valorAlvo; }

    public double getValorAcumulado() { return valorAcumulado; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void depositar(double valor) {
        this.valorAcumulado += valor;
    }

    public double getProgresso() {
        return (valorAcumulado / valorAlvo) * 100;
    }

    public boolean atingida() {
        return valorAcumulado >= valorAlvo;
    }

    public double getValorRestante() {
        double restante = valorAlvo - valorAcumulado;
        return restante < 0 ? 0 : restante;
    }

    @Override
    public String toString() {
        String status = atingida() ? "ATINGIDA" : "Em andamento";
        return "[ID: " + id + "] " + nome +
               " | Alvo: R$ " + String.format("%.2f", valorAlvo) +
               " | Acumulado: R$ " + String.format("%.2f", valorAcumulado) +
               " | Progresso: " + String.format("%.1f", getProgresso()) + "%" +
               " | " + status;
    }
}