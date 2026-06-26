package financeiro.gui;

import financeiro.model.Transacao;
import financeiro.model.tipos.Receita;
import financeiro.model.tipos.Despesa;
import financeiro.model.tipos.Investimento;
import financeiro.service.TransacaoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TelaTransacao extends JFrame {

    private TransacaoService service;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private JTextField campDescricao, campValor, campCategoria, campRentabilidade, campId;
    private JComboBox<String> comboTipo;

    public TelaTransacao(TransacaoService service) {
        this.service = service;

        setTitle("Gerenciar Transações");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PAINEL DO FORMULÁRIO
        JPanel painelForm = new JPanel(new GridLayout(7, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Nova Transação"));
        painelForm.setPreferredSize(new Dimension(750, 220));

        painelForm.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Receita", "Despesa", "Investimento"});
        painelForm.add(comboTipo);

        painelForm.add(new JLabel("Descrição:"));
        campDescricao = new JTextField();
        painelForm.add(campDescricao);

        painelForm.add(new JLabel("Valor (R$):"));
        campValor = new JTextField();
        painelForm.add(campValor);

        painelForm.add(new JLabel("Categoria:"));
        campCategoria = new JTextField();
        painelForm.add(campCategoria);

        painelForm.add(new JLabel("Rentabilidade (só Investimento):"));
        campRentabilidade = new JTextField();
        painelForm.add(campRentabilidade);

        painelForm.add(new JLabel("ID (para atualizar/remover):"));
        campId = new JTextField();
        painelForm.add(campId);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover");
        JButton btnListar = new JButton("Listar Todos");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnListar);

        // TABELA
        String[] colunas = {"ID", "Tipo", "Descrição", "Valor", "Categoria", "Data"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scroll = new JScrollPane(tabela);

        // MONTAGEM DA TELA
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(painelForm, BorderLayout.CENTER);
        painelTopo.add(painelBotoes, BorderLayout.SOUTH);

        add(painelTopo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // AÇÕES DOS BOTÕES
        btnAdicionar.addActionListener(e -> adicionar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnRemover.addActionListener(e -> remover());
        btnListar.addActionListener(e -> listar());

        listar();
    }

    private void adicionar() {
        try {
            String descricao = campDescricao.getText().trim();
            double valor = Double.parseDouble(campValor.getText().trim());
            String categoria = campCategoria.getText().trim();
            String tipo = (String) comboTipo.getSelectedItem();

            if (descricao.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            if (tipo.equals("Receita")) {
                service.adicionarReceita(descricao, valor, LocalDate.now(), categoria);
            } else if (tipo.equals("Despesa")) {
                service.adicionarDespesa(descricao, valor, LocalDate.now(), categoria);
            } else {
                double rentabilidade = Double.parseDouble(campRentabilidade.getText().trim());
                service.adicionarInvestimento(descricao, valor, LocalDate.now(), categoria, rentabilidade);
            }

            JOptionPane.showMessageDialog(this, "Transação adicionada com sucesso!");
            limparCampos();
            listar();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Use ponto para decimais. Ex: 150.00");
        }
    }

    private void atualizar() {
        try {
            int id = Integer.parseInt(campId.getText().trim());
            String descricao = campDescricao.getText().trim();
            double valor = Double.parseDouble(campValor.getText().trim());
            String categoria = campCategoria.getText().trim();

            boolean ok = service.atualizar(id, descricao, valor, categoria);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Transação atualizada!");
                limparCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "ID não encontrado.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID ou valor inválido!");
        }
    }

    private void remover() {
        try {
            int id = Integer.parseInt(campId.getText().trim());
            boolean ok = service.remover(id);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Transação removida!");
                limparCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "ID não encontrado.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido!");
        }
    }

    private void listar() {
        modeloTabela.setRowCount(0);
        List<Transacao> lista = service.listarTodas();
        for (Transacao t : lista) {
            modeloTabela.addRow(new Object[]{
                t.getId(),
                t.getTipo(),
                t.getDescricao(),
                String.format("R$ %.2f", t.getValor()),
                t.getCategoria(),
                t.getData()
            });
        }
    }

    private void limparCampos() {
        campDescricao.setText("");
        campValor.setText("");
        campCategoria.setText("");
        campRentabilidade.setText("");
        campId.setText("");
    }
}