package personagens;

import Exceptions.NomeInvalidoException;
import Exceptions.PersonagemDuplicadoException;
import Exceptions.PersonagemNaoEncontradoException;
import Exceptions.AtributoNuloException;

import java.util.HashMap;
import java.util.Map;

public class Guilda {
	private String nome;
	private Map<String, Personagem> personagens;

	public Guilda(String nome) throws NomeInvalidoException, AtributoNuloException  {
		if (nome == null){
			throw new AtributoNuloException();
		}
		if (nome.trim().isEmpty()) {
			throw new NomeInvalidoException();
		}
		this.nome = nome;
		this.personagens = new HashMap<>();
	}

	public void adicionarPersonagens(Personagem p) throws PersonagemDuplicadoException {
		if (p == null) {
			throw new IllegalArgumentException("O personagem não pode ser nulo.");
		}
		if (personagens.containsKey(p.getNome())) {
			throw new PersonagemDuplicadoException();
		}
		personagens.put(p.getNome(), p);
	}

	public Personagem buscarPersonagem(String nome) throws PersonagemNaoEncontradoException {
		if (personagens.containsKey(nome)) {
			return personagens.get(nome);
		}
		throw new PersonagemNaoEncontradoException();
	}

	public void removerPersonagem(String nome) throws PersonagemNaoEncontradoException {
		if (personagens.containsKey(nome)) {
			personagens.remove(nome);
		} else {
			throw new PersonagemNaoEncontradoException();
		}
	}

	public void listarPersonagens() {
		System.out.println("Guilda: " + this.nome);
		if (personagens.isEmpty()) {
			System.out.println("A guilda não possui nenhum personagem");
		} else {
			for (Personagem p : personagens.values()) {
				System.out.println(p);
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NomeInvalidoException, AtributoNuloException {
		if (nome == null){
			throw new AtributoNuloException();
		}
		if (nome.trim().isEmpty()) {
			throw new NomeInvalidoException();
		}
		this.nome = nome;
	}
}