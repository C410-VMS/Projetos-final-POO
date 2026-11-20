package inventario;

import Exceptions.AtributoInvalidoException;
import Exceptions.AtributoNuloException;
import Exceptions.CapacidadeInventarioExcedidaException;
import Exceptions.ItemNaoEncontradoException;
import Exceptions.NomeInvalidoException;
import Exceptions.SlotInvalidoException;

public class Armadura extends Item implements IEquipavel {
    private int defesa;
    private int slot;

    public Armadura(String nome, Raridade raridade, int defesa, int slot)
            throws NomeInvalidoException, AtributoNuloException, AtributoInvalidoException, SlotInvalidoException {
        super(nome, raridade);

        if (defesa <= 0) {
            throw new AtributoInvalidoException();
        }
        if (slot < 0 || slot >= 3) {
            throw new SlotInvalidoException();
        }

        this.defesa = defesa;
        this.slot = slot;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("[%s] - Defesa: %d - Raridade --[%s]--", this.getNome(), this.defesa, this.getRaridade());
    }

    @Override
    public void equipar(Inventario inv)
            throws AtributoNuloException, SlotInvalidoException, ItemNaoEncontradoException, CapacidadeInventarioExcedidaException {
        if (inv == null) {
            throw new AtributoNuloException();
        }

        Item itemEquipado = inv.getItemCorpo(this.slot);

        if (itemEquipado != null) {
            ((IEquipavel) itemEquipado).desequipar(inv);
        }

        inv.setItemCorpo(this.slot, this);
        inv.removerItem(this);
        System.out.println("Você equipou: " + getNome());
    }

    @Override
    public void desequipar(Inventario inv)
            throws AtributoNuloException, SlotInvalidoException, CapacidadeInventarioExcedidaException {
        if (inv == null) {
            throw new AtributoNuloException();
        }

        inv.setItemCorpo(this.slot, null);
        inv.adcionarItem(this);
        System.out.println("Você guardou " + getNome() + " na mochila");
    }
}