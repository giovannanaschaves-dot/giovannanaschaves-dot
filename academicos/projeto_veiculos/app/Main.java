package app;

import veiculos.aereo.JatoGuerra;
import veiculos.terrestre.Carro;
import veiculos.aquatico.Jetski;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args){
        // Não é possível instanciar objeto de classe abstrata
        //Veiculo veiculo_1 = new Veiculo("Fusca");

        //Terrestre terrestre_1 = new Terrestre("Tanque de Guerra", 8);
        Carro carro_1 = new Carro("Opala", 4, "Gasolina");
        carro_1.acelerar(100);
        carro_1.mover();
        carro_1.desacelerar(20);
        carro_1.mover();
        carro_1.mostrarInfo();

        Carro carro_2 = new Carro("BYD Dolphin", 4, "Elétrico");
        carro_2.mostrarInfo();

        JatoGuerra jato_1 = new JatoGuerra("F-16", 20);
        jato_1.acelerar(2100);
        jato_1.ganharAltitude(50000);
        jato_1.mover();
        for(int i=0; i < 10; i++) {
            jato_1.atirarMissel();
        }
        jato_1.perderAltitude(50000);
        jato_1.desacelerar(2100);
        jato_1.mostrarInfo();

        Jetski jetski_1 = new Jetski("Yamaha FX", true, 2);
        jetski_1.acelerar(50);
        jetski_1.mover();
        jetski_1.desacelerar(40);
        jetski_1.mostrarInfo();
    }
}
