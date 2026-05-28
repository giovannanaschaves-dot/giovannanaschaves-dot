package financeiro.model.tipos;

import financeiro.model.Transacao;
import java.time.LocalDate;

public class Investimento extends Transacao {

    private double rentabilidadeEsperada;

    public Investimento(String descricao, double valor, LocalDate data,
                        String categoria, double rentabilidadeEsperada) {
        super(descricao, valor, data, categoria);
        this.rentabilidadeEsperada = rentabilidadeEsperada;
    }

    public double getRentabilidadeEsperada() { return rentabilidadeEsperada; }
    public void setRentabilidadeEsperada(double rentabilidadeEsperada) {
        this.rentabilidadeEsperada = rentabilidadeEsperada;
    }

    public double calcularRendimento() {
        return getValor() * rentabilidadeEsperada;
    }

    @Override
    public String getTipo() {
        return "INVESTIMENTO";
    }

    @Override
    public double getImpactoNoSaldo() {
        return -getValor();
    }

    @Override
    public String toString() {
        return super.toString() +
               " | Rentab.: " + String.format("%.0f", rentabilidadeEsperada * 100) + "%" +
               " | Rendimento esp.: R$ " + String.format("%.2f", calcularRendimento());
    }
}