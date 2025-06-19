package br.ufpb.projetoLP;

public class NumeroJaExisteException extends Exception{
    public NumeroJaExisteException (String message) {
        super(message);
    }
    public NumeroJaExisteException() {
        super("Número já existe");
    }
}
