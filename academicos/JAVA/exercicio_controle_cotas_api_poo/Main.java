public class Main {
    public static void main(String[] args) {

        // Cria uma chave com limite pequeno, para testar rápido
        ChaveApi chave = new ChaveApi("sk-abc123", "Basic", 3);

        System.out.println("=== Chave criada ===");
        System.out.println("Token: " + chave.getToken());
        System.out.println("Plano: " + chave.getPlano());
        System.out.println("Limite: " + chave.getLimiteRequisicoes());
        System.out.println("Realizadas: " + chave.getRequisicoesRealizadas());
        System.out.println("Ativa: " + chave.isAtiva());

        System.out.println("\n=== Teste 1: usar até estourar o limite ===");
        for (int i = 1; i <= 4; i++) {
            try {
                chave.registrarChamada();
                System.out.println("Chamada " + i + " OK. Realizadas: " + chave.getRequisicoesRealizadas());
            } catch (IllegalStateException e) {
                System.out.println("Chamada " + i + " recusada: " + e.getMessage());
            }
        }

        System.out.println("\n=== Teste 2: upgrade com limite menor ===");
        try {
            chave.fazerUpgrade("Pro", 2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n=== Teste 3: upgrade válido ===");
        chave.fazerUpgrade("Pro", 10);
        System.out.println("Plano: " + chave.getPlano()
                + " | Limite: " + chave.getLimiteRequisicoes()
                + " | Realizadas: " + chave.getRequisicoesRealizadas());

        System.out.println("\n=== Teste 4: bloquear a chave ===");
        chave.bloquearChave();
        try {
            chave.registrarChamada();
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n=== Teste 5: desbloquear e resetar o ciclo ===");
        chave.desbloquearChave();
        chave.resetarCiclo();
        System.out.println("Ativa: " + chave.isAtiva() + " | Realizadas: " + chave.getRequisicoesRealizadas());
        chave.registrarChamada();
        System.out.println("Nova chamada OK. Realizadas: " + chave.getRequisicoesRealizadas());
    }
}