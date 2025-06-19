package br.ufpb.projetoLP;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GravadorDePedidoEmArquivos {
    private String arquivoDePedidos;

    public GravadorDePedidoEmArquivos(String arquivoDePedidos){
        this.arquivoDePedidos = arquivoDePedidos;
    }
    public GravadorDePedidoEmArquivos(){
        this("pedidos.txt");
    }

    public List<Pedido> recuperarPedidos() throws IOException {
        BufferedReader leitor = null;
        List<Pedido> pedidosLidos = new LinkedList<>();
        try {
            leitor = new BufferedReader(new FileReader(this.arquivoDePedidos));
            boolean acabou = false;
            while (!acabou) {
                String linhaLida = leitor.readLine();
                if (linhaLida != null) {
                    String[] dadosPedido = linhaLida.split("###");
                    Pedido p = new Pedido(
                            Integer.parseInt(dadosPedido[0]),
                            dadosPedido[1],
                            dadosPedido[2],
                            Double.parseDouble(dadosPedido[3]), // correção para double
                            dadosPedido[4]
                    );
                    pedidosLidos.add(p);
                } else {
                    acabou = true;
                }
            }
            return pedidosLidos;
        } finally {
            if (leitor != null) {
                leitor.close();
            }
        }
    }
    public void gravarPedidos(List<Pedido> pedidos) throws IOException {
        BufferedWriter escritor = null;
        try {
            escritor = new BufferedWriter(new FileWriter(this.arquivoDePedidos));
            for (Pedido p : pedidos){
                escritor.write(p.getNumeroPedido() + "###" + p.getNomeRefeicao() + "###"
                        + p.getTipoRefeicao() + "###" + p.getPrecoRefeicao() + "###" + p.getTamanho() + "\n");
            }
        } finally {
            if (escritor!=null) {
                escritor.close();
            }
        }
    }
}
