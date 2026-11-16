package personagens;

public class Arcano extends Personagem {

	public Arcano(String nome, Atributos atributos) {
		super(nome, atributos);
	}
	
	@Override
	public double calcularPoderCombate(){
		return this.atributos.getInteligencia()*2.5;
	}
}
