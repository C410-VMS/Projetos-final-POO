package Exceptions;

public class PersonagemDuplicadoException extends Exception {
    public PersonagemDuplicadoException() {
        super("Personagem duplicado.");
    }
}
