package inventario;

public abstract class Item {

    //Atributos
    private String nome;
    private Raridade raridade;

    //Construtor
    Item(String nome, Raridade raridade) {
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
