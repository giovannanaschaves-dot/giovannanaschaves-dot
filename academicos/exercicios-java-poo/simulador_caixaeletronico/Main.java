import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double saldo = 500.0;
        int opcao;

        do {

            System.out.println("\n===MENU===");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Realizar Depósito");
            System.out.println("3 - Realizar Saque");
            System.out.println("4 - Sair");
            System.out.println("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Seu saldo é: R$ " + saldo);
                    break;

                case 2:
                    System.out.print("Digite o valor para depósito: ");
                    double deposito = scanner.nextDouble();

                    if (deposito > 0) {
                        saldo += deposito;
                        System.out.println("Depósito realizado com sucesso!");
                    } else {
                        System.out.println("Valor inválido para depósito.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o valor para saque: ");
                    double saque = scanner.nextDouble();

                    if (saque <= 0) {
                        System.out.println("Valor inválido para saque.");
                    } else if (saque > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= saque;
                        System.out.println("Saque realizado com sucesso!");
                    }
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 4);
    }
}
