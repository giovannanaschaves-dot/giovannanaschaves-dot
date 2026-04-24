package veiculos;

public abstract class Veiculo {
    protected String nome;
    protected double velocidade;

    public Veiculo(String nome){
        this.nome = nome;
        this.velocidade = 0;
    }

    public void acelerar(double incremento){
        if(incremento > 0){
            velocidade += incremento;
        }
    }

    public void desacelerar(double drecremento){
        if(drecremento > 0){
            velocidade -= drecremento;
            if(velocidade < 0){
                velocidade = 0;
            }
        }
    }

    // Método abstrato
    public abstract void mover();

    public void mostrarInfo(){
        System.out.println("Nome: "+nome);
        System.out.println("Velocidade: "+velocidade);
    }
}
