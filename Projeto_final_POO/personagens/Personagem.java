package personagens;

import Exceptions.AtributoInvalidoException;
import Exceptions.AtributoNuloException;
import Exceptions.NomeInvalidoException;

public abstract class Personagem {

	private String nome;
	protected Atributos atributos;

	public Personagem(String nome, Atributos atributos) throws NomeInvalidoException, AtributoNuloException {
		if (nome == null || atributos == null) {
			throw new AtributoNuloException();
		}
		if (nome.trim().isEmpty()) {
			throw new NomeInvalidoException();
		}

		this.nome = nome;
		this.atributos = atributos;
	}

	public abstract double calcularPoderCombate();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NomeInvalidoException, AtributoNuloException {
		if (nome == null) {
			throw new AtributoNuloException();
		}
		if (nome.trim().isEmpty()) {
			throw new NomeInvalidoException();
		}
		this.nome = nome;
	}

	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(int forca, int inteligencia, int destreza, int vida) throws AtributoInvalidoException {
		this.atributos = new Atributos(forca, inteligencia, destreza, vida);
	}

	@Override
	public String toString() {
		return String.format("[%s] %s - Poder: %.2f",
				this.getClass().getSimpleName(), nome, calcularPoderCombate());
	}
}