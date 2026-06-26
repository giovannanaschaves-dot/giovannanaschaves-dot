package financeiro.gui;

import financeiro.service.TransacaoService;
import financeiro.service.MetaService;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private TransacaoService transacaoService;
    private MetaService metaService;

    public TelaPrincipal() {
        transacaoService = new TransacaoService();
        metaService = new MetaService();

        setTitle("Sistema Financeiro Pessoal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Sistema Financeiro Pessoal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Botões
        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 60, 20, 60));

        JButton btnTransacoes = new JButton("Gerenciar Transações");
        JButton btnMetas = new JButton("Gerenciar Metas");
        JButton btnSair = new JButton("Sair");

        btnTransacoes.addActionListener(e -> {
            new TelaTransacao(transacaoService).setVisible(true);
        });

        btnMetas.addActionListener(e -> {
            new TelaMeta(metaService).setVisible(true);
        });

        btnSair.addActionListener(e -> {
            System.exit(0);
        });

        painelBotoes.add(btnTransacoes);
        painelBotoes.add(btnMetas);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);
    }
}