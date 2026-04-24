package veiculos.terrestre;

public class Carro extends Terrestre{
    protected String tipoCombustivel;

    public Carro(String nome, int numeroRodas,String tipoCombustivel){
        super(nome,numeroRodas);
        this.tipoCombustivel = tipoCombustivel;
    }

    @Override // Método abstrato de Veículo, obrigação de implementar
    public void mover() {
        System.out.println(String.format("O Carro %s está se movendo com a velocidade %.2f km/h",this.nome,this.velocidade));
    }

    @Override
    public void mostrarInfo(){
        super.mostrarInfo();
        System.out.println("Quantidade de Rodas: "+numeroRodas);
        System.out.println("Tipo de Combustível: "+tipoCombustivel);
    }
}
