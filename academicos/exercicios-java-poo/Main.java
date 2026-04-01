// Giovanna Nascimento Chaves - Atividade avaliativa 16_03
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int numero = scanner.nextInt();

        if (numero <= 0) {
            System.out.println("Número inválido. Por favor, digite um número maior que zero.");
            return;
        }

        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            if(resultado % 2 == 0) {
                System.out.println(numero + "x" + i + "=" + resultado + "(Par)");
            } else {
                System.out.println(numero + "x" + i + "=" + resultado + "(Ímpar)");   
            }    
            }
        }
    }
