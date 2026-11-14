package personagens;

public abstract class Personagem {
	
	//Atributos 
	
	private String nome;
	protected Atributos atributos;
	
	public Personagem(String nome, Atributos atributos) {
		this.nome=nome;
		this.atributos=atributos;
	}
	
	public abstract double calcularPoderCombate();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public Atributos getAtributos() {
		return atributos;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s - Poder: %.2f",
				this.getClass().getSimpleName(), nome, calcularPoderCombate());
	}
}
