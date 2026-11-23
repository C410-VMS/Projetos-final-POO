package inventario;

import Exceptions.AtributoNuloException;
import Exceptions.CapacidadeInventarioExcedidaException;
import Exceptions.ItemNaoEncontradoException;
import Exceptions.SlotInvalidoException;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> mochila;
    private Item[] corpo;
    private Item armaEquipada;

    public Inventario() {
        mochila = new ArrayList<>();
        this.corpo = new Item[3];
    }

    // Gerenciamento da mochila
    public void adcionarItem(Item item) throws AtributoNuloException, CapacidadeInventarioExcedidaException {
        if (item == null) {
            throw new AtributoNuloException();
        }

        int quantidadeItem = 0;
        for (Item i : mochila) {
            if (i.getNome().equals(item.getNome())) {
                quantidadeItem++;
            }
        }

        if (quantidadeItem >= 10) {
            throw new CapacidadeInventarioExcedidaException();
        } else { 
            mochila.add(item);
            System.out.println(item.getNome() + " adicionado ao inventário");
        }
    }

    public void removerItem(Item item) throws AtributoNuloException, ItemNaoEncontradoException {
        if (item == null) {
            throw new AtributoNuloException();
        }
        if (!mochila.contains(item)) {
            throw new ItemNaoEncontradoException();
        }
        mochila.remove(item);
    }

    // Equipáveis

    // Armadura
    public void setItemCorpo(int slot, Item item) throws SlotInvalidoException {
        if (slot < 0 || slot >= corpo.length) {
            throw new SlotInvalidoException();
        }
        this.corpo[slot] = item;
    }

    public Item getItemCorpo(int slot) throws SlotInvalidoException {
        if (slot < 0 || slot >= corpo.length) {
            throw new SlotInvalidoException();
        }
        return this.corpo[slot];
    }

    // Arma
    public void setArmaEquip(Item arma) {
        this.armaEquipada = arma;
    }

    public Item getArmaEquip() {
        return this.armaEquipada;
    }

    // "Usar" item
    public void usarItem(Item item)
            throws AtributoNuloException, ItemNaoEncontradoException, SlotInvalidoException, CapacidadeInventarioExcedidaException {
        if (item == null) {
            throw new AtributoNuloException();
        }
        if (!mochila.contains(item)) {
            throw new ItemNaoEncontradoException();
        }

        if (item instanceof IConsumivel) {
            ((IConsumivel) item).consumir();
            mochila.remove(item);
        } else if (item instanceof IEquipavel) {
            ((IEquipavel) item).equipar(this);
        }
    }

    public void exibirIventario() {
        System.out.println("\n---- Equipamentos ----");
        System.out.println("Arma: ");
        if (armaEquipada != null) {
            System.out.println(armaEquipada.getNome());
        } else {
            System.out.println("[Vazio]");
        }

        System.out.println("---- Armadura ----");
        String[] slots = {"Cabeça", "Peito", "Pernas"};
        for (int i = 0; i < corpo.length; i++) {
            System.out.println(slots[i] + ": ");
            if (corpo[i] != null) {
                System.out.println(corpo[i].getNome());
            } else {
                System.out.println("[Vazio]");
            }
        }

        System.out.println("---- Mochila ----");
        if (mochila.isEmpty()) {
            System.out.println("[Vazio]");
        } else {
            for (Item item : mochila) {
                System.out.println(item.exibirDetalhes());
            }
        }
        System.out.println("---------x---------\n");
    }

}
