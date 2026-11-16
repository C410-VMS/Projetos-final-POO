package personagens;

public class Arqueiro extends Personagem {
	
	public Arqueiro(String nome, Atributos atributos) {
		super(nome, atributos);
	}

	@Override
	public double calcularPoderCombate() {
		return this.atributos.getForca() *2.0;
	}
}
