package br.ufpb.projetoLP;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GravadorDeFuncionarioEmArquivos {
    private String arquivoDeFuncionarios;

    public GravadorDeFuncionarioEmArquivos(String arquivoDeFuncionarios) {
        this.arquivoDeFuncionarios = arquivoDeFuncionarios;
    }

    public GravadorDeFuncionarioEmArquivos() {
        this("funcionarios.txt");
    }

    public List<Funcionario> recuperarPedidos() throws IOException {
        BufferedReader leitor = null;
        List<Funcionario> funcionariosLidos = new LinkedList<>();
        try {
            leitor = new BufferedReader(new FileReader(this.arquivoDeFuncionarios));
            boolean acabou = false;
            while (!acabou) {
                String linhaLida = leitor.readLine();
                if (linhaLida != null) {
                    String[] dadosPedido = linhaLida.split("###");
                    Funcionario p = new Funcionario(
                            dadosPedido[0],
                            dadosPedido[1],
                            dadosPedido[2],
                            dadosPedido[3]
                            );
                    funcionariosLidos.add(p);
                } else {
                    acabou = true;
                }
            }
            return funcionariosLidos;
        } finally {
            if (leitor != null) {
                leitor.close();
            }
        }
    }
    public void gravarFuncionarios(List<Funcionario> funcionarios) throws IOException {
        BufferedWriter escritor = null;
        try {
            escritor = new BufferedWriter(new FileWriter(this.arquivoDeFuncionarios));
            for (Funcionario p : funcionarios){
                escritor.write(p.getNome() + "###" + p.getCpf() + "###"
                        + p.getTelefone() + "###" + p.getCargo() + "\n");
            }
        } finally {
            if (escritor!=null) {
                escritor.close();
            }
        }
    }

}