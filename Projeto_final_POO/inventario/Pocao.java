package inventario;

import Exceptions.AtributoInvalidoException;
import Exceptions.AtributoNuloException;
import Exceptions.NomeInvalidoException;

public class Pocao extends Item implements IConsumivel {
    private int cura;

    public Pocao(String nome, Raridade raridade, int cura)
            throws NomeInvalidoException, AtributoNuloException, AtributoInvalidoException {
        super(nome, raridade);
        if (cura <= 0) {
            throw new AtributoInvalidoException();
        }
        this.cura = cura;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("[%s] - Cura: %d", this.getNome(), this.cura);
    }

    @Override
    public void consumir() {
        System.out.println("Você bebeu " + getNome() + " e recuperou " + cura + " PV!");
    }
}