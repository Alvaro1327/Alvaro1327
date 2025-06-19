package br.ufpb.projetoLP;

import java.util.List;

public class Funcionario {

    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;

    public static final String TIPO_GARCOM = "Garçom";
    public static final String TIPO_COZINHEIRO = "Cozinheiro";
    public static final String TIPO_GERENTE = "Gerente";

    public Funcionario(String nome, String cpf, String telefone, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String toString(){
        return "Funcionário: " + nome +
                "\nCPF: " + cpf +
                "\nN.º Telefone: " + telefone +
                "\nCargo: " + cargo;
    }

}