package financeiro.gui;

import financeiro.model.Meta;
import financeiro.service.MetaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaMeta extends JFrame {

    private MetaService service;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private JTextField campNome, campValorAlvo, campDescricao, campDeposito, campId;

    public TelaMeta(MetaService service) {
        this.service = service;

        setTitle("Gerenciar Metas");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PAINEL DO FORMULÁRIO
        JPanel painelForm = new JPanel(new GridLayout(6, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Nova Meta"));
        painelForm.setPreferredSize(new Dimension(750, 210));

        painelForm.add(new JLabel("Nome:"));
        campNome = new JTextField();
        painelForm.add(campNome);

        painelForm.add(new JLabel("Valor Alvo (R$):"));
        campValorAlvo = new JTextField();
        painelForm.add(campValorAlvo);

        painelForm.add(new JLabel("Descrição:"));
        campDescricao = new JTextField();
        painelForm.add(campDescricao);

        painelForm.add(new JLabel("Valor para Depositar (R$):"));
        campDeposito = new JTextField();
        painelForm.add(campDeposito);

        painelForm.add(new JLabel("ID (para atualizar/remover/depositar):"));
        campId = new JTextField();
        painelForm.add(campId);

        // BOTÕES
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnCriar = new JButton("Criar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover");
        JButton btnDepositar = new JButton("Depositar");
        JButton btnListar = new JButton("Listar Todas");

        painelBotoes.add(btnCriar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnDepositar);
        painelBotoes.add(btnListar);

        // TABELA
        String[] colunas = {"ID", "Nome", "Descrição", "Valor Alvo", "Valor Acumulado", "Atingida"};
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
        btnCriar.addActionListener(e -> criar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnRemover.addActionListener(e -> remover());
        btnDepositar.addActionListener(e -> depositar());
        btnListar.addActionListener(e -> listar());

        listar();
    }

    private void criar() {
        try {
            String nome = campNome.getText().trim();
            double valorAlvo = Double.parseDouble(campValorAlvo.getText().trim());
            String descricao = campDescricao.getText().trim();

            if (nome.isEmpty() || descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            service.criar(nome, valorAlvo, descricao);
            JOptionPane.showMessageDialog(this, "Meta criada com sucesso!");
            limparCampos();
            listar();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Use ponto para decimais. Ex: 1000.00");
        }
    }

    private void atualizar() {
        try {
            int id = Integer.parseInt(campId.getText().trim());
            String nome = campNome.getText().trim();
            double valorAlvo = Double.parseDouble(campValorAlvo.getText().trim());
            String descricao = campDescricao.getText().trim();

            boolean ok = service.atualizar(id, nome, valorAlvo, descricao);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Meta atualizada!");
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
                JOptionPane.showMessageDialog(this, "Meta removida!");
                limparCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "ID não encontrado.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido!");
        }
    }

    private void depositar() {
        try {
            int id = Integer.parseInt(campId.getText().trim());
            double valor = Double.parseDouble(campDeposito.getText().trim());

            boolean ok = service.depositar(id, valor);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Depósito realizado!");
                limparCampos();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "ID não encontrado.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID ou valor inválido!");
        }
    }

    private void listar() {
        modeloTabela.setRowCount(0);
        List<Meta> metas = service.listarTodas();
        for (Meta m : metas) {
            modeloTabela.addRow(new Object[]{
                m.getId(),
                m.getNome(),
                m.getDescricao(),
                String.format("R$ %.2f", m.getValorAlvo()),
                String.format("R$ %.2f", m.getValorAcumulado()),
                m.atingida() ? "Sim ✅" : "Não"
            });
        }
    }

    private void limparCampos() {
        campNome.setText("");
        campValorAlvo.setText("");
        campDescricao.setText("");
        campDeposito.setText("");
        campId.setText("");
    }
}