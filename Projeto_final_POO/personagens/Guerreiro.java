package personagens;

import Exceptions.NomeInvalidoException;
import Exceptions.AtributoNuloException;

public class Guerreiro extends Personagem {
	
	public Guerreiro(String nome, Atributos atributos) throws NomeInvalidoException, AtributoNuloException {

		super(nome, atributos);
	}
	
	@Override
	public double calcularPoderCombate() {
		return this.atributos.getForca()*2.0;
	}
}
