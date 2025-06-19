package br.ufpb.projetoLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioList implements FuncionarioInterface {
    private List<Funcionario> funcionarios;
    private GravadorDeFuncionarioEmArquivos gravador;

    public FuncionarioList() {
        this.funcionarios = new ArrayList<>();
        this.gravador = new GravadorDeFuncionarioEmArquivos();
    }

    @Override
    public void contratarFuncionario(Funcionario novoFuncionario) {
        if (novoFuncionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo");
        }
        this.funcionarios.add(novoFuncionario);
    }

    @Override
    public boolean despedirFuncionario(String nome, String cpf) {
        if (nome == null || cpf == null) {
            throw new IllegalArgumentException("Nome e CPF não podem ser nulos");
        }
        for (Funcionario funcionario : this.funcionarios) {
            if (funcionario.getNome().equals(nome) && funcionario.getCpf().equals(cpf)) {
                this.funcionarios.remove(funcionario);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Funcionario> getListarFuncionario(){
        return List.copyOf(this.funcionarios);
    }

    public void salvarDados() throws IOException {
        this.gravador.gravarFuncionarios(this.funcionarios);
    }
    public void recuperarDados() {
        try {
            List<Funcionario> funcionariosRecuperados = this.gravador.recuperarPedidos();
            this.funcionarios.clear();
            for (Funcionario funcionario : funcionariosRecuperados) {
                this.contratarFuncionario(funcionario);
            }
        } catch (IOException e) {
            System.err.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}