package inventario;

public class Pocao extends Item implements IConsumivel {
    private int cura;

    public Pocao(String nome, Raridade raridade, int cura) {
        super(nome, raridade);
        this.cura = cura;
    }

    @Override
    public String exibirDetalhes(){
        return String.format("[%s] - Cura: %d", this.getNome(), this.cura);
    }

    @Override
    public void consumir(){
        System.out.println("Você bebeu " + getNome() + " e recuperou " + cura + " PV!");
    }
}
