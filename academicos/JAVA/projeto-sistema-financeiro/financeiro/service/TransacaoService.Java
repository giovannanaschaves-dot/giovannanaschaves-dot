package financeiro.service;

import financeiro.model.Transacao;
import financeiro.model.tipos.Receita;
import financeiro.model.tipos.Despesa;
import financeiro.model.tipos.Investimento;
import financeiro.util.JsonUtil;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoService {

    private static final String ARQUIVO = "dados/transacoes.json";
    private List<Transacao> transacoes;

    public TransacaoService() {
        Type tipo = new TypeToken<List<Transacao>>(){}.getType();
        List<Transacao> carregadas = JsonUtil.carregar(ARQUIVO, tipo);
        this.transacoes = carregadas != null ? carregadas : new ArrayList<>();
    }

    // CREATE

    public Receita adicionarReceita(String descricao, double valor, LocalDate data, String categoria) {
        Receita r = new Receita(descricao, valor, data, categoria);
        transacoes.add(r);
        JsonUtil.salvar(ARQUIVO, transacoes);
        return r;
    }

    public Despesa adicionarDespesa(String descricao, double valor, LocalDate data, String categoria) {
        Despesa d = new Despesa(descricao, valor, data, categoria);
        transacoes.add(d);
        JsonUtil.salvar(ARQUIVO, transacoes);
        return d;
    }

    public Investimento adicionarInvestimento(String descricao, double valor, LocalDate data,
                                              String categoria, double rentabilidade) {
        Investimento i = new Investimento(descricao, valor, data, categoria, rentabilidade);
        transacoes.add(i);
        JsonUtil.salvar(ARQUIVO, transacoes);
        return i;
    }

    // READ

    public List<Transacao> listarTodas() {
        return transacoes;
    }

    public List<Transacao> listarPorTipo(String tipo) {
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao t : transacoes) {
            if (t.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public Transacao buscarPorId(int id) {
        for (Transacao t : transacoes) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // UPDATE

    public boolean atualizar(int id, String descricao, double valor, String categoria) {
        Transacao t = buscarPorId(id);
        if (t == null) return false;

        t.setDescricao(descricao);
        t.setValor(valor);
        t.setCategoria(categoria);
        JsonUtil.salvar(ARQUIVO, transacoes);
        return true;
    }

    // DELETE

    public boolean remover(int id) {
        Transacao t = buscarPorId(id);
        if (t == null) return false;

        transacoes.remove(t);
        JsonUtil.salvar(ARQUIVO, transacoes);
        return true;
    }

    // CÁLCULOS

    public double calcularSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            saldo += t.getImpactoNoSaldo();
        }
        return saldo;
    }
}