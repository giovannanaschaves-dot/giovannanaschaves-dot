package financeiro.model.tipos;

import financeiro.model.Transacao;
import java.time.LocalDate;

public class Despesa extends Transacao {

    public Despesa(String descricao, double valor, LocalDate data, String categoria) {
        super(descricao, valor, data, categoria);
    }

    @Override
    public String getTipo() {
        return "DESPESA";
    }

    @Override
    public double getImpactoNoSaldo() {
        return -getValor();
    }
}