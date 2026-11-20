package Exceptions;

public class PersonagemNaoEncontradoException extends Exception {
    public PersonagemNaoEncontradoException() {

        super("Personagem não encontrado.");
    }
}
