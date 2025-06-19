package br.ufpb.projetoLP;

public class RefeicaoNaoEncontradaException extends Exception {
    public RefeicaoNaoEncontradaException(String message) {
        super(message);
    }
    public RefeicaoNaoEncontradaException(){
        super("refeição não encontrada");
    }

}