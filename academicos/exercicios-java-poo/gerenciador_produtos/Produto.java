public class Produto {

    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

// Construtor
    public Produto(String nome, double preco) {
    this.nome = nome;
    this.preco = preco;
    this.quantidadeEmEstoque = 0;
}

// Getter e Setter 
public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public double getPreco() {
    return preco;
}

public void setPreco(double preco) {
    this.preco = preco;
}

public int getQuantidadeEmEstoque() {
    return quantidadeEmEstoque;
}

// Comportamentos
public void adicionarEstoque(int quantidade) {
    if (quantidade > 0) {
        quantidadeEmEstoque += quantidade;
    }
}

public void vender(int quantidade) {
    if (quantidade > 0 && quantidade <= quantidadeEmEstoque) {
        quantidadeEmEstoque -= quantidade;
    } else {
        System.out.println("Estoque insuficiente para a venda.");
    }
}

public void exibirResumo() {
    System.out.println("Nome: " + nome);
    System.out.println("Preço: " + preco);
    System.out.println("Estoque: " + quantidadeEmEstoque);
}
}
