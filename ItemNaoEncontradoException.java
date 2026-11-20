package Exceptions;

public class ItemNaoEncontradoException extends Exception {
    public ItemNaoEncontradoException() {

        super("Item não encontrado.");
    }
}
