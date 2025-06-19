package br.ufpb.projetoLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PedidoList implements PedidoInterface {
    private List<Pedido> pedidos;
    private GravadorDePedidoEmArquivos gravador;


    public PedidoList(){
        this.pedidos = new ArrayList<>();
        this.gravador = new GravadorDePedidoEmArquivos();
    }

    @Override
    public void cadastrarPedido(Pedido pedido){
        if(!this.pedidos.contains(pedido)){
            this.pedidos.add(pedido);
        }
    }

    public boolean cancelarPedido(int numeroPedido) throws NumeroNaoEncontradoException{
        for(Pedido c : this.pedidos){
            if(c.getNumeroPedido() == numeroPedido){
                this.pedidos.remove(c);
                return true;
            }
        }
        throw new NumeroNaoEncontradoException("Número do pedido não encontrado.");
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) throws NumeroNaoEncontradoException{
        for(Pedido c : this.pedidos){
            if(c.getNumeroPedido() == numeroPedido){
                return c;
            }
        }
        throw new NumeroNaoEncontradoException("Número do pedido não encontrado.");
    }

    public List<Pedido> pedidosPorTipoRefeicao(String tipoRefecao) throws RefeicaoNaoEncontradaException{
        List<Pedido> pedidosPorTipo = new ArrayList<>();
        for(Pedido c : this.pedidos){
            if(c.getTipoRefeicao().equalsIgnoreCase(tipoRefecao)){
                pedidosPorTipo.add(c);
            }
        }
        if(pedidosPorTipo.isEmpty()){
            throw new RefeicaoNaoEncontradaException("Tipo de refeição não encontrado.");
        }
        return pedidosPorTipo;
    }

    public List<Pedido> getPedidos(){
        return List.copyOf(this.pedidos);
    }

    public void salvarDados() throws IOException {
        this.gravador.gravarPedidos(this.pedidos);
    }
    public void recuperarDados() {
        try {
            List<Pedido> pedidosRecuperados = this.gravador.recuperarPedidos();
            this.pedidos.clear();
            for (Pedido pedido : pedidosRecuperados) {
                this.cadastrarPedido(pedido);
            }
        } catch (IOException e) {
            System.err.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

}
