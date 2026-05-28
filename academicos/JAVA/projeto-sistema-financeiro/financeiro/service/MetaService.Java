package financeiro.service;

import financeiro.model.Meta;

import java.util.ArrayList;
import java.util.List;

public class MetaService {

    private List<Meta> metas = new ArrayList<>();

    //CREATE

    public Meta criar(String nome, double valorAlvo, String descricao) {
        Meta m = new Meta(nome, valorAlvo, descricao);
        metas.add(m);
        return m;
    }

    //READ

    public List<Meta> listarTodas() {
        return metas;
    }

    public Meta buscarPorId(int id) {
        for (Meta m : metas) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    //UPDATE

    public boolean atualizar(int id, String nome, double valorAlvo, String descricao) {
        Meta m = buscarPorId(id);
        if (m == null) return false;

        m.setNome(nome);
        m.setValorAlvo(valorAlvo);
        m.setDescricao(descricao);
        return true;
    }

    public boolean depositar(int id, double valor) {
        Meta m = buscarPorId(id);
        if (m == null) return false;

        m.depositar(valor);
        return true;
    }

    //DELETE

    public boolean remover(int id) {
        Meta m = buscarPorId(id);
        if (m == null) return false;

        metas.remove(m);
        return true;
    }
}