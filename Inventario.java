package inventario;

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
    public void adcionarItem(Item item) {
        int quantidadeItem = 0;
        for (Item i : mochila) {
            if (i.getNome().equals(item.getNome())) {
                quantidadeItem++;
            }
        }

        if (item != null && quantidadeItem < 10) {
            mochila.add(item);
            System.out.println(item.getNome() + " adicionado ao inventário");
        } else {
            System.out.println("Capacidade excedida");
        }
    }

    public boolean removerItem(Item item) {
        if(item != null && mochila.contains(item)) {
            mochila.remove(item);
            return true;
        }
        return false;
    }

    // Equipáveis

    // Armadura
    public void setItemCorpo(int slot, Item item) {
        this.corpo[slot] = item;
    }
    public Item getItemCorpo(int slot) {
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
    public void usarItem(Item item) {
        if(item != null && mochila.contains(item)) {
            if (item instanceof IConsumivel) {
                ((IConsumivel) item).consumir();
                mochila.remove(item);
            } else if (item instanceof IEquipavel) {
                ((IEquipavel) item).equipar(this);
            }
        } else {
            System.out.println("Item inválido");
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
                item.exibirDetalhes();
            }
        }
        System.out.println("---------x---------\n");
    }
}
