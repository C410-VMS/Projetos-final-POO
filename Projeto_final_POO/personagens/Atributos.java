package personagens;

import Exceptions.AtributoInvalidoException;

public class Atributos {
	
	private int forca;
	private int inteligencia;
	private int destreza;
	private int vida;
	
	
	public Atributos(int forca, int inteligencia,int destreza, int vida) throws AtributoInvalidoException {
		if((forca <= 0) || (inteligencia <= 0) || (destreza <= 0) || (vida <= 0)){
			throw new AtributoInvalidoException();
		}
		this.forca=forca;
		this.inteligencia=inteligencia;
		this.destreza=destreza;
		this.vida=vida;
	}
	
	public int getForca() {
		return forca;
	}
	
	public void setForca(int forca) throws AtributoInvalidoException {
		if(forca <= 0){
			throw new AtributoInvalidoException();
		}
		else{
			this.forca=forca;
		}

	}
	
	public int getInteligencia() {
		return inteligencia;
	}
	
	public void setInteligencia(int inteligencia) throws AtributoInvalidoException {
		if(inteligencia <= 0){
			throw new AtributoInvalidoException();
		}
		else{
			this.inteligencia=inteligencia;
		}
}
	
	public int getDestreza() {
		return destreza;
	}
	
	public void setDestreza(int destreza) throws AtributoInvalidoException {
		if(destreza <= 0){
			throw new AtributoInvalidoException();
		}
		else{
			this.destreza=destreza;
		}
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) throws AtributoInvalidoException {
		if(vida <= 0){
			throw new AtributoInvalidoException();
		}
		else{
			this.vida=vida;
		}
	}
	
}
