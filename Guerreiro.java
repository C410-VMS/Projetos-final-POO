package personagens;

public class Guerreiro extends Personagem {
	
	public Guerreiro(String nome, Atributos atributos) {
		super(nome, atributos);
	}
	
	@Override
	public double calcularPoderCombate() {
		return this.atributos.getForca()*1.8;
	}
}
