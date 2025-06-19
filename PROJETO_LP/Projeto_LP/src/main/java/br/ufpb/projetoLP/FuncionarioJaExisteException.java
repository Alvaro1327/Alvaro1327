package br.ufpb.projetoLP;

public class FuncionarioJaExisteException extends Exception{
    public FuncionarioJaExisteException (String message) {
        super(message);
    }
    public FuncionarioJaExisteException(){
        super("Funcionário já cadastrado");
    }
}
