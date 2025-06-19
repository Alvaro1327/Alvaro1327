package br.ufpb.projetoLP;

public class NumeroNaoEncontradoException extends Exception{
    public NumeroNaoEncontradoException(String message) {
        super(message);
    }

    public NumeroNaoEncontradoException() {
        super("Número não encontrado");
    }
}