package inventario;

public class Arma extends Item implements IEquipavel {

    //Atributos
    private int dano;

    //Construtores
    Arma(String nome, Raridade raridade, int dano) {
        super(nome, raridade);
        this.dano = dano;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("[%s] - Poder: %d - Raridade --[%s]--", this.getNome(), this.dano, this.getRaridade());
    }

    @Override
    public void equipar(Inventario inv) {
        Item itemEquipado = inv.getArmaEquip();

        if (itemEquipado != null) {
            ((IEquipavel) itemEquipado).desequipar(inv);
        }

        inv.setArmaEquip(this);
        inv.removerItem(this);
        System.out.println("Você equipou: " + getNome());
    }

    @Override
    public void desequipar(Inventario inv) {
        inv.setArmaEquip(null);
        inv.removerItem(this);
        System.out.println("Você guardou " + getNome() + " na mochila");
    }
}
