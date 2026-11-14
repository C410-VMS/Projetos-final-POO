package personagens;

import java.util.HashMap;
import java.util.Map;

public class Guilda {
	private String nome;
	private Map<String, Personagem> personagens;
	
	public Guilda(String nome) {
		this.nome=nome;
		this.personagens = new HashMap<>();
	
	}
	
	public boolean adicionarPersonagens(Personagem p) {
		if (p!= null && p.getNome() != null) {
			personagens.put(p.getNome(), p);
			return true;
		}
		return false;
	}
	
	public Personagem buscarPersonagem(String nome) {
		return personagens.get(nome);
	}
	
	public boolean removerPersonagem(String nome) {
		if(personagens.containsKey(nome)) {
			personagens.remove(nome);
			return true;
		}
	return false;
	}	
	
	public void listarPersonagens() {
		System.out.println("Guilda: " + this.nome);
		if (personagens.isEmpty()) {
			System.out.println("A guilda não possui nenhum personagem");
		}else {
			for (Personagem p : personagens.values()) {
				System.out.println(p);
			}
			
		}
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
}