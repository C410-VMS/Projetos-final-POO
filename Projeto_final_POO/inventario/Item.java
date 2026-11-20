package inventario;

import Exceptions.AtributoNuloException;
import Exceptions.NomeInvalidoException;

public abstract class Item {

    //Atributos
    private String nome;
    private Raridade raridade;

    //Construtor
    public Item(String nome, Raridade raridade) throws NomeInvalidoException, AtributoNuloException {
        if (nome == null || raridade == null) {
            throw new AtributoNuloException();
        }
        if (nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        this.nome = nome;
        this.raridade = raridade;
    }

    public String getNome() {
        return nome;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public abstract String exibirDetalhes();
}