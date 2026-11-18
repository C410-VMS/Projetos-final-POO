package inventario;

public class Armadura extends Item implements IEquipavel {
    private int defesa;
    private int slot;

    public Armadura(String nome, Raridade raridade, int defesa, int slot) {
        super(nome, raridade);
        this.defesa = defesa;
        this.slot = slot;
    }

    @Override
    public String exibirDetalhes(){
        return String.format("[%s] - Defesa: %d - Raridade --[%s]--", this.getNome(), this.defesa, this.getRaridade());
    }

    @Override
    public void equipar(Inventario inv){
        Item itemEquipado = inv.getItemCorpo(this.slot);

        if (itemEquipado != null){
            ((IEquipavel) itemEquipado).desequipar(inv);
        }

        inv.setItemCorpo(this.slot, this);
        inv.removerItem(this);
        System.out.println("Você equipou: " + getNome());
    }

    @Override
    public void desequipar(Inventario inv){
        inv.setItemCorpo(this.slot, null);
        inv.adcionarItem(this);
        System.out.println("Você guardou " + getNome() + " na mochila");
    }
}
