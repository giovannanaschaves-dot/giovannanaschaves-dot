package financeiro.model;

import java.time.LocalDate;

public abstract class Transacao {

    private static int contadorId = 1;

    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private String categoria;

    public Transacao(String descricao, double valor, LocalDate data, String categoria) {
        this.id = contadorId++;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public int getId() { return id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public abstract String getTipo();
    public abstract double getImpactoNoSaldo();

    @Override
    public String toString() {
        return "[ID: " + id + "] " + getTipo() + " | " + descricao +
               " | R$ " + String.format("%.2f", valor) +
               " | " + categoria + " | " + data;
    }
}