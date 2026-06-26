package financeiro.service;

import financeiro.model.Meta;
import financeiro.util.JsonUtil;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MetaService {

    private static final String ARQUIVO = "dados/metas.json";
    private List<Meta> metas;

    public MetaService() {
        Type tipo = new TypeToken<List<Meta>>(){}.getType();
        List<Meta> carregadas = JsonUtil.carregar(ARQUIVO, tipo);
        this.metas = carregadas != null ? carregadas : new ArrayList<>();
    }

    // CREATE

    public Meta criar(String nome, double valorAlvo, String descricao) {
        Meta m = new Meta(nome, valorAlvo, descricao);
        metas.add(m);
        JsonUtil.salvar(ARQUIVO, metas);
        return m;
    }

    // READ

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

    // UPDATE

    public boolean atualizar(int id, String nome, double valorAlvo, String descricao) {
        Meta m = buscarPorId(id);
        if (m == null) return false;

        m.setNome(nome);
        m.setValorAlvo(valorAlvo);
        m.setDescricao(descricao);
        JsonUtil.salvar(ARQUIVO, metas);
        return true;
    }

    public boolean depositar(int id, double valor) {
        Meta m = buscarPorId(id);
        if (m == null) return false;

        m.depositar(valor);
        JsonUtil.salvar(ARQUIVO, metas);
        return true;
    }

    // DELETE

    public boolean remover(int id) {
        Meta m = buscarPorId(id);
        if (m == null) return false;

        metas.remove(m);
        JsonUtil.salvar(ARQUIVO, metas);
        return true;
    }
}