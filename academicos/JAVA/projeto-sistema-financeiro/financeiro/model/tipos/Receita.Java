package financeiro.model.tipos;

import financeiro.model.Transacao;
import java.time.LocalDate;

public class Receita extends Transacao {

    public Receita(String descricao, double valor, LocalDate data, String categoria) {
        super(descricao, valor, data, categoria);
    }

    @Override
    public String getTipo() {
        return "RECEITA";
    }

    @Override
    public double getImpactoNoSaldo() {
        return +getValor();
    }
}