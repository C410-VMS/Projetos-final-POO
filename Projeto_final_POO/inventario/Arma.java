package inventario;

import Exceptions.AtributoInvalidoException;
import Exceptions.AtributoNuloException;
import Exceptions.CapacidadeInventarioExcedidaException;
import Exceptions.ItemNaoEncontradoException;
import Exceptions.NomeInvalidoException;
import Exceptions.SlotInvalidoException;

public class Arma extends Item implements IEquipavel {

    //Atributos
    private int dano;

    //Construtores
    public Arma(String nome, Raridade raridade, int dano)
            throws NomeInvalidoException, AtributoNuloException, AtributoInvalidoException {
        super(nome, raridade);
        if (dano <= 0) {
            throw new AtributoInvalidoException();
        }
        this.dano = dano;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("[%s] - Poder: %d - Raridade --[%s]--", this.getNome(), this.dano, this.getRaridade());
    }

    @Override
    public void equipar(Inventario inv)
            throws AtributoNuloException, SlotInvalidoException, ItemNaoEncontradoException, CapacidadeInventarioExcedidaException {
        if (inv == null) {
            throw new AtributoNuloException();
        }

        Item itemEquipado = inv.getArmaEquip();

        if (itemEquipado != null) {
            ((IEquipavel) itemEquipado).desequipar(inv);
        }

        inv.setArmaEquip(this);
        inv.removerItem(this);
        System.out.println("Você equipou: " + getNome());
    }

    @Override
    public void desequipar(Inventario inv)
            throws AtributoNuloException, SlotInvalidoException, ItemNaoEncontradoException, CapacidadeInventarioExcedidaException {
        if (inv == null) {
            throw new AtributoNuloException();
        }

        inv.setArmaEquip(null);
        inv.adcionarItem(this);
        System.out.println("Você guardou " + getNome() + " na mochila");
    }
}