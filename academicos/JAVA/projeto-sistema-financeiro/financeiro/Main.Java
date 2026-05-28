package financeiro;

import financeiro.service.TransacaoService;
import financeiro.service.MetaService;
import financeiro.model.Transacao;
import financeiro.model.Meta;
import financeiro.model.tipos.Receita;
import financeiro.model.tipos.Despesa;
import financeiro.model.tipos.Investimento;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static TransacaoService transacaoService = new TransacaoService();
    static MetaService metaService = new MetaService();

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=============================");
            System.out.println("   SISTEMA FINANCEIRO PESSOAL");
            System.out.println("=============================");
            System.out.println("1. Gerenciar Transacoes");
            System.out.println("2. Gerenciar Metas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": menuTransacoes(); break;
                case "2": menuMetas(); break;
                case "0": rodando = false; break;
                default: System.out.println("Opcao invalida.");
            }
        }

        System.out.println("Encerrando sistema. Ate logo!");
    }

    //MENU TRANSAÇÕES 

    static void menuTransacoes() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- TRANSACOES ---");
            System.out.println("1. Adicionar Receita");
            System.out.println("2. Adicionar Despesa");
            System.out.println("3. Adicionar Investimento");
            System.out.println("4. Listar Transacoes");
            System.out.println("5. Atualizar Transacao");
            System.out.println("6. Remover Transacao");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": adicionarReceita(); break;
                case "2": adicionarDespesa(); break;
                case "3": adicionarInvestimento(); break;
                case "4": listarTransacoes(); break;
                case "5": atualizarTransacao(); break;
                case "6": removerTransacao(); break;
                case "0": voltar = true; break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    static void adicionarReceita() {
        System.out.println("\n-- Nova Receita --");
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        Receita r = transacaoService.adicionarReceita(descricao, valor, LocalDate.now(), categoria);
        System.out.println("Receita adicionada! ID: " + r.getId());
    }

    static void adicionarDespesa() {
        System.out.println("\n-- Nova Despesa --");
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        Despesa d = transacaoService.adicionarDespesa(descricao, valor, LocalDate.now(), categoria);
        System.out.println("Despesa adicionada! ID: " + d.getId());
    }

    static void adicionarInvestimento() {
        System.out.println("\n-- Novo Investimento --");
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Rentabilidade esperada (ex: 0.12 para 12%): ");
        double rentabilidade = Double.parseDouble(scanner.nextLine());

        Investimento i = transacaoService.adicionarInvestimento(descricao, valor, LocalDate.now(), categoria, rentabilidade);
        System.out.println("Investimento adicionado! ID: " + i.getId());
    }

    static void listarTransacoes() {
        System.out.println("\n-- Listando Transacoes --");
        System.out.println("1. Todas");
        System.out.println("2. So Receitas");
        System.out.println("3. So Despesas");
        System.out.println("4. So Investimentos");
        System.out.print("Filtro: ");

        String opcao = scanner.nextLine();
        List<Transacao> lista;

        switch (opcao) {
            case "2": lista = transacaoService.listarPorTipo("RECEITA"); break;
            case "3": lista = transacaoService.listarPorTipo("DESPESA"); break;
            case "4": lista = transacaoService.listarPorTipo("INVESTIMENTO"); break;
            default:  lista = transacaoService.listarTodas(); break;
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhuma transacao encontrada.");
        } else {
            for (Transacao t : lista) {
                System.out.println(t);
            }
            System.out.println("Saldo atual: R$ " + String.format("%.2f", transacaoService.calcularSaldo()));
        }
    }

    static void atualizarTransacao() {
        System.out.println("\n-- Atualizar Transacao --");
        System.out.print("ID da transacao: ");
        int id = Integer.parseInt(scanner.nextLine());

        Transacao t = transacaoService.buscarPorId(id);
        if (t == null) {
            System.out.println("Transacao nao encontrada.");
            return;
        }

        System.out.println("Transacao atual: " + t);
        System.out.print("Nova descricao: ");
        String descricao = scanner.nextLine();

        System.out.print("Novo valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        System.out.print("Nova categoria: ");
        String categoria = scanner.nextLine();

        transacaoService.atualizar(id, descricao, valor, categoria);
        System.out.println("Atualizado com sucesso!");
    }

    static void removerTransacao() {
        System.out.println("\n-- Remover Transacao --");
        System.out.print("ID da transacao: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean removido = transacaoService.remover(id);
        if (removido) {
            System.out.println("Transacao removida!");
        } else {
            System.out.println("ID nao encontrado.");
        }
    }

    //MENU METAS 

    static void menuMetas() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- METAS ---");
            System.out.println("1. Criar Meta");
            System.out.println("2. Listar Metas");
            System.out.println("3. Depositar em Meta");
            System.out.println("4. Atualizar Meta");
            System.out.println("5. Remover Meta");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": criarMeta(); break;
                case "2": listarMetas(); break;
                case "3": depositarMeta(); break;
                case "4": atualizarMeta(); break;
                case "5": removerMeta(); break;
                case "0": voltar = true; break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    static void criarMeta() {
        System.out.println("\n-- Nova Meta --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Valor alvo: ");
        double valorAlvo = Double.parseDouble(scanner.nextLine());

        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        Meta m = metaService.criar(nome, valorAlvo, descricao);
        System.out.println("Meta criada! ID: " + m.getId());
    }

    static void listarMetas() {
        System.out.println("\n-- Metas --");
        List<Meta> metas = metaService.listarTodas();

        if (metas.isEmpty()) {
            System.out.println("Nenhuma meta cadastrada.");
        } else {
            for (Meta m : metas) {
                System.out.println(m);
            }
        }
    }

    static void depositarMeta() {
        System.out.println("\n-- Depositar em Meta --");
        System.out.print("ID da meta: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Valor a depositar: ");
        double valor = Double.parseDouble(scanner.nextLine());

        boolean ok = metaService.depositar(id, valor);
        if (ok) {
            System.out.println("Deposito realizado!");
            System.out.println(metaService.buscarPorId(id));
        } else {
            System.out.println("Meta nao encontrada.");
        }
    }

    static void atualizarMeta() {
        System.out.println("\n-- Atualizar Meta --");
        System.out.print("ID da meta: ");
        int id = Integer.parseInt(scanner.nextLine());

        Meta m = metaService.buscarPorId(id);
        if (m == null) {
            System.out.println("Meta nao encontrada.");
            return;
        }

        System.out.println("Meta atual: " + m);
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo valor alvo: ");
        double valorAlvo = Double.parseDouble(scanner.nextLine());

        System.out.print("Nova descricao: ");
        String descricao = scanner.nextLine();

        metaService.atualizar(id, nome, valorAlvo, descricao);
        System.out.println("Meta atualizada!");
    }

    static void removerMeta() {
        System.out.println("\n-- Remover Meta --");
        System.out.print("ID da meta: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean ok = metaService.remover(id);
        if (ok) {
            System.out.println("Meta removida!");
        } else {
            System.out.println("ID nao encontrado.");
        }
    }
}
