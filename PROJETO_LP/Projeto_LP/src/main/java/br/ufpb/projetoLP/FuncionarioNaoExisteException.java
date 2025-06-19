package br.ufpb.projetoLP;

public class FuncionarioNaoExisteException extends Exception{
    public FuncionarioNaoExisteException (String message) {
        super(message);
    }
    public FuncionarioNaoExisteException(){
        super("Funcionário não existe");
    }
}
