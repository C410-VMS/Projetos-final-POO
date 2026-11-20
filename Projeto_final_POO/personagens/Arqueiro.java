package personagens;

import Exceptions.NomeInvalidoException;
import Exceptions.AtributoNuloException;

public class Arqueiro extends Personagem {
	
	public Arqueiro(String nome, Atributos atributos) throws NomeInvalidoException, AtributoNuloException {

		super(nome, atributos);
	}

	@Override
	public double calcularPoderCombate() {
		return this.atributos.getDestreza() *1.5;
	}
}
