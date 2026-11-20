package personagens;

import Exceptions.NomeInvalidoException;
import Exceptions.AtributoNuloException;

public class Arcano extends Personagem {

	public Arcano(String nome, Atributos atributos) throws NomeInvalidoException, AtributoNuloException {

		super(nome, atributos);
	}
	
	@Override
	public double calcularPoderCombate(){
		return this.atributos.getInteligencia()*2.5;
	}
}
