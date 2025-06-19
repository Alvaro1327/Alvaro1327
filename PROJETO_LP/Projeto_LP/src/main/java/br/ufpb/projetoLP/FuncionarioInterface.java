package br.ufpb.projetoLP;

import java.io.IOException;
import java.util.List;

public interface FuncionarioInterface {
    void contratarFuncionario(Funcionario funcionario) throws FuncionarioJaExisteException;
    boolean despedirFuncionario(String nome, String cpf) throws FuncionarioNaoExisteException;
    List<Funcionario> getListarFuncionario();
    void salvarDados() throws IOException;
    void recuperarDados() throws IOException;
}