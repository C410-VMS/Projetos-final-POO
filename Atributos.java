package personagens;

public class Atributos {
	
	private int forca;
	private int inteligencia;
	private int destreza;
	private int vida;
	
	
	public Atributos(int forca, int inteligencia,int destreza, int vida) {
		this.forca=forca;
		this.inteligencia=inteligencia;
		this.destreza=destreza;
		this.vida=vida;
	}
	
	public int getForca() {
		return forca;
	}
	
	public void setForca(int forca) {
		this.forca=forca;
	}
	
	public int getInteligencia() {
		return inteligencia;
	}
	
	public void setInteligencia(int inteligencia) {
		this.inteligencia=inteligencia;
	}
	
	public int getDestreza() {
		return destreza;
	}
	
	public void setDestreza(int destreza) {
		this.destreza=destreza;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida=vida;
	}
	
}
