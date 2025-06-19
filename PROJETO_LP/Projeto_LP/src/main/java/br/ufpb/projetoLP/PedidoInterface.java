package br.ufpb.projetoLP;

import java.io.IOException;
import java.util.List;

public interface PedidoInterface {

    //PEDIDOLIST
    void cadastrarPedido(Pedido pedido) throws NumeroJaExisteException;
    boolean cancelarPedido(int numeroPedido) throws NumeroNaoEncontradoException;
    Pedido buscarPedidoPorNumero(int numeroPedido) throws NumeroNaoEncontradoException;
    List<Pedido> pedidosPorTipoRefeicao(String tipoRefecao) throws RefeicaoNaoEncontradaException;
    List<Pedido> getPedidos();
    void salvarDados() throws IOException;
    void recuperarDados() throws IOException;

}
